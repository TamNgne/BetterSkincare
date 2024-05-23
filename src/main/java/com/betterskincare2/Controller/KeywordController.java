package com.betterskincare2.Controller;

import com.betterskincare2.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;


public class KeywordController extends MainController implements Initializable{

    @FXML
    private TableView<Product> tableProduct1;

    @FXML
    private TableColumn<Product, String> labelCol;

    @FXML
    private TableColumn<Product, String> brandCol;

    @FXML
    private TableColumn<Product, String> nameCol;

    @FXML
    private TableColumn<Product, Integer> priceCol;

    @FXML
    private TableColumn<Product, Double> rankCol;

    @FXML
    private TableColumn<Product, String> ingredCol;

    @FXML
    private TextField keyword;

    ObservableList<Product> productObservableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resource) {
        searchProduct();
    }
    public void searchProduct(){
        Connection connect = connectDb();

        String query = "SELECT c.Label, c.Brand, c.Name, c.Price, c.Rank, c.Ingredients FROM cosmetic c ";

        try {
            PreparedStatement preparedStatement = connect.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String label = resultSet.getString("Label");
                String name = resultSet.getString("Name");
                String brand = resultSet.getString("Brand");
                Integer price = resultSet.getInt("Price");
                Double rank = resultSet.getDouble("Rank");
                String ingred = resultSet.getString("Ingredients");

                productObservableList.add(new Product(label,brand,name,price, rank, ingred));
            }

            labelCol.setCellValueFactory(new PropertyValueFactory<>("Label"));
            brandCol.setCellValueFactory(new PropertyValueFactory<>("Brand"));
            nameCol.setCellValueFactory(new PropertyValueFactory<>("Name"));
            priceCol.setCellValueFactory(new PropertyValueFactory<>("Price"));
            rankCol.setCellValueFactory(new PropertyValueFactory<>("Rank"));
            ingredCol.setCellValueFactory(new PropertyValueFactory<>("Ingredients"));

            tableProduct1.setItems(productObservableList);

            //Initial fitlered product
            FilteredList<Product> filteredData = new FilteredList<>(productObservableList, b -> true);
            keyword.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(product -> {
                    if (newValue.isEmpty() || newValue.isBlank() || newValue == null)
                        return true;
                    String searchKeyword = newValue.toLowerCase();

                    if (product.getName().toLowerCase().contains(searchKeyword))
                            return true; //found a product
                    else if (product.getIngredients().toLowerCase().contains(searchKeyword))
                        return true;
                    else if (product.getBrand().toLowerCase().contains(searchKeyword))
                        return true;
                    else if (product.getLabel().toLowerCase().contains(searchKeyword))
                        return true;
                    else if (product.getPrice().toString().contains(searchKeyword))
                        return true;
                    else if (Double.toString(product.getRank()).contains(searchKeyword))
                        return true;
                    else  return false; //cant find
                });
            });

            SortedList<Product> sortedData = new SortedList<> (filteredData);
            //Bind sorted result with table view
            sortedData.comparatorProperty().bind(tableProduct1.comparatorProperty());

            //Apply filtered and sorted
            tableProduct1.setItems(sortedData);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




}
