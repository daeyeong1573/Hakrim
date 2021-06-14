package com.example.hakrim

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.hakrim.databinding.FragmentInformationBinding
import com.example.hakrim.databinding.FragmentSchoolInformationBinding

class SchoolInformationFragment  : Fragment() {
    private val binding by lazy { FragmentSchoolInformationBinding.inflate(layoutInflater) }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {



        return binding.root
    }

}