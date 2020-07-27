public enum MangaDexLogin {
    MANGADEX_HOMEPAGE("https://mangadex.org/"),
    MANGADEX_LOGIN_PAGE("https://mangadex.org/login"),
    MANGADEX_LOGIN_FORM("login_form"),
    MANGADEX_LOGIN_USERNAME_DIV("login_username"),
    MANGADEX_LOGIN_PASSWORD_DIV("login_password");

    private String webComponent;

    public String getWebComponent(){
        return this.webComponent;
    }

    private MangaDexLogin(String webComponent){
        this.webComponent = webComponent;
    }
}
