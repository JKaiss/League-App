package com.jaafoura.leagueapp.presenter

import com.jaafoura.leagueapp.common.base.BasePresenter
import com.jaafoura.leagueapp.network.GlobalApi
import com.jaafoura.leagueapp.presenter.view.TeamView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

/**
 * The Presenter that will present the Team view.
 * @param teamView the Team view to be presented by the presenter
 * @property GlobalApi the API interface implementation
 */
class TeamPresenter(teamView: TeamView) : BasePresenter<TeamView>(teamView) {

    @Inject
    lateinit var globalApi: GlobalApi

    override fun onViewCreated() {
        loadLeagues()
    }

    /**
     * Loads the teams from the API and presents them in the view when retrieved, or shows error if
     * any.
     */
    fun loadTeams(value: String) {
        view.showLoading()
        GlobalScope.launch(Dispatchers.Main) {
            val request = globalApi.getAllTeamsAsync(value)
            try {
                val response = request.await()
                response.body()?.let {
                    view.updateTeams(it.teams)
                    view.hideLoading()
                }
            } catch (e: HttpException) {
                view.showError(e.message.toString())
                view.hideLoading()
            } catch (e: Throwable) {
                view.showError(e.message.toString())
                view.hideLoading()
            }
        }
    }

    /**
     * Loads the leagues from the API and presents them in the view when retrieved, or showss error if
     * any.
     */
    fun loadLeagues() {
        view.showLoading()
        GlobalScope.launch(Dispatchers.Main) {
            val request = globalApi.getAllLeaguesAsync()
            try {
                val response = request.await()
                response.body()?.let {
                    view.updateLeagues(it.leagues)
                    view.hideLoading()
                }
            } catch (e: HttpException) {
                view.showError(e.message.toString())
                view.hideLoading()
            } catch (e: Throwable) {
                view.showError(e.message.toString())
                view.hideLoading()
            }
        }
    }
}