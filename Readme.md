# License
[GPL 3](https://github.com/Rishikesh01/kastbin/blob/master/LICENSE)

# Kastbin

Kastbin is an attempt to make pastbin backend clone.The whole project is written in kotlin and it uses spring and mongodb.

The project is in its beta stage

# Endpoints
``/past``-accepts POST request.Used for pasting the text.

``/past/{url}``-accepts GET request.To view the text.

``/user/profile`` -accepts GET and PUT request.To view and update user profile.

``/user/past``- accepts GET request.To view all the pasts made by the user.

``/signup``- accepts POST request.To sign-up user.

``/basic`` - authenticates user using Http basic(Email and password).

``/oauth`` - used for both authentication and registering user.

# Prerequisites before running the project
- you need to get your google clientId and client secret from  [Google cloud console](https://console.cloud.google.com/apis/dashboard) and past those in application.yml which is located in resources folder
- set up mongodb and add username and password if you have configured them for mongodb to application.yml

# Building the project and running it

simply run ``./gradle build`` 
you can find the jar file in build/libs folder.

To run just use ``java -jar name.jar``



