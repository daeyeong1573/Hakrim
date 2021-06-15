package com.example.hakrim.util

import androidx.databinding.DataBindingUtil
import com.example.hakrim.R
import com.example.hakrim.databinding.FragmentMealBinding
import java.text.SimpleDateFormat
import java.util.*

class Time() {
    val now: Long = System.currentTimeMillis() // 현재시간을 msec 으로 구한다.
    val date = Date(now) // 현재시간을 Date에 저장한다
    val dateFormat = SimpleDateFormat("yyyyMMdd", Locale("ko", "KR")).format(date)
    val dateFormat2 = SimpleDateFormat("yyyy년 MM월 dd일 \nEE요일", Locale("ko", "KR")).format(date)
    var stringTime = dateFormat2.format(date)

}