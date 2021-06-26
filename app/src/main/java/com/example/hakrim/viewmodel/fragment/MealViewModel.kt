package com.example.hakrim.viewmodel.fragment

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hakrim.dto.mealp.Meal
import com.example.hakrim.retrofit.Builder
import com.example.hakrim.util.Time
import retrofit2.Call
import retrofit2.Response
import java.lang.NullPointerException
import java.util.*
import javax.security.auth.callback.Callback

enum class ActionType {
    PLUS, MINUS, SH
}

open class MealViewModel : ViewModel() {
    companion object {
        val TAG: String = "로그"
    }

    private val _date = MutableLiveData<Long>()
    private val _meal = MutableLiveData<String>()

    val date: LiveData<Long>
        get() = _date

    val meal: LiveData<String>
        get() = _meal

    val time = Time(System.currentTimeMillis())

    init {
        _date.value = time.now
        _meal.value = ""
    }

    fun changeDate(actionType: ActionType, int: Long) {
        when (actionType) {
            ActionType.PLUS ->
                _date.value = _date.value?.plus(int)
            ActionType.MINUS ->
                _date.value = _date.value?.minus(int)
        }
    }

    fun mealShow(day: String, sc_code: Int) {
        Builder.mealApi.MealService(day, sc_code).enqueue(object : retrofit2.Callback<Meal> {
            override fun onResponse(call: Call<Meal>, response: Response<Meal>) {
                try {
                    val mres = response.body()!!.mealServiceDietInfo[1].row
                    if (response.isSuccessful) {
                        for (i in mres.indices) {
                            val obj = mres[i]
                            _meal.postValue(obj.DDISH_NM)
                            Log.d(TAG, "onResponse: ${meal}")
                        }
                    }
                }catch (e : NullPointerException){
                    _meal.postValue("급식 정보가 없습니다.")
                }
            }

            override fun onFailure(call: Call<Meal>, t: Throwable) {
                Log.d(TAG, "onFailure: $t")
            }

        })
    }


}