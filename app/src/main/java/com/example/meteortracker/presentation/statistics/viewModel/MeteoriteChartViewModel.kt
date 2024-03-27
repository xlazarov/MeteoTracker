package com.example.meteortracker.presentation.statistics.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.meteortracker.data.ChartData
import com.example.meteortracker.data.MeteoriteFilter
import com.example.meteortracker.service.NasaApiService
import com.example.meteortracker.service.getSelectQuery
import com.example.meteortracker.service.getWhereQuery
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MeteoriteChartViewModel @Inject constructor(
    private val nasaApi: NasaApiService
) : ViewModel() {

    private var chartX = ""

    private val token = "WmoOZLhIHEEFG028ZyGpizTST"
    private var where: String = ""
    private var select: String = ""
    private var group: String = ""

    private val _filter = MutableLiveData<MeteoriteFilter>()
    val filter: LiveData<MeteoriteFilter> = _filter

    private val _chartData = MutableLiveData<List<ChartData>>()
    val chartData: LiveData<List<ChartData>> = _chartData

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    init {
        _filter.value = MeteoriteFilter()
    }

    private fun fetchChartData() {
        _isLoading.value = true
        viewModelScope.launch {
            where = getWhereQuery(_filter.value ?: MeteoriteFilter())
            select = getSelectQuery(chartX)
            group = chartX

            val response = nasaApi.fetchChartData(token, select, where, group)

            Log.i("ChartVM", "\$select=$select&\$where=$where&\$group=$group\n${response.raw()}\n${response.body()?.size}")

            if (response.isSuccessful && response.body() != null) {
                _chartData.value = response.body()

                Log.i("ChartVM", "Fetched chart data: \n${response.raw()}\n${response.body()?.size}")
            } else {
                Log.e("ChartVM", "Error fetching chart data: ${response.errorBody()}")
            }
            _isLoading.value = false
        }
    }

    fun chartSetup(filter: MeteoriteFilter, data: String) {
        chartX = data
        if (_filter.value != filter) {
            _filter.value = filter
            fetchChartData()
        }
    }
}
