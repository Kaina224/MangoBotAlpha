import java.io.IOException;
import java.util.HashMap;

public class CommandPopulator {

    private BasicTextCommandPopulator basicTextCommandPopulator;

    CommandPopulator() {
        basicTextCommandPopulator = new BasicTextCommandPopulator();
    }

    public HashMap<String, Command> createBotCommands() throws IOException {
        HashMap<String, Command> botCommands = new HashMap<>();
        basicTextCommandPopulator.loadBasicTextCommands(botCommands);
        return botCommands;
    }
}
