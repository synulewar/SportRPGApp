package com.synowkrz.sportrpg.inventory

import org.junit.Assert.*
import org.junit.Test

class ItemTest {

    @Test
    fun testConvertItemConversion() {
        var item = Item("Sword of Arangor", "Very strong sword", 1000, 1,1,2,2,1)
        var itemList = listOf(item)
        var str = Item.convertItemListIntoJson(itemList)
        println(str)
        assertNotNull(str)

        var itemListAfterConversion = Item.convertStringIntoItemList(str)
        var itemAfterConversion = itemListAfterConversion.get(0)
        assertEquals(item, itemAfterConversion)
    }

}