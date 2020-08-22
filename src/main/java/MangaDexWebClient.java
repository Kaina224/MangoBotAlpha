import com.gargoylesoftware.htmlunit.WebClient;

import java.io.IOException;

public class MangaDexWebClient {
    private WebClient webClient;
    private MangaDexLoginHandler mangaDexLoginHandler;
    private boolean alreadyAttemptedLogin;
    private boolean loginSucceeded;

    MangaDexWebClient(){
        webClient = new WebClient();
        webClient.getOptions().setCssEnabled(false);
        mangaDexLoginHandler = new MangaDexLoginHandler();
        alreadyAttemptedLogin = false;
    }

    public void attemptLogin(){
        try{
            mangaDexLoginHandler.handleLogin(webClient);
            alreadyAttemptedLogin = true;
            loginSucceeded = true;
        }
        catch (IOException | InterruptedException e){
            alreadyAttemptedLogin = true;
            loginSucceeded = false;
        }
    }

    boolean hasAlreadyAttemptedLogin(){
        return alreadyAttemptedLogin;
    }

    boolean hasLoginSucceeded(){
        return loginSucceeded;
    }

}
