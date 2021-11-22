package com.example.hakrim.viewmodel.fragment

import android.util.Log
import androidx.databinding.ObservableArrayList
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hakrim.dto.schoolschedule.Row
import com.example.hakrim.dto.schoolschedule.SchoolSchedule
import com.example.hakrim.retrofit.Builder
import com.example.hakrim.retrofit.MealApi
import com.example.hakrim.util.Time
import com.example.hakrim.viewmodel.fragment.MealViewModel.Companion.TAG
import org.koin.core.Koin
import retrofit2.Call
import retrofit2.Response
import java.lang.NullPointerException

class SchoolScheduleViewModel(private val schApi: MealApi) : ViewModel() {

    private val _date = MutableLiveData<Long>()
    private val _sch = ObservableArrayList<Row>()
    val time = Time(System.currentTimeMillis())


    val date: LiveData<Long>
        get() = _date

    val sch: ObservableArrayList<Row>
        get() = _sch

    init {
        _date.value = time.now
    }

    fun changeDate(actionType: ActionType, int: Long) {
        when (actionType) {
            ActionType.PLUS -> {
                _date.value = _date.value?.plus(int)
                Log.d(TAG, "changeDatePlus: ${_date.value}")
            }
            ActionType.MINUS -> {
                _date.value = _date.value?.minus(int)
                Log.d(TAG, "changeDateMinus: ${_date.value}")
            }
        }
    }

    fun showSch(day: String){
        schApi.schoolSchedule(scheduleStart = day).enqueue(object :
            retrofit2.Callback<SchoolSchedule> {
            override fun onResponse(
                call: Call<SchoolSchedule>,
                response: Response<SchoolSchedule>
            ) {
                try {
                    val res = response.body()!!.SchoolSchedule[1].row
                    if (response.isSuccessful) {
                        for(i in res.indices)
                        _sch.add(Row(res[i].AA_YMD,res[1].EVENT_NM))
                    }
                } catch (e: NullPointerException) {
                }
            }

            override fun onFailure(call: Call<SchoolSchedule>, t: Throwable) {
                Log.d(TAG, "onFailure: $t")
            }

        })

    }


}