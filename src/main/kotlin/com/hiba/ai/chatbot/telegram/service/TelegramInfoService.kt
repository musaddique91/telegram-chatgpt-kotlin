package com.hiba.ai.chatbot.telegram.service

import com.hiba.ai.chatbot.telegram.config.ChatProperties
import com.hiba.ai.chatbot.telegram.entity.TelegramActivityLog
import com.hiba.ai.chatbot.telegram.repository.TelegramActivityLogRepository
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.objects.Update

@Service
class TelegramInfoService(private val telegramActivityLogRepository: TelegramActivityLogRepository) {

    @Async
    public fun logRequest(update: Update?) {
        update?.let {
            val message = it.message
            val log = TelegramActivityLog(
                id = 0,
                chatId = it.updateId.toLong(),
                userId = message.from.id,
                userName = message.from?.userName,
                firstName = message.from?.firstName,
                lastName = message.from?.lastName,
                reqText = message.text,
                sentResponse = false
            )
            telegramActivityLogRepository.save(log)
        }
    }

    @Async
    public fun makeSent(chatId: Long, userId: Long) {
        val log = telegramActivityLogRepository.findOneByChatIdAndUserIdAndSentResponse(
            chatId,
            userId
        )
        log?.let {
            it.sentResponse = true
            telegramActivityLogRepository.save(it)
        }
    }
}