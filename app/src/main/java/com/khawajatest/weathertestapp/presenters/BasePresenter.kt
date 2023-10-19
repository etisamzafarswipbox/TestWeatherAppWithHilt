package com.khawajatest.weathertestapp.presenters

import com.khawajatest.weathertestapp.interfaces.WeatherView

interface BaseView

open class BasePresenter<V : WeatherView> {
    protected var view: V? = null

    fun attachView(view: V) {
        this.view = view
    }

    fun detachView() {
        this.view = null
    }
}
