package com.daocastro.contactapp.data.entities.phone

import com.google.gson.annotations.SerializedName

data class Phone(
    @SerializedName("id")
    val id : String?,
    @SerializedName("phone")
    val phone : String?
):java.io.Serializable