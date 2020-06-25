package com.app.nearby_me.model.pojo

import com.google.gson.annotations.SerializedName

data class ResponseDTO(
    @SerializedName("meta") val metaDTO: MetaDTO,
    @SerializedName("response") val venuesDTO: VenuesDTO

)

data class MetaDTO(
    @SerializedName("code") val code: Int,
    @SerializedName("requestId") val requestId: String
)

data class VenuesDTO(@SerializedName("venues") val list: List<PlaceDTO>)