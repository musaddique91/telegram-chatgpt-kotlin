package com.hiba.ai.chatbot.telegram.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class ChatProperties {
    @Value("\${hiba.chat.telegram.token}")
    lateinit var telegramToken: String
    @Value("\${hiba.chat.telegram.username}")
    lateinit var telegramUsername: String
    @Value("\${hiba.chat.openai.token}")
    lateinit var openAIToken: String
}