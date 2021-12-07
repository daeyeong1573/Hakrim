package com.example.hakrim.adpater

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.hakrim.dto.schoolschedule.Row

object SchDataBindingUtils {
    @BindingAdapter("listData")
    @JvmStatic
    fun bindList(recyclerView: RecyclerView, items : ArrayList<Row>?) {
        val adapter = recyclerView as RecyclerAdapter

    }
}
