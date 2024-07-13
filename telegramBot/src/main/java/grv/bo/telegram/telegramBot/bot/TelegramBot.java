package grv.bo.telegram.telegramBot.bot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Slf4j
public class TelegramBot extends TelegramLongPollingBot {

    private final String botName;

//    @Autowired
//    public CallCreated callCreated
    public TelegramBot (String botname,String botToken){
        super(botToken);
        this.botName=botname;
    }
    @Override
    public void onUpdateReceived(Update update) {
        if(update.hasMessage() && update.getMessage().hasText()){
            Message message=update.getMessage();
            var chatId=message.getChatId();
            log.info("message received: {}",message);
            var messageText=message.getText();
            log.info(messageText);
            try{
                execute(new SendMessage(chatId.toString(),"Hello Man"));
//                execute(new SendMessage(chatId.toString(),response)); // for external use call that function and add at autowired
            } catch (TelegramApiException e) {
                log.error("Exception durning processing telegram api: {}",e.getMessage());
            }
        }
    }

    @Override
    public String getBotUsername() {
        return this.botName;
    }
}
