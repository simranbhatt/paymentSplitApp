package Service;

import Model.User;

import java.util.HashMap;

public class UserService {
    private static HashMap<Integer, User> allUsers = new HashMap<>();

    public User addUser(int userId, String userName, String userEmail, String userMobileNumber) {
        User newUser = new User(userId, userName, userEmail, userMobileNumber);
        allUsers.put(userId, newUser);
        return newUser;
    }

    public HashMap<Integer, User> getAllUsers() {
        return allUsers;
    }

    public User getUserById(int userId) {
        return allUsers.get(userId);
    }
}
