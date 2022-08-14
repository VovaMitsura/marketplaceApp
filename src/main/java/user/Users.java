package user;

import java.util.ArrayList;
import java.util.List;

/*Class Users contains list of users and method for checking whether user contain certain id,
 return user by given id*/
public class Users {

    private final List<User> users = new ArrayList<>();


    public List<User> getUsersList() {
        return users;
    }


    public boolean idExists(int id) {
        for (User user : users) {
            if (user.getId() == id) {
                return true;
            }
        }
        return false;
    }

    public User getUserById(int id) {
        if (idExists(id)) {
            for (User user : users) {
                if (user.getId() == id) {
                    return user;
                }
            }
        }

        return null;
    }

    @Override
    public String toString() {
        return "List of users: " + users + "\n";
    }

}
