package com.betterskincare2.Controller;

import com.betterskincare2.Product;
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
import java.util.HashMap;

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
         String query = "SELECT c.ProductID, c.Label, c.Brand, c.Name, c.Price, c.Rank, c.Ingredients FROM cosmetic c ";
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

                     Product product = new Product(id, label, brand, name, price, rank, ingred);

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
