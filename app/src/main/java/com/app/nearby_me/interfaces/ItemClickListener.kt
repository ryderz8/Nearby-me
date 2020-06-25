package com.app.nearby_me.interfaces

import com.app.nearby_me.model.pojo.PlaceDTO

interface ItemClickListener {

    fun onItemClick(dto: PlaceDTO)
}