package com.jaafoura.leagueapp.ui.activity

import androidx.recyclerview.widget.LinearLayoutManager
import android.util.Log
import android.view.View.GONE
import android.view.View.VISIBLE
import com.jaafoura.leagueapp.R
import com.jaafoura.leagueapp.common.base.BaseAbstractActivity
import com.jaafoura.leagueapp.data.model.Player
import com.jaafoura.leagueapp.databinding.ActivityPlayerBinding
import com.jaafoura.leagueapp.presenter.PlayerPresenter
import com.jaafoura.leagueapp.presenter.view.PlayerView
import com.jaafoura.leagueapp.ui.adapter.SimpleLayoutAdapter
import com.jaafoura.leagueapp.ui.adapter.common.SimpleBaseAdapter

class PlayerActivity : BaseAbstractActivity<ActivityPlayerBinding, PlayerPresenter>(), PlayerView, SimpleBaseAdapter.OnItemClickListener<Player> {
    override fun onItemClick(item: Player) {
        Log.e("click", "empty")
    }

    override fun updatePlayer(players: List<Player>) {
        binding.recyclerViewPlayers.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        binding.recyclerViewPlayers.adapter = object : SimpleLayoutAdapter(R.layout.holder_player, this as OnItemClickListener<Any>) {
            override fun getObjForPosition(position: Int): Any {
                return players[position]
            }

            override fun getItemCount(): Int {
                return players.size
            }
        }
    }

    //region Presenter view method

    override fun instantiatePresenter(): PlayerPresenter {
        return PlayerPresenter(this)
    }

    override fun showError(error: String) {
        binding.tvError.visibility = VISIBLE
        binding.tvError.text = getString(R.string.error_message)
        binding.recyclerViewPlayers.visibility = GONE
    }

    override fun showLoading() {
        binding.progressCircular.visibility = VISIBLE
        binding.tvError.visibility = GONE
        binding.recyclerViewPlayers.visibility = GONE

    }

    override fun hideLoading() {
        binding.progressCircular.visibility = GONE
        binding.recyclerViewPlayers.visibility = VISIBLE
    }

    //endregion


    //region Activity

    override fun initializeUI() {
        val profileName = intent.getStringExtra("strLeague")
        presenter.loadPlayers(profileName)
    }

    override fun getViewId(): Int {
        return com.jaafoura.leagueapp.R.layout.activity_player
    }

    //endregion

}