package com.example.todoapp.room

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class ItemViewModel(application: Application) :AndroidViewModel(application) {

    var allNotes : LiveData<List<ItemData>>
    private var repository: ItemRepository

    init {
        val dao=ItemDataBase.getDatabase(application).getNoteDao()
        repository= ItemRepository(dao)
        allNotes=repository.getAllNote()
    }

    //Deleting
    fun deleteItem(id:Int) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(id)
    }

    //Inserting
    fun addItem(itemData: ItemData) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(itemData)
    }

    //updating
    fun updateItem(title:String,description:String,date:String,id:Int) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(title,description,date,id)
    }

}