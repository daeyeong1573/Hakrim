package com.example.hakrim.view.fragment

import com.example.hakrim.adpater.RecyclerAdapter
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hakrim.R
import com.example.hakrim.databinding.FragmentSchoolSchBinding
import com.example.hakrim.dto.schoolschedule.Row
import com.example.hakrim.util.Time
import com.example.hakrim.viewmodel.fragment.ActionType
import com.example.hakrim.viewmodel.fragment.SchoolScheduleViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class SchoolSchFragment : Fragment() {
    lateinit var binding: FragmentSchoolSchBinding
    private val scheduleViewModel: SchoolScheduleViewModel by inject()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_school_sch,
                container,
                false
        )
        binding.lifecycleOwner = this
        binding.viewModel = scheduleViewModel
        binding.fragment = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        scheduleViewModel.date.observe(viewLifecycleOwner, Observer {
            var time = Time(it)
            Log.d(MealFragment.TAG, "onViewCreated: ${time.dateFormat4}")
            binding.mCalender.text = time.dateFormat4
            scheduleViewModel.showSch("${time.dateFormat}")
            scheduleViewModel.schRepositorys.observe(viewLifecycleOwner, Observer {
                val adapter = RecyclerAdapter(it)
                binding.scheduleRecycler.adapter = adapter

            })
        })



    }

    fun dateChange(view: View) {
        Log.d(MealFragment.TAG, "dataChange: 클릭성공")
        val input: Long = 2678400000
        when (view) {
            binding.datePlus -> {
                Log.d(MealFragment.TAG, "dateChange: 데이터 체인지")
                scheduleViewModel.changeDate(actionType = ActionType.PLUS, int = input)
            }
            binding.dateMinus ->
                scheduleViewModel.changeDate(actionType = ActionType.MINUS, int = input)
        }

    }



}