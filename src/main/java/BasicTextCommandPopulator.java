import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class BasicTextCommandPopulator {
    private String fileName;

    public BasicTextCommandPopulator(String fileName){
        this.fileName = fileName;
    }

    public HashMap<String, Command> loadTextCommands(HashMap<String, Command> botCommands) throws IOException{
        Properties properties = new Properties();
        try {
            loadPropertiesFile(properties);
        } catch (NullPointerException e){
            throw new NullPointerException("Bot command file somehow ended up being null.");
        }
        return loadBasicTextCommands(botCommands, properties);
    }

    void loadPropertiesFile(Properties properties) throws IOException{
        Optional<InputStream> inputStream = Optional.ofNullable(getClass().getClassLoader().getResourceAsStream(fileName));
        if(inputStream.isPresent()){
            properties.load(inputStream.get());
        }
        else{
            throw new FileNotFoundException(fileName + " does not exist.");
        }
    }

    HashMap<String, Command> loadBasicTextCommands(HashMap<String, Command> botCommands, Properties properties){
        Set<String> keys = properties.stringPropertyNames();
        for (String key : keys) {
            botCommands.put(key, event -> event.getMessage()
                                                     .getChannel()
                                                     .flatMap(channel -> channel.createMessage(properties.getProperty(key))
                                                     .then()));
        }
        return botCommands;
    }
}
