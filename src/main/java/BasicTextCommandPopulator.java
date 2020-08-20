import java.util.*;

public class BasicTextCommandPopulator {
    public BasicTextCommandPopulator(){

    }

    HashMap<String, Command> loadBasicTextCommands(HashMap<String, Command> botCommands){
        for (StaticBotCommandEnums commands : StaticBotCommandEnums.values()){
            botCommands.put(commands.getCommand(), event -> event.getMessage()
                                                                 .getChannel()
                                                                 .flatMap(channel -> channel.createMessage(commands.getResponse())
                                                                         .then()));

        }
        return botCommands;
    }
}
