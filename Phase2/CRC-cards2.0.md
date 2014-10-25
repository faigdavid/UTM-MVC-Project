#Authenticator#
| Responsibilities                      | Collaborators |
|:--------------------------------------|:-------------:|
|Authenticate a user+password           |User_DAO       |
|Build a user object from User_DAO info |               |

#SuperBoard#
| Responsibilities                      | Collaborators |
|:--------------------------------------|:-------------:|
|Create/delete Boards                   |SuperBoard_DAO |
|Keep track of all Boards               |               |

#Board#
| Responsibilities                      | Collaborators |
|:--------------------------------------|:-------------:|
|Keep a Title (Topic name)              |BoardUsers_DAO |
|Keep a board ID                        |BoardMessages_DAO|
|Ask the DAOs to read/write Messages    |               |
|Ask the DAOs to change/read the user-list|             |

#User#
| Responsibilities                      | Collaborators |
|:--------------------------------------|:-------------:|
|Have a username                        |User_DAO       |
|Have an ID                             |Board          |
|Can pass commands to User DAO          |SuperBoard     |
|Tries to pass commands to Board        |               |
|Tries to pass commands to SuperBoard   |               |



#User_DAO#
| Responsibilities                      | Collaborators |
|:--------------------------------------|:-------------:|
|Store all user information             |               |
|Check user information                 |               |
|Change user information                |               |

#BoardMessages_DAO#
| Responsibilities                      | Collaborators |
|:--------------------------------------|:-------------:|
|Write to messages on a board           |               |
|Read from messages on a board          |               |

#BoardUsers_DAO#
| Responsibilities                      | Collaborators |
|:--------------------------------------|:-------------:|
|Store/remove users                     |               |
|Mark users as Admin/Moderator          |               |
|Mark users who are banned              |               |
|Get users who are on the board         |               |

#SuperBoard_DAO#
| Responsibilities                      | Collaborators |
|:--------------------------------------|:-------------:|
|Store/remove all Boards                |               |
|Get Boards on the SuperBoard           |               |
