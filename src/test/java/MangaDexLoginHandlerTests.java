import com.gargoylesoftware.htmlunit.*;
import com.gargoylesoftware.htmlunit.html.*;
import org.junit.Test;
import org.mockito.internal.matchers.Null;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class MangaDexLoginHandlerTests {
    @Test
    public void getLoginPage_loginPageIsWrong_assertIOException() throws IOException {
        MangaDexLoginHandler mangaDexLoginHandler = new MangaDexLoginHandler();

        WebClient webClient = mock(WebClient.class);
        doThrow(new IOException()).when(webClient).getPage(MangaDexLoginHtmlComponents.MANGADEX_LOGIN_PAGE_URL.getWebComponent());
        IOException e = assertThrows(IOException.class, () -> mangaDexLoginHandler.getLoginPage(webClient));
        assertEquals(MangaDexLoginErrors.MANGADEX_LOGIN_PAGE_URL_WRONG_ERROR.getError(), e.getMessage());
    }

    @Test
    public void getLoginPage_loginPageIsNull_assertNullPointerException() throws IOException {
        MangaDexLoginHandler mangaDexLoginHandler = new MangaDexLoginHandler();

        WebClient webClient = mock(WebClient.class);
        doThrow(new NullPointerException()).when(webClient).getPage(MangaDexLoginHtmlComponents.MANGADEX_LOGIN_PAGE_URL.getWebComponent());
        NullPointerException e = assertThrows(NullPointerException.class, () -> mangaDexLoginHandler.getLoginPage(webClient));
        assertEquals(MangaDexLoginErrors.MANGADEX_LOGIN_PAGE_URL_NULL_ERROR.getError(), e.getMessage());
    }

    @Test
    public void getLoginPage_loginPageIsCorrect_assertHomePageCorrect() throws IOException{
        MangaDexLoginHandler mangaDexLoginHandler = new MangaDexLoginHandler();

        HtmlPage expectedPage = mock(HtmlPage.class);
        WebClient webClient = mock(WebClient.class);
        when(webClient.getPage(MangaDexLoginHtmlComponents.MANGADEX_LOGIN_PAGE_URL.getWebComponent())).thenReturn(expectedPage);
        HtmlPage actualPage = mangaDexLoginHandler.getLoginPage(webClient);
        assertEquals(expectedPage, actualPage);
    }

    @Test
    public void getLoginForm_loginFormIDIsIncorrect_assertFieldNotFoundException(){
        MangaDexLoginHandler mangaDexLoginHandler = new MangaDexLoginHandler();

        HtmlPage htmlPage = mock(HtmlPage.class);
        HtmlForm mockForm = mock(HtmlForm.class);
        List<HtmlForm> mockFormList = new ArrayList<>();
        mockFormList.add(mockForm);

        when(htmlPage.getForms()).thenReturn(mockFormList);
        when(mockForm.getId()).thenReturn("some_wrong_id");

        FieldNotFoundException e = assertThrows(FieldNotFoundException.class, () -> mangaDexLoginHandler.getLoginForm(htmlPage));
        assertEquals(MangaDexLoginErrors.MANGADEX_LOGIN_FORM_WRONG_ERROR.getError(), e.getMessage());
    }


    @Test
    public void getLoginForm_loginFormRetrieved_assertFormCorrect(){
        MangaDexLoginHandler mangaDexLoginHandler = new MangaDexLoginHandler();

        HtmlPage loginPage = mock(HtmlPage.class);
        HtmlForm expectedForm = mock(HtmlForm.class);
        List<HtmlForm> mockFormList = new ArrayList<>();
        mockFormList.add(expectedForm);

        when(loginPage.getForms()).thenReturn(mockFormList);
        when(expectedForm.getId()).thenReturn(MangaDexLoginHtmlComponents.MANGADEX_LOGIN_FORM.getWebComponent());

        HtmlForm actualForm = mangaDexLoginHandler.getLoginForm(loginPage);
        assertEquals(expectedForm, actualForm);
    }

    @Test
    public void getUsernameTextInput_usernameInputIsWrong_assertFieldNotFoundException(){
        MangaDexLoginHandler mangaDexLoginHandler = new MangaDexLoginHandler();

        HtmlForm htmlForm = mock(HtmlForm.class);
        List<HtmlInput> inputList = new ArrayList<>();
        when(htmlForm.getInputsByName(MangaDexLoginHtmlComponents.MANGADEX_LOGIN_USERNAME_INPUT.getWebComponent()))
                .thenReturn(inputList);
        doThrow(new ElementNotFoundException("", "", ""))
                .when(htmlForm).getInputsByName(MangaDexLoginHtmlComponents.MANGADEX_LOGIN_USERNAME_INPUT.getWebComponent());

        FieldNotFoundException e = assertThrows(FieldNotFoundException.class, () -> mangaDexLoginHandler.getUsernameTextInput(htmlForm));
        assertEquals(MangaDexLoginErrors.MANGADEX_LOGIN_USERNAME_INPUT_WRONG_ERROR.getError(), e.getMessage());
    }

    @Test
    public void getUsernameTextInput_usernameInputIsNull_assertNullPointerException(){
        MangaDexLoginHandler mangaDexLoginHandler = new MangaDexLoginHandler();

        HtmlForm htmlForm = mock(HtmlForm.class);
        List<HtmlInput> inputList = new ArrayList<>();
        when(htmlForm.getInputsByName(MangaDexLoginHtmlComponents.MANGADEX_LOGIN_USERNAME_INPUT.getWebComponent()))
                .thenReturn(inputList);
        doThrow(new NullPointerException(""))
                .when(htmlForm).getInputsByName(MangaDexLoginHtmlComponents.MANGADEX_LOGIN_USERNAME_INPUT.getWebComponent());

        NullPointerException e = assertThrows(NullPointerException.class, () -> mangaDexLoginHandler.getUsernameTextInput(htmlForm));
        assertEquals(MangaDexLoginErrors.MANGADEX_LOGIN_USERNAME_INPUT_NULL_ERROR.getError(), e.getMessage());
    }

    @Test
    public void getUserNameTextInput_usernameInputRetrieved_assertInputCorrect(){
        MangaDexLoginHandler mangaDexLoginHandler = new MangaDexLoginHandler();

        List<HtmlInput> inputList = new ArrayList<>();
        HtmlForm htmlForm = mock(HtmlForm.class);
        HtmlTextInput expectedTextInput = mock(HtmlTextInput.class);
        inputList.add(expectedTextInput);
        when(htmlForm.getInputsByName(MangaDexLoginHtmlComponents.MANGADEX_LOGIN_USERNAME_INPUT.getWebComponent()))
                .thenReturn(inputList);
        when(htmlForm.getInputByName(MangaDexLoginHtmlComponents.MANGADEX_LOGIN_USERNAME_INPUT.getWebComponent()))
                .thenReturn(inputList);

        HtmlInput actualTextInput = mangaDexLoginHandler.getUsernameTextInput(htmlForm);
        assertEquals(expectedTextInput, actualTextInput);
    }

    @Test
    public void getPasswordTextInput_passwordInputIsWrong_assertFieldNotFoundException(){
        MangaDexLoginHandler mangaDexLoginHandler = new MangaDexLoginHandler();

        HtmlForm htmlForm = mock(HtmlForm.class);
        List<HtmlInput> inputList = new ArrayList<>();
        when(htmlForm.getInputsByName(MangaDexLoginHtmlComponents.MANGADEX_LOGIN_PASSWORD_INPUT.getWebComponent()))
                .thenReturn(inputList);
        doThrow(new ElementNotFoundException("", "", ""))
                .when(htmlForm).getInputsByName(MangaDexLoginHtmlComponents.MANGADEX_LOGIN_PASSWORD_INPUT.getWebComponent());

        FieldNotFoundException e = assertThrows(FieldNotFoundException.class, () -> mangaDexLoginHandler.getPasswordTextInput(htmlForm));
        assertEquals(MangaDexLoginErrors.MANGADEX_LOGIN_PASSWORD_INPUT_WRONG_ERROR.getError(), e.getMessage());
    }

    @Test
    public void getPasswordTextInput_passwordInputIsNull_assertNullPointerException(){
        MangaDexLoginHandler mangaDexLoginHandler = new MangaDexLoginHandler();

        HtmlForm htmlForm = mock(HtmlForm.class);
        List<HtmlInput> inputList = new ArrayList<>();
        when(htmlForm.getInputsByName(MangaDexLoginHtmlComponents.MANGADEX_LOGIN_PASSWORD_INPUT.getWebComponent()))
                .thenReturn(inputList);
        doThrow(new NullPointerException(""))
                .when(htmlForm).getInputsByName(MangaDexLoginHtmlComponents.MANGADEX_LOGIN_PASSWORD_INPUT.getWebComponent());

        NullPointerException e = assertThrows(NullPointerException.class, () -> mangaDexLoginHandler.getPasswordTextInput(htmlForm));
        assertEquals(MangaDexLoginErrors.MANGADEX_LOGIN_PASSWORD_INPUT_NULL_ERROR.getError(), e.getMessage());
    }

    @Test
    public void getPasswordTextInput_passwordInputRetrieved_assertPasswordInputCorrect(){
        MangaDexLoginHandler mangaDexLoginHandler = new MangaDexLoginHandler();

        List<HtmlInput> inputList = new ArrayList<>();
        HtmlForm htmlForm = mock(HtmlForm.class);
        HtmlPasswordInput expectedPasswordInput = mock(HtmlPasswordInput.class);
        inputList.add(expectedPasswordInput);
        when(htmlForm.getInputsByName(MangaDexLoginHtmlComponents.MANGADEX_LOGIN_PASSWORD_INPUT.getWebComponent()))
                .thenReturn(inputList);
        when(htmlForm.getInputByName(MangaDexLoginHtmlComponents.MANGADEX_LOGIN_PASSWORD_INPUT.getWebComponent()))
                .thenReturn(inputList);

        HtmlInput actualTextInput = mangaDexLoginHandler.getPasswordTextInput(htmlForm);
        assertEquals(expectedPasswordInput, actualTextInput);
    }

    @Test
    public void getRememberMeCheckBoxInput_checkBoxInputIsWrong_assertFieldNotFoundException(){
        MangaDexLoginHandler mangaDexLoginHandler = new MangaDexLoginHandler();

        HtmlForm htmlForm = mock(HtmlForm.class);
        List<HtmlInput> inputList = new ArrayList<>();
        when(htmlForm.getInputsByName(MangaDexLoginHtmlComponents.MANGADEX_LOGIN_REMEMBER_ME_CHECKBOX.getWebComponent()))
                .thenReturn(inputList);
        doThrow(new ElementNotFoundException("", "", ""))
                .when(htmlForm).getInputsByName(MangaDexLoginHtmlComponents.MANGADEX_LOGIN_REMEMBER_ME_CHECKBOX.getWebComponent());

        FieldNotFoundException e = assertThrows(FieldNotFoundException.class, () -> mangaDexLoginHandler.getRememberMeCheckBoxInput(htmlForm));
        assertEquals(MangaDexLoginErrors.MANGADEX_LOGIN_REMEMBER_ME_CHECKBOX_WRONG_ERROR.getError(), e.getMessage());
    }

    @Test
    public void getRememberMeCheckBoxInput_checkBoxInputIsNull_assertNullPointerException(){
        MangaDexLoginHandler mangaDexLoginHandler = new MangaDexLoginHandler();

        HtmlForm htmlForm = mock(HtmlForm.class);
        List<HtmlInput> inputList = new ArrayList<>();
        when(htmlForm.getInputsByName(MangaDexLoginHtmlComponents.MANGADEX_LOGIN_REMEMBER_ME_CHECKBOX.getWebComponent()))
                .thenReturn(inputList);
        doThrow(new NullPointerException(""))
                .when(htmlForm).getInputsByName(MangaDexLoginHtmlComponents.MANGADEX_LOGIN_REMEMBER_ME_CHECKBOX.getWebComponent());

        NullPointerException e = assertThrows(NullPointerException.class, () -> mangaDexLoginHandler.getRememberMeCheckBoxInput(htmlForm));
        assertEquals(MangaDexLoginErrors.MANGADEX_LOGIN_REMEMBER_ME_CHECKBOX_NULL_ERROR.getError(), e.getMessage());
    }

    @Test
    public void getRememberMeCheckBoxInput_checkBoxInputRetrieved_assertCheckBoxCorrect(){
        MangaDexLoginHandler mangaDexLoginHandler = new MangaDexLoginHandler();

        HtmlForm htmlForm = mock(HtmlForm.class);
        List<HtmlInput> inputList = new ArrayList<>();
        HtmlCheckBoxInput expectedCheckBoxInput = mock(HtmlCheckBoxInput.class);
        inputList.add(expectedCheckBoxInput);
        when(htmlForm.getInputsByName(MangaDexLoginHtmlComponents.MANGADEX_LOGIN_REMEMBER_ME_CHECKBOX.getWebComponent()))
                .thenReturn(inputList);
        when(htmlForm.getInputByName(MangaDexLoginHtmlComponents.MANGADEX_LOGIN_REMEMBER_ME_CHECKBOX.getWebComponent()))
                .thenReturn(inputList);

        HtmlCheckBoxInput actualCheckBoxInput = mangaDexLoginHandler.getRememberMeCheckBoxInput(htmlForm);
        assertEquals(expectedCheckBoxInput, actualCheckBoxInput);
    }

    @Test
    public void getLoginButton_loginButtonIsWrong_assertFieldNotFoundException(){
        MangaDexLoginHandler mangaDexLoginHandler = new MangaDexLoginHandler();

        HtmlPage htmlPage = mock(HtmlPage.class);
        when(htmlPage.getFirstByXPath(MangaDexLoginHtmlComponents.MANGADEX_LOGIN_LOGIN_BUTTON_XPATH.getWebComponent()))
                .thenReturn(null);
        FieldNotFoundException e = assertThrows(FieldNotFoundException.class, () -> mangaDexLoginHandler.getLoginButton(htmlPage));
        assertEquals(MangaDexLoginErrors.MANGADEX_LOGIN_BUTTON_XPATH_WRONG_ERROR.getError(), e.getMessage());
    }

    @Test
    public void getLoginButton_loginButtonRetrieved_assertLoginButtonCorrect(){
        MangaDexLoginHandler mangaDexLoginHandler = new MangaDexLoginHandler();

        HtmlPage htmlPage = mock(HtmlPage.class);
        HtmlButton expectedButton = mock(HtmlButton.class);
        when(htmlPage.getFirstByXPath(MangaDexLoginHtmlComponents.MANGADEX_LOGIN_LOGIN_BUTTON_XPATH.getWebComponent()))
                .thenReturn(expectedButton);

        HtmlButton actualButton = mangaDexLoginHandler.getLoginButton(htmlPage);
        assertEquals(expectedButton, actualButton);
    }

    @Test
    public void hasLoginSucceeded_URLisHomePage_assertTrue() throws MalformedURLException {
        WebClient webClient = mock(WebClient.class);
        WebWindow webWindow = mock(WebWindow.class);
        Page page = mock(Page.class);
        URL url = new URL(MangaDexHomePageComponents.MANGADEX_HOMEPAGE_URL.getWebComponent());
        MangaDexLoginHandler mangaDexLoginHandler = new MangaDexLoginHandler();

        when(webClient.getCurrentWindow()).thenReturn(webWindow);
        when(webWindow.getEnclosedPage()).thenReturn(page);
        when(page.getUrl()).thenReturn(url);

        assertTrue(mangaDexLoginHandler.hasLoginSucceeded(webClient));
    }

    @Test
    public void hasLoginSucceeded_URLisNotHomePage_assertFalse() throws MalformedURLException {
        MangaDexLoginHandler mangaDexLoginHandler = new MangaDexLoginHandler();
        URL url = new URL("https://somewrongurl.com");

        WebClient webClient = mock(WebClient.class);
        WebWindow webWindow = mock(WebWindow.class);
        Page page = mock(Page.class);
        when(webClient.getCurrentWindow()).thenReturn(webWindow);
        when(webWindow.getEnclosedPage()).thenReturn(page);
        when(page.getUrl()).thenReturn(url);

        assertFalse(mangaDexLoginHandler.hasLoginSucceeded(webClient));
    }

    @Test
    public void getHomePage_homePageIsWrong_throwsIOException() throws IOException {
        MangaDexLoginHandler mangaDexLoginHandler = new MangaDexLoginHandler();

        WebClient webClient = mock(WebClient.class);
        doThrow(new IOException()).when(webClient).getPage(MangaDexHomePageComponents.MANGADEX_HOMEPAGE_URL.getWebComponent());
        IOException e = assertThrows(IOException.class, () -> mangaDexLoginHandler.getHomePage(webClient));
        assertEquals(MangaDexLoginErrors.MANGADEX_LOGIN_HOME_PAGE_URL_WRONG_ERROR.getError(), e.getMessage());
    }

    @Test
    public void getHomePage_homePageIsNull_throwsNullPointerException() throws IOException {
        MangaDexLoginHandler mangaDexLoginHandler = new MangaDexLoginHandler();

        WebClient webClient = mock(WebClient.class);
        doThrow(new NullPointerException()).when(webClient).getPage(MangaDexHomePageComponents.MANGADEX_HOMEPAGE_URL.getWebComponent());
        NullPointerException e = assertThrows(NullPointerException.class, () -> mangaDexLoginHandler.getHomePage(webClient));
        assertEquals(MangaDexLoginErrors.MANGADEX_LOGIN_HOME_PAGE_URL_NULL_ERROR.getError(), e.getMessage());
    }

    @Test
    public void getHomePage_homePageIsCorrect_assertPagesEqual() throws IOException {
        MangaDexLoginHandler mangaDexLoginHandler = new MangaDexLoginHandler();

        WebClient webClient = mock(WebClient.class);
        HtmlPage expectedPage = mock(HtmlPage.class);
        when(webClient.getPage(MangaDexHomePageComponents.MANGADEX_HOMEPAGE_URL.getWebComponent())).thenReturn(expectedPage);
        HtmlPage actualPage = mangaDexLoginHandler.getHomePage(webClient);
        assertEquals(expectedPage, actualPage);
    }

    @Test
    public void getUserAnchor_userAnchorNotFound_assertEmptyOptional() throws IOException{
        MangaDexLoginHandler mangaDexLoginHandler = new MangaDexLoginHandler();

        HtmlPage htmlPage = mock(HtmlPage.class);
        doThrow(new ElementNotFoundException("", "", ""))
                .when(htmlPage).getAnchorByHref(MangaDexHomePageComponents.MANGADEX_USER_HREF.getWebComponent());

        Optional<HtmlAnchor> userAnchor = mangaDexLoginHandler.getUserAnchor(htmlPage);
        assertTrue(userAnchor.isEmpty());
    }

    @Test
    public void getUserAnchor_userAnchorFound_assertOptionalFilledAndCorrect() throws IOException {
        MangaDexLoginHandler mangaDexLoginHandler = new MangaDexLoginHandler();

        HtmlPage htmlPage = mock(HtmlPage.class);
        HtmlAnchor expectedAnchor = mock(HtmlAnchor.class);
        when(htmlPage.getAnchorByHref(MangaDexHomePageComponents.MANGADEX_USER_HREF.getWebComponent()))
                .thenReturn(expectedAnchor);

        Optional<HtmlAnchor> actualAnchor = mangaDexLoginHandler.getUserAnchor(htmlPage);
        assertTrue(actualAnchor.isPresent());
        assertEquals(expectedAnchor, actualAnchor.get());
    }
}
