package org.lazyproger.keyboard;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

public class Keyboard {

    public ReplyKeyboardMarkup setReplyKeyboard(){

        ReplyKeyboardMarkup keyboard = new ReplyKeyboardMarkup();
        List<KeyboardRow> rows = new ArrayList<>();

        KeyboardRow row = new KeyboardRow();
        row.add("1");
        row.add("2");
        rows.add(row);

        row = new KeyboardRow();
        row.add("3");
        row.add("4");
        rows.add(row);

        keyboard.setKeyboard(rows);
        return keyboard;
    }
}
