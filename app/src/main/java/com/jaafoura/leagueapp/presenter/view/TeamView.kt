package com.jaafoura.leagueapp.presenter.view

import androidx.annotation.StringRes
import com.jaafoura.leagueapp.common.base.BaseView
import com.jaafoura.leagueapp.data.model.League
import com.jaafoura.leagueapp.data.model.Team

/**
 * Interface providing required method for a view displaying teams
 */
interface TeamView : BaseView {
    /**
     * Updates the previous teams by the specified ones
     * @param teams the list of teams that will replace existing ones
     */
    fun updateTeams(teams: List<Team>)

    /**
     * Updates the previous leagues by the specified ones
     * @param leagues the list of teams that will replace existing ones
     */
    fun updateLeagues(leagues: List<League>)


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