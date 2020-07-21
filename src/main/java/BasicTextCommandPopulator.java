import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Optional;
import java.util.Properties;

public class TextCommandRetriever {
    InputStream inputStream;
    private String fileName;

    public TextCommandRetriever(String fileName){
        this.fileName = fileName;
    }

    public HashMap<String, Command> getTextCommands() throws IOException{
        try {
            Properties properties = new Properties();
            loadPropertiesFile(properties);
        } catch (Exception e){
            throw new (e);
        }
        return new HashMap<String, Command>();
    }

    void loadPropertiesFile(Properties properties) throws IOException{
        Optional<InputStream>  inputStream = Optional.ofNullable(getClass().getClassLoader().getResourceAsStream(fileName));
        if(inputStream.isPresent()){
            properties.load(inputStream.get());
        }
        else{
            throw new FileNotFoundException(fileName + " does not exist.");
        }
    }
}
