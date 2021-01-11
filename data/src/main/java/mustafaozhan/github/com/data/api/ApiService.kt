/*
 Copyright (c) 2020 Mustafa Ozhan. All rights reserved.
 */
package mustafaozhan.github.com.data.api

import mustafaozhan.github.com.data.model.EventsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("discovery/v2/events.json")
    suspend fun getEvents(
        @Query("number") pageNumber: String,
        @Query("size") size: String,
        @Query("apikey") key: String
    ): EventsResponse
}
