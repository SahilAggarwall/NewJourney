package com.fretron.di

import com.fretron.services.EmailConsumer
import com.fretron.services.SignupProducer
import dagger.Component
import org.glassfish.grizzly.http.server.HttpServer
import javax.inject.Singleton

@Singleton
@Component(modules = [HttpModule::class, DatabaseModule::class, ConfigModule::class, KafkaModule::class])
interface AppComponent {
    fun httpServer(): HttpServer
    fun signupProducer(): SignupProducer
    fun emailConsumer(): EmailConsumer
}
