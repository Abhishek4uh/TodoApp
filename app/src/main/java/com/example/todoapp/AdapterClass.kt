package com.example.todoapp

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.databinding.ItemViewBinding
import com.example.todoapp.room.ItemData
import java.util.*
import kotlin.collections.ArrayList


class AdapterClass(private val noteClickInterface: NoteClickInterface): RecyclerView.Adapter<AdapterClass.MyViewHolder>() {

    private var cxt:Context?=null
    private val data = ArrayList<ItemData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        cxt=parent.context
        return MyViewHolder(ItemViewBinding.inflate(LayoutInflater.from(cxt),parent,false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentData= data[position]
        holder.mBinding.title.text = currentData.title?.capitalize(Locale.ROOT)
        holder.mBinding.description.text= currentData.description
        holder.mBinding.date.text= currentData.date

        holder.mBinding.root.setOnClickListener {
            noteClickInterface.onNoteClick(data[position])
        }

    }

    override fun getItemCount(): Int {
        return data.size
    }

    class MyViewHolder(val mBinding: ItemViewBinding) :RecyclerView.ViewHolder(mBinding.root){
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(newList: List<ItemData>) {
        data.clear()
        data.addAll(newList)
        notifyDataSetChanged()

    }
}

interface NoteClickInterface {
    // creating a method for click action on recycler view item for updating it.
    fun onNoteClick(itemData: ItemData)
}