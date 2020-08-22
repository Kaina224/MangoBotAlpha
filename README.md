# MangoBot
Discord bot meant to retrieve new updates from Mangadex and alert subscribers that chapters they are following are updated. For learning purposes.

Uses Discord4J and HTMLUnit.

Abandoned due to the following:
- Implementation of design has flaws due to attempt to login to an existing account; CAPTCHA detects the login attempt. No desire to circumvent their protection without the permission of the site owners
- HTMLUnit is extremely slow even with CSS disabled; Javascript would need to be enabled to check the login procedure so it adds a lot of extra run time
- Design decision was to login to the website and only check followed updates due to not wanting to continuously ping the website and contribute to potential DDOS situations; a member of the website has stated that up to a request per second is allowed, so this is not needed anymore
- Python has superior scraping capabilities, switching the implementation of the project to that

Original Design:

Database would have at least Series + Users, design needs to be fleshed out more 

Website would be scraped with HTMLUnit; bot account would login, and then check any series followed to see if any new chapters are updated; it would pull this information from the site, store the new chapter, and then notify users interested in the series that the chapter has come out. Once notified, it would store that that specific chapter has already sent a notification out to prevent spamming.

Scraped information would be communited to Discord useres via Discord4J via messages. 
The bot would be allowed to ping and notify users interested. As this is mainly a bot only meant for personal use and a few friends, scalability and large scale operation (ie, having to deal with character limit due to a large number of individuals being pinged) was not considered. 
