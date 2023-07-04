package com.daocastro.contactapp.presentation.contacts

import androidx.lifecycle.ViewModel
import com.daocastro.contactapp.domain.ContactsUseCase
import com.daocastro.contactapp.presentation.State
import com.daocastro.contactapp.utils.Utils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@HiltViewModel
class ContactsActivityViewModel @Inject constructor(private val contactsUseCase: ContactsUseCase) :
    ViewModel()  {
    fun getContactResponse() = flow {
        emit(State.LoadingState)
        try {
            emit(State.DataState(contactsUseCase()))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(Utils.resolveError(e))
        }
    }
}