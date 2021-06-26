package com.example.hakrim.retrofit

import android.telecom.Call
import com.example.hakrim.dto.mealp.Meal
import retrofit2.http.GET
import retrofit2.http.Query

interface MealApi {
    @GET("hub/mealServiceDietInfo")
    fun MealService(
        @Query("MLSV_YMD") day: String, // 급식 날짜
        @Query("MMEAL_SC_CODE") sc: Int, // 조식,점식,석식 코드
        @Query("KEY") key: String = "",//자신의 api 인증키
        @Query("ATPT_OFCDC_SC_CODE") region: String = "F10",// 시교육청 코드
        @Query("SD_SCHUL_CODE") school: Int = 7380292, //학교 코드
        @Query("Type") type: String = "json", //api를 json 형식으로 읽어오기
        @Query("pIndex") index: Int = 1,
        @Query("pSize") size: Int = 100

    ): retrofit2.Call<Meal>


    @GET("hub/mealServiceDietInfo")
    fun schoolInformation(
        @Query("Key") key: String,
        @Query("Type") type: String = "json",
        @Query("ATPT_OFCDC_SC_CODE") region: String = "F10",
        @Query("SD_SCHUL_CODE") school: Int = 7380292,
        @Query("pIndex") index: Int = 1,
        @Query("pSize") size: Int = 100,
    )

}

