import java.io.IOException;
import java.util.HashMap;

public class CommandPopulator {

    private BasicTextCommandPopulator basicTextCommandPopulator;
    private DynamicBotCommandPopulator dynamicBotCommandPopulator;

    CommandPopulator(MangaDexWebClient mangaDexWebClient) {
        basicTextCommandPopulator = new BasicTextCommandPopulator();
        dynamicBotCommandPopulator = new DynamicBotCommandPopulator(mangaDexWebClient);
    }

    public HashMap<String, Command> createBotCommands() throws IOException {
        HashMap<String, Command> botCommands = new HashMap<>();
        basicTextCommandPopulator.loadBasicTextCommands(botCommands);
        dynamicBotCommandPopulator.loadDynamicCommands(botCommands);
        return botCommands;
    }
}
