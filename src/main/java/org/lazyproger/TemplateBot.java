package org.lazyproger;

import org.lazyproger.config.Config;
import org.lazyproger.keyboard.InlineKeyboard;
import org.lazyproger.keyboard.Keyboard;
import org.lazyproger.menu.CommandMenu;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class TemplateBot extends TelegramLongPollingBot {


    private Config config;
    private CommandMenu menu;
    private Keyboard keyboard;
    private InlineKeyboard inlineKeyboard;

    public TemplateBot(Config config, CommandMenu menu, Keyboard keyboard, InlineKeyboard inlineKeyboard) {
        this.config = config;
        this.menu = menu;
        this.keyboard = keyboard;
        this.inlineKeyboard = inlineKeyboard;

        try {
            execute(new SetMyCommands(menu.setMenu(), new BotCommandScopeDefault(), null));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpdateReceived(Update update){

        if (update.hasMessage() && update.getMessage().hasText() && update.getMessage().getText().startsWith("/")){

            long chat_id = update.getMessage().getChatId();
            SendMessage message = new SendMessage();
            String message_text = update.getMessage().getText();
            message.setChatId(chat_id);
            message.setText(message_text);


            try {
                execute(message);
            } catch (TelegramApiException e){
                e.printStackTrace();
            }

        }

    }

    @Override
    public String getBotUsername() {
        return config.getBotName();
    }

    @Override
    public String getBotToken(){
        return config.getBotToken();
    }
}
