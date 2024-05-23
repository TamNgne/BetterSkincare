package com.betterskincare2.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MainController {
    private Stage stage;
    private Scene scene;

    public Connection connect;
    public PreparedStatement prepare;
    public ResultSet result;

    public static Connection connectDb(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/skincare", "root", "TamNguyencute"); // address, database username, database password
            return connect;
        }catch(Exception e){e.printStackTrace();}
        return null;
    }

    public void switchToCategory(ActionEvent actionEvent) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/com/betterskincare2/category-view.fxml"));
        scene = new Scene(root);
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void switchToKeyword(ActionEvent actionEvent) throws  Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/com/betterskincare2/keyword-view.fxml"));
        scene = new Scene(root);
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void switchToProduct(ActionEvent actionEvent) throws  Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/com/betterskincare2/product-view.fxml"));
        scene = new Scene(root);
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    public void switchToHome(ActionEvent actionEvent) throws  Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/com/betterskincare2/hello-view.fxml"));
        scene = new Scene(root);
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }


}
