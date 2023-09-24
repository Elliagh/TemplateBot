package org.lazyproger.menu;

import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;

import java.util.ArrayList;
import java.util.List;

public class CommandMenu {

    private final List<BotCommand> menu = new ArrayList<>();

    public List<BotCommand> setMenu() {
        menu.add(new BotCommand("/start", "start command"));
        menu.add(new BotCommand("/help", "show all commands"));

        return menu;
    }
}
