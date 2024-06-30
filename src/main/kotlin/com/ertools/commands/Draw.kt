package com.ertools.commands

import net.dv8tion.jda.api.entities.Message

class Draw(call: String): Command(call = call) {

    init {
        println("TEST: Draw success")
    }

    override fun execute(message: Message) {

    }

}