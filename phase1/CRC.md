#Server#
| Responsibilities                      | Collaborators |
|:--------------------------------------|:-------------:|
|Create connection with Users           |Board          |
|Disallow banned user from Connecting   |User           |
|Keep Admin info.                       |Admin          |
|Keep boards info.                      |               |
|Create/delete Boards                   |               |
|Keep User info.                        |               |
|Manages User                           |               |

#Message#
| Responsibilities                      | Collaborators |
|:--------------------------------------|:-------------:|
|Send messages to Board.                |Board          |
|Keep Message logs such as <br> author(User), time it was posted, related topic|User|
|Store Message string                   |               |

#Board#
| Responsibilities                      | Collaborators |
|:--------------------------------------|:-------------:|
|Keep Moderator                         |Moderator      |
|Store Messages                         |Server         |
|Disallow banned User from joining      |               |
|Keep User info. Show messages to User  |               |
|Keep Board info(topic,tags,etc)        |               |

#User#
| Responsibilities                      | Collaborators |
|:--------------------------------------|:-------------:|
|Join boards                            |Server         |
|Create new board with new topics       |Board          |
|Send & receive messages                |               |
|Edit user info (nickname, description) |               |
|See avaliable topics                   |               |
|Username/password                      |               |

#Moderator#
| Responsibilities                      | Collaborators |
|:--------------------------------------|:-------------:|
|Inherit User, has higher privilege -<BR> Have control over specific Boards|Server|
|Ban Users from the board               |Board          |
|Delete messages in the Board           |               |
|Set announcements                      |               | 

#Topic Owner#
| Responsibilities                      | Collaborators |
|:--------------------------------------|:-------------:|
|Inherit Moderator, has higher privilege -<BR> Have control over his/her own topic|Board|
|Promote user to moderator              |               |

#Admin#
| Responsibilities                      | Collaborators |
|:--------------------------------------|:-------------:|
|Inherit Moderator, has higher privilege -<BR> Have control over Server|Server|
|Ban Users from the Server              |Board          |
|Edit User info                         |               |
|Promote/demote Users                   |               |

##Scenario 1##

User Story: As a female who does not want to reveal the fact that she is female, I want to be able to post messages anonymously. 

This could be done by using the Message and the Board class. Once a user indicates he wishes to be anonymous the User field in the Message class would simply be replaced by "anonymous" and then passed onto the Board class for posting.

##Scenario 2##

User Story: In order to find things that I like, I want to be able to see similar topics.

This could be utilized with the Board class. It could try to find boards that share similar tags or have titles that match some words. It could find these boards by iterating over the list contained by the Server class

##Scenario 3##

User Story: As a TA  I want to be able to remove students from a topic.

This could be done by assigning the TA a Topic Owner class. With this she could ban users she does not want in the conversation. The ban would be maintained by the Board class and reject the user if they would try to rejoin.
