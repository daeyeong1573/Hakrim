package com.example.hakrim.adpater

import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableArrayList
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.hakrim.dto.schoolschedule.Row

object SchDataBindingUtils {
    @BindingAdapter("listData")
    @JvmStatic
    fun bindList(recyclerView: RecyclerView, items : ObservableArrayList<Row>) {
        if(recyclerView.adapter == null){
            val adapter = RecyclerAdapter()
            recyclerView.adapter = adapter
        }
        (recyclerView.adapter as RecyclerAdapter).items = items
        recyclerView.adapter?.notifyDataSetChanged()
    }
}
