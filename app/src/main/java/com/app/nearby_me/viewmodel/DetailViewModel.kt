package com.app.nearby_me.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.nearby_me.constant.Constant
import com.app.nearby_me.model.pojo.PlaceDTO

class DetailViewModel : ViewModel() {

    var placeDTO = MutableLiveData<PlaceDTO>()
    var url = MutableLiveData<String>()


    fun setWebURL(latitude: Double, longitude: Double) {
        url.value =
            "https://maps.googleapis.com/maps/api/staticmap?center=Seattle,+WA&zoom=13&size=600x300&maptype=roadmap\n" +
                    "&markers=color:blue%7Clabel:S%7C${Constant.CENTER_LOCATION_LAT_LANG}&markers=color:red%7Clabel:G%7C$latitude,$longitude\n" +
                    "&key=AIzaSyAspzNhbGwrcbKEHTywTgRRFEqrXZekRf8"
    }
}