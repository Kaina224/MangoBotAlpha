import java.io.IOException;
import java.util.HashMap;

public class DynamicBotCommandPopulator {
    MangaDexWebClient mangaDexWebClient;

    public DynamicBotCommandPopulator(MangaDexWebClient mangaDexWebClient){
        this.mangaDexWebClient = mangaDexWebClient;
    }

    HashMap<String, Command> loadDynamicCommands(HashMap<String, Command> botCommands){
        loadMangaDexCommands(botCommands);
        return botCommands;
    }

    HashMap<String, Command> loadMangaDexCommands(HashMap<String, Command> botCommands){
        botCommands = loadLoginCommand(botCommands);
        return botCommands;
    }

    HashMap<String, Command> loadLoginCommand(HashMap<String, Command> botCommands){
        botCommands.put(DynamicBotCommandEnums.ATTEMPT_LOGIN.getCommand(), event ->
                event.getMessage()
                     .getChannel()
                     .flatMap(channel -> channel.createMessage(getLoginResponse()))
                     .then());
        return botCommands;
    }

    String getLoginResponse(){
        String result = "";
        if(mangaDexWebClient.hasAlreadyAttemptedLogin() && mangaDexWebClient.hasLoginSucceeded()){
            return MangaDexLoginCommandResponses.MANGADEX_LOGIN_ATTEMPT_ALREADY_OCCURRED_SUCCESSFUL_ATTEMPT.getResponse();
        }
        else if(mangaDexWebClient.hasAlreadyAttemptedLogin() && !mangaDexWebClient.hasLoginSucceeded()){
            return MangaDexLoginCommandResponses.MANGADEX_LOGIN_ATTEMPT_ALREADY_OCCURRED_FAILED_ATTEMPT.getResponse();
        }
        mangaDexWebClient.attemptLogin();
        if(mangaDexWebClient.hasLoginSucceeded()){
            result = MangaDexLoginCommandResponses.MANGADEX_LOGIN_ATTEMPT_SUCCEEDED.getResponse();
        }
        else{
            result = MangaDexLoginCommandResponses.MANGADEX_LOGIN_ATTEMPT_FAILED.getResponse();
        }
        return result;
    }
}
