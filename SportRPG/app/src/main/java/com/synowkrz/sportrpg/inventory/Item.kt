package com.synowkrz.sportrpg.inventory

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@Entity(tableName = "itemsTable")
data class Item(@PrimaryKey val name:String,
                val description: String,
                val cost: Int,
                val primaryParameter: Int,
                val primaryParameterValue: Int,
                val secondaryParameter: Int,
                val secondaryParameterValue: Int,
                val itemType: Int) {

    companion object {
        fun convertStringIntoItemList(itemString: String) : List<Item> {
            val gson = Gson()
            val itemList : List<Item> = gson.fromJson(itemString, object : TypeToken<List<Item>>() {}.type)
            return itemList
        }

        fun convertItemListIntoJson(itemList: List<Item>) : String {
            val gson = Gson()
            return gson.toJson(itemList)
        }
    }
}

