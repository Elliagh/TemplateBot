# TemplateBot
Unstable version based on TelegramLongPollingBot

```
   For old version chekout branch "old"
```

### How to launch

To launch Bot input your BotName and BotToken registered from BotFather in dispatcher/src/main/resources/application.properties

### Project architecture

Telegram Message -> Dispatcher -> RabbitMQ -> Node ->
-> RabbitMQ -> Dispatcher -> Telegram Message


### Plans

```
   Add registation from email, templates for menu, keyboard
```
