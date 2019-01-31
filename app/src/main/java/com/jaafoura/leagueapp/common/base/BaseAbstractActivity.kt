package com.jaafoura.leagueapp.common.base

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.jaafoura.leagueapp.ui.activity.PlayerActivity

abstract class BaseAbstractActivity<D : ViewDataBinding, P : BasePresenter<BaseView>> : BaseView, AppCompatActivity() {

    /**
     * DataBinding instance of the view
     */
    lateinit var binding: D

    /**
     * Presenter instance
     */
    protected lateinit var presenter: P

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, getViewId())
        presenter = instantiatePresenter()
        initializeUI()
    }

    /**
     * init ui
     */
    abstract fun initializeUI()

    /**
     * @return id of the layout
     */
    abstract fun getViewId(): Int

    /**
     * Instantiates the presenter the Activity is based on.
     */
    protected abstract fun instantiatePresenter(): P

    override fun getContext(): Context {
        return this
    }

    fun goForward(strLeague: String) {
        val intent = Intent(this@BaseAbstractActivity, PlayerActivity::class.java)
        intent.putExtra("strLeague", strLeague)
        startActivity(intent)
    }
}