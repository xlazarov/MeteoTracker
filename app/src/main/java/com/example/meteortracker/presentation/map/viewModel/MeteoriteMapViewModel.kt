package com.example.meteortracker.presentation.map.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.meteortracker.data.Meteorite
import com.example.meteortracker.data.MeteoriteFilter
import com.example.meteortracker.service.NasaApiService
import com.example.meteortracker.service.getWhereQuery
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MeteoriteMapViewModel @Inject constructor(
    private val nasaApi: NasaApiService
) : ViewModel() {

    private val token = "WmoOZLhIHEEFG028ZyGpizTST"
    private var where: String = ""

    private val _filter = MutableLiveData<MeteoriteFilter>()
    val filter: LiveData<MeteoriteFilter> = _filter

    private val _mapData = MutableLiveData<List<Meteorite>>()
    val mapData: LiveData<List<Meteorite>> = _mapData

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    init {
        _filter.value = MeteoriteFilter(yearFrom = "2000", yearTo = "2024")
        fetchMapData()
    }

    private fun fetchMapData() {
        _isLoading.value = true
        viewModelScope.launch {
            where = getWhereQuery(_filter.value ?: MeteoriteFilter())

            val response = nasaApi.fetchMapData(token, where)
            if (response.isSuccessful && response.body() != null) {
                _mapData.value = response.body()
            } else {
                Log.e("MapVM", "Error fetching map data: ${response.errorBody()}")
            }
            _isLoading.value = false
        }
    }

    fun setFilter(newFilter: MeteoriteFilter) {
        if (_filter.value != newFilter) {
            _filter.value = newFilter
            fetchMapData()
        }
    }

    fun clearFilter() {
        _filter.value = MeteoriteFilter()
        fetchMapData()
    }
}
