package com.app.nearby_me.model.network.networkImpl

import com.app.nearby_me.BuildConfig
import com.app.nearby_me.constant.Constant
import com.app.nearby_me.model.network.client.WebServiceClient
import com.app.nearby_me.model.network.interfaces.ApiInterface
import com.app.nearby_me.model.pojo.ResponseDTO
import com.app.nearby_me.model.pojo.Result
import java.io.IOException

class PlaceSearchNetwork {

    suspend fun getSearchPlaces(newText: String): Result<ResponseDTO> {
        val map = HashMap<String, String>()
        map["client_id"] = BuildConfig.FOURSQUARE_CLIENT_ID
        map["client_secret"] = BuildConfig.FOURSQUARE_CLIENT_SECRET
        map["near"] = Constant.CENTER_LOCATION_NAME
        map["query"] = newText
        map["v"] = Constant.VERSION_NAME
        map["limit"] = Constant.RESULT_LIMIT
        val apiInterface = WebServiceClient.createRequest(ApiInterface::class.java)
        val response = apiInterface.getSearchResult(map)
        return if (response.isSuccessful) {
            Result.Success(response.body()!!)
        } else {
            Result.Error(IOException("Error occurred during fetching places!"))
        }
    }
}