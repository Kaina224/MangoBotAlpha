import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class BasicTextCommandPopulatorTests {
    @Test
    public void loadBasicTextCommands__assertCommandsProperlyLoaded(){
        BasicTextCommandPopulator basicTextCommandPopulator = new BasicTextCommandPopulator();

        ArrayList<String> expectedCommands = new ArrayList<>();
        expectedCommands.add(StaticBotCommandEnums.PING_COMMAND.getCommand());
        ArrayList<Command> expectedResponses = new ArrayList<>();
        expectedResponses.add(event -> event.getMessage()
                                            .getChannel()
                                            .flatMap(channel -> channel.createMessage(StaticBotCommandEnums.PING_COMMAND.getResponse()))
                                            .then());

        HashMap<String, Command> actualBotCommands = new HashMap<>();
        basicTextCommandPopulator.loadBasicTextCommands(actualBotCommands);
        ArrayList<String> actualCommands = new ArrayList<>(actualBotCommands.keySet());
        ArrayList<Command> actualResponses = new ArrayList<>(actualBotCommands.values());

        assertEquals(expectedCommands, actualCommands);
        assertEquals(expectedResponses.get(0).getClass().getClassLoader().toString(),
                     actualResponses.get(0).getClass().getClassLoader().toString());
    }
}
