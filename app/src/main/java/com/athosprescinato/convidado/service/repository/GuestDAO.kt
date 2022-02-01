package com.athosprescinato.convidado.service.repository

import androidx.room.*
import com.athosprescinato.convidado.service.model.GuestModel

@Dao
interface GuestDAO {

    @Insert
    fun save(guest: GuestModel) : Long

    @Update
    fun update(guest: GuestModel) : Int

    @Delete
    fun delete(guest: GuestModel)

    @Query("SELECT * FROM guest WHERE id = :id")
    fun get(id: Int): GuestModel

    @Query("SELECT * FROM guest")
    fun getInvited(): List<GuestModel>

    @Query("SELECT * FROM guest WHERE presence = 1")
    fun getPresent(): List<GuestModel>

    @Query("SELECT * FROM guest WHERE presence = 0")
    fun getAbsent(): List<GuestModel>



}