package com.example.hakrim.view.fragment

import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.example.hakrim.R
import com.example.hakrim.databinding.FragmentInformationBinding
import com.example.hakrim.databinding.FragmentMealBinding
import com.example.hakrim.viewmodel.fragment.MealViewModel
import com.example.hakrim.viewmodel.fragment.SchoolInformationViewModel

class InformationFragment : Fragment() {
    lateinit var binding: FragmentInformationBinding
    private val viewModel: SchoolInformationViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_information, container, false)
        binding.schoolInformation = viewModel
        binding.lifecycleOwner = this

        viewModel.schoolInformationShow()
        return binding.root
    }

}