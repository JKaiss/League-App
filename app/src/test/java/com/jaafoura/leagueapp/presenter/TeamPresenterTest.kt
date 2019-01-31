package com.jaafoura.leagueapp.presenter

import com.google.common.truth.Truth.assertThat
import com.jaafoura.leagueapp.network.GlobalApi
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.SocketPolicy.DISCONNECT_AFTER_REQUEST
import org.junit.Assert.fail
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.IOException

class TeamPresenterTest {
    @get:Rule
    val server = MockWebServer()

    private lateinit var service: GlobalApi

    @Before
    fun setUp() {
        val retrofit = Retrofit.Builder()
                .baseUrl(server.url("/"))
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build()
        service = retrofit.create(GlobalApi::class.java)
    }

    @Test
    fun responseSuccess() = runBlocking {

        server.enqueue(MockResponse().setBody("{\"teams\":null}"))

        val deferred = service.getAllTeamsAsync("")
        assertThat(deferred.await().body()).isEqualTo("{\"teams\":null}")
    }

    @Test
    fun responseFailure() = runBlocking {
        server.enqueue(MockResponse().setSocketPolicy(DISCONNECT_AFTER_REQUEST))

        val deferred = service.getAllLeaguesAsync()
        try {
            deferred.await()
            fail()
        } catch (e: IOException) {
        }
    }
}