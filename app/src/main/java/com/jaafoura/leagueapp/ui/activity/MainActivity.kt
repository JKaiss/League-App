package com.jaafoura.leagueapp.ui.activity

import android.content.Intent
import android.support.v7.widget.GridLayoutManager
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.jaafoura.leagueapp.R
import com.jaafoura.leagueapp.common.base.BaseAbstractActivity
import com.jaafoura.leagueapp.data.model.League
import com.jaafoura.leagueapp.data.model.Team
import com.jaafoura.leagueapp.databinding.ActivityMainBinding
import com.jaafoura.leagueapp.presenter.TeamPresenter
import com.jaafoura.leagueapp.presenter.view.TeamView
import com.jaafoura.leagueapp.ui.adapter.SimpleLayoutAdapter
import com.jaafoura.leagueapp.ui.adapter.common.SimpleBaseAdapter

class MainActivity : BaseAbstractActivity<ActivityMainBinding, TeamPresenter>(), TeamView, SimpleBaseAdapter.OnItemClickListener<Team> {

    //region Click from list

    override fun onItemClick(item: Team) {
        goForward(item.strTeam)
    }

    //endregion


    //region Presenter view method

    override fun updateLeagues(leagues: List<League>) {

        // Initialize a new array adapter object
        val adapter = initAdapter(leagues)

        // Set the AutoCompleteTextView adapter
        binding.autoCompleteTextView.setAdapter(adapter)


        // Set an item click listener for auto complete text view
        binding.autoCompleteTextView.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            val selectedItem = parent.getItemAtPosition(position).toString()
            // Display the clicked item using toast
            presenter.loadTeams(selectedItem)
        }


        // Set a focus change listener for auto complete text view
        binding.autoCompleteTextView.onFocusChangeListener = View.OnFocusChangeListener { view, b ->
            if (b) {
                // Display the suggestion dropdown on focus
                binding.autoCompleteTextView.showDropDown()
            }
        }

    }

    private fun initAdapter(leagues: List<League>): ArrayAdapter<String> {
        val leaguesArray = Array(leagues.size) { "$it" }
        var i = 0
        leagues.forEach {
            leaguesArray[i++] = it.strLeague
        }
        return ArrayAdapter<String>(
                this, // Context
                android.R.layout.simple_dropdown_item_1line, // Layout
                leaguesArray)
    }


    override fun updateTeams(teams: List<Team>) {
        binding.recyclerViewTeams.layoutManager = GridLayoutManager(this, 2)
        binding.recyclerViewTeams.adapter = object : SimpleLayoutAdapter(R.layout.holder_team, this as OnItemClickListener<Any>) {
            override fun getObjForPosition(position: Int): Any {
                return teams[position]
            }

            override fun getItemCount(): Int {
                return teams.size
            }
        }
    }

    override fun instantiatePresenter(): TeamPresenter {
        return TeamPresenter(this)
    }

    override fun showError(error: String) {
        binding.tvError.visibility = VISIBLE
        binding.tvError.text = getString(R.string.error_message)
    }

    override fun showLoading() {
        binding.progressCircular.visibility = VISIBLE
        binding.tvError.visibility = GONE
    }

    override fun hideLoading() {
        binding.progressCircular.visibility = GONE
    }

    //endregion


    //region Base Activity

    override fun initializeUI() {
        presenter.onViewCreated()
    }

    override fun getViewId(): Int {
        return R.layout.activity_main
    }

    //endregion
    private fun goForward(strLeague: String) {
        val intent = Intent(this, PlayerActivity::class.java)
        intent.putExtra("strLeague", strLeague)
        startActivity(intent)
    }

}
