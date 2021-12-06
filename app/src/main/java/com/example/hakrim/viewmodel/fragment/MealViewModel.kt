package com.example.hakrim.viewmodel.fragment

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hakrim.dto.mealp.Meal
import com.example.hakrim.repository.MainRepository
import com.example.hakrim.retrofit.MealApi
import com.example.hakrim.util.Time
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Response
import java.lang.NullPointerException

enum class ActionType {
    PLUS, MINUS, SH
}

open class MealViewModel(private val repository: MainRepository) : ViewModel() {
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

    fun mealShow(day:String,sc_code:Int){
        repository.getMeal(day,sc_code)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ items->
                try {
                  val item = items.mealServiceDietInfo[1].row
                    _meal.value = item[0].DDISH_NM
                }catch(e: Exception){
                    _meal.postValue("급식정보가 없습니다.")
                }
            },{e->
                _meal.postValue(e.toString())
            })
    }

//    fun mealShow(day: String, sc_code: Int) {
//        service.MealService(day, sc_code).enqueue(object : retrofit2.Callback<Meal> {
//            override fun onResponse(call: Call<Meal>, response: Response<Meal>) {
//                try {
//                    val mres = response.body()!!.mealServiceDietInfo[1].row
//                    if (response.isSuccessful) {
//                        _meal.value = mres[0].DDISH_NM
//                    }
//                } catch (e: NullPointerException) {
//                    _meal.postValue("급식 정보가 없습니다.")
//                }
//            }
//
//            override fun onFailure(call: Call<Meal>, t: Throwable) {
//                Log.d(TAG, "onFailure: $t")
//            }
//
//        })
//    }



}