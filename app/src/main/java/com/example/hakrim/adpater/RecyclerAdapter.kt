package com.example.hakrim.adpater

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableArrayList
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.hakrim.R
import com.example.hakrim.databinding.SchoolScheduleRowBinding
import com.example.hakrim.dto.schoolschedule.Row
import com.example.hakrim.repository.MainRepository
import com.example.hakrim.viewmodel.fragment.SchoolScheduleViewModel

class RecyclerAdapter(private val items : List<Row>) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {


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

    override fun getItemCount(): Int {
        return items.size
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class ViewHolder(private val binding: SchoolScheduleRowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Row) = with(binding){
            scheduleItem = item
            scheduleDayText.text = item.AA_YMD
            scheduleText.text = item.EVENT_NM
        }
    }
//
//    fun update(updated : ArrayList<Row>){
//        items.addAll(updated)
//    }

    companion object SchDiffUtil : DiffUtil.ItemCallback<Row>() {
        override fun areItemsTheSame(oldItem: Row, newItem: Row): Boolean {
            //각 아이템들의 고유한 값을 비교해야 한다.
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Row, newItem: Row): Boolean {
            return oldItem == newItem
        }
    }


}