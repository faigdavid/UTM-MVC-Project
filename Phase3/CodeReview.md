| Reviewer | Coder |
|:---------|:-----:|
| R-ed  | trallgorm |
| trallgorm   | R-ed |
| benbennza1 | shk0307 | 
| shk0307 | benbennza1 |
| ekelundh | faigdavid |
|faigdavid | ekelundh |

#Reviewer: R-ed #

-I found several issues on github such as: dirty worktrees, non fast-forward errors, and conflicts.
 I found these errors when working in CodeReview.md and ConsoleView.java.  I believe these errors
 occurred when Dmitry and I were both coding on the same files.
 
-Dmitry coded the instructions on how to use the console.  He made well use of user made functions
 to print the instructions, he could've used System.out.println but instead used the printString
 function coded by David.
 
-We had a bug where the console commands would be taken in as part of a message when we post to a board.
 Dmitry solved this bug by omitting the first three characters of the user input.
 
-Dmitry fixed the problem where all of us would manually fix the directory path in the database.  He
 added a function ((System.getProperty("user.dir")) that would automatically fix the directory 
 path regardless which computer we use.

#Reviewer: trallgorm #
The features which I focused most during this review are the credit section, the fixed regex, and the refresh function which can all be found here: https://github.com/csc301-fall2014/Proj-UTM-Team6-repo/commit/57e2b236e45fea32e334c9765b0ae3eacf6da2a7. The code that you wrote for the credit section was both succint and humorous. The refresh function worked in both cases and was easily understandable. However, the regex portion may not be entirely correct because it says /w would be an acceptable input for private messaging, which is problematic since we didn't actually implement private messaging. This would create some problems if the user entered /w as an input. However overall your code was of high quality and was very easily understandable.  

#Reviewer: benbennza1 #
* reminded that we do not need to implement more features when we do not have our release plan done yet
* suggested that since we have literally 6 assignments due in one weeks, we DO NOT need to implement GUI or anything fancy
* helped out with skeleton code, client, console I/O

NOTE: Our group had a really busy week, we had 2 assignments due on Monday, 1 assignment due on Wednesday, 2 on Friday (today). Please take that as an excuse for not making everything perfect. 

#Reviewer: shk0307 #
Major commit of benbennza1: client.java (https://github.com/csc301-fall2014/Proj-UTM-Team6-repo/commit/a50cd5a838a6a0390d39f18d9a7ad5d96eedc312)

- I found several minor bugs in the initial commit of client.java, but most of those were due to some critical methods in other classes were not done at the moment. ( https://github.com/csc301-fall2014/Proj-UTM-Team6-repo/issues/1)
- Those bugs were fixed in the end, and in doing so, we collaborated together to make some important decision on design of DAO and other classes. 
- His initial implementation of GUI (https://github.com/csc301-fall2014/Proj-UTM-Team6-repo/commit/db0ba845c3900f64f4777a5bbfdef4c34ea72308) was okay starter to implementing GUI for the program.

#Reviewer: ekelundh #
<type review here>

The multiples commits from the creation of Controller:

I looked through the creation of controller on monday the 10th november 2014. (There are a bunch). The few issues I noticed mainly had to do with how you created and stored your variables, and who called what. You accepted most of my changes while writting the node (as we used pair programming). 

I particularly like how you implemented the MVC as we saw in class to allow for multiple view use (will likely be useful for the GUI).

#Reviewer: faigdavid #
<type review here>
The multpile commits for ConsoleView:

His implementation of ConsoleView on November 10 was lightweight and simple. The use of switch cases bothered me, but for the sake of quick and simple code, it was the obvious choice. We discussed the sort of issues that might come up in the future, and agreed on final skeleton for ConsoleView that was eventually completed by the while team.

I particularly liked the use of strings to keep states. It could have been an enumeration, but for the sake of quick and simple code, it was very effective.
