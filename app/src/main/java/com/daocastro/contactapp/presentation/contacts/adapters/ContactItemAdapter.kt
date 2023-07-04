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
import com.daocastro.contactapp.data.entities.email.Email
import com.daocastro.contactapp.data.entities.phone.Phone
import com.daocastro.contactapp.presentation.contacts.ContactsActivity
import com.daocastro.contactapp.presentation.contacts.contact_detail.ContactDetailActivity

class ContactItemAdapter<T>:RecyclerView.Adapter<ContactItemAdapter.MyViewHolder> {

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tvEmail:TextView
        var ivEmail:ImageView
        var ivPhone:ImageView
        var lyMain:LinearLayout
        init {
            tvEmail =  view.findViewById<TextView>(R.id.tvemail)
            ivEmail =  view.findViewById<ImageView>(R.id.ivemail)
            ivPhone =  view.findViewById<ImageView>(R.id.ivphone)
            lyMain =  view.findViewById<LinearLayout>(R.id.lymain)
        }
    }

    var _contacts: ArrayList<T> = arrayListOf()
    var _context: Context? = null
    var _type: Int? = null

    constructor(items: ArrayList<T>, context: Context?,type:Int) {
        _contacts = items
        _context = context
        _type = type
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_email_phone_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return _contacts.size ?:0
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val b = _contacts.get(position)
        if(_type == 0){
            val email = b as Email
            holder.ivEmail.visibility = View.VISIBLE
            holder.ivPhone.visibility = View.GONE
            holder.tvEmail.text = email.email

            holder.lyMain.setOnClickListener {
                email.email?.let { it1 -> (_context as ContactDetailActivity).sendEmail(it1) }
            }
        }

        if(_type == 1){
            val phone = b as Phone
            holder.ivEmail.visibility = View.GONE
            holder.ivPhone.visibility = View.VISIBLE
            holder.tvEmail.text = phone.phone

            holder.lyMain.setOnClickListener {
                phone.phone?.let { it1 -> (_context as ContactDetailActivity).callPhone(it1) }
            }
        }
    }
}