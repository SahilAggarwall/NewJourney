package com.fretron.repositories

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fretron.avro.*
import com.mongodb.BasicDBObject
import org.bson.Document
import com.mongodb.client.MongoCollection
import com.mongodb.client.MongoDatabase
import javax.inject.*

class UserRepository @Inject constructor(private val database: MongoDatabase) {

    private val collection: MongoCollection<Document> = database.getCollection("users")
    private val mapper = jacksonObjectMapper()

    fun saveUser(user: User) {
        val doc = Document.parse(user.toString())
        collection.insertOne(doc)
    }

    fun getUserByEmail(email: String): User? {
        val query = Document("email", email)
        val result = collection.find(query).first() ?: return null
        result.remove("_id")
        val json = result.toJson()
        return mapper.readValue(json, User::class.java)
    }

    fun getAllUsers(): List<User> {
        return collection.find().map { document ->
            document.remove("_id")
            val json = document.toJson()
            mapper.readValue(json, User::class.java)
        }.toList()
    }

    fun updateUser(email: String, updatedUserJson: String): Boolean {
        val query = Document("email", email)
        val objectMapper = ObjectMapper()
        val updatedUser = objectMapper.readValue(updatedUserJson, User::class.java)
        val update = Document("\$set", BasicDBObject().apply {
            if (updatedUser.name != null) append("name", updatedUser.name)
            if (updatedUser.email != null) append("email", updatedUser.email)
            if (updatedUser.password != null) append("password", updatedUser.password)
            if (updatedUser.role != null) append("role", updatedUser.role)
        })
        val result = collection.updateOne(query, update)
        return result.modifiedCount > 0
    }


    fun deleteUser(email: String): Boolean {
        val query = Document("email", email)
        val result = collection.deleteOne(query)
        return result.deletedCount > 0
    }
}
