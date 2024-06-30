package com.ertools.commands

import net.dv8tion.jda.api.entities.Message

class Help(
    call: String, requirePrefix: Boolean, requireAdmin: Boolean
): Command(
    call = call,
    requirePrefix = requirePrefix,
    requireAdmin = requireAdmin
) {
    override fun execute(message: Message) {
        println("Help execution success.")
    }
}