# MangoBot
Discord bot meant to retrieve new updates from Mangadex and alert subscribers that chapters they are following are updated. For learning purposes.

Uses Discord4J and HTMLUnit.

Abandoned due to the following:
- Implementation of design has flaws due to attempt to login to an existing account; CAPTCHA detects the login attempt. No desire to circumvent their protection without the permission of the site owners
- HTMLUnit is extremely slow even with CSS disabled; Javascript would need to be enabled to check the login procedure so it adds a lot of extra run time
- Design decision was to login to the website and only check followed updates due to not wanting to continuously ping the website and contribute to potential DDOS situations; a member of the website has stated that up to a request per second is allowed, so this is not needed anymore
- Python has superior scraping capabilities, switching the implementation of the project to that

