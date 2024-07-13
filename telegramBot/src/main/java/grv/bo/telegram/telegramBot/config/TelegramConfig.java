package grv.bo.telegram.telegramBot.config;

import grv.bo.telegram.telegramBot.bot.TelegramBot;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Configuration
@Slf4j
public class TelegramConfig {


    @Bean
    public TelegramBot telegramBot(@Value("${bot.name}") String botname,@Value("${bot.token}") String botToken){
        TelegramBot telegramBot=new TelegramBot(botname,botToken);
        try{
            var telegramBotsApi= new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(telegramBot);
        } catch (TelegramApiException e) {
            log.error("Exception durning registration telegram api: {}",e.getMessage());
        }
        return telegramBot;
    }
}
