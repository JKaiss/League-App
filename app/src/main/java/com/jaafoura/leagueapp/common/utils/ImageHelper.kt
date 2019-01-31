package com.jaafoura.leagueapp.common.utils

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.request.RequestOptions
import com.jaafoura.leagueapp.R


@BindingAdapter("imageUrl")
fun ImageView.setImageUrl(strTeamBadge: String?) {
    val options = RequestOptions()
            .centerCrop()
            .placeholder(R.drawable.ic_default_avatar)
            .error(R.drawable.ic_default_avatar)
            .priority(Priority.HIGH)

    Glide.with(context)
            .load(strTeamBadge)
            .apply(options)
            .into(this)
}