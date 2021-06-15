package com.example.hakrim.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
    import androidx.fragment.app.Fragment
import com.example.hakrim.databinding.FragmentInformationBinding
import com.example.hakrim.databinding.FragmentMealBinding
import com.example.hakrim.util.Time
import java.lang.reflect.Array.get

class MealFragment : Fragment() {
    private val binding by lazy { FragmentMealBinding.inflate(layoutInflater) }
    lateinit var time : Time


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.time = Time()
    }

    fun mealSelect(){

    }

}