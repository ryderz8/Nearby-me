package com.app.nearby_me.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.app.nearby_me.R
import com.app.nearby_me.adapter.PlacesAdapter
import com.app.nearby_me.interfaces.ItemClickListener
import com.app.nearby_me.model.network.networkImpl.PlaceSearchNetwork
import com.app.nearby_me.model.pojo.PlaceDTO
import com.app.nearby_me.model.pojo.Result
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


class SearchResultViewModel(private val appContext: Application) : AndroidViewModel(appContext),
    ItemClickListener {

    val adapter = PlacesAdapter(this)
    val goToNext = MutableLiveData<PlaceDTO>()
    val openMap = MutableLiveData<Boolean>()
    val list = MutableLiveData<List<PlaceDTO>>()
    val dataAvailable = MutableLiveData<Boolean>(false)
    private val _message =
        MutableLiveData<String>(appContext.getString(R.string.no_venue_available))
    val message: LiveData<String>
        get() = _message

    private val _isProgressbarVisible = MutableLiveData<Boolean>(false)
    val isProgressbarVisible: LiveData<Boolean>
        get() = _isProgressbarVisible
    private var job = Job()


    fun getDataFromServerBySearchKey(newText: String) {
        _isProgressbarVisible.postValue(true)
        job = viewModelScope.launch {
            val result = PlaceSearchNetwork().getSearchPlaces(newText)
            _isProgressbarVisible.value = false
            when (result) {
                is Result.Success -> {
                    list.value = result.data.venuesDTO.list
                    dataAvailable.value = result.data.venuesDTO.list.isNotEmpty()
                    adapter.submitList(list.value)
                }
                is Result.Error -> _message.value = appContext.getString(R.string.no_data)

            }
        }
    }

    override fun onItemClick(dto: PlaceDTO) {
        goToNext.value = dto
    }

    fun openMap() {
        openMap.value = true
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}