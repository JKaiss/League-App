package com.jaafoura.leagueapp.presenter.view

import androidx.annotation.StringRes
import com.jaafoura.leagueapp.common.base.BaseView
import com.jaafoura.leagueapp.data.model.League
import com.jaafoura.leagueapp.data.model.Player
import com.jaafoura.leagueapp.data.model.Team

/**
 * Interface providing required method for a view displaying teams
 */
interface PlayerView : BaseView {
    /**
     * Updates the previous players by the specified ones
     * @param players the list of teams that will replace existing ones
     */
    fun updatePlayer(players: List<Player>)

    /**
     * Displays an error in the view
     * @param error the error to display in the view
     */
    fun showError(error: String)

    /**
     * Displays an error in the view
     * @param errorResId the resource id of the error to display in the view
     */
    fun showError(@StringRes errorResId: Int) {
        this.showError(getContext().getString(errorResId))
    }

    /**
     * Displays the loading indicator of the view
     */
    fun showLoading()

    /**
     * Hides the loading indicator of the view
     */
    fun hideLoading()
}