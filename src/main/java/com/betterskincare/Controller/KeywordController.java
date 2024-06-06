package com.betterskincare.Controller;

import com.betterskincare.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;


import java.net.URL;
import java.util.HashMap;
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
                String[] searchKeywords = newValue.toLowerCase().split("\\s+");

                for (String searchKeyword : searchKeywords) {
                    boolean matches = product.getName().toLowerCase().contains(searchKeyword)
                            || product.getIngredients().toLowerCase().contains(searchKeyword)
                            || product.getBrand().toLowerCase().contains(searchKeyword)
                            || product.getLabel().toLowerCase().contains(searchKeyword)
                            || product.getPrice().toString().contains(searchKeyword)
                            || Double.toString(product.getRank()).contains(searchKeyword);
                    if (!matches) {
                        return false;
                    }
                }
                return true;
            });
        });

        SortedList<Product> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableProduct1.comparatorProperty());
        tableProduct1.setItems(sortedData);
    }

}
