package com.example.hakrim

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hakrim.databinding.SchoolScheduleRowBinding
import com.example.hakrim.dto.mealp.schoolschedule.Row
import com.example.hakrim.dto.mealp.schoolschedule.SchoolSchedule
import com.example.hakrim.dto.mealp.schoolschedule.SchoolScheduleX

class SchoolScheduleAdapter(private var scheduleList: List<Row>) :
    RecyclerView.Adapter<SchoolScheduleAdapter.ViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = SchoolScheduleRowBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    class ViewHolder(private val binding: SchoolScheduleRowBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Row) {
            binding.scheduleItem = item
            binding.executePendingBindings()


        }

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(scheduleList[position])
    }

    override fun getItemCount() = scheduleList.size
    fun setData(it: List<SchoolScheduleX>) {
        this.scheduleList=it[1].row
        notifyDataSetChanged()
    }
}