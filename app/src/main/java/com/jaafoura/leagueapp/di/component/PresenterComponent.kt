package com.jaafoura.leagueapp.di.component

import com.jaafoura.leagueapp.common.base.BaseView
import com.jaafoura.leagueapp.di.module.GlobalModule
import com.jaafoura.leagueapp.di.module.RemoteModule
import com.jaafoura.leagueapp.presenter.PlayerPresenter
import com.jaafoura.leagueapp.presenter.TeamPresenter
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

/**
 * Component providing inject() methods for presenters.
 */
@Singleton
@Component(modules = [(GlobalModule::class), (RemoteModule::class)])
interface PresenterComponent {
    /**
     * Injects required dependencies into the specified TeamPresenter.
     * @param teamPresenter TeamPresenter in which to inject the dependencies
     */
    fun inject(teamPresenter: TeamPresenter)

    /**
     * Injects required dependencies into the specified PlayerPresenter.
     * @param playerPresenter PlayerPresenter in which to inject the dependencies
     */
    fun inject(playerPresenter: PlayerPresenter)


    @Component.Builder
    interface Builder {
        fun build(): PresenterComponent

        fun remoteModule(remoteModule: RemoteModule): Builder
        fun globalModule(globalModule: GlobalModule): Builder

        @BindsInstance
        fun baseView(baseView: BaseView): Builder
    }
}