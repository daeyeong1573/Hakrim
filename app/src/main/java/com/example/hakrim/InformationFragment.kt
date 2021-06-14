package com.example.hakrim
import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.hakrim.databinding.FragmentInformationBinding

class InformationFragment  : Fragment() {
    private val binding by lazy { FragmentInformationBinding.inflate(layoutInflater) }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {



        return binding.root
    }

}