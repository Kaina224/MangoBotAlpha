public enum MangaDexLoginHtmlComponents {
    MANGADEX_LOGIN_PAGE_URL("https://mangadex.org/login"),
    MANGADEX_LOGIN_FORM("login_form"),
    MANGADEX_LOGIN_USERNAME_INPUT("login_username"),
    MANGADEX_LOGIN_PASSWORD_INPUT("login_password"),
    MANGADEX_LOGIN_REMEMBER_ME_CHECKBOX("remember_me"),
    MANGADEX_LOGIN_LOGIN_BUTTON("login_button");

    private String webComponent;

    public String getWebComponent(){
        return this.webComponent;
    }

    MangaDexLoginHtmlComponents(String webComponent){
        this.webComponent = webComponent;
    }
}
