package com.fretron.di

import com.mongodb.client.MongoClient
import com.mongodb.client.MongoClients
import com.mongodb.client.MongoDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class DatabaseModule {


    @Provides
    @Singleton
    fun provideMongoClient(@Named(MONGO_CONNECT) connectionString: String): MongoClient {
        return MongoClients.create(connectionString)
    }

    @Provides
    @Singleton
    fun provideDatabase(mongoClient: MongoClient, @Named(DB_NAME) dbName: String): MongoDatabase {
        return mongoClient.getDatabase(dbName)
    }

}
