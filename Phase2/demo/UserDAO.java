public interface User_DAO {
	public void storeUserInformation();
	public void getUserInformation();
	public void changeUserInformation(User user);
}

public class UserDAO extends User_DAO {

}
