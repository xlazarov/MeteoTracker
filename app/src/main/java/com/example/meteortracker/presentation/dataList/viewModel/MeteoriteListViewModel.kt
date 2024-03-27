package com.example.meteortracker.presentation.dataList.viewModel

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
class MeteoriteListViewModel @Inject constructor(
    private val nasaApi: NasaApiService
) : ViewModel() {

    private val token = "WmoOZLhIHEEFG028ZyGpizTST"
    private var offset = 0
    private var limit = 50
    private var where: String = ""

    private val _filter = MutableLiveData<MeteoriteFilter>()
    val filter: LiveData<MeteoriteFilter> = _filter

    private val _dataList = MutableLiveData<List<Meteorite>>()
    val dataList: LiveData<List<Meteorite>> = _dataList

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    init {
        fetchDataList()
        _filter.value = MeteoriteFilter()
    }

    fun fetchDataList() {
        viewModelScope.launch {
            _isLoading.value = true

            val response = nasaApi.fetchListData(token, limit, offset, where)
            if (response.isSuccessful && response.body() != null) {
                val currentData = _dataList.value ?: emptyList()
                _dataList.value = currentData + response.body()!!
                offset += limit

                Log.i("fetchData", "Fetched ${response.body()!!.size} meteorites")
            } else {
                Log.e("fetchData", "Error fetching meteorite data: ${response.errorBody()}")
            }
            _isLoading.value = false
        }
    }

    fun fetchByFilter() {
        val newQuery = getWhereQuery(_filter.value!!)

        where = newQuery
        offset = 0
        limit = 50
        _dataList.value = emptyList()

        fetchDataList()
    }

    fun setFilter(filter: MeteoriteFilter) {
        _filter.value = filter
    }

    fun clearFilter() {
        where = ""
        _filter.value = MeteoriteFilter()
        _dataList.value = emptyList()
        offset = 0
        limit = 50

        fetchDataList()
    }
}