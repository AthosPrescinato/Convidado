package com.athosprescinato.convidado.service.repository

import android.content.Context
import com.athosprescinato.convidado.service.model.GuestModel

class GuestRepository(context: Context) {

    private val mDataBase = GuestDataBase.getDatabase(context).guestDAO()


    fun get(id: Int): GuestModel {
        return mDataBase.get(id)
    }

    fun getAll(): List<GuestModel> {
        return mDataBase.getInvited()
    }

    fun getPresence(): List<GuestModel> {
        return mDataBase.getPresent()
    }

    fun getAbsent(): List<GuestModel> {
        return mDataBase.getAbsent()
    }

    fun save(guest: GuestModel): Boolean {
        return mDataBase.save(guest) > 0
    }

    fun update(guest: GuestModel): Boolean {
        return mDataBase.update(guest) > 0
    }

    fun delete(guest: GuestModel) {
        mDataBase.delete(guest)
    }

}