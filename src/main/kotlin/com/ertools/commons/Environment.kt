package com.ertools.commons

import io.github.cdimascio.dotenv.Dotenv

object Environment {
    lateinit var clientName: String
        private set
    lateinit var clientId: String
        private set
    lateinit var clientSecret: String
        private set
    lateinit var publicKey: String
        private set
    lateinit var token: String
        private set
    lateinit var permissionInteger: String
        private set
    lateinit var adminId: String
        private set

    fun load() {
        val dotenv = Dotenv.load()
        clientName = dotenv["client_name"] ?: throw IllegalStateException("Env client_name not found.")
        clientId = dotenv["client_id"] ?: throw IllegalStateException("Env client_id not found.")
        clientSecret = dotenv["client_secret"] ?: throw IllegalStateException("Env client_secret not found.")
        publicKey = dotenv["public_key"] ?: throw IllegalStateException("Env public_key not found.")
        token = dotenv["token"] ?: throw IllegalStateException("Env token not found.")
        permissionInteger = dotenv["permission_integer"] ?: throw IllegalStateException("Env permission_integer not found.")
        adminId = dotenv["admin_id"] ?: throw IllegalStateException("Env admin_id not found.")
    }
}