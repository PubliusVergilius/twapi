Twapi is a Twitter like API. With Twapi, our primary responsibility is to allow people to post new messages and view messages posted by other people. After that, we’ll look at two additional actions this API might need: listing lots of messages and exporting all messages to a different storage system. It is a RESTfull API. Uses spring boot, spring modulith, TDD, Hateoas, Postgres.

✅ Domain Overview
Core Features: 

1 - Post a new message

2 - View a specific message

3 - List many messages (pagination)

4 - Export all messages (e.g., to a file or external system) through a Job (spring batch)

Modular Architecture with Spring Modulith
Built-in Authentication on the gateway with Spring Cloud Gateway
Concurrent by default with Webflux
Kubernetes, Docker

![image](https://github.com/user-attachments/assets/3430ecc0-625e-4f23-8647-96a9c865456a)

## TODO
- make user have unique username
- if the username is not registered, it responds with an error ("user not registerd")
- make user create and retrieve his own posts
### Add Modulith package
### Add Spring Gateway
- Create another project
- Configure a multi spring web context
- Configure the auth. service api local to the gateway
- Configure the gateway api to be public, sitting between all other services and client
### Add flyway
### Add TDD
- Controllers are tested in isolation
- Services are tested in integration with H2 in-memory database
### Add a Redis database in which commentaries to posts are temporarily stored. A second service or module will check if there is any explict word. The user id will be the store in a list. You can have a dictionnary of explicit words or use AI to detect any harmfull sentence.
