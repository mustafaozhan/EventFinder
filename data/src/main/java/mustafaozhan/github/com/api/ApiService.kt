/*
 Copyright (c) 2020 Mustafa Ozhan. All rights reserved.
 */
package mustafaozhan.github.com.api

import mustafaozhan.github.com.model.EventsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("discovery/v2/events.json")
    suspend fun getEvents(
        @Query("page") pageNumber: String,
        @Query("size") size: String,
        @Query("apikey") key: String
    ): EventsResponse
}
