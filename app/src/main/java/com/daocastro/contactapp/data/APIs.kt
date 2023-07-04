package com.daocastro.contactapp.data

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import retrofit2.http.GET

interface APIs {
    @GET("contacts")
    suspend fun getContacts(): JsonArray
}