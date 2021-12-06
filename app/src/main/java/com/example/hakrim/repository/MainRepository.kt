package com.example.hakrim.repository

import com.example.hakrim.retrofit.MealApi

class MainRepository constructor(private val api : MealApi) {
    fun getSch(day : String) = api.schoolSchedule(scheduleStart = day)
    fun getMeal(day:String,sc_code:Int) = api.MealService(day = day, sc = sc_code)
    fun getInfo() = api.schoolInformation()
}