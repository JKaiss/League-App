package com.jaafoura.leagueapp.ui.adapter

import com.jaafoura.leagueapp.ui.adapter.common.SimpleBaseAdapter

abstract class SimpleLayoutAdapter(private val layoutId: Int, itemClickListener: OnItemClickListener<Any>) : SimpleBaseAdapter<Any>(itemClickListener) {

    override fun getLayoutIdForPosition(position: Int): Int {
        return layoutId
    }
}
