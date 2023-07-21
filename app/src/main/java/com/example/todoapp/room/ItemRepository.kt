package com.example.todoapp.room

import androidx.lifecycle.LiveData


class ItemRepository(private val itemDao: ItemDao) {

    //inserting
    suspend fun insert(itemData: ItemData){
        itemDao.insertItem(itemData)
    }
    //deleting
    suspend fun delete(id:Int){
        itemDao.deleteItem(id)
    }
    //updating
    suspend fun update(title:String,description:String,date:String,id:Int){
        itemDao.updateItem(title,description,date,id)
    }
    //fetching
    fun getAllNote(): LiveData<List<ItemData>> {
        return itemDao.getNotes()
    }

}