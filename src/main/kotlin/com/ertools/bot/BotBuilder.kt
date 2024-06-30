package com.ertools.bot

import com.ertools.commands.Command
import com.ertools.commons.Configuration
import com.ertools.commons.Environment
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.requests.GatewayIntent
import kotlin.reflect.full.primaryConstructor

class BotBuilder {
    private lateinit var jda: JDA
    private lateinit var registeredCommands: HashMap<String, Command>
    private lateinit var botListener: BotListener

    fun build() {
        try {
            Configuration.load()
            Environment.load()
            registerCommands()
            registerListener()
            configureJDA()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun registerCommands() {
        registeredCommands = HashMap()
        Configuration.commands.forEach { command ->
            if(!command.value.enabled) return@forEach
            val commandName = command.key
            try {
                val clazz = Class.forName("com.ertools.commands.$commandName").kotlin
                if(clazz.isAbstract) return@forEach

                val constructor = clazz.primaryConstructor ?: return@forEach
                val commandObject = constructor.call(
                    command.value.call,
                    command.value.reqPrefix,
                    command.value.reqAdmin
                )
                registeredCommands[command.value.call] = commandObject as Command
            } catch (e: ClassNotFoundException) {
                error("BotBuilder: Command $commandName does not exist.")
            }
        }
    }

    private fun registerListener() {
        botListener = BotListener(registeredCommands)
    }

    private fun configureJDA() {
        jda = JDABuilder
            .createDefault(Environment.token)
            .enableIntents(GatewayIntent.DIRECT_MESSAGES)
            .enableIntents(GatewayIntent.MESSAGE_CONTENT)
            .addEventListeners(botListener)
            .build()
            .awaitReady()

    }
}