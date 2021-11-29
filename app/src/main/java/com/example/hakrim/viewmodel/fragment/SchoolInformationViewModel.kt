package com.example.hakrim.viewmodel.fragment

import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hakrim.dto.schoolinformation.SchoolInformation
import com.example.hakrim.retrofit.Builder
import com.example.hakrim.retrofit.MealApi
import com.example.hakrim.viewmodel.fragment.MealViewModel.Companion.TAG
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.koin.core.Koin
import retrofit2.Call
import retrofit2.Response

class SchoolInformationViewModel(private val service: MealApi) : ViewModel() {


    private val _schoolName = MutableLiveData<String>()
    private val _schoolEngName = MutableLiveData<String>()
    private val _schoolCode = MutableLiveData<String>()
    private val _schoolKind = MutableLiveData<String>()
    private val _schoolHomePage = MutableLiveData<String>()
    private val _schoolAnniversary = MutableLiveData<String>()
    private val _schoolRoadAddress = MutableLiveData<String>()
    private val _schoolPost = MutableLiveData<String>()
    private val _schoolPhoneNumber = MutableLiveData<String>()
    private val _schoolFaxNumber = MutableLiveData<String>()

    val schoolName: LiveData<String>
        get() = _schoolName
    val schoolEngName: LiveData<String>
        get() = _schoolEngName
    val schoolCode: LiveData<String>
        get() = _schoolCode
    val schoolKind: LiveData<String>
        get() = _schoolKind
    val schoolHomePage: LiveData<String>
        get() = _schoolHomePage
    val schoolAnniversary: LiveData<String>
        get() = _schoolAnniversary
    val schoolRoadAddress: LiveData<String>
        get() = _schoolRoadAddress
    val schoolPost: LiveData<String>
        get() = _schoolPost
    val schoolPhoneNumber: LiveData<String>
        get() = _schoolPhoneNumber
    val schoolFaxNumber: LiveData<String>
        get() = _schoolFaxNumber

    fun schoolInformationShow() {
        service.schoolInformation()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({ items ->
                val res = items.schoolInfo[1].row
                try {
                    Log.d(TAG, "onResponse: $res")
                    _schoolName.value = res[0].SCHUL_NM
                    _schoolEngName.value = res[0].ENG_SCHUL_NM
                    _schoolCode.value = res[0].SD_SCHUL_CODE
                    _schoolKind.value = res[0].SCHUL_KND_SC_NM
                    _schoolHomePage.value = res[0].HMPG_ADRES
                    _schoolAnniversary.value = res[0].FOAS_MEMRD
                    _schoolRoadAddress.value = res[0].ORG_RDNMA
                    _schoolPost.value = res[0].ORG_RDNZC
                    _schoolPhoneNumber.value = res[0].ORG_TELNO
                    _schoolFaxNumber.value = res[0].ORG_FAXNO
                } catch (e: Exception) {
                    Log.d(TAG, "schoolInformationShow: $e")
                }
            },{e->
                Log.d(TAG, "schoolInformationShow: $e")
            })
    }
//
//    fun schoolInformationShow() {
//        service.schoolInformation().enqueue(object : retrofit2.Callback<SchoolInformation> {
//            override fun onResponse(call: Call<SchoolInformation>, response: Response<SchoolInformation>) {
//                try {
//                    val res = response.body()!!.schoolInfo[1].row
//                    if (response.isSuccessful) {
//                        Log.d(TAG, "onResponse: $res")
//                        _schoolName.value = res[0].SCHUL_NM
//                        _schoolEngName.value = res[0].ENG_SCHUL_NM
//                        _schoolCode.value = res[0].SD_SCHUL_CODE
//                        _schoolKind.value = res[0].SCHUL_KND_SC_NM
//                        _schoolHomePage.value = res[0].HMPG_ADRES
//                        _schoolAnniversary.value = res[0].FOAS_MEMRD
//                        _schoolRoadAddress.value = res[0].ORG_RDNMA
//                        _schoolPost.value = res[0].ORG_RDNZC
//                        _schoolPhoneNumber.value = res[0].ORG_TELNO
//                        _schoolFaxNumber.value = res[0].ORG_FAXNO
//                    }
//                }catch (e:NullPointerException){
//
//                    Log.d(TAG, "onResponse: error ")
//
//                }
//            }
//
//            override fun onFailure(call: Call<SchoolInformation>, t: Throwable) {
//                Log.d(MealViewModel.TAG, "onFailure: $t")
//            }
//
//        })
//    }
}