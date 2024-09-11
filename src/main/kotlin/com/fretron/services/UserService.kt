package com.fretron.services

import com.fretron.avro.*
import com.fretron.repositories.UserRepository
import com.fretron.utils.JWTUtil
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserService @Inject constructor(
    private val userRepository: UserRepository
) {

    fun signup(user: User): String {
        if (user.role == "ADMIN") {
            return ("You can't signup as ADMIN")
        }

        if (userRepository.getUserByEmail(user.email.toString()) != null) {
            return ("User with email ${user.email} already exists!")
        }
        userRepository.saveUser(user)
        return ("User Created + ${user.email} successfully")
    }

    fun login(credentials: User): String {
        val user =
            userRepository.getUserByEmail(credentials.email.toString()) ?: return ("Invalid email or password!")

        if (user.password != credentials.password) {
            return ("Invalid email or password!")
        }

        val token = JWTUtil.generateToken(user)
        return ("token:($token)")
    }

    fun createUser(token: String?, user: User): String {
        if (token == null || !JWTUtil.validateToken(token)) {
            return ("Invalid token")
        }

        val userRole = JWTUtil.getRoleFromToken(token)
        if (userRole != "ADMIN") {
            return ("Only ADMIN can create users")
        }

        if (userRepository.getUserByEmail(user.email.toString()) != null) {
            return ("User with email ${user.email} already exists!")
        }

        userRepository.saveUser(user)
        return ("User created successfully!")
    }

    fun getAllUsers(token: String?): String {
        if (token == null || !JWTUtil.validateToken(token)) {
            return ("Invalid token")
        }

        val userRole = JWTUtil.getRoleFromToken(token)
        if (userRole != "ADMIN") {
            return ("Only ADMIN can view users")
        }

        val users = userRepository.getAllUsers()
        return ("$users")
    }

    fun updateUser(token: String?, email: String, updatedUser: String): String {
        if (token == null || !JWTUtil.validateToken(token)) {
            return ("Invalid token")
        }

        val userRole = JWTUtil.getRoleFromToken(token)
        if (userRole != "ADMIN") {
            return ("Only ADMIN can update users")
        }
        val result = userRepository.updateUser(email, updatedUser)
        return if (result) {
            ("User updated successfully!")
        } else {
            ("User not found")
        }
    }

    fun deleteUser(token: String?, email: String): String {
        if (token == null || !JWTUtil.validateToken(token)) {
            return ("Invalid token")
        }

        val userRole = JWTUtil.getRoleFromToken(token)
        if (userRole != "ADMIN") {
            return ("Only ADMIN can delete users")
        }

        userRepository.getUserByEmail(email) ?: return ("User not found")

        userRepository.deleteUser(email)
        return ("User deleted successfully")
    }
}
