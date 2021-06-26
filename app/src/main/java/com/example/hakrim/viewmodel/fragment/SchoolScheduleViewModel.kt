package com.example.hakrim.viewmodel.fragment

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hakrim.dto.mealp.schoolschedule.Row
import com.example.hakrim.util.Time
import com.example.hakrim.viewmodel.fragment.MealViewModel.Companion.TAG

class SchoolScheduleViewModel : ViewModel() {

    private val _date = MutableLiveData<Long>()
    val time = Time(System.currentTimeMillis())


    val date: LiveData<Long>
        get() = _date

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

    val getAllMovie: MutableLiveData<List<Row>> = MutableLiveData()

    /**
    fun schoolInformationShow(day: String) {
    Builder.mealApi.schoolSchedule(scheduleStart = day)
    .enqueue(object : retrofit2.Callback<SchoolSchedule> {
    override fun onResponse(
    call: Call<SchoolSchedule>,
    response: Response<SchoolSchedule>
    ) {
    try {

    if (response.isSuccessful) {
    getAllMovie.value = response.body()!!.SchoolSchedule[1].row
    }
    } catch (e: NullPointerException) {
    Log.d(TAG, "onResponse: error $e ")

    }
    }

    override fun onFailure(call: Call<SchoolSchedule>, t: Throwable) {
    Log.d(TAG, "onFailure: $t")
    }

    })
    }
     */
}