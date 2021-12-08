package com.example.hakrim.adpater

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.hakrim.R
import com.example.hakrim.databinding.SchoolScheduleRowBinding
import com.example.hakrim.dto.schoolschedule.Row
import com.example.hakrim.viewmodel.fragment.SchoolScheduleViewModel

class RecyclerAdapter() : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {
    var items = ArrayList<Row>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<SchoolScheduleRowBinding>(
            layoutInflater,
            viewType,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.school_schedule_row
    }

    override fun getItemCount(): Int = items.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class ViewHolder(private val binding: SchoolScheduleRowBinding) :
        RecyclerView.ViewHolder(binding.root) {

            fun bind(item : Row) = with(binding){
                row = item
        }
    }

}