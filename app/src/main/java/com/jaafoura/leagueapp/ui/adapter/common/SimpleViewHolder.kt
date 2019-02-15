package com.jaafoura.leagueapp.ui.adapter.common

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

import com.jaafoura.leagueapp.BR

class SimpleViewHolder(private val binding: ViewDataBinding) : androidx.recyclerview.widget.RecyclerView.ViewHolder(binding.root) {

    fun bind(obj: Any) {
        binding.setVariable(BR.result, obj)
        binding.executePendingBindings()
    }
}
