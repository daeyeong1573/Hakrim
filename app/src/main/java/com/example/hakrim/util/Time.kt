package com.example.hakrim.util

import android.widget.CalendarView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import com.example.hakrim.R
import com.example.hakrim.databinding.FragmentMealBinding
import java.text.SimpleDateFormat
import java.util.*

class Time(now:Long) {

    var now: Long = now
    val date = Date(now) // 현재시간을 Date에 저장한다
    val dateFormat = SimpleDateFormat("yyyyMMdd", Locale("ko", "KR")).format(date)
    val dateFormat2 = SimpleDateFormat("yyyy년 MM월 dd일 \nEE요일", Locale("ko", "KR")).format(date)

    val dateFormat4 = SimpleDateFormat("MM월", Locale("ko", "KR")).format(date)

    val stringTime = dateFormat2.format(date)


}