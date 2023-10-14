package ru.lazyproger.controller;

import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.lazyproger.service.UpdateProducer;
import ru.lazyproger.utils.MessageUtils;

import static ru.lazyproger.model.RabbitQueue.*;

@Component
@Log4j
public class UpdateController {

    private TelegramBot telegramBot;
    private final MessageUtils messageUtils;
    private final UpdateProducer updateProducer;

    public UpdateController(MessageUtils messageUtils, UpdateProducer updateProducer) {
        this.messageUtils = messageUtils;
        this.updateProducer = updateProducer;
    }

    public void registerBot(TelegramBot telegramBot) {
        this.telegramBot = telegramBot;
    }

    public void processUpdate(Update update){
        if(update == null){
            log.error("Received update is null");
            return;
        }
        if (update.hasMessage()){
            distributeByMessageType(update);
        } else {
            log.error("Recieved unsupported message type" + update);
        }
    }
    //TODO rewrite
    private void distributeByMessageType(Update update){
        Message message = update.getMessage();
        if (message.hasText()){
            processTextMessage(update);
        } else if (message.hasDocument()){
            processDocumentMessage(update);
        } else if (message.hasPhoto()){
            processPhotoMessage(update);
        } else {
            setUnsupportedMessageTypeView(update);
        }

    }

    private void setUnsupportedMessageTypeView(Update update) {
        SendMessage sendMessage = messageUtils.generateSendMessageWithText(
          update, "Unsupported message type"
        );
        setView(sendMessage);

    }

    public void setView(SendMessage sendMessage) {
        telegramBot.sendAnswerMessage(sendMessage);
    }

    private void processPhotoMessage(Update update) {
        updateProducer.produce(PHOTO_MESSAGE_UPDATE, update);
        setFileIsRecievedView(update);
    }

    private void setFileIsRecievedView(Update update) {
        SendMessage sendMessage = messageUtils.generateSendMessageWithText(
                update, "File recieved, wait for processing"
        );
    }

    private void processDocumentMessage(Update update) {
        updateProducer.produce(DOC_MESSAGE_UPDATE, update);
    }

    private void processTextMessage(Update update) {
        updateProducer.produce(TEXT_MESSAGE_UPDATE, update);
    }

}
