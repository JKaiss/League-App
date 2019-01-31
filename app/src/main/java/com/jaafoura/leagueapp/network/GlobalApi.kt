package com.jaafoura.leagueapp.network

import com.jaafoura.leagueapp.data.model.ResponseLeagues
import com.jaafoura.leagueapp.data.model.ResponsePlayers
import com.jaafoura.leagueapp.data.model.ResponseTeam
import kotlinx.coroutines.Deferred

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GlobalApi {
    @GET("searchplayers.php")
    fun getPlayersAsync(@Query("t") value: String): Deferred<Response<ResponsePlayers>>

    @GET("search_all_teams.php")
    fun getAllTeamsAsync(@Query("l") value: String): Deferred<Response<ResponseTeam>>


    @GET("all_leagues.php")
    fun getAllLeaguesAsync(): Deferred<Response<ResponseLeagues>>
}