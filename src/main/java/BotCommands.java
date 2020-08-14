public enum BotCommands {
    BOT_COMMAND_PING("ping", "Pong!");

    private String botCommand;
    private String botCommandResponse;

    BotCommands(String botCommand, String botCommandResponse){
        this.botCommand = botCommand;
        this.botCommandResponse = botCommandResponse;
    }
}
