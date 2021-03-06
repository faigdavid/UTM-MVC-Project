# Architecture

There are three parts to the overall architecture: The Model, The DAOs, and the Views. 

We chose this MVC design pattern because it is a neat and efficient way to separate the tasks that the program needs to perform. The reason behind this decision was to make it easy to give each group member a component to implement on their own. 
This proved to be a very good decision since the MVC design decoupled the components nicely, making our code easy to put together. Furthermore, switching the implementation of certain components was as easy as importing a different package; for example, we could switch the imported DAOs to local, and the entire program would run using local files instead of the SQL server. (The local DAOs are currently out of date so we technically can't, but it's nice to think we can).

-----

## View

*The View interacts only with the Model via the ModelController class. Each view implements the ModelEventListener interface, so that they may respond to data and state updates from the model.

*We implemented two views: ConsoleView and GUIView. The console view was made for the initial demo, and to make sure the Model and DAOs worked properly. The GUIView was later implemented with special features such as creating a thread that auto-refreshes the board.

-----

## Class: ModelController

*The ModelController uses a Listener design pattern: It keeps a list of views that all implement the ModelEventListener interface, and it sends commands such as "recieveBoards()" to all of the listening views.

*The ModelController also implements the ViewEventListener interface. So, commands such as "requestBoards()" can be sent from the View to the ModelController. In order to implement such commands, the ModelController interacts with the Model and DAOs to request data or data updates.

-----

## Model

*The Model itself is just the Object classes such as User and Board and Message, all of which are constructed by the DAOs as they access the data from the servers.

*All the classes were constructed with their relevant information (name, id, etc) as well as special information and methods that ensure the consistency of the data. 

For example: The user objects have a post(message) function that posts the given message to the user's currentBoard attribute. Without the existence of both the user, and the user's currentBoard, it is impossible to post a message.

-----

## DAOs

*The DAOs are multiple classes all of which access the SQL server to either request or update the data.

*The DAOs all use prepared statements and proper escape techniques to avoid the possibility of SQL injection.

-----

## SQL Server

*A Postgresql server hosted by public website.
