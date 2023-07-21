package com.example.todoapp.room

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface ItemDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertItem(itemData: ItemData)

    @Query("DELETE FROM my_data WHERE id = :id")
    suspend fun deleteItem(id: Int)

    @Query("UPDATE my_data SET  title= :title,description = :description, date=:date WHERE id =:id")
    suspend fun updateItem(title:String,description:String,date:String,id:Int )

    @Query(value = "Select * from my_data order by id ASC")
    fun getNotes(): LiveData<List<ItemData>>

}