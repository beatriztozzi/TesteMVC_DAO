package controller;

import model.UserModel;
import DAO.UserDAO;
import view.UserView;

public class UserController {
    private UserView userView;
    private UserDAO userDAO;
    private UserModel userModel;

    public UserController() {
        userView = new UserView();
        userModel = userView.createUser(new UserModel());

        userDAO = new UserDAO();
        userDAO.insertUser(userModel);

//        String username = userDAO.getByUsername(userModel.getUsername());
        UserModel byUsername2 = userDAO.getByUsername2(userModel.getUsername());
        userView.showUserInfo(byUsername2);
    }
}
