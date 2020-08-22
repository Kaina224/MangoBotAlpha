public enum DynamicBotCommandEnums {
    ATTEMPT_LOGIN("login");

    private String command;

    DynamicBotCommandEnums(String command){
        this.command = command;
    }

    String getCommand(){
        return this.command;
    }
}
