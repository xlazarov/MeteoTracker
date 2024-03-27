package com.example.meteortracker.service

import com.example.meteortracker.data.ChartData
import com.example.meteortracker.data.Meteorite
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NasaApiService {

    @GET("resource/y77d-th95.json?")
    suspend fun fetchListData(
        @Query("\$\$app_token") appToken: String,
        @Query("\$limit") limit: Int,
        @Query("\$offset") offset: Int,
        @Query("\$where") whereClause: String
    ): Response<List<Meteorite>>

    @GET("resource/y77d-th95.json?")
    suspend fun fetchMapData(
        @Query("\$\$app_token") appToken: String,
        @Query("\$where") whereClause: String,
    ): Response<List<Meteorite>>

    @GET("resource/y77d-th95.json?")
    suspend fun fetchChartData(
        @Query("\$\$app_token") appToken: String,
        @Query("\$select") selectClause: String,
        @Query("\$where") whereClause: String,
        @Query("\$group") groupClause: String
    ): Response<List<ChartData>>

}
