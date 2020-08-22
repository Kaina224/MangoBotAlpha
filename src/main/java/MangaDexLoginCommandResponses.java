public enum MangaDexLoginCommandResponses {

    MANGADEX_LOGIN_ATTEMPT_ALREADY_OCCURRED_FAILED_ATTEMPT("```A failed login attempt has already occurred this session. " +
                                                           "Check your credentials and the site to ensure nothing has changed.```"),
    MANGADEX_LOGIN_ATTEMPT_ALREADY_OCCURRED_SUCCESSFUL_ATTEMPT("```A login attempt has already occurred this session. " +
                                                               "Another login does not need to occur.```"),
    MANGADEX_LOGIN_ATTEMPT_SUCCEEDED("```Login successful! (´｡• ω •｡`)```"),
    MANGADEX_LOGIN_ATTEMPT_FAILED("```Login has failed. Add dynamic error checking to this message. .･ﾟﾟ･(／ω＼)･ﾟﾟ･.```");

    private String response;
    MangaDexLoginCommandResponses(String response){
        this.response = response;
    }

    String getResponse(){
        return response;
    }
}
