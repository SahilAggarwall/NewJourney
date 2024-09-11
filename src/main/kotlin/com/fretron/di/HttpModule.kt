package com.fretron.di

import dagger.*
import javax.inject.*
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory
import org.glassfish.grizzly.http.server.HttpServer
import org.glassfish.jersey.server.ResourceConfig
import com.fretron.resources.*
import java.net.URI

@Module
class HttpModule {

    @Provides
    @Singleton
    fun provideResourceConfig(userResources: UserResources): ResourceConfig {
        return ResourceConfig()
            .register(userResources)
    }

    @Provides
    @Singleton
    fun provideHttpServer(resourceConfig: ResourceConfig, @Named(PORT)port: Int): HttpServer {
        // Create and configure Grizzly HTTP server
        val uri = URI.create("http://localhost:$port/")
        val server = GrizzlyHttpServerFactory.createHttpServer(uri, resourceConfig)
        server.start()
        return server
    }
}
