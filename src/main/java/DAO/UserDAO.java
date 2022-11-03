package DAO;

import model.UserModel;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
    private DataBaseConnection connection;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;
    SecretKey chave = Crypto.gerarChave();
    GCMParameterSpec iv = Crypto.gerarIV();


    public UserDAO(){
        connection = new DataBaseConnection();
    }

    public UserModel getByUsername2(String username){
        try{
            String query = "SELECT * from tbUserInfo where username = ?";
            preparedStatement = connection.getConnection().prepareStatement(query);
            preparedStatement.setString(1,username);
            this.resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                String usernameDB = resultSet.getString(1);
                String emailDB = resultSet.getString(2);
                String passwordDB = resultSet.getString(3);

                return new UserModel(usernameDB, emailDB, passwordDB);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new UserModel();
    }


    public boolean insertUser(UserModel user) {
        try {
            String query = "INSERT INTO tbUserInfo (username, email, upassword) VALUES (?, ?, ?)";
            preparedStatement = connection.getConnection().prepareStatement(query);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, Crypto.encriptarDado(user.getUpassword(), chave, iv));
            preparedStatement.execute();
            return preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException | NoSuchPaddingException | IllegalBlockSizeException |
                 NoSuchAlgorithmException | BadPaddingException | InvalidKeyException e) {
            throw new RuntimeException(e);
        }

        return false;
    }

    public boolean updateUser(UserModel user) {
        try {
            String query = "UPDATE tbUserInfo SET password = ? WHERE email = ?";
            preparedStatement = connection.getConnection().prepareStatement(query);
            preparedStatement.setString(1, user.getUpassword());
            preparedStatement.setString(2, user.getEmail());
            this.resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteUser(UserModel user){
        try {
            String query = "DELETE FROM tbUserInfo WHERE email = ?";
            preparedStatement = connection.getConnection().prepareStatement(query);
            preparedStatement.setString(1, user.getEmail());
            this.resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}