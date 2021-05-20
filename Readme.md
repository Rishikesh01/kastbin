# Kastbin

Kastbin is an attempt to make pastbin backend clone attempt
it.The whole project is written in kotlin and it uses spring and mongodb.

The project is in its beta stage

# Endpoints
``/past``-accepts POST request.Used for pasting the text.

``/past/{url}``-accepts GET request.To view the text.

``/user/profile`` -accepts GET and PUT request.To view and update user profile.

``/user/past``- accepts GET request.To view all the pasts made by the user.

``/signup``- accepts POST request.To sign-up user.

``/basic`` - authenticates user using Http basic(Email and password).

``/oauth`` - used for both authentication and registering user.
