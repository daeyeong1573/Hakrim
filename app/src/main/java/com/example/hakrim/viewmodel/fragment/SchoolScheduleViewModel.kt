package com.example.hakrim.viewmodel.fragment

import android.util.Log
import androidx.databinding.ObservableArrayList
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hakrim.dto.schoolschedule.Row
import com.example.hakrim.repository.MainRepository
import com.example.hakrim.util.Time
import com.example.hakrim.viewmodel.fragment.MealViewModel.Companion.TAG
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class SchoolScheduleViewModel(private val schRepostiroy : MainRepository) : ViewModel() {

    val schList = ObservableArrayList<Row>()

    private val _date = MutableLiveData<Long>()
    val date: LiveData<Long>
        get() = _date

    val time = Time(System.currentTimeMillis())

    init {
        _date.value = time.now
    }


    fun changeDate(actionType: ActionType, int: Long) {
        when (actionType) {
            ActionType.PLUS -> {
                _date.value = _date.value?.plus(int)
                schList.clear()
                Log.d(TAG, "changeDatePlus: ${_date.value}")
            }
            ActionType.MINUS -> {
                _date.value = _date.value?.minus(int)
                schList.clear()
                Log.d(TAG, "changeDateMinus: ${_date.value}")
            }
        }
    }

    fun showSch(day: String){
        schRepostiroy.getSch(day)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                try {
                    val item = it.SchoolSchedule[1].row
                    schList.addAll(item)
                }catch(e: Exception){
                  schList.add(Row("","데이터 정보가 없습니다."))
                }
            },{ e->
                Log.d(TAG, "showSchErr : ${e.message}")
            })
    }


}