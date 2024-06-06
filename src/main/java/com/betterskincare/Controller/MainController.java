package com.betterskincare.Controller;

import com.betterskincare.Product;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainController {
    private Stage stage;
    private Scene scene;
    private HashMap<Integer, Product> productMap;

    public Connection connect;
    public PreparedStatement prepare;
    public ResultSet result;

    public static Connection connectDb(){ //connect database
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/skincare", "root", "TamNguyencute"); // address, database username, database password
            return connect;
        } catch(Exception e){e.printStackTrace();}
        return null;
    }
     public HashMap<Integer, Product> loadProduct() {
         productMap = new HashMap<>();
         Connection connect = connectDb();
         String query = "SELECT * FROM cosmetic c ";
         if (connect != null) {
             try {

                 PreparedStatement preparedStatement = connect.prepareStatement(query);
                 ResultSet resultSet = preparedStatement.executeQuery();

                 while (resultSet.next()) {
                     Integer id = resultSet.getInt("ProductID");
                     String label = resultSet.getString("Label");
                     String name = resultSet.getString("Name");
                     String brand = resultSet.getString("Brand");
                     Integer price = resultSet.getInt("Price");
                     Double rank = resultSet.getDouble("Rank");
                     String ingred = resultSet.getString("Ingredients");

                     // Get skin type attributes and convert to boolean
                     boolean combination = resultSet.getInt("Combination") == 1;
                     boolean dry = resultSet.getInt("Dry") == 1;
                     boolean normal = resultSet.getInt("Normal") == 1;
                     boolean oily = resultSet.getInt("Oily") == 1;
                     boolean sensitive = resultSet.getInt("Sensitive") == 1;

                     // Determine which skin types match and add them to the skinTypesMatchedList
                     List<String> skinTypesMatchedList = new ArrayList<>();
                     if (combination) {
                         skinTypesMatchedList.add("Combination");
                     }
                     if (normal) {
                         skinTypesMatchedList.add("Normal");
                     }
                     if (dry) {
                         skinTypesMatchedList.add("Dry");
                     }
                     if (oily) {
                         skinTypesMatchedList.add("Oily");
                     }
                     if (sensitive) {
                         skinTypesMatchedList.add("Sensitive");
                     }

                     // Create Product object

                     Product product = new Product(id, label, brand, name, price, rank, ingred, combination, dry, normal, oily, sensitive,  skinTypesMatchedList);

                     productMap.put(id, product);
                 }
                 resultSet.close();
                 preparedStatement.close();
             } catch (Exception e) {
                 e.printStackTrace();
             }
         }
         return productMap;
    }

    //change scenes
    public void switchToCategory(ActionEvent actionEvent) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/com/betterskincare/category-view.fxml"));
        scene = new Scene(root);
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void switchToKeyword(ActionEvent actionEvent) throws  Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/com/betterskincare/keyword-view.fxml"));
        scene = new Scene(root);
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }

    public void switchToHome(ActionEvent actionEvent) throws  Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/com/betterskincare/hello-view.fxml"));
        scene = new Scene(root);
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

        // Center the stage on the screen
        centerStage(stage);
    }

    private void centerStage(Stage stage) {
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((screenBounds.getWidth() - stage.getWidth()) / 2);
        stage.setY((screenBounds.getHeight() - stage.getHeight()) / 2);
    }

}
