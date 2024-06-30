package com.ertools.commands

import net.dv8tion.jda.api.entities.Message

abstract class Command (
    val call: String = String(),
    val requirePrefix: Boolean = true,
    val requireAdmin: Boolean = false
) {
    abstract fun execute(message: Message)
}