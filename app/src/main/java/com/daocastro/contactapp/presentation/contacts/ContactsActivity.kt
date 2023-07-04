package com.daocastro.contactapp.presentation.contacts

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.daocastro.contactapp.data.entities.contacts.Contact
import com.daocastro.contactapp.data.entities.contacts.Contacts
import com.daocastro.contactapp.databinding.ActivityContactsBinding
import com.daocastro.contactapp.presentation.State
import com.daocastro.contactapp.presentation.contacts.adapters.ContactAdapter
import com.daocastro.contactapp.presentation.contacts.contact_detail.ContactDetailActivity
import com.daocastro.contactapp.presentation.contacts.contact_detail.ContactDetailActivity.Companion.EXTRA_CONTACT
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ContactsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityContactsBinding
    private val viewModel: ContactsActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        getContacts()
    }

    private fun getContacts()
    {
        lifecycleScope.launch {
            viewModel.getContactResponse()
                .collect {
                    when (it) {
                        is State.DataState -> {
                            val contacts = fromJson("{contacts:${it.data}}")
                            val adapter = ContactAdapter(contacts.contacts, this@ContactsActivity)
                            val mLayoutManager: RecyclerView.LayoutManager =
                                LinearLayoutManager(this@ContactsActivity, LinearLayoutManager.VERTICAL, false)
                            binding.rvcontacts.setLayoutManager(mLayoutManager)
                            binding.rvcontacts.setHasFixedSize(true)
                            binding.rvcontacts.setAdapter(adapter)
                        }
                        is State.ErrorState -> {

                        }
                        is State.LoadingState -> {

                        }
                    }

                }
        }
    }

    private fun fromJson(json:String):Contacts{
        return Gson().fromJson<Contacts>(json, Contacts::class.java)
    }

    fun goToDetail(contact:Contact)
    {
        startActivity(Intent(this@ContactsActivity, ContactDetailActivity::class.java).apply {
            this.putExtra(EXTRA_CONTACT,contact)
        })
    }
}