package com.example.todoapp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.format.DateFormat
import android.util.Log
import androidx.activity.viewModels
import androidx.core.widget.addTextChangedListener
import com.example.todoapp.databinding.ActivityCreateBinding
import com.example.todoapp.room.ItemData
import com.example.todoapp.room.ItemViewModel
import java.text.SimpleDateFormat
import java.util.*

class CreateActivity : AppCompatActivity() {

    //initialization
    lateinit var createBinding: ActivityCreateBinding
    private val itemViewModel: ItemViewModel by viewModels()

    @SuppressLint("SimpleDateFormat")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        createBinding=ActivityCreateBinding.inflate(layoutInflater)
        setContentView(createBinding.root)
        supportActionBar?.hide()


        var title: String? = null
        var description: String?=null
        val d= SimpleDateFormat("dd MMM yyyy - HH:mm")
        val date:String= d.format(Date())

        createBinding.title.addTextChangedListener{
            title = it.toString()
        }
        createBinding.description.addTextChangedListener {
            description = it.toString()
        }

        createBinding.saveBTN.setOnClickListener {
            if(!title.isNullOrEmpty() && !description.isNullOrEmpty()){
                val myData=ItemData(title,description,date)
                itemViewModel.addItem(myData)
                finish()
            }
        }
    }
}