# API like Twitter but uses HATEOS

### The user Tweet timeline and user mention timeline endpoints allow you to get Posts using the user ID. In order to get the user ID from a username, you can use the new user lookup endpoint v2. Replace the USER_NAME with the username of your choice and XXXX with your own bearer token that you obtained.

`curl --request GET 'https://api.x.com/2/users/by/username/USER_NAME --header 'Authorization: Bearer XXXXXX'`

`{
   "data": {
       "id": "2244994945",
       "name": "Developers",
       "username": "XDevelopers"
   }
}`

`{
    "data": {
        "author_id": "2244994945",
        "text": "What did the developer write in their Valentine’s card?\n  \nwhile(true) {\n    I = Love(You);  \n}",
        "id": "1228393702244134912",
        "created_at": "2020-02-14T19:00:55.000Z"
    },
    "includes": {
        "users": [
            {
                "username": "XDevelopers",
                "name": "Developers",
                "id": "2244994945"
            }
        ]
    }
}`
