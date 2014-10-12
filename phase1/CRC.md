#Server#
| Responsibilities                      | Collaborators |
|:--------------------------------------|:-------------:|
|Create connection with Users           |Board          |
|Disallow banned user from Connecting   |User           |
|Keep Admin                             |Admin          |
|Keep all the boards and show it to User|               |
|Create/delete Boards                  |               |

#Message#
| Responsibilities                      | Collaborators |
|:--------------------------------------|:-------------:|
|Send messages to Board.                |Board          |
|Keep Message informations such as <br> author(User), time it was posted, related topic|User|
|Store Message string                   |               |

#Board#
| Responsibilities                      | Collaborators |
|:--------------------------------------|:-------------:|
|Keep Moderator                         |Moderator      |
|Store Messages                         |               |
|Disallow banned User from joining      |               |

#User#
| Responsibilities                      | Collaborators |
|:--------------------------------------|:-------------:|
|Join boards                            |Server         |
|Create new board with new topics       |Board          |
|Send & receive messages                |               |
|Edit user info (nickname, description) |               |

#Moderator#
| Responsibilities                      | Collaborators |
|:--------------------------------------|:-------------:|
|Inherit User, has higher privilege -<BR> Have control over specific Boards|Server|
|Ban Users from the board               |Board          |
|Delete messages in the Board           |               |
|Set announcements                      |               | 

#Admin#
| Responsibilities                      | Collaborators |
|:--------------------------------------|:-------------:|
|Inherit Moderator, has higher privilege -<BR> Have control over Server|Server|
|Ban Users from the Server              |Board          |
|Edit User info                         |               |
|Promote/demote Users                   |               |

