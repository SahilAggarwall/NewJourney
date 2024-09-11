package com.fretron.resources

import javax.ws.rs.*
import javax.ws.rs.core.*
import com.fretron.avro.*
import com.fretron.services.UserService
import javax.inject.Inject

@Path("/api")
class UserResources @Inject constructor(
    private val userService: UserService
) {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    fun helloWorld(): String {
        return "Hello, World!"
    }

    @POST
    @Path("/signup")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    fun signup(user: User): String {
        return userService.signup(user)
    }

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    fun login(credentials: User): String {
        return userService.login(credentials)
    }

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    fun createUser(@HeaderParam("Authorization") token: String?, user: User): String {
        return userService.createUser(token, user)
    }

    @GET
    @Path("/users")
    @Produces(MediaType.APPLICATION_JSON)
    fun getAllUsers(@HeaderParam("Authorization") token: String?): String {
        return userService.getAllUsers(token)
    }

    @PUT
    @Path("/update/{email}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    fun updateUser(
        @HeaderParam("Authorization") token: String?, @PathParam("email") email: String, updatedUser: String
    ): String {
        return userService.updateUser(token, email, updatedUser)
    }

    @DELETE
    @Path("/delete/{email}")
    @Produces(MediaType.APPLICATION_JSON)
    fun deleteUser(@HeaderParam("Authorization") token: String?, @PathParam("email") email: String): String {
        return userService.deleteUser(token, email)
    }
}
