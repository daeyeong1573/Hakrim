package com.example.hakrim.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.hakrim.R
import com.example.hakrim.databinding.FragmentInformationBinding
import com.example.hakrim.databinding.FragmentMealBinding
import com.example.hakrim.dto.mealp.Meal
import com.example.hakrim.util.Time
import com.example.hakrim.viewmodel.fragment.ActionType
import com.example.hakrim.viewmodel.fragment.MealViewModel

class MealFragment : Fragment() {
    companion object {
        val TAG: String = "Fragment"
    }

    lateinit var binding: FragmentMealBinding
    private val mealViewModel: MealViewModel by viewModels()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_meal, container, false)
        binding.viewModel = MealViewModel()
        binding.fragment = this
        binding.lifecycleOwner = this


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mealViewModel.date.observe(viewLifecycleOwner, Observer {
            var time = Time(it)
            Log.d(TAG, "onViewCreated: $it")
            binding.mCalender.text = time.stringTime
        })

        mealViewModel.meal.observe(viewLifecycleOwner, Observer { it ->
            binding.mealMenu.text = it.filter { it in '가'..'힣' || it =='&' || it == '<' || it == ' '}.replace("<","\n")
        })


//        mealSelect(binding.breakFast)
    }


    fun dateChange(view: View) {
        Log.d(TAG, "dataChange: 클릭성공")
        val input: Long = 86400000
        when (view) {
            binding.datePlus -> {
                Log.d(TAG, "dateChange: 데이터 체인지")
                mealViewModel.changeDate(actionType = ActionType.PLUS, int = input)
            }
            binding.dateMinus ->
                mealViewModel.changeDate(actionType = ActionType.MINUS, int = input)
        }
        Log.d(TAG, "MealFragment - ${mealViewModel.date}")

    }



    fun mealSelect(view: View) {
        mealViewModel.date.observe(viewLifecycleOwner, Observer {
            var time = Time(it)
            when (view) {
                binding.breakFast -> {
                    mealViewModel.mealShow(day = "${time.dateFormat}", sc_code = 1)
                }
                binding.lunch -> {
                    mealViewModel.mealShow(day = "${time.dateFormat}", sc_code = 2)
                }

                binding.dinner -> {
                    mealViewModel.mealShow(day = "${time.dateFormat}", sc_code = 3)
                }
            }
        })
    }

}

