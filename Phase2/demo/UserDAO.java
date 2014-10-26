public interface BoardMessages_DAO {
	public void storeUserInformation(String Username, int ID);
	public void removeUserInformation(String Username, int ID);
	public void changeUserInformation(User user);
}

public class UserDAO implements BoardMessages_DAO {

}
