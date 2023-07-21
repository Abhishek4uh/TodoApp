package com.example.todoapp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.core.widget.addTextChangedListener
import com.example.todoapp.databinding.ActivityUpdateBinding
import com.example.todoapp.room.ItemData
import com.example.todoapp.room.ItemViewModel
import java.text.SimpleDateFormat
import java.util.*


class UpdateActivity : AppCompatActivity() {
    lateinit var updateBinding: ActivityUpdateBinding
    private val itemViewModel: ItemViewModel by viewModels()
    var noteID = -1;
    lateinit var title:String
    lateinit var description:String

    @SuppressLint("SimpleDateFormat")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        updateBinding=ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(updateBinding.root)
        supportActionBar?.hide()



        updateBinding.titleTV.addTextChangedListener {
            title = it.toString().uppercase()
        }

        updateBinding.descriptionTV.addTextChangedListener {
            description = it.toString()
        }


        //setting all the previous data to fields
        val noteType = intent.getStringExtra("noteType")
        if(noteType.equals("myType")){
            val noteTitle = intent.getStringExtra("noteTitle")
            val noteDescription = intent.getStringExtra("noteDescription")
            noteID = intent.getIntExtra("noteId", -1)

            updateBinding.titleTV.setText(noteTitle?.uppercase())
            updateBinding.descriptionTV.setText(noteDescription)
        }
        else{
            updateBinding.updateBTNEdit.text = "Save Note"
        }

        updateBinding.updateBTNEdit.setOnClickListener {
            val d= SimpleDateFormat("dd MMM yyyy - HH:mm")
            val date:String= d.format(Date())
            itemViewModel.updateItem(title,description,date,noteID)
            finish()
        }

        updateBinding.deleteBTNEdit.setOnClickListener {
            itemViewModel.deleteItem(noteID)
            finish()
        }
    }
}
