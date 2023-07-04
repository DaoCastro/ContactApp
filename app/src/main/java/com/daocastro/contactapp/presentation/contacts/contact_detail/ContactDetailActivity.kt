package com.daocastro.contactapp.presentation.contacts.contact_detail

import android.Manifest.permission.CALL_PHONE
import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.avatarfirst.avatargenlib.AvatarConstants
import com.avatarfirst.avatargenlib.AvatarGenerator
import com.daocastro.contactapp.data.entities.contacts.Contact
import com.daocastro.contactapp.data.entities.email.Email
import com.daocastro.contactapp.data.entities.phone.Phone
import com.daocastro.contactapp.databinding.ActivityContactDetailBinding
import com.daocastro.contactapp.presentation.contacts.adapters.ContactItemAdapter


class ContactDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityContactDetailBinding
    private var contact: Contact? = null

    companion object{
        const val EXTRA_CONTACT = "Contact"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getExtra()
    }

    private fun getExtra()
    {
        var _bundle=intent.extras
        if(_bundle!=null){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                contact = _bundle.getSerializable(EXTRA_CONTACT, Contact::class.java)
            }
            else {
                contact = _bundle.get(EXTRA_CONTACT) as Contact
            }
            init()
        }
    }

    private fun init()
    {
        binding.tvnombre.text = "${contact?.firstName} ${contact?.lastName}"
        binding.tvtipo.text="${contact?.title}"
        binding.ivcontact.setImageDrawable(
            this.let {
                AvatarGenerator.avatarImage(
                    it,
                    200,
                    AvatarConstants.CIRCLE,
                    "${contact?.firstName} ${contact?.lastName}"
                )
            }
        )
        initRecicleViewEmail()
        initRecicleViewPhone()

    }

    private fun initRecicleViewEmail()
    {   contact?.let { contact->
            val adapter = ContactItemAdapter<Email>(contact.emails, this@ContactDetailActivity, 0)
            val mLayoutManager: RecyclerView.LayoutManager =
                LinearLayoutManager(this@ContactDetailActivity, LinearLayoutManager.VERTICAL, false)
            binding.rvemail.setLayoutManager(mLayoutManager)
            binding.rvemail.setHasFixedSize(true)
            binding.rvemail.setAdapter(adapter)
        }
    }

    private fun initRecicleViewPhone()
    {   contact?.let { contact->
            val adapter = ContactItemAdapter<Phone>(contact.phones, this@ContactDetailActivity, 1)
            val mLayoutManager: RecyclerView.LayoutManager =
                LinearLayoutManager(this@ContactDetailActivity, LinearLayoutManager.VERTICAL, false)
            binding.rvphone.setLayoutManager(mLayoutManager)
            binding.rvphone.setHasFixedSize(true)
            binding.rvphone.setAdapter(adapter)
        }
    }

    fun sendEmail(email:String){
        val mailto = "mailto:${email}"

        val emailIntent = Intent(Intent.ACTION_SENDTO)
        emailIntent.data = Uri.parse(mailto)
        try {
            startActivity(emailIntent)
        } catch (e: ActivityNotFoundException) {
            //TODO: Handle case where no email app is available
        }
    }

    fun callPhone(phone:String){
        val tel = "tel:${phone}"

        val phoneIntent = Intent(Intent.ACTION_CALL)
        phoneIntent.data = Uri.parse(tel)
        try {
            if (ContextCompat.checkSelfPermission(this, CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                startActivity(phoneIntent);
            } else {
                requestPermissions( arrayOf(CALL_PHONE), 1);
            }
        } catch (e: ActivityNotFoundException) {
            //TODO: Handle case where no email app is available
        }
    }
}