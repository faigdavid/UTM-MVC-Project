#Authenticator#
| Responsibilities                      | Collaborators |
|:--------------------------------------|:-------------:|
|Authenticate a user+password           |UserDAO        |
|Build a user object from User_DAO info |               |

#Console#
| Responsibilities                      	    | Collaborators   |
|:--------------------------------------------|:---------------:|  	
|Can ask user to authenticate his/her account |User  	  		    |
|Can do what board class can do               |Board			      |
|Can do what the user class can do  		  |Authenticator        |

#Board#
| Responsibilities                      | Collaborators   |
|:--------------------------------------|:---------------:|
|Has a Board ID              |BoardDAO		   	    |
|Has a Name              |		   	    |
|Has a Password              |		   	    |
|Keep a Title (Topic name)              |   	    |
|Keep a board ID                        |				          |
|Post Messages   						            |                 |
|Delete Messages                        |                 |

#User#
| Responsibilities                      | Collaborators |
|:--------------------------------------|:-------------:|
|Have a username                        |UserDAO        |
|Have an ID                             |Board          |
|Can delete a user from the server(self)|    			      |
|Can Leave a board       				        |               |
|Can join a board					              |               |
|Can post to a board					          |               |

#UserDAO#
| Responsibilities                      | Collaborators |
|:--------------------------------------|:-------------:|
|Can store all user information         |               |
|Can get user information               |               |
|Can save user information              |               |

#BoardDAO#
| Responsibilities                      | Collaborators |
|:--------------------------------------|:-------------:|
|Can create a board        				      |               |
|Can get a board               			    |               |
|Can delete a board               		  |               |
|Can get all boards               		  |               |

#MessagesDAO#
| Responsibilities                      | Collaborators |
|:--------------------------------------|:-------------:|
|Can get messages       				        |               |
|Can get messages from a board          |               |
|Can delete a message               	  |               |
|Can add a message to a board           |               |

#SubscriptionDAO#
| Responsibilities                      		     | Collaborators |
|:-----------------------------------------------|:-------------:|
|Can get subscriptions from a board     	       |               |
|Can get all boards that a user is subscribed to |               |
|Can subscribe a user to a board              	 |               |
|Can unsubscribe a user to a board               |               |
