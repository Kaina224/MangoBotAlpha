public enum MangaDexHomePageComponents {
    MANGADEX_HOMEPAGE_URL("https://mangadex.org/"),
    MANGADEX_USER_HREF("/user/??????/fakename");

    private String component;

    MangaDexHomePageComponents(String component){
       this.component = component;
    }

    public String getWebComponent(){
        return component;
    }
}
