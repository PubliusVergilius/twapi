Twapi is a Twitter like API.
With Twapi, our primary responsibility is to allow people to post new messages and
view messages posted by other people.
After that, we’ll
look at two additional actions this API might need: listing lots of messages and export-
ing all messages to a different storage system.
It is a RESTfull API.
Uses spring boot, spring modulith, TDD, Hateos, MongoDB

✅ Domain Overview
Core Features:

1 - Post a new message

2 - View a specific message

3 - List many messages (pagination)

4 - Export all messages (e.g., to a file or external system)

Modular Architecture

twapi/
├── application/       # REST Controllers, Request/Response mapping
├── domain/            # Domain models and interfaces
├── infrastructure/    # Persistence, file system, third-party integrations
├── support/           # Shared utils, error handling, DTO mapping, etc.
└── configuration/     # Modulith-specific config
