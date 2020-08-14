import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

public class MangaDexLoginHandler {
    public void handleLogin(WebClient webClient) throws  IOException, InterruptedException{
        if(isNotLoggedin(webClient)){
            mangaDexAccountLogin(webClient);
        }
        else{
            finishLogin(webClient, true);
        }
    }

    boolean isNotLoggedin(WebClient webClient) throws IOException, MalformedURLException {
        boolean isNotLoggedIn = false;
        final HtmlPage homePage = getHomePage(webClient);
        Optional<HtmlAnchor> userAnchor = getUserAnchor(homePage);
        if(userAnchor.isEmpty()){
            isNotLoggedIn = true;
        }
        return isNotLoggedIn;
    }

    public void mangaDexAccountLogin(WebClient webClient) throws IOException, InterruptedException {
        final HtmlPage loginPage = getLoginPage(webClient);
        final HtmlForm loginForm = getLoginForm(loginPage);
        final HtmlTextInput usernameInput = getUsernameTextInput(loginForm);
        final HtmlTextInput passwordInput = getPasswordTextInput(loginForm);
        final HtmlCheckBoxInput rememberMeCheckBoxInput = getRememberMeCheckBoxInput(loginForm);
        final HtmlButton loginButton = getLoginButton(loginForm);
        attemptLogin(webClient,
                     passwordInput,
                     rememberMeCheckBoxInput,
                     loginButton, usernameInput);
    }

    HtmlPage getHomePage(WebClient webClient) throws IOException{
        final HtmlPage homePage;
        try{
            homePage = webClient.getPage(MangaDexHomePageComponents.MANGADEX_HOMEPAGE_URL.getWebComponent());
        }
        catch (IOException e){
            throw new IOException(MangaDexLoginErrors.MANGADEX_LOGIN_HOME_PAGE_URL_WRONG_ERROR.getError());
        }
        catch (NullPointerException e){
            throw new NullPointerException(MangaDexLoginErrors.MANGADEX_LOGIN_HOME_PAGE_URL_NULL_ERROR.getError());
        }
        return homePage;
    }

    Optional<HtmlAnchor> getUserAnchor(HtmlPage homePage) throws IOException {
        final HtmlAnchor userAnchor;
        Optional<HtmlAnchor> optionalAnchor;
        try{
            userAnchor = homePage.getAnchorByHref(MangaDexHomePageComponents.MANGADEX_USER_HREF.getWebComponent());
            optionalAnchor = Optional.of(userAnchor);
        }
        catch (ElementNotFoundException e){
            optionalAnchor = Optional.empty();
        }
        return optionalAnchor;
    }

    HtmlPage getLoginPage(WebClient webClient) throws IOException{
        final HtmlPage loginPage;
        try{
            loginPage = webClient.getPage(MangaDexLoginHtmlComponents.MANGADEX_LOGIN_PAGE_URL.getWebComponent());
        } catch (IOException e){
            throw new IOException(MangaDexLoginErrors.MANGADEX_LOGIN_PAGE_URL_WRONG_ERROR.getError());
        } catch (NullPointerException e){
            throw new NullPointerException(MangaDexLoginErrors.MANGADEX_LOGIN_PAGE_URL_NULL_ERROR.getError());
        }
        return loginPage;
    }

    HtmlForm getLoginForm(HtmlPage loginPage) throws ElementNotFoundException {
        final HtmlForm usernameForm;
        try{
            usernameForm = loginPage.getFormByName(MangaDexLoginHtmlComponents.MANGADEX_LOGIN_FORM.getWebComponent());
        }
        catch (ElementNotFoundException e){
            throw new FieldNotFoundException(MangaDexLoginErrors.MANGADEX_LOGIN_FORM_WRONG_ERROR.getError());
        }
        catch (NullPointerException e){
            throw new NullPointerException(MangaDexLoginErrors.MANGADEX_LOGIN_FORM_NULL_ERROR.getError());
        }
        return usernameForm;
    }

    HtmlTextInput getUsernameTextInput(HtmlForm loginForm) {
        final HtmlTextInput usernameInput;
        try{
            usernameInput = loginForm.getInputByName(MangaDexLoginHtmlComponents.MANGADEX_LOGIN_USERNAME_INPUT.getWebComponent());
        }
        catch (ElementNotFoundException e){
            throw new FieldNotFoundException(MangaDexLoginErrors.MANGADEX_LOGIN_USERNAME_INPUT_WRONG_ERROR.getError());
        }
        catch (NullPointerException e){
            throw new NullPointerException(MangaDexLoginErrors.MANGADEX_LOGIN_USERNAME_INPUT_NULL_ERROR.getError());
        }
        return usernameInput;
    }

    HtmlTextInput getPasswordTextInput(HtmlForm loginForm) {
        final HtmlTextInput passwordInput;
        try{
            passwordInput = loginForm.getInputByName(MangaDexLoginHtmlComponents.MANGADEX_LOGIN_PASSWORD_INPUT.getWebComponent());
        }
        catch (ElementNotFoundException e){
            throw new FieldNotFoundException(MangaDexLoginErrors.MANGADEX_LOGIN_PASSWORD_INPUT_WRONG_ERROR.getError());
        }
        catch (NullPointerException e){
            throw new NullPointerException(MangaDexLoginErrors.MANGADEX_LOGIN_PASSWORD_INPUT_NULL_ERROR.getError());
        }
        return passwordInput;
    }

    HtmlCheckBoxInput getRememberMeCheckBoxInput(HtmlForm loginForm){
        final HtmlCheckBoxInput rememberMeCheckBoxInput;
        try{
            rememberMeCheckBoxInput = loginForm.getInputByName(MangaDexLoginHtmlComponents.MANGADEX_LOGIN_REMEMBER_ME_CHECKBOX.getWebComponent());
        }
        catch (ElementNotFoundException e){
            throw new FieldNotFoundException(MangaDexLoginErrors.MANGADEX_LOGIN_REMEMBER_ME_CHECKBOX_WRONG_ERROR.getError());
        }
        catch (NullPointerException e){
            throw new NullPointerException(MangaDexLoginErrors.MANGADEX_LOGIN_REMEMBER_ME_CHECKBOX_NULL_ERROR.getError());
        }
        return rememberMeCheckBoxInput;
    }

    HtmlButton getLoginButton(HtmlForm loginForm){
        final HtmlButton loginButton;
        try{
            loginButton = loginForm.getButtonByName(MangaDexLoginHtmlComponents.MANGADEX_LOGIN_LOGIN_BUTTON.getWebComponent());
        }
        catch (ElementNotFoundException e){
            throw new FieldNotFoundException(MangaDexLoginErrors.MANGADEX_LOGIN_LOGIN_BUTTON_WRONG_ERROR.getError());
        }
        catch (NullPointerException e){
            throw new NullPointerException(MangaDexLoginErrors.MANGADEX_LOGIN_LOGIN_BUTTON_NULL_ERROR.getError());
        }
        return loginButton;
    }

    void attemptLogin(WebClient webClient,
                      HtmlTextInput passwordInput,
                      HtmlCheckBoxInput rememberMeCheckBoxInput,
                      HtmlButton loginButton,
                      HtmlTextInput usernameInput) throws IOException, InterruptedException{
        //Change fields, click button, then validate redirect to home page, otherwise throw loginfailedexception
        Properties properties = new Properties();
        loadCredentialsPropertiesFile(properties);
        usernameInput.setText(properties.getProperty("mangadexusername"));
        passwordInput.setText(properties.getProperty("mangadexpassword"));
        rememberMeCheckBoxInput.setChecked(true);
        loginButton.click();

        try{
            Thread.sleep(1000);
        } catch (InterruptedException e){
            Thread.currentThread().interrupt();
            throw new InterruptedException("Placeholder Text");
        }

        if (hasLoginSucceeded(webClient)){
            //Do something with the bot
        }
        else{
            throw new RuntimeException(MangaDexLoginErrors.MANGADEX_LOGIN_FAILED_LOGIN_ERROR.getError());
        }
    }

    boolean hasLoginSucceeded(WebClient webClient){
        String currentURL = webClient.getCurrentWindow().getEnclosedPage().getUrl().toString();
        if (currentURL.equals(MangaDexHomePageComponents.MANGADEX_HOMEPAGE_URL.getWebComponent())){
            return true;
        }
        else{
            return false;
        }
    }

    void finishLogin(WebClient webClient, boolean alreadyLoggedOn){
        //empty for now, do something with the bot later
    }

    void loadCredentialsPropertiesFile(Properties properties) throws IOException{
        Optional<InputStream> inputStream = Optional.ofNullable(getClass()
                .getClassLoader()
                .getResourceAsStream(PropertiesFileNames.MANGADEX_LOGIN_CREDENTIALS.getFileName()));
        if(inputStream.isPresent()){
            properties.load(inputStream.get());
        }
        else{
            throw new FileNotFoundException(MangaDexLoginErrors.MANGADEX_LOGIN_CREDENTIALS_PROPERTY_FILES_MISSING.getError());
        }
    }
}
