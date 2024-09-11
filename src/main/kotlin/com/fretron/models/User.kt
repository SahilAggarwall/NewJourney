package com.fretron.models

import org.bson.Document
import org.bson.types.ObjectId

data class User(
    val name: String?=null,
    val email: String,
    val password: String?=null,
    val role: Role?=null
) {
    enum class Role {
        ADMIN, NON_ADMIN
    }
//
//    // Convert User to Document
//    fun toDocument(): Document {
//        return Document().apply {
//            append("_id", ObjectId())
//            append("name", this@User.name)
//            append("email", this@User.email)
//            append("password", this@User.password)
//            append("role", this@User.role.toString())
//        }
//    }
//
//    // Convert Document to User
//    companion object {
//        fun fromDocument(doc: Document): User {
//            return User(
//                name = doc.getString("name"),
//                email = doc.getString("email"),
//                password = doc.getString("password"),
//                role = User.Role.valueOf(doc.getString("role"))
//            )
//        }
//    }
}

data class UserUpdate(
    val name: String? = null,
    val email: String? = null,
    val password: String? = null,
    val role: Role? = null
) {
    enum class Role {
        ADMIN, NON_ADMIN
    }
}
