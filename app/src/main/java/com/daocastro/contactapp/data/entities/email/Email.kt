package com.daocastro.contactapp.data.entities.email

import com.google.gson.annotations.SerializedName


data class Email(
    @SerializedName("id")
    val id : String?,
    @SerializedName("email")
    val email : String?
):java.io.Serializable