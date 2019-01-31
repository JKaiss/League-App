package com.jaafoura.leagueapp.ui.adapter.common

import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView

import com.jaafoura.leagueapp.BR

class SimpleViewHolder(private val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(obj: Any) {
        binding.setVariable(BR.result, obj)
        binding.executePendingBindings()
    }
}
