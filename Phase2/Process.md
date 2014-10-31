Meeting 1
Date: October 22nd, 2014
Location: TA's office in DH builduing


Meeting 2
Date: October 27th, 2014
Location: Davis builduing in UTM

Quick Summary: During this meeting we completely revamped the design of our program and clarified our CRC cards, and this was the focus of our sprint from the previous week rather than actual code. Doing a retrospective meeting for this section would not make much sense since in the last sprint we mainly discussed what our design should be like and we discussed it together rather than implementing actual features. 

Important Ideas:
-Removed Superboard class
  We decided to do this because it did not seem like it was useful. It's function was replaced by getAllBoards() in the      Boards DAO.
-Changed the responsibilities of the Board class and Messaging class
  This was due to an agreement between team members that the board class should not be storing the messages, and should only store info directly related to the board.

We decided to complete the basic skeleton code and non-DAO classes for our first sprint. This would mean that we should complete the basic outline of the program and every class that is not a DAO, like Authenticate, Board, and User. We decided to split these tasks up a bit vaguely, that is a general topic was assigned to each person but they could with another person at any time and some parts were up for grabs. 

Our main focus was on two user stories. One of these was to effectively authenticate and create a user as per Bin's user story we discussed in the release plan. This would include a user being able to create an account and log in. 

The second user story we hoped to implement was to be able to join specific boards. For this stage we decided that being able to join one board at a time was enough for this sprint and decided not to focus on subcribing to multiple boards.


 
 
  
 




#### `Phase2/Process.md`
 
 A report describing your scrum-like process. The report should include the following items:
 
  * A summary of your planning and review meetings for both sprints (i.e. 4 meetings in total).
    * Includes date and location.
    * Please keep it short, and give us the highlights.
    * Include important decisions you've made, and arguments for/against them.
    * Tip: You can use GitHub to label all the user stories you plan to complete during a sprint, and include a link to an issue search result ([example](https://github.com/csc301-fall2014/CSC301H1F-L5101-Home/issues?q=is%3Aissue+label%3ARelease-Plan-1+)) in your planning meeting summary.
    * For the review/retrospective meeting, we would like to see some numbers and/or interesting statistics.   
      For example: Percentage of planned work that was actually completed, distribution of completed work among team members, percentage of planned work completed categorized by priority, etc.    
      The point is to evaluate the estimations that you've made during the planning meeting.
  * A summary of your "daily" scrum meetings.
    * Please include date and location.
    * If you use a chat, you can simply copy-paste the highlight of the chat.
    * Otherwise, please give us a very quick summary.    
      A good format would be a list, specifying the highlights of each member's update (each highlights should be 1-3 sentences long).
  * Description of how you used GitHub issue management system (e.g. naming conventions, labels, team's conventions, etc.)
  * Description of any other major decisions/conventions you may have made/used during the process, and why.
    * For example: Did you have a "scrum master"? Why? Do you think it was the right decision?
