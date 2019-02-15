package com.jaafoura.leagueapp.data.model

import androidx.databinding.BindingAdapter
import android.widget.ImageView
import com.bumptech.glide.Glide


data class Team(val idTeam: String
                , val strTeam: String
                , val intFormedYear: String
                , val strSport: String
                , val strLeague: String
                , val strStadium: String
                , val strTeamBadge: String)