package com.app.nearby_me.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.nearby_me.constant.Constant
import com.app.nearby_me.model.pojo.PlaceDTO

class DetailViewModel : ViewModel() {

    var placeDTO = MutableLiveData<PlaceDTO>()
    var url = MutableLiveData<String>()


    fun setWebURL(latitude: Double, longitude: Double) {
//        url.value =
//            "https://maps.googleapis.com/maps/api/staticmap?center=Seattle,+WA&zoom=13&size=600x300&maptype=roadmap\n" +
//                    "&markers=color:blue%7Clabel:S%7C${Constant.CENTER_LOCATION_LAT_LANG}&markers=color:red%7Clabel:G%7C$latitude,$longitude\n" +
//                    "&key=AIzaSyA7j9adG9qTML49VUJnyRUkHx88uUXyfAw"
        url.value = "https://api.mapbox.com/styles/v1/mapbox/streets-v11/static/pin-s-a+9ed4bd(-122.46589,37.77343),pin-s-b+000(-122.42816,37.75965),path-5+f44-0.5(%7DrpeFxbnjVsFwdAvr@cHgFor@jEmAlFmEMwM_FuItCkOi@wc@bg@wBSgM)/auto/500x300?access_token=${Constant.MAPBOX_TOKEN}"
    }
}