# Fergola Bot
![](https://shields.io/badge/JDA-5.0-violet) ![](https://shields.io/badge/v1.1-purple)

This is template repository for Discord bots written in Kotlin language.

## Release

`
version 1.1
`

## Technologies

- Kotlin 1.8.22
- JDA 5.0.0-beta.5
- JDK 20
- Gradle 8.4

## Application execution

1. Make sure your JAVA_HOME paths to jdk directory.
2. Clone repository:
```
git clone https://github.com/ExaggeratedRumors/fergola-bot
```
3. Create `.env` file in root directory and fill with content:
```env
 client_name=YOUR_BOT_NAME
 client_id=YOUR_CLIENT_ID
 client_secret=YOUR_CLIENT_SECRET
 public_key=YOUR_BOT_PUBLIC_KEY
 token=YOUR_BOT_TOKEN
 permission_integer=YOUR_PERMISSIONS_INTEGER
 admin_id=YOUR_ACCOUNT_ID
```
4. Edit `application.conf` file in directory `src/main/resources` defining your own commands parameters based on structure:
```conf
properties {
    prefix = "!"
}

commands {
    Draw {
        call = draw
        enabled = true
        req_prefix = true
        req_admin = false
    },
    Help {
        call = help
        enabled = true
        req_prefix = true
        req_admin = false
    },
    ...
}
```
5. Create your own commands by extending Command class:
```kotlin
class MyCommand(
    call: String, requirePrefix: Boolean, requireAdmin: Boolean
): Command(
    call = call,
    requirePrefix = requirePrefix,
    requireAdmin = requireAdmin
) {
    override fun execute(message: Message) {
        /** TODO **/
        println("My command has been executed.")
        message.author
            .openPrivateChannel()
            .complete()
            .sendMessage("You called $call command.")
            .queue()
    }
}
```
6. Open application root directory and run application:
```
./gradlew run
```