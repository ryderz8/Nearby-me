package com.app.nearby_me.model.network.interfaces

import com.app.nearby_me.constant.APIConstant
import com.app.nearby_me.model.pojo.ResponseDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap


interface ApiInterface {

    @GET(APIConstant.FORSQUARE_VENUE_SEARCH_API)
    suspend fun getSearchResult(@QueryMap params: Map<String, String>): Response<ResponseDTO>
}