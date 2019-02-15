package com.jaafoura.leagueapp.ui.adapter.common

import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup


abstract class SimpleBaseAdapter<T> internal constructor(private val itemClickListener: OnItemClickListener<T>) : androidx.recyclerview.widget.RecyclerView.Adapter<SimpleViewHolder>() {

    interface OnItemClickListener<T> {
        fun onItemClick(item: T)
    }

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): SimpleViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ViewDataBinding>(
                layoutInflater, viewType, parent, false)
        return SimpleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SimpleViewHolder,
                                  position: Int) {
        val obj = getObjForPosition(position)
        holder.itemView.setOnClickListener { itemClickListener!!.onItemClick(obj) }
        holder.bind(obj as Any)
    }

    override fun getItemViewType(position: Int): Int {
        return getLayoutIdForPosition(position)
    }

    protected abstract fun getObjForPosition(position: Int): T

    protected abstract fun getLayoutIdForPosition(position: Int): Int
}
