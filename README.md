# MangoBot
Discord bot meant to retrieve new updates from manga related sites and alert subscribers that chapters they are following are updated.

Project in work; not sure of viability. Will be abandoned if unfeasible.

Uses Discord4J and HTMLUnit.

TODO for LoginHandler:
- Add check to see if login is necessary
- Generate a thread to handle login

TODO for MangaDexWebClient:
- Have it be generated in a separate thread to handle login
- Have it call and test the loginhandler

TODO for BotCommands:
- Remove property file usage for generating static commands and have them be populated via enums
