public enum MangaDexHomePageComponents {
    MANGADEX_HOMEPAGE_URL("https://mangadex.org/"),
    MANGADEX_NAVIGATION_BAR_SUPPORTED_CONTENT("navbarSupportedContent"),
    MANGADEX_NAVIGATION_BAR_UNORDERED_LIST("navbar-nav"),
    MANGADEX_NAVIGATION_BAR_USER_LIST_ITEM("nav-item mx-1 dropdown show"),
    MANGADEX_USER_HREF("/user/686317/kaina");

    private String component;

    MangaDexHomePageComponents(String component){
       this.component = component;
    }

    public String getWebComponent(){
        return component;
    }
}
