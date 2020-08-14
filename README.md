# MangoBot
Discord bot meant to retrieve new updates from manga related sites and alert subscribers that chapters they are following are updated.

Uses Discord4J and HTMLUnit.

TODO for LoginHandler:
- Figure out how to handle login in thread
- Have LoginHandler pass information to Bot and present it in Discord Client

TODO for MangaDexWebClient:
- Have it be generated in a separate thread to handle login
- Have it call and test the loginhandler

TODO for BotCommands:
- Remove property file usage for generating static commands and have them be populated via enums
