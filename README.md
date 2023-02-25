# telegram-chatgpt-kotlin
integrate telegram chatbot with chatgpt and kotlin/mysql 

# Description: Here we integrate telegram chatbot with OpenAI ChatGPT in Kotlin and mysql
# How to run

# 1. before running this application we need tokens for telegram and openAI

   a. Telegram Token: 
      
      Step 1. Find telegram bot named "@botfarther", he will help you with creating and managing your bot.
      
      Step 2. Print “/help” and you will see all possible commands that the botfather can operate.
      
      Step 3. To create a new bot type “/newbot” or click on it.
      
      Step 4. Congratulations! You've just created your Telegram bot. You will see a new API token generated for it.
      
      In sample above it's 270485614:AAHfiqksKZ8WmR2zSjiQ7_v4TMAKdiHm9T0

      Copy your API token to the plugin.
      
  b. OpenAI Token: refre this link-> https://platform.openai.com/account/api-keys
  
# 2. Setup mysql database as per application.properties. 

Import the application in IDE and run as spring boot.

#Run as Docker
1. docker build -t telegram-chatbot:0.1 .
2. docker-compose -f telegram-chat-docker-compose.yaml up -d
