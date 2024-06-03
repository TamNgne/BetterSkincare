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
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


import java.net.URL;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;


public class KeywordController extends MainController implements Initializable {


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
    private TableColumn<Product, Integer> idCol;


    @FXML
    private TableColumn<Product, Double> rankCol;


    @FXML
    private TableColumn<Product, String> ingredCol;


    @FXML
    private TextField keyword;


    ObservableList<Product> productObservableList = FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle resource) {
        loadProductData();
        setupSearchFilter();
    }


    private void loadProductData() {
        HashMap<Integer, Product> productMap = loadProduct();
        productObservableList.addAll(productMap.values());

        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        labelCol.setCellValueFactory(new PropertyValueFactory<>("label"));
        brandCol.setCellValueFactory(new PropertyValueFactory<>("brand"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        rankCol.setCellValueFactory(new PropertyValueFactory<>("rank"));
        ingredCol.setCellValueFactory(new PropertyValueFactory<>("ingredients"));



        tableProduct1.setItems(productObservableList);
    }


    private void setupSearchFilter() {
        FilteredList<Product> filteredData = new FilteredList<>(productObservableList, b -> true);
        keyword.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(product -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String searchKeyword = newValue.toLowerCase();
                return product.getName().toLowerCase().contains(searchKeyword)
                        || product.getIngredients().toLowerCase().contains(searchKeyword)
                        || product.getBrand().toLowerCase().contains(searchKeyword)
                        || product.getLabel().toLowerCase().contains(searchKeyword)
                        || product.getPrice().toString().contains(searchKeyword)
                        || Double.toString(product.getRank()).contains(searchKeyword);
            });
        });


        SortedList<Product> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableProduct1.comparatorProperty());
        tableProduct1.setItems(sortedData);
    }
}
