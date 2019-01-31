package com.jaafoura.leagueapp.presenter

import com.jaafoura.leagueapp.common.base.BasePresenter
import com.jaafoura.leagueapp.network.GlobalApi
import com.jaafoura.leagueapp.presenter.view.PlayerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject


/**
 * The Presenter that will present the Player view.
 * @param playerView the Player view to be presented by the presenter
 * @property GlobalApi the API interface implementation
 */
class PlayerPresenter(playerView: PlayerView) : BasePresenter<PlayerView>(playerView) {

    @Inject
    lateinit var globalApi: GlobalApi

    override fun onViewCreated() {
    }

    /**
     * Loads the player from the API and presents them in the view when retrieved, or showss error if
     * any.
     */
    fun loadPlayers(value: String) {
        view.showLoading()
        GlobalScope.launch(Dispatchers.Main) {
            val request = globalApi.getPlayersAsync(value)
            try {
                val response = request.await()
                response.body()?.let {
                    view.updatePlayer(it.player)
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