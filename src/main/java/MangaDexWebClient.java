import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.Properties;

public class MangaDexWebClient {
    private WebClient webClient;
    private MangaDexLoginHandler mangaDexLoginHandler;

    MangaDexWebClient(WebClient webClient){
        this.webClient = webClient;
        mangaDexLoginHandler = new MangaDexLoginHandler();
    }

    public void fetchFollowedMangaList() throws IOException, InterruptedException{
        mangaDexLoginHandler.handleLogin(webClient);
    }

}
