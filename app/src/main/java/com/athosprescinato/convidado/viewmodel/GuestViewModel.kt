package com.athosprescinato.convidado.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.athosprescinato.convidado.service.constants.GuestConstants
import com.athosprescinato.convidado.service.model.GuestModel
import com.athosprescinato.convidado.service.repository.GuestRepository

class GuestViewModel(application: Application) : AndroidViewModel(application) {

    private val mGuestRepository = GuestRepository.getInstance(application)

    private val mGuestList = MutableLiveData<List<GuestModel>>()
    val guestList: LiveData<List<GuestModel>> = mGuestList

    fun load(filter: Int) {

        if(filter == GuestConstants.FILTER.EMPTY){
            mGuestList.value = mGuestRepository.getAll()

        } else if (filter == GuestConstants.FILTER.PRESENT){
            mGuestList.value = mGuestRepository.getPresence()

        } else {
            mGuestList.value = mGuestRepository.getAbsent()

        }


    }

    fun delete(id: Int){
        mGuestRepository.delete(id)
    }
}