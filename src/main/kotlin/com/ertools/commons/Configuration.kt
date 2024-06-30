package com.ertools.commons

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.typesafe.config.Config
import com.typesafe.config.ConfigFactory


object Configuration {
    lateinit var properties: Properties
        private set
    lateinit var commands: List<Command>
        private set

    fun load() {
        val config: Config = ConfigFactory.load("application.conf")
        val configuration = jacksonObjectMapper().readValue<Configuration>(config.root().render())
        this.properties = configuration.properties
        this.commands = configuration.commands
    }

    data class Properties(
        val prefix: String
    )

    data class Command(
        val call: String,
        val enabled: Boolean,
        val prefix: Boolean,
        val admin: Boolean
    )
}

