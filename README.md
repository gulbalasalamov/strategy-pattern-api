## Strategy Pattern
The Strategy Pattern defines a family of algorithms, encapsulates each one, and makes them interchangeable. 
Strategy is based on composition: you can alter parts of the object’s behavior by supplying it with different strategies that correspond to that behavior.
That's said, strategy pattern uses composition instead of inheritance. In the strategy pattern, behaviors are defined as separate interfaces and specific classes that implement these interfaces. This allows better decoupling between the behavior and the class that uses the behavior. The behavior can be changed without breaking the classes that use it, and the classes can switch between behaviors by changing the specific implementation used without requiring any significant code changes. Behaviors can also be changed at run-time as well as at design-time.


## Design and Data Structures
In line with guides introduced in Head First Object Oriented Design Analysis & Design book, I aim to create tried-and-true design pattern and principles.
Creating systems using composition gives you a lot more flexibility. Not only does it let you encapsulate a family of algorithms into their own set of classes, but it also lets you change behavior at runtime as long as the object you’re composing with implements the correct behavior interface.

## Demo App
The demo app is a personal music advisor communicating with Spotify API and makes preference-based suggestions and share links to new releases and featured playlists.
By implementing interaction with the API, it displays responses of API by converting JSON objects to strings. 
It reads user input and provides information at the user's request.

- featured - a list of Spotify-featured playlists with their links fetched from API;
- new - a list of new albums with artists and links on Spotify;
- categories - a list of all available categories on Spotify (just their names);
- playlists C_NAME - where C_NAME is the name of category. The list contains playlists of this category and their links on Spotify;
- exit - shuts down the application.

Full documentation how to work with api could be found at https://developer.spotify.com/documentation/web-api/reference/.

The music advisor in a nutshell us powerful guide to the world of music. Below is an output example of the described program.

<img src="https://github.com/gulbalasalamov/strategy-pattern-api/blob/master/src/main/java/strategy-pattern-api-demo.gif" alt="Inventory Management App gif" title="Inventory Management App gif" width="900"/>

# Contributing
Pull requests are welcomed. For major changes, please open an issue first to discuss what you would like to change. Thanks!

Happy Coding!!!

## Copyright
© Gulbala Salamov
