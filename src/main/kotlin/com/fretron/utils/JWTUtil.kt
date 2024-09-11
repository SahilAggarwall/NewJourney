package com.fretron.utils

import com.fretron.avro.*
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import java.util.Date

object JWTUtil {
    private const val SECRET_KEY = "597fd922cfd84d157371e822ec797937dbdbdeca27e0e6a062a1363ca1cbe054"  // Replace with a secure key
    //private const val EXPIRATION_TIME = 86400000  // 1 day in milliseconds

    fun generateToken(user: User): String {
        return Jwts.builder()
            .setSubject(user.email.toString())
            .claim("role", user.role)
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
            .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
            .compact()
    }

    fun validateToken(token: String): Boolean {
        try {
            Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
            return true
        } catch (e: Exception) {
            return false
        }
    }

    fun getEmailFromToken(token: String): String {
        return Jwts.parser()
            .setSigningKey(SECRET_KEY)
            .parseClaimsJws(token)
            .body
            .subject
    }

    fun getRoleFromToken(token: String): String {
        return Jwts.parser()
            .setSigningKey(SECRET_KEY)
            .parseClaimsJws(token)
            .body
            .get("role", String::class.java)
    }
}
