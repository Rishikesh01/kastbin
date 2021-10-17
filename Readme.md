# License
[GPL 3](https://github.com/Rishikesh01/kastbin/blob/master/LICENSE)

# Kastbin

Kastbin is a backend clone of pastbin
The project is in its beta stage

# Endpoints
``/past``-accepts POST request.Used for pasting the text.

``/past/{url}``-accepts GET request.To view the past.

``/user/profile`` -accepts GET and PUT request.To view and update user profile.

``/user/past``- accepts GET request.To view all the pasts made by the user.

``/signup``- accepts POST request.To sign-up user.

``/basic`` - authenticates user using Http basic(Email and password).

``/oauth`` - used for both authentication and registering user.

``/admin/action`` - accepts PUT request.used to ban or lock a user

``/admin/active/users`` - accepts GET request.returns all the current active users

``/admin/action/taken/id`` - accepts POST request.returns details of action taken on user

``/admin/signup`` - accepts POST request if the request was made by an autheticated admin

# Prerequisites before running the project
- you need to get your google clientId and client secret from  [Google cloud console](https://console.cloud.google.com/apis/dashboard) and past those in application.yml which is located in resources folder
- set up mongodb/postgres and add username and password if you have configured them for mongodb/postgres to application.yml

# Building the project and running it

simply run ``./gradle build`` 
you can find the jar file in build/libs folder.

To run just use ``java -jar name.jar``



