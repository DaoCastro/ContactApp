package com.daocastro.contactapp.data.entities.contacts

import com.daocastro.contactapp.data.entities.email.Email
import com.daocastro.contactapp.data.entities.phone.Phone
import com.google.gson.annotations.SerializedName

data class Contacts(
    @SerializedName("contacts")
    val contacts : ArrayList<Contact> = arrayListOf(),
)

data class Contact (
    @SerializedName("id")
    val id : String?,
    @SerializedName("firstname")
    val firstName : String?,
    @SerializedName("lastname")
    val lastName : String?,
    @SerializedName("title")
    val title : String?,
    @SerializedName("emails")
    val emails : ArrayList<Email> = arrayListOf(),
    @SerializedName("phones")
    val phones : ArrayList<Phone> = arrayListOf()
):java.io.Serializable