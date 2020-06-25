package com.app.nearby_me.model.pojo

import android.location.Location
import com.google.gson.annotations.SerializedName

data class PlaceDTO(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("location") val locationDTO: LocationDTO,
    @SerializedName("categories") val categoryDTO: List<CategoryDTO>,
    @SerializedName("stats") val statsDTO: StatsDTO,
    @SerializedName("contact") val contactDTO: ContactDTO?
) {
    fun getCategoryName(): String {
        return if (categoryDTO.isNotEmpty()) categoryDTO[0].name else "N/A"
    }

    fun getImageURL(): String {
        return if (categoryDTO.isNotEmpty()) categoryDTO[0].icon.toString() else ""
    }

    fun getDistance(): String {
        val centerLocation = Location("Seatle")
        centerLocation.latitude = 47.6062
        centerLocation.longitude = -122.3321
        val placeLocation = Location("PlaceLocation")
        placeLocation.latitude = locationDTO.latitude
        placeLocation.longitude = locationDTO.longitude
        val miles = "%.2f".format((centerLocation.distanceTo(placeLocation)) * 0.000621371192)
        return "$miles Mile from Seatle"
    }

    fun getAddress(): String {
        return if (locationDTO.address.isNotEmpty()) locationDTO.address.joinToString(",") else "N/A"
    }

    fun getPhoneNumber(): String {
        return if (contactDTO == null || contactDTO.phoneNumber.isNullOrEmpty()) "N/A" else contactDTO.phoneNumber
    }

    fun getTwitter(): String {
        return if (contactDTO == null || contactDTO.twitter.isNullOrEmpty()) "N/A" else contactDTO.twitter
    }
}

data class LocationDTO(
    @SerializedName("formattedAddress") val address: List<String>,
    @SerializedName("lat") val latitude: Double,
    @SerializedName("lng") val longitude: Double
)


data class CategoryDTO(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("icon") val icon: IconDTO
)


data class IconDTO(
    @SerializedName("prefix") val prefix: String,
    @SerializedName("suffix") val suffix: String
) {
    override fun toString(): String {
        return "${prefix}bg_88$suffix"
    }
}


data class ContactDTO(
    @SerializedName("formattedPhone") val phoneNumber: String?,
    @SerializedName("twitter") val twitter: String?
)


data class StatsDTO(
    @SerializedName("tipCount") val tipCount: String,
    @SerializedName("usersCount") val userCount: String,
    @SerializedName("checkinsCount") val checkInCount: String,
    @SerializedName("visitsCount") val visitCount: String
)

