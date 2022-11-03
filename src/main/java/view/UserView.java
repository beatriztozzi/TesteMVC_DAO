package view;

import model.UserModel;

import java.util.Scanner;

public class UserView {
    Scanner scanner;

    public UserView() {
        this.scanner = new Scanner(System.in);

    }

    public void showUserInfo (UserModel userModel) {
        System.out.println("User: " + userModel.getUsername());
        System.out.println("E-mail: " + userModel.getEmail());
        System.out.println("Password: " + userModel.getUpassword());
    }

    public UserModel createUser (UserModel userModel) {
        System.out.println("Username: ");
        userModel.setUsername(scanner.nextLine());

        System.out.println("E-mail: ");
        userModel.setEmail(scanner.nextLine());

        System.out.println("Password: ");
        userModel.setUpassword(scanner.nextLine());

        return userModel;
    }
}
