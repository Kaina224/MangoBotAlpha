import discord4j.core.event.domain.message.MessageCreateEvent;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class BasicTextCommandPopulatorTests {
    @Test
    public void loadBasicTextCommands_properFileName_assertCommandsProperlyLoaded() throws IOException {
        String fileName = "basictextcommandstests.properties";
        BasicTextCommandPopulator basicTextCommandPopulator = new BasicTextCommandPopulator(fileName);

        ArrayList<String> expectedCommands = new ArrayList<>();
        expectedCommands.add("testcommand");
        ArrayList<Command> expectedResponses = new ArrayList<>();
        expectedResponses.add(event -> event.getMessage()
                                            .getChannel()
                                            .flatMap(channel -> channel.createMessage("testresponse"))
                                            .then());

        HashMap<String, Command> actualBotCommands = new HashMap<>();
        basicTextCommandPopulator.loadTextCommands(actualBotCommands);
        ArrayList<String> actualCommands = new ArrayList<>(actualBotCommands.keySet());
        ArrayList<Command> actualResponses = new ArrayList<>(actualBotCommands.values());

        assertEquals(expectedCommands, actualCommands);
        assertEquals(expectedResponses.get(0).getClass().getClassLoader().toString(), actualResponses.get(0).getClass().getClassLoader().toString());
    }

    @Test
    public void loadBasicTextCommands_nullFileName_throwsNullPointerException(){
        BasicTextCommandPopulator basicTextCommandPopulator = new BasicTextCommandPopulator(null);
        NullPointerException e = assertThrows(NullPointerException.class, () -> basicTextCommandPopulator.loadTextCommands(new HashMap<>()));
        assertEquals("Bot command file somehow ended up being null.", e.getMessage());
    }

    @Test
    public void loadBasicTextCommands_incorrectFileName_throwsFileNotFoundException() throws IOException {
        BasicTextCommandPopulator basicTextCommandPopulator = new BasicTextCommandPopulator("BadFileName.File");
        FileNotFoundException e = assertThrows(FileNotFoundException.class, () -> basicTextCommandPopulator.loadTextCommands(new HashMap<>()));
        assertEquals("BadFileName.File does not exist.", e.getMessage());
    }
}
