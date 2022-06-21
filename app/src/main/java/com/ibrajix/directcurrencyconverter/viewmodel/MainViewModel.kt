package com.ibrajix.directcurrencyconverter.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ibrajix.directcurrencyconverter.helper.Resource
import com.ibrajix.directcurrencyconverter.helper.SingleLiveEvent
import com.ibrajix.directcurrencyconverter.model.ApiResponse
import com.ibrajix.directcurrencyconverter.model.Rates
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainViewModel @ViewModelInject constructor(private val mainRepo: MainRepo) : ViewModel()  {



    private val _data = SingleLiveEvent<Resource<ApiResponse>>()



    val data  =  _data
    val convertedRate = MutableLiveData<Double>()



    fun getConvertedData(access_key: String, from: String, to: String, amount: Double) {
        viewModelScope.launch {
            mainRepo.getConvertedData(access_key, from, to, amount).collect {
                data.value = it
            }
        }
    }


}