package com.synowkrz.sportrpg.Dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.synowkrz.sportrpg.Model.Item

@Dao
interface ItemDao {

    @Query("SELECT * from itemsTable WHERE name = :name")
    fun getItem(name: String) : Item

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertItem(item: Item)
}