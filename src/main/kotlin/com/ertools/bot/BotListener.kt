package com.ertools.bot

import com.ertools.commands.Command
import com.ertools.commons.Configuration
import com.ertools.commons.Environment
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter

class BotListener(private val registeredCommands: Map<String, Command>): ListenerAdapter() {
    override fun onMessageReceived(event: MessageReceivedEvent) {
        if (event.author.isBot) return
        val prefix = event.message.contentRaw.substring(
            0, Configuration.properties.prefix.length
        )
        val message = event.message.contentRaw.drop(
            Configuration.properties.prefix.length
        )
        val authorId = event.message.author.idLong

        registeredCommands.forEach { (_, command) ->
            if (commandsConditions(command, prefix, message, authorId)) command.execute(event.message)
        }
    }

    private fun commandsConditions(command: Command, prefix: String, message: String, authorId: Long): Boolean {
        if(command.requirePrefix && prefix != Configuration.properties.prefix) return false
        if(command.requirePrefix && message.split(" ")[0] != command.call) return false
        else if(!command.requirePrefix && !prefix.plus(message).contains(command.call)) return false
        if(command.requireAdmin && authorId != Environment.adminId.toLong()) return false
        return true
    }
}