package com.example.hakrim.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import com.example.hakrim.retrofit.Builder
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hakrim.R
import com.example.hakrim.SchoolScheduleAdapter
import com.example.hakrim.databinding.FragmentSchoolInformationBinding
import com.example.hakrim.dto.mealp.schoolschedule.Row
import com.example.hakrim.dto.mealp.schoolschedule.SchoolSchedule
import com.example.hakrim.dto.mealp.schoolschedule.SchoolScheduleX
import com.example.hakrim.util.Time
import com.example.hakrim.viewmodel.fragment.ActionType
import com.example.hakrim.viewmodel.fragment.MealViewModel
import com.example.hakrim.viewmodel.fragment.MealViewModel.Companion.TAG
import com.example.hakrim.viewmodel.fragment.SchoolScheduleViewModel
import retrofit2.Call
import retrofit2.Response

class SchoolInformationFragment : Fragment() {
    lateinit var binding: FragmentSchoolInformationBinding
    private val scheduleViewModel: SchoolScheduleViewModel by viewModels()
    private var scheduleAdapter: SchoolScheduleAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_school_information,
            container,
            false
        )

        binding.viewModel = scheduleViewModel
        binding.fragment = this
        setData()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        scheduleViewModel.date.observe(viewLifecycleOwner, Observer {
            var time = Time(it)
            Log.d(MealFragment.TAG, "onViewCreated: $it")
            binding.mCalender.text = time.dateFormat4
        })


    }

    private fun setAdapter(scheduleList: List<SchoolScheduleX>) {
        scheduleAdapter = SchoolScheduleAdapter(scheduleList[1].row)
        binding.scheduleRecycler.adapter = SchoolScheduleAdapter(scheduleList[1].row)
        binding.scheduleRecycler.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.scheduleRecycler.setHasFixedSize(false)


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

    private fun setData() {
        scheduleViewModel.date.observe(
            viewLifecycleOwner,
        ) {
            var time = Time(it)

            Builder.mealApi.schoolSchedule(scheduleStart = time.dateFormat)
                .enqueue(object : retrofit2.Callback<SchoolSchedule> {
                    override fun onResponse(
                        call: Call<SchoolSchedule>,
                        response: Response<SchoolSchedule>
                    ) {
                        try {

                            if (response.isSuccessful) {
                                val body = response.body()
                                body?.let { it ->
                                    setAdapter(it.SchoolSchedule)

                                }
                            }
                        } catch (e: NullPointerException) {
                            Log.d(TAG, "onResponse: error $e ")

                        }
                    }

                    override fun onFailure(call: Call<SchoolSchedule>, t: Throwable) {
                        Log.d(TAG, "onFailure: $t")
                    }

                })
        }

        /**
        private fun setData() {
        scheduleViewModel.date.observe(
        viewLifecycleOwner,
        ) {
        var time = Time(it)
        scheduleViewModel.schoolInformationShow(time.dateFormat3)

        scheduleViewModel.getAllMovie.observe(viewLifecycleOwner, { dd ->
        Log.d(MealViewModel.TAG, "schedule: $dd")

        scheduleAdapter?.setData(dd)
        })
        }

        }
         */
    }
}