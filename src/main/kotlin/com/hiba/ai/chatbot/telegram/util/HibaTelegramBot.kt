package com.hiba.ai.chatbot.telegram.util

import com.hiba.ai.chatbot.telegram.config.ChatProperties
import com.hiba.ai.chatbot.telegram.service.HibaOpenAIService
import com.hiba.ai.chatbot.telegram.service.TelegramInfoService
import org.springframework.boot.context.event.ApplicationStartedEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Service
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.TelegramBotsApi
import org.telegram.telegrambots.meta.api.objects.Message
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession

@Service
class HibaTelegramBot(
    val hibaOpenAIService: HibaOpenAIService,
    val telegramInfoService: TelegramInfoService,
    val chatProperties: ChatProperties,
) : TelegramLongPollingBot(chatProperties.telegramToken) {

    @EventListener(ApplicationStartedEvent::class)
    public fun init() {
        val telegramBotsApi = TelegramBotsApi(DefaultBotSession::class.java)
        telegramBotsApi.registerBot(this)
    }

    override fun getBotUsername(): String {
        return chatProperties.telegramUsername
    }

    override fun onUpdateReceived(update: Update?) {
        telegramInfoService.logRequest(update)
        update?.let {
            val chatId = it.message.chatId
            val message = it.message
            generateReply(chatId, message)
        }
    }

    private fun generateReply(chatId: Long, message: Message) {
        hibaOpenAIService.getResponse(this, message);
    }
}