# COM/212.Final --- Jay, Russell, Brooke, Miles

GUI based program that can store movies in a database to be accessed by users.

Movies are objects stored by both ID and Date, it was given that these values are assumed to always be unique. 
Moives can be accessed directly by users using their ID or by the lowest rating by admins. 
Admins can remove movies from avaliblity and users can added them to their wishlists and watch them whenever they want. 
If a user tries to access an unavalible movie, they are given a pop-up prompt and the movie is removed from their wishlist.

Users are their own objects stored by credit card, this number is assumed to be unique and under 6 digits in length 
(any longer and it runs the risk of being a long var type and not an int var type). Users can sort movies by ID or date and access them individually. 
They can also view their own watch history and full wishlist from a menubar.

Admins are not an object and use a simple string comparison for login, the username for the admin login is "username" and the password is "password".
Admins can access users files at any time by CC number and either their names and emails, as well as view their wishlists. Admins can also add movies 
and remove movies from avaliblity based on the lowest scored movies.

To run this program, please fork and clone the repo, run javac \*.java and then java WelcomeGUI, if you create a user, you need to login before closing and 
always remember to press logout in both admin and user GUI, otherwise things will not save.

Please note than the terminal files, while vauely functional, are not intended to be used as the main product, they are mainly a testbed for new functions and ideas.
They do not interact with the same serialized files so information will not be carried from the CLI to GUI interfaces.

Thanks for checking out our program!
- Jay, Russell, Brooke, Miles
