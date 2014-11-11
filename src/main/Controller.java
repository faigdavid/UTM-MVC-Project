package main;

public class Controller implements ViewEventListener{
	private User user;
	private ModelEventListener view;
	public Controller(ModelEventListener view){
		this.user = null;
		this.view = view;
		view.addViewEventListener(this);
	}
	
	@Override
	public void register(String userName, String password1, String password2){
		UserDAO userDao = new UserLocalDAO();

		if (password1.equals(password2)) {
			this.user = userDao.createUser(userName, password1);
			if (this.user != null) {
				view.changeStateLoggedIn();
				view.printString("User Created.");
				
			}else{
				view.printString("Failed to create new user.");
			}
		} else {
			view.printString("The passwords did not match!");
		}
	}
	@Override
	public void changeBoardByName(String name) {
		if (assertLoggedIn()){
			BoardLocalDAO DAO = new BoardLocalDAO();
			Board toChange = DAO.getBoardByName(name);
			if (this.user.joinBoard(toChange.getBid()) == 1){
				view.changeStateInBoard();
			} else {
				view.printString("Failed to join the board.");
			}
		}else{
			view.printString("You are not logged in.");
		}
		
	}
	@Override
	public void changeBoardByBid(String bid) {
		if (assertLoggedIn()){
			if (this.user.joinBoard(bid) == 1){
				view.changeStateInBoard();
			} else {
				view.printString("Failed to join the board.");
			}
		}else{
			view.printString("You are not logged in.");
		}
		
		
	}
	@Override
	public void getBoardMessages() {
		if (assertLoggedIn()){
			MessageLocalDAO DAO = new MessageLocalDAO();
			DAO.getMessages(this.user.getcurrentBoard().getBid());
		}else{
			view.printString("You are not logged in.");
		}
		
	}

	
	@Override
	public void login(String username, String password) {
		user = new Authenticator().authenticateUser(username, password);
		if (user == null){
			view.printString("FAILED TO LOG IN");
		} else {
			view.changeStateLoggedIn();
		}
		return;
		
	}
	
	@Override
	public void post(String message) {
		if (user.post(message) != 1){
			view.printString("Message failed to post.");
		}
	}
	


	
	/*NON-OVERRIDES*/
	
	/*ALways call this to check that you've logged in.*/
	private boolean assertLoggedIn(){
		return this.user != null;
	}


	
	

	
	
}
