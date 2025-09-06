package Networking

import retrofit2.http.GET

interface BookApiService {

    @GET("recent")
    suspend fun getRecentBooks(): BookResponse
}