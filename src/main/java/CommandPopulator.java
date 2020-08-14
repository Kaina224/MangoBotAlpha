import java.io.IOException;
import java.util.HashMap;

public class CommandPopulator {

    private static final String COMMANDS_FILE = "commands.properties";
    private BasicTextCommandPopulator basicTextCommandPopulator;

    CommandPopulator(){
        basicTextCommandPopulator = new BasicTextCommandPopulator(COMMANDS_FILE);
    }

    public HashMap<String, Command> createBotCommands() throws IOException {
        HashMap<String, Command> botCommands = new HashMap<>();
        basicTextCommandPopulator.loadTextCommands(botCommands);
        return botCommands;
    }
}
