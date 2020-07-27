import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebWindow;
import com.gargoylesoftware.htmlunit.html.HtmlButton;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.junit.Test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class MangaDexWebClientTests {
    @Test
    public void getLoginPage_loginPageIsWrong_assertIOException() throws IOException {
        WebClient webClient = mock(WebClient.class);
        MangaDexWebClient mangaDexWebClient = new MangaDexWebClient(webClient);
        doThrow(new IOException()).when(webClient).getPage(MangaDexLoginHtmlComponents.MANGADEX_LOGIN_PAGE_URL.getWebComponent());
        IOException e = assertThrows(IOException.class, () -> mangaDexWebClient.getLoginPage());
        assertEquals(MangaDexLoginErrors.MANGADEX_LOGIN_PAGE_URL_WRONG_ERROR.getError(), e.getMessage());
    }

    @Test
    public void getLoginPage_loginPageIsNull_assertNullPointerException() throws IOException{
        WebClient webClient = mock(WebClient.class);
        MangaDexWebClient mangaDexWebClient = new MangaDexWebClient(webClient);
        doThrow(new NullPointerException()).when(webClient).getPage(MangaDexLoginHtmlComponents.MANGADEX_LOGIN_PAGE_URL.getWebComponent());
        NullPointerException e = assertThrows(NullPointerException.class, () -> mangaDexWebClient.getLoginPage());
        assertEquals(MangaDexLoginErrors.MANGADEX_LOGIN_PAGE_URL_NULL_ERROR.getError(), e.getMessage());
    }

    @Test
    public void getLoginForm_loginFormIsIncorrect_assertFieldNotFoundException(){
        WebClient webClient = new WebClient();
        MangaDexWebClient mangaDexWebClient = new MangaDexWebClient(webClient);
        HtmlPage htmlPage = mock(HtmlPage.class);
        doThrow(new ElementNotFoundException("","",""))
                .when(htmlPage).getFormByName(MangaDexLoginHtmlComponents.MANGADEX_LOGIN_FORM.getWebComponent());
        FieldNotFoundException e = assertThrows(FieldNotFoundException.class, () -> mangaDexWebClient.getLoginForm(htmlPage));
        assertEquals(MangaDexLoginErrors.MANGADEX_LOGIN_FORM_WRONG_ERROR.getError(), e.getMessage());
    }

    @Test
    public void getLoginForm_loginFormIsNull_assertNullPointerException(){
        WebClient webClient = new WebClient();
        MangaDexWebClient mangaDexWebClient = new MangaDexWebClient(webClient);
        HtmlPage htmlPage = mock(HtmlPage.class);
        doThrow(new NullPointerException())
                .when(htmlPage).getFormByName(MangaDexLoginHtmlComponents.MANGADEX_LOGIN_FORM.getWebComponent());
        NullPointerException e = assertThrows(NullPointerException.class, () -> mangaDexWebClient.getLoginForm(htmlPage));
        assertEquals(MangaDexLoginErrors.MANGADEX_LOGIN_FORM_NULL_ERROR.getError(), e.getMessage());
    }

    @Test
    public void getUsernameTextInput_usernameInputIsWrong_assertFieldNotFoundException(){
        WebClient webClient = new WebClient();
        MangaDexWebClient mangaDexWebClient = new MangaDexWebClient(webClient);

        HtmlForm htmlForm = mock(HtmlForm.class);
        List<HtmlInput> inputList = new ArrayList<>();
        when(htmlForm.getInputsByName(MangaDexLoginHtmlComponents.MANGADEX_LOGIN_USERNAME_INPUT.getWebComponent()))
                .thenReturn(inputList);
        doThrow(new ElementNotFoundException("", "", ""))
                .when(htmlForm).getInputsByName(MangaDexLoginHtmlComponents.MANGADEX_LOGIN_USERNAME_INPUT.getWebComponent());

        FieldNotFoundException e = assertThrows(FieldNotFoundException.class, () -> mangaDexWebClient.getUsernameTextInput(htmlForm));
        assertEquals(MangaDexLoginErrors.MANGADEX_LOGIN_USERNAME_INPUT_WRONG_ERROR.getError(), e.getMessage());
    }

    @Test
    public void getUsernameTextInput_usernameInputIsNull_assertNullPointerException(){
        WebClient webClient = new WebClient();
        MangaDexWebClient mangaDexWebClient = new MangaDexWebClient(webClient);

        HtmlForm htmlForm = mock(HtmlForm.class);
        List<HtmlInput> inputList = new ArrayList<>();
        when(htmlForm.getInputsByName(MangaDexLoginHtmlComponents.MANGADEX_LOGIN_USERNAME_INPUT.getWebComponent()))
                .thenReturn(inputList);
        doThrow(new NullPointerException(""))
                .when(htmlForm).getInputsByName(MangaDexLoginHtmlComponents.MANGADEX_LOGIN_USERNAME_INPUT.getWebComponent());

        NullPointerException e = assertThrows(NullPointerException.class, () -> mangaDexWebClient.getUsernameTextInput(htmlForm));
        assertEquals(MangaDexLoginErrors.MANGADEX_LOGIN_USERNAME_INPUT_NULL_ERROR.getError(), e.getMessage());
    }

    @Test
    public void getPasswordTextInput_passwordInputIsWrong_assertFieldNotFoundException(){
        WebClient webClient = new WebClient();
        MangaDexWebClient mangaDexWebClient = new MangaDexWebClient(webClient);

        HtmlForm htmlForm = mock(HtmlForm.class);
        List<HtmlInput> inputList = new ArrayList<>();
        when(htmlForm.getInputsByName(MangaDexLoginHtmlComponents.MANGADEX_LOGIN_PASSWORD_INPUT.getWebComponent()))
                .thenReturn(inputList);
        doThrow(new ElementNotFoundException("", "", ""))
                .when(htmlForm).getInputsByName(MangaDexLoginHtmlComponents.MANGADEX_LOGIN_PASSWORD_INPUT.getWebComponent());

        FieldNotFoundException e = assertThrows(FieldNotFoundException.class, () -> mangaDexWebClient.getPasswordTextInput(htmlForm));
        assertEquals(MangaDexLoginErrors.MANGADEX_LOGIN_PASSWORD_INPUT_WRONG_ERROR.getError(), e.getMessage());
    }

    @Test
    public void getPasswordTextInput_passwordInputIsNull_assertNullPointerException(){
        WebClient webClient = new WebClient();
        MangaDexWebClient mangaDexWebClient = new MangaDexWebClient(webClient);

        HtmlForm htmlForm = mock(HtmlForm.class);
        List<HtmlInput> inputList = new ArrayList<>();
        when(htmlForm.getInputsByName(MangaDexLoginHtmlComponents.MANGADEX_LOGIN_PASSWORD_INPUT.getWebComponent()))
                .thenReturn(inputList);
        doThrow(new NullPointerException(""))
                .when(htmlForm).getInputsByName(MangaDexLoginHtmlComponents.MANGADEX_LOGIN_PASSWORD_INPUT.getWebComponent());

        NullPointerException e = assertThrows(NullPointerException.class, () -> mangaDexWebClient.getPasswordTextInput(htmlForm));
        assertEquals(MangaDexLoginErrors.MANGADEX_LOGIN_PASSWORD_INPUT_NULL_ERROR.getError(), e.getMessage());
    }

    @Test
    public void getRememberMeCheckBoxInput_checkBoxInputIsWrong_assertFieldNotFoundException(){
        WebClient webClient = new WebClient();
        MangaDexWebClient mangaDexWebClient = new MangaDexWebClient(webClient);

        HtmlForm htmlForm = mock(HtmlForm.class);
        List<HtmlInput> inputList = new ArrayList<>();
        when(htmlForm.getInputsByName(MangaDexLoginHtmlComponents.MANGADEX_LOGIN_REMEMBER_ME_CHECKBOX.getWebComponent()))
                .thenReturn(inputList);
        doThrow(new ElementNotFoundException("", "", ""))
                .when(htmlForm).getInputsByName(MangaDexLoginHtmlComponents.MANGADEX_LOGIN_REMEMBER_ME_CHECKBOX.getWebComponent());

        FieldNotFoundException e = assertThrows(FieldNotFoundException.class, () -> mangaDexWebClient.getRememberMeCheckBoxInput(htmlForm));
        assertEquals(MangaDexLoginErrors.MANGADEX_LOGIN_REMEMBER_ME_CHECKBOX_WRONG_ERROR.getError(), e.getMessage());
    }

    @Test
    public void getRememberMeCheckBoxInput_checkBoxInputIsNull_assertNullPointerException(){
        WebClient webClient = new WebClient();
        MangaDexWebClient mangaDexWebClient = new MangaDexWebClient(webClient);

        HtmlForm htmlForm = mock(HtmlForm.class);
        List<HtmlInput> inputList = new ArrayList<>();
        when(htmlForm.getInputsByName(MangaDexLoginHtmlComponents.MANGADEX_LOGIN_REMEMBER_ME_CHECKBOX.getWebComponent()))
                .thenReturn(inputList);
        doThrow(new NullPointerException(""))
                .when(htmlForm).getInputsByName(MangaDexLoginHtmlComponents.MANGADEX_LOGIN_REMEMBER_ME_CHECKBOX.getWebComponent());

        NullPointerException e = assertThrows(NullPointerException.class, () -> mangaDexWebClient.getRememberMeCheckBoxInput(htmlForm));
        assertEquals(MangaDexLoginErrors.MANGADEX_LOGIN_REMEMBER_ME_CHECKBOX_NULL_ERROR.getError(), e.getMessage());
    }

    @Test
    public void getLoginButton_loginButtonIsWrong_assertFieldNotFoundException(){
        WebClient webClient = new WebClient();
        MangaDexWebClient mangaDexWebClient = new MangaDexWebClient(webClient);

        HtmlForm htmlForm = mock(HtmlForm.class);
        doThrow(new ElementNotFoundException("", "",""))
                .when(htmlForm).getButtonByName(MangaDexLoginHtmlComponents.MANGADEX_LOGIN_LOGIN_BUTTON.getWebComponent());

        FieldNotFoundException e = assertThrows(FieldNotFoundException.class, () -> mangaDexWebClient.getLoginButton(htmlForm));
        assertEquals(MangaDexLoginErrors.MANGADEX_LOGIN_LOGIN_BUTTON_WRONG_ERROR.getError(), e.getMessage());
    }

    @Test
    public void getLoginButton_loginButtonIsNull_assertNullPointerException(){
        WebClient webClient = new WebClient();
        MangaDexWebClient mangaDexWebClient = new MangaDexWebClient(webClient);

        HtmlForm htmlForm = mock(HtmlForm.class);
        doThrow(new NullPointerException(""))
                .when(htmlForm).getButtonByName(MangaDexLoginHtmlComponents.MANGADEX_LOGIN_LOGIN_BUTTON.getWebComponent());

        NullPointerException e = assertThrows(NullPointerException.class, () -> mangaDexWebClient.getLoginButton(htmlForm));
        assertEquals(MangaDexLoginErrors.MANGADEX_LOGIN_LOGIN_BUTTON_NULL_ERROR.getError(), e.getMessage());
    }

    @Test
    public void hasLoginSucceeded_URLisHomePage_assertTrue() throws MalformedURLException {
        WebClient webClient = mock(WebClient.class);
        WebWindow webWindow = mock(WebWindow.class);
        Page page = mock(Page.class);
        URL url = new URL(MangaDexLoginHtmlComponents.MANGADEX_HOMEPAGE_URL.getWebComponent());
        MangaDexWebClient mangaDexWebClient = new MangaDexWebClient(webClient);

        when(webClient.getCurrentWindow()).thenReturn(webWindow);
        when(webWindow.getEnclosedPage()).thenReturn(page);
        when(page.getUrl()).thenReturn(url);

        assertTrue(mangaDexWebClient.hasLoginSucceeded());
    }

    @Test
    public void hasLoginSucceeded_URLisNotHomePage_assertFalse() throws MalformedURLException{
        WebClient webClient = mock(WebClient.class);
        WebWindow webWindow = mock(WebWindow.class);
        Page page = mock(Page.class);
        URL url = new URL("https://somewrongurl.com");
        MangaDexWebClient mangaDexWebClient = new MangaDexWebClient(webClient);

        when(webClient.getCurrentWindow()).thenReturn(webWindow);
        when(webWindow.getEnclosedPage()).thenReturn(page);
        when(page.getUrl()).thenReturn(url);

        assertFalse(mangaDexWebClient.hasLoginSucceeded());
    }
}
