package com.hiba.ai.chatbot.telegram.service

import com.aallam.openai.api.completion.CompletionRequest
import com.aallam.openai.api.model.ModelId
import com.aallam.openai.client.OpenAI
import com.hiba.ai.chatbot.telegram.config.ChatProperties
import com.hiba.ai.chatbot.telegram.util.HibaTelegramBot
import kotlinx.coroutines.runBlocking
import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Message
import org.telegram.telegrambots.meta.exceptions.TelegramApiException

@Service
class HibaOpenAIService(private val chatProperties: ChatProperties) {
    private val openAI = OpenAI(chatProperties.openAIToken)

    fun getResponse(bot: HibaTelegramBot, message: Message) {
        completionModalResponse(message, bot)
    }

    fun completionModalResponse(message: Message, bot: HibaTelegramBot) = runBlocking {
        try {
            val ada = openAI.model(ModelId("text-davinci-003"));
            val completionRequest = CompletionRequest(
                model = ada.id,
                prompt = message.text,
                maxTokens = 2048,
                echo = false
            )
//            val completions = openAI.completions(completionRequest)
//            completions.onEach { it.choices.forEach {
//                if (!it.text.isNullOrBlank()) {
//                    val text = it.text.trim()
//                    print(text)
//                    sendResponse(bot, message, text)
//                }
//            } }
            val completion = openAI.completion(completionRequest)
            println("start")
            completion.choices.forEach {
                if (!it.text.isNullOrBlank()) {
                    val text = it.text.trim()
                    print(text)
                    sendResponse(bot, message, text)
                }
            };
        } catch (e: Exception) {
            print("Error while OpenAI call")
            e.printStackTrace()
            sendResponse(bot, message, "Service is down, please try again later")
        }
    }

    private fun sendResponse(bot: HibaTelegramBot, message: Message, responseStr: String) {
        val replyMessage = SendMessage().apply {
            setChatId(message.chatId)
            text = responseStr
        }
        try {
            bot.execute(replyMessage)
        } catch (e: TelegramApiException) {
            e.printStackTrace()
        }
    }
}