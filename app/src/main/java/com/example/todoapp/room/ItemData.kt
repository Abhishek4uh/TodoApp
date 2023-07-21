package com.example.todoapp.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "my_data")
data class ItemData(@ColumnInfo(name = "title") var title:String?,
                    @ColumnInfo(name = "description") var description:String?,
                    @ColumnInfo(name = "date") var date:String?) {
                    @PrimaryKey(autoGenerate = true) var id:Int=0
}
