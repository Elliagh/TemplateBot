package org.lazyproger.keyboard;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class InlineKeyboard {

    public InlineKeyboardMarkup setInlineKeyboard(){
        InlineKeyboardMarkup keyboard = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();
        List<InlineKeyboardButton> rowInLine = new ArrayList<>();

        InlineKeyboardButton firstButton = new InlineKeyboardButton("1");
        firstButton.setCallbackData("first button");

        InlineKeyboardButton secondButton = new InlineKeyboardButton("2");
        secondButton.setCallbackData("second button");

        rowInLine.add(firstButton);
        rowInLine.add(secondButton);

        rowsInLine.add(rowInLine);

        keyboard.setKeyboard(rowsInLine);

        return keyboard;
    }
}
