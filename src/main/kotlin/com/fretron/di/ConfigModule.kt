package com.fretron.di

import com.fretron.config.Context
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class ConfigModule {

    @Provides
    @Named(MONGO_CONNECT)
    @Singleton
    fun provideMongoConnectionString(): String {
        val connectionString = Context.getConfig()?.getString(MONGO_CONNECT)
        return connectionString ?: throw IllegalArgumentException("MongoDB connection string is not provided in configuration")
    }

    @Provides
    @Named(DB_NAME)
    @Singleton
    fun provideDatabaseName(): String {
        val dbName = Context.getConfig()?.getString(DB_NAME)
        return dbName ?: throw IllegalArgumentException("Database name is not provided in configuration")
    }

    @Provides
    @Named(PORT)
    @Singleton
    fun provideHttpPort(): Int {
        val port = Context.getConfig()?.getString(PORT)?.toIntOrNull()
        return port ?: throw IllegalArgumentException("Port number is not provided or is invalid in configuration")
    }
}
