package com.ertools.commons

import com.typesafe.config.Config
import com.typesafe.config.ConfigFactory


object Configuration {
    lateinit var properties: Properties
        private set
    lateinit var commands: HashMap<String, Command>
        private set

    fun load() {
        try {
            val config: Config = ConfigFactory.load("application.conf")

            /** Properties **/
            properties = Properties(
                prefix = config.getConfig("properties").getString("prefix")
            )

            /** Commands **/
            commands = HashMap()
            val commandsConfig = config.getConfig("commands")
            for (commandName in commandsConfig.root().keys) {
                val commandConfig = commandsConfig.getConfig(commandName)
                val call = commandConfig.getString("call")
                val enabled = commandConfig.getBoolean("enabled")
                val reqPrefix = commandConfig.getBoolean("req_prefix")
                val reqAdmin = commandConfig.getBoolean("req_admin")
                commands[commandName] = Command(call, enabled, reqPrefix, reqAdmin)
            }
        } catch (e: Exception) {
            error("Configuration: Content of application.conf file is not valid.")
            e.printStackTrace()
        }
    }

    data class Properties(
        val prefix: String
    )

    data class Command(
        val call: String,
        val enabled: Boolean,
        val reqPrefix: Boolean,
        val reqAdmin: Boolean
    )
}

