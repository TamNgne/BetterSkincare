package com.betterskincare.Controller;

import com.betterskincare.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

public class ProductController extends MainController implements Initializable {

    @FXML
    private TableView<Product> tableChart;

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
    private TableColumn<Product, String> ingredientsCol;

    @FXML
    private TableColumn<Product, String> skintypeMatchedCol;

    private ObservableList<Product> productObservableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resource) {
        // Initialize table columns
        labelCol.setCellValueFactory(new PropertyValueFactory<>("label"));
        brandCol.setCellValueFactory(new PropertyValueFactory<>("brand"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        rankCol.setCellValueFactory(new PropertyValueFactory<>("rank"));
        ingredientsCol.setCellValueFactory(new PropertyValueFactory<>("ingredients"));
        skintypeMatchedCol.setCellValueFactory(new PropertyValueFactory<>("skintypesMatchedString"));

        tableChart.setItems(productObservableList);
    }

    public void filterProduct(String rankRange, String priceRange, String brand, String type, List<String> selectedSkintypes) {
        HashMap<Integer, Product> productMap = loadProduct();
        productObservableList.clear();

        for (Product product : productMap.values()) {
            boolean rankMatches = false;
            boolean priceMatches = false;
            boolean brandMatches = false;
            boolean typeMatches = false;
            boolean skintypeMatches = false;

            double rank = product.getRank();
            int price = product.getPrice();
            String productBrand = product.getBrand();
            String productType = product.getLabel();


            // Filter by rank

            switch (rankRange) {
                case "Under 2":
                    rankMatches = rank < 2;
                    break;
                case "2 to 3":
                    rankMatches = rank >= 2 && rank < 3;
                    break;
                case "3 to 4":
                    rankMatches = rank >= 3 && rank < 4;
                    break;
                case "4 to 5":
                    rankMatches = rank >= 4 && rank <= 5;
                    break;
            }

            // Filter by price
            switch (priceRange) {
                case "Under 50$":
                    priceMatches = price < 50;
                    break;
                case "50$ to 100$":
                    priceMatches = price >= 50 && price < 100;
                    break;
                case  "100$ to 150$":
                    priceMatches = price >= 100 && price < 150;
                    break;
                case "150$ to 200$":
                    priceMatches = price >= 150 && price < 200;
                    break;
                case "Above 200$":
                    priceMatches = price > 200;
                    break;
            }

            //Filter by Type
            if (type.equals("None"))
                typeMatches = true;
            else {
                switch (type) {
                    case "Moisturizer":
                        typeMatches = productType.equals("Moisturizer");
                        break;
                    case "Cleanser":
                        typeMatches = productType.equals("Cleanser");
                        break;
                    case "Treatment":
                        typeMatches = productType.equals("Treatment");
                        break;
                    case "Face Mask":
                        typeMatches = productType.equals("Face Mask");
                        break;
                    case "Eye cream":
                        typeMatches = productType.equals("Eye cream");
                        break;
                }
            }

            //Filter by brand
            if (brand.equals("None"))
                brandMatches = true;
            else
                brandMatches = productBrand.equalsIgnoreCase(brand);

            // Filter by skin type
            if (selectedSkintypes.isEmpty()) {
                // If no skin types are selected, consider it a match
                skintypeMatches = true;
            } else {
                // If any of the selected skin types match, consider it a match
                for (String skinType : selectedSkintypes) {
                    switch (skinType) {
                        case "Combination":
                            skintypeMatches |= product.isCombination();
                            break;
                        case "Normal":
                            skintypeMatches |= product.isNormal();
                            break;
                        case "Dry":
                            skintypeMatches |= product.isDry();
                            break;
                        case "Oily":
                            skintypeMatches |= product.isOily();
                            break;
                        case "Sensitive":
                            skintypeMatches |= product.isSensitive();
                            break;
                    }
                }
            }

//            // Filter by skin type
            if (selectedSkintypes.isEmpty()) {
                skintypeMatches = true;
            } else {
                List<String> productSkinTypes = product.getSkinTypesMatched();
                skintypeMatches = selectedSkintypes.stream().allMatch(productSkinTypes::contains);
            }


            //FilteredProduct
            if (rankMatches && priceMatches && typeMatches && brandMatches && skintypeMatches)
                productObservableList.add(product);
        }
    }
}
