package com.daocastro.contactapp.presentation.contacts.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.avatarfirst.avatargenlib.AvatarConstants
import com.avatarfirst.avatargenlib.AvatarGenerator
import com.daocastro.contactapp.R
import com.daocastro.contactapp.data.entities.contacts.Contact
import com.daocastro.contactapp.data.entities.contacts.Contacts
import com.daocastro.contactapp.presentation.contacts.ContactsActivity

class ContactAdapter:RecyclerView.Adapter<ContactAdapter.MyViewHolder> {

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tvnombre: TextView
        var tvtipo: TextView
        var ivcontact: ImageView
        var lymain: LinearLayout
        init {
            tvnombre = view.findViewById<TextView>(R.id.tvnombre)
            tvtipo = view.findViewById<TextView>(R.id.tvtipo)
            ivcontact = view.findViewById<ImageView>(R.id.ivcontact)
            lymain = view.findViewById<LinearLayout>(R.id.lymain)
        }
    }

    var _contacts: ArrayList<Contact> = arrayListOf()
    var _context: Context? = null

    constructor(items: ArrayList<Contact>, context: Context?) {
        _contacts = items
        _context = context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_contact_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return _contacts?.size?:0
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val b = _contacts?.get(position)
        holder.tvnombre.text="${b?.firstName} ${b?.lastName}"
        holder.tvtipo.text="${b?.title}"
        holder.ivcontact.setImageDrawable(
            _context?.let {
                AvatarGenerator.avatarImage(
                    it,
                    200,
                    AvatarConstants.CIRCLE,
                    "${b?.firstName} ${b?.lastName}"
                )
            }
        )
        holder.ivcontact.setOnClickListener {
            b?.let { contact ->
                gotToDetail(contact)
            }
        }
        holder.tvnombre.setOnClickListener {
            b?.let { contact ->
                gotToDetail(contact)
            }
        }

    }

    private fun gotToDetail(contact:Contact)
    {
        (_context as ContactsActivity).goToDetail(contact)
    }
}