public enum StaticBotCommandEnums {
    PING_COMMAND("ping", "Pong!");

    private String command;
    private String response;

    StaticBotCommandEnums(String command, String response){
        this.command = command;
        this.response = response;
    }

    String getCommand(){
        return command;
    }

    String getResponse(){
        return response;
    }
}
