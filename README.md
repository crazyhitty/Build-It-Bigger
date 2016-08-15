# Build-It-Bigger
Simple app that loads jokes from java library, android library and google app engine module.

# Installation instructions
1. Download the project or clone it via git.
2. In project root, use command `./gradlew installFreeDebug` to install free variant of the app and `./gradlew installPaidDebug` to install paid variant of the app.
3. Then use command `./gradlew appengineRun` to start the google app engine backend module and use `./gradlew appengineStop` to stop it.
4. You can test if the app engine with its api is working correctly or not by using a custom task `startGoogleAppEngineTesting`, which you can further run using command `./gradlew startGoogleAppEngineTesting`. This task will automatically run the app engine, run the instrumentation tests and then stop it.

# Api settings
In order to run google app engine properly on your localhost machine, please modify the constants `LOCAL_HOST_IP_ADDRESS` and `PORT` located in `app\src\main\java\com\udacity\gradle\builditbigger\data\ApiSettings.java` according to your requirements.
