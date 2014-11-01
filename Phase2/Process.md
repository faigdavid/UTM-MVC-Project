Meeting 1

Date: October 22nd, 2014

Location: TA's office in DH building

Quick Summary: This was our first meeting and we discussed the various features that we hoped to implement with the TA, how the program should be designed and asked various questions about the marking. 

Important Ideas:

We were slightly overzealous in terms of what features we wanted to do and the TA cooled our enthusiasm. We decided to stick to the bare minimum and just deliver a basic working project rather than promising features like anonymous posting during Phase 2. We decided that in general, we should be able to post and read messages, join boards, and log in.

We decided against implementing actual features and code during this sprint and thought that it would be far more effective if we perfected our designs and how everything worked together before starting actual code. 

Meeting 2

Date: October 27th, 2014

Location: Davis building in UTM

Quick Summary: 

During this meeting we completely revamped the design of our program and clarified our CRC cards, and this was the focus of our sprint from the previous week rather than actual code. Doing a retrospective meeting for this section would not make much sense since in the last sprint we mainly discussed what our design should be like and we discussed it together rather than implementing actual features. 

Important Ideas:

-Removed Superboard class:
  We decided to do this because it did not seem like it was useful. It's function was replaced by getAllBoards() in the      Boards DAO.
-Changed the responsibilities of the Board class and Messaging class:
  This was due to an agreement between team members that the board class should not be storing the messages, and should only store info directly related to the board.

We decided to complete the basic skeleton code and non-DAO classes for our first sprint. This would mean that we should complete the basic outline of the program and every class that is not a DAO, like Authenticate, Board, and User. We decided to split these tasks up a bit vaguely, that is a general topic was assigned to each person but they could with another person at any time and some parts were up for grabs. 

User Stories:

Our main focus was on two user stories. One of these was to effectively authenticate and create a user as per Bin's user story we discussed in the release plan. This would include a user being able to create an account and log in. 

The second user story we hoped to implement was to be able to join specific boards. For this stage we decided that being able to join one board at a time was enough for this sprint and decided not to focus on subscribing to multiple boards.

Meeting 3: 

Date: October 29th, 2014

Location: Davis Building in UTM

Quick Summary:

Since we were running on a very tight schedule we decided to combine the retrospective/review meeting and the meeting for the next sprint. There was simply not enough time for all of our group members to meet twice. During this meeting we discussed the necessity of having proper naming conventions and how the DAOs should function. 

Important Ideas: 

  After a review of our previous code we saw that the way everyone named variables was very inconsistent. This was a problem because it made our code harder to read and could prove to be problematic in the future. So we agreed on number of common conventions like naming variables starting with a lower case letter and the rest of the words in the name starting with a capital letter for example, userLocalDAO. 
  
  This was also the meeting during which we focused on our DAOs. We decided how we would utilize the interfaces and the actual DAO classes, since during a review of the code we saw that the interfaces were implemented incorrectly in terms of syntax. We also decided on how we should store the information since some consistency was desperately needed. In the end a major decision was to move board info from one giant file, to a collection of files where each .txt represented a board. This simplified the error checking process by quite a bit since we could just check whether a certain file exists to see whether a board exists. 
  
Review:

  The main decisions that came from the review are listed above, mainly naming conventions and the DAO. As to who finished what, it was slightly more confusing. While everyone in the team did the work assigned to them a lot of people decided to rewrite and modify other people's files to fix bugs and reflect their own code better. This was problematic as not everybody decided to use github issues to communicate. Percentage wise, everyone did 100% of the work assigned to them but around 30-40% of the work was later re-written by someone else. This wasn't necessarily a problem but it did create some issues that could have been solved by better communication.
  
User Stories:

We decided to improve the board utilizing the user story that called for the ability to subscribe to multiple boards. This would allow the user to read messages from more than a single board and topic.

A new user story that we decided to do was the basic ability to post and read messages. Every board that the user subscribed to could both be posted to and send messages to be read to the client. 

Meeting 4:

Date: October 31st 2014

Location: IB250 room in UTM

Quick Summary:

This was our final meeting in which the primary focus was on reviewing the work that we did and fixing some last minute bugs. It was a relatively short meeting since everything we wanted to do was done and there wasn't much to discuss. 

Important Ideas:

There was a bug where the messages did not print the correct names and instead printed the username of the current logged in user. This was one of the bugfixes we focused on during the meeting.

Review:

Looking over the code everything seemed coherent as the communication process was smoothed out since the last meeting. The main things we discussed was how the code worked, but in general every class did what we agreed it should do in our previous sprint and everything worked smoothly together. There were some issues with using absolute paths vs relative paths, but it was not a major problem. 

User Stories: 

None, since we effectively completed them all. 

Daily Meetings: 

In all probability our biggest decision was that we stayed away from daily scrum meetings. This was because everyone in the group was incredibly busy during midterm season, and there was no feasible way for everyone to work in regular intervals or meet as a team. We decided to instead have large scrum meetings, which happened at essentially every other day due to our schedule. Our scrum meetings were then essentially the same as normal scrum meetings, but also with the content that would normally be covered in the supposed daily meetings. We of course could have done daily meetings but it would have taken an inordinate amount of time and would have severely slowed our process and impacted our marks. 

When we did attempt daily meetings it was typically on Skype and we were working from home. We attempted to have an official daily meeting on two days, October 25th and October 30th. The logs for these meetings were uploaded to the phase 2 directory. 

Scrum Process Decisions:

Another decision that we made was regarding to the leader of the team. At first one of the group members volunteered to be the scrum leader, but this didn't particularly work out as it didn't seem like there was anything the scrum leader should have done different than the other team members. We met together, discussed things together, assigned issues together and worked together. A leader wasn't necessary since we arrived at our decisions via a discussion involving the entire group. 

As to our usage of the issuing system, while we agreed we would use it more it was mostly an afterthought rather than something we actually did to increase efficiency. It would often be the case that everyone would agree on what the issue is and how we would fix it and someone would submit the issue for the sake of formality. Initially we planned to use the issuing system to distinguish what each user was working on at the time, but this turned out to be ineffective. 
