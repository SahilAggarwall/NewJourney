package com.fretron

import com.fretron.config.Context
import com.fretron.di.AppComponent
import com.fretron.di.DaggerAppComponent

fun main() {
    Context.init(arrayOf("src/main/kotlin/resources/Local.xml"))
    val appComponent: AppComponent = DaggerAppComponent.create()
    appComponent.httpServer()
    appComponent.emailConsumer().startConsuming()
    println("Server Started on http://localhost:8080/")
}
