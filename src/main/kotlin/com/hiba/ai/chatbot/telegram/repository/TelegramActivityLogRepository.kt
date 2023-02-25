package com.hiba.ai.chatbot.telegram.repository

import com.hiba.ai.chatbot.telegram.entity.TelegramActivityLog
import org.springframework.data.jpa.repository.JpaRepository

interface TelegramActivityLogRepository : JpaRepository<TelegramActivityLog, Long> {
    fun findOneByChatIdAndUserIdAndSentResponse(chatId: Long, userId: Long, sendResponse: Boolean = false):TelegramActivityLog?
}