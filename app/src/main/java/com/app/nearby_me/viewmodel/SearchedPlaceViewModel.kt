package com.app.nearby_me.viewmodel

import androidx.lifecycle.ViewModel
import com.app.nearby_me.model.pojo.PlaceDTO

class SearchedPlaceViewModel : ViewModel() {
    private var mapList = mutableListOf<PlaceDTO>()
    private lateinit var placeDTO: PlaceDTO

    fun getList() = mapList
    fun setList(list: List<PlaceDTO>?) {
        list?.run {
            mapList = list.toMutableList()
        }
    }

    fun getPlace(): PlaceDTO {
        return placeDTO
    }

    fun setPlace(placeDTO: PlaceDTO) {
        this.placeDTO = placeDTO
    }
}