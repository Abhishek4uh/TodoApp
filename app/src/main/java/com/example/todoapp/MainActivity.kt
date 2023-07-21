package com.example.todoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.todoapp.databinding.ActivityMainBinding
import com.example.todoapp.room.ItemData
import com.example.todoapp.room.ItemViewModel

class MainActivity : AppCompatActivity(),NoteClickInterface{
    lateinit var mainBinding: ActivityMainBinding
    private val itemViewModel:ItemViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainBinding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)


        mainBinding.addBTNNotes.setOnClickListener {
            val intent=Intent(this@MainActivity,CreateActivity::class.java)
            startActivity(intent)
        }

        //setting Layout Manager
        mainBinding.recyclerview.layoutManager=GridLayoutManager(this,2)
        val adapterClass=AdapterClass(this)
        mainBinding.recyclerview.adapter=adapterClass

        //viewmodel
        itemViewModel.allNotes.observe(this, Observer {
            adapterClass.updateList(it)
        })
    }

    override fun onNoteClick(itemData: ItemData) {
        val intent = Intent(this@MainActivity, UpdateActivity::class.java)
        intent.putExtra("noteType", "myType")
        intent.putExtra("noteTitle", itemData.title)
        intent.putExtra("noteDescription", itemData.description)
        intent.putExtra("noteDate", itemData.date)
        intent.putExtra("noteId",itemData.id)
        startActivity(intent)
    }
}