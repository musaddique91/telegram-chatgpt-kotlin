package com.hiba.ai.chatbot.telegram.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.time.LocalDateTime

@Entity
class TelegramActivityLog(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long,
    var chatId: Long,
    var userId: Long?,
    var userName: String?,
    var firstName: String?,
    var lastName: String?,
    var requestDate: LocalDateTime = LocalDateTime.now(),
    var reqText: String,
    var sentResponse: Boolean
)