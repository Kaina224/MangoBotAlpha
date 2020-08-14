public enum MangaDexLoginErrors {
    MANGADEX_LOGIN_PAGE_URL_WRONG_ERROR("Login page for Mangadex has been changed. " +
            "                           Please update the MangaDexLoginHtmlComponents enum to match changed URL."),
    MANGADEX_LOGIN_PAGE_URL_NULL_ERROR("Page name input somehow ended up being null. " +
                                       "Check your changes to ensure this doesn't happen."),
    MANGADEX_LOGIN_FORM_NULL_ERROR("The login form supplied somehow ended up being null. " +
                                   "Check your changes to ensure this doesn't happen."),
    MANGADEX_LOGIN_FORM_WRONG_ERROR("The login form has changed from the previous working iteration. " +
                                    "Please update the MangaDexLoginHtmlComponents enum to match changed form name."),
    MANGADEX_LOGIN_USERNAME_INPUT_WRONG_ERROR("Username input name for Mangadex has been changed. " +
                                              "Please update the MangaDexLoginHtmlComponents enum to match changed input name."),
    MANGADEX_LOGIN_USERNAME_INPUT_NULL_ERROR("The username input name supplied somehow ended up being null. " +
                                             "Check your changes to ensure this doesn't happen."),
    MANGADEX_LOGIN_PASSWORD_INPUT_WRONG_ERROR("Password input name for Mangadex has been changed. " +
                                              "Please update the MangaDexLoginHtmlComponents enum to match changed input name."),
    MANGADEX_LOGIN_PASSWORD_INPUT_NULL_ERROR("The password input name supplied somehow ended up being null. " +
                                             "Check your changes to ensure this doesn't happen."),
    MANGADEX_LOGIN_REMEMBER_ME_CHECKBOX_WRONG_ERROR("The remember me check box name supplied has been changed. " +
                                                    "Please update the MangaDexLoginHtmlComponents to match changed checkbox name."),
    MANGADEX_LOGIN_REMEMBER_ME_CHECKBOX_NULL_ERROR("The checkbox input name supplied somehow ended up being null. " +
                                                   "Check your changes to ensure this doesn't happen."),
    MANGADEX_LOGIN_CREDENTIALS_PROPERTY_FILES_MISSING(PropertiesFileNames.MANGADEX_LOGIN_CREDENTIALS.getFileName() +
                                                     " is missing. Check the project to ensure the file is there. "),
    MANGADEX_LOGIN_LOGIN_BUTTON_WRONG_ERROR("The login button name supplied has been changed. " +
                                            "Please update the MangaDexLoginHtmlComponents to match changed button name. "),
    MANGADEX_LOGIN_LOGIN_BUTTON_NULL_ERROR("The login button name supplied somehow ended up being null. " +
                                           "Check your changes to ensure this doesn't happen. "),
    MANGADEX_LOGIN_FAILED_LOGIN_ERROR("Either the username or password supplied in the credentials file is incorrect. " +
                                      "Manually login to the account to check it's still working, and then change the " +
                                      "credentials supplied in the file to match a working login."),
    MANGADEX_LOGIN_HOME_PAGE_URL_WRONG_ERROR("Home page for Mangadex has been changed. " +
                                             "Please update the MangaDexHomePageComponents to match changed home URL. "),
    MANGADEX_LOGIN_HOME_PAGE_URL_NULL_ERROR("The homepage URL supplied somehow ended up being null. " +
                                            "Check your changes to ensure this doesn't happen.");

    private String error;

    MangaDexLoginErrors(String error){
        this.error = error;
    }

    public String getError(){
        return error;
    }
}
