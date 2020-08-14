import discord4j.core.DiscordClient;
import discord4j.core.DiscordClientBuilder;
import discord4j.core.event.domain.message.MessageCreateEvent;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.HashMap;

public class MangoBot {

    private static HashMap<String, Command> commands;

    public static void main(String args[]) throws IOException{
        loadCommands();
        final DiscordClient client = new DiscordClientBuilder(args[0]).build();
        client.getEventDispatcher().on(MessageCreateEvent.class)
                .flatMap(event -> Mono.justOrEmpty(event.getMessage().getContent())
                        .flatMap(content -> Flux.fromIterable(commands.entrySet())
                        .filter(entry -> content.startsWith('!' + entry.getKey()))
                        .flatMap(entry -> entry.getValue().execute(event))
                        .next()))
                .subscribe();
        client.login().block();
    }

    private static void loadCommands() throws IOException {
        CommandPopulator commandPopulator = new CommandPopulator();
        commands = commandPopulator.createBotCommands();
    }
}
