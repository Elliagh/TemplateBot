package org.lazyproger.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {

    private final String BotToken;
    private final String BotName;

    public Config(){

        Properties properties = new Properties();

        try (InputStream inputStream = Config.class.getResourceAsStream("/application.properties")){
            properties.load(inputStream);
        } catch (IOException e){
            throw new RuntimeException(e);
        }

        this.BotToken = properties.getProperty("bot.token");
        this.BotName = properties.getProperty("bot.name");
    }

    public String getBotToken(){
        return BotToken;
    }

    public String getBotName(){
        return BotName;
    }
}
