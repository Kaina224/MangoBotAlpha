public enum MangaDexHomePageComponents {
    MANGADEX_HOMEPAGE_URL("https://mangadex.org/"),
    MANGADEX_USER_HREF("/user/686317/kaina");

    private String component;

    MangaDexHomePageComponents(String component){
       this.component = component;
    }

    public String getWebComponent(){
        return component;
    }
}
