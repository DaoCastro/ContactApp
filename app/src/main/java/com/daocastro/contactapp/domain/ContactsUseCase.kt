package com.daocastro.contactapp.domain

import com.daocastro.contactapp.data.APIs
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import javax.inject.Inject

class ContactsUseCase @Inject constructor(private val apIs: APIs)  {

    suspend operator fun invoke(): JsonArray {
        val response = apIs.getContacts()

        return response
    }
}