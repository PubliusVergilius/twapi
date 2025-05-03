Twapi is a Twitter like API. With Twapi, our primary responsibility is to allow people to post new messages and view messages posted by other people. After that, we’ll look at two additional actions this API might need: listing lots of messages and exporting all messages to a different storage system. It is a RESTfull API. Uses spring boot, spring modulith, TDD, Hateoas, Postgres.

✅ Domain Overview
Core Features: 

1 - Post a new message

2 - View a specific message

3 - List many messages (pagination)

4 - Export all messages (e.g., to a file or external system)

Modular Architecture

`twapi
├── application/       # REST Controllers, Request/Response mapping
├── domain/            # Domain models and interfaces
├── infrastructure/    # Persistence, file system, third-party integrations
├── support/           # Shared utils, error handling, DTO mapping, etc.
└── configuration/     # Modulith-specific config
`

## TODO
### Add a redis database in which commentaries to posts are temporarily stored. A second service or module will check if there is any explict word. The user id will be the store in a list. You can have a dictionnary of explicit words or use AI to detect any harmfull sentence.
