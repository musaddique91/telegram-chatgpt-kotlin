package com.hiba.ai.chatbot.telegram

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableAsync

@SpringBootApplication
@EnableAsync
@EntityScan("com.hiba.ai.chatbot.telegram.entity")
class HibaTelegramChatApplication

fun main(args: Array<String>) {
    runApplication<HibaTelegramChatApplication>(*args)
}
