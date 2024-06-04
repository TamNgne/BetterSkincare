package com.betterskincare2.Controller;

import javafx.collections.FXCollections;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.jar.Attributes;

public class CategoryController extends MainController {

    @FXML
    private ChoiceBox<String> Brandcb;

    @FXML
    private ChoiceBox<String> Typecb;

    @FXML
    private ChoiceBox<String> Rankcb;

    @FXML
    private ChoiceBox<String> Pricecb;

    @FXML
    private ListView<CheckBox> Skintypelv;

    private Stage stage;
    private Scene scene;

    public void initialize() {
        // Populate rankChoiceBox
        Typecb.setItems(FXCollections.observableArrayList(
                "Moisturizer", "Cleanser", "Treatment", "Face Mask", "Eye cream", "Sun protect"));
        Typecb.setValue("None"); // default value

        // Populate rankChoiceBox
        Rankcb.setItems(FXCollections.observableArrayList(
                "Under 2", "2 to 3", "3 to 4", "4 to 5"
        ));
        Rankcb.setValue("Under 2"); // default value

        // Populate priceChoiceBox
        Pricecb.setItems(FXCollections.observableArrayList(
                "Under 50$", "50$ to 100$", "100$ to 150$", "150$ to 200$", "Above 200$"
        ));
        Pricecb.setValue("Under 50$"); // default value

        // Populate brandChoiceBox
        ObservableList<String> brands = FXCollections.observableArrayList(
                "None", "LA MER", "SK-II", "DRUNK ELEPHANT", "IT COSMETICS", "TATCHA",
                "KIEHL'S SINCE 1851", "FRESH", "BELIF", "SUNDAY RILEY", "FARMACY",
                "FIRST AID BEAUTY", "CLINIQUE", "BAREMINERALS", "SHISEIDO",
                "CHARLOTTE TILBURY", "ORIGINS", "OLEHENRIKSEN", "JOSIE MARAN",
                "FARSÁLI", "LANEIGE", "DR. JART+", "GLOW RECIPE", "HERBIVORE",
                "TARTE", "CAUDALIE", "YOUTH TO THE PEOPLE", "BIOSSANCE",
                "ESTÉE LAUDER", "SON & PARK", "AMOREPACIFIC", "BOBBI BROWN",
                "ERBORIAN", "SMASHBOX", "GLAMGLOW", "PETER THOMAS ROTH",
                "JACK BLACK", "PHILOSOPHY", "PERRICONE MD", "LANCÔME",
                "ALGENIST", "MAKE UP FOR EVER", "KORRES", "MURAD", "GUERLAIN",
                "DIOR", "KORA ORGANICS", "DR. DENNIS GROSS SKINCARE",
                "REN CLEAN SKINCARE", "KOPARI", "CLARINS", "NURSE JAMIE",
                "LANCER", "L'OCCITANE", "KATE SOMERVILLE", "SUPERGOOP!",
                "SATURDAY SKIN", "SEPHORA COLLECTION", "MILK MAKEUP",
                "DR. BRANDT SKINCARE", "J.ONE", "KAT VON D", "TATA HARPER",
                "GO-TO", "BOSCIA", "EVE LOM", "MAKEUP ERASER", "LAURA MERCIER",
                "NEOGEN DERMALOGY", "INDIE LEE", "KOH GEN DO", "VOLITION BEAUTY",
                "TOO FACED", "SKIN INC SUPPLEMENT BAR", "NUFACE", "KLORANE",
                "BEAUTYBIO", "NARS", "BLITHE", "ERNO LASZLO", "CLARISONIC",
                "FENTY BEAUTY BY RIHANNA", "DERMADOCTOR", "PEACE OUT",
                "BIOEFFECT", "BECCA", "COVER FX", "MOON JUICE", "PRIVATE DOCTOR",
                "HUM NUTRITION", "FOREO", "LIGHTSTIM", "COOLA", "OMOROVICZA",
                "SUMMER FRIDAYS", "SAND & SKY", "TOO COOL FOR SCHOOL",
                "DR ROEBUCK’S", "SKIN LAUNDRY", "ANTHONY", "ST. TROPEZ TANNING ESSENTIALS",
                "NATURALLY SERIOUS", "WANDER BEAUTY", "EDIBLE BEAUTY",
                "YVES SAINT LAURENT", "KENZOKI", "APIVITA", "ILIA",
                "MDSOLARSCIENCES", "MOROCCANOIL", "TOM FORD", "KAPLAN MD",
                "CANE + AUSTIN", "BLACK UP", "VITA LIBERATA", "URBAN DECAY",
                "DERMAFLASH"
        );
        Brandcb.setItems(brands);
        Brandcb.setValue("None"); // default value

        skintypeListView();
    }
    private void skintypeListView() {
        ObservableList<CheckBox> skintype = FXCollections.observableArrayList();
        String[] skinTypes = {"Combination", "Dry", "Normal", "Oily", "Sensitive"};

        for (String type : skinTypes) {
            CheckBox checkBox = new CheckBox(type);
            skintype.add(checkBox);
        }
        Skintypelv.setItems(skintype);
    }

    @FXML
    public void handleShowProducts(ActionEvent event) throws IOException {
        String selectedRank = Rankcb.getValue();
        String selectedPrice = Pricecb.getValue();
        String selectedBrand = Brandcb.getValue();
        String selectedType = Typecb.getValue();

        // Get selected skin types
        ObservableList<CheckBox> selectedSkinTypes = Skintypelv.getSelectionModel().getSelectedItems();
        List<String> selectedSkinTypeNames = new ArrayList<>();
        for (CheckBox checkBox : selectedSkinTypes) {
            selectedSkinTypeNames.add(checkBox.getText());
        }

        // Pass the selected choices to the ProductController
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/betterskincare2/product-view.fxml"));
        Parent root = loader.load();

        ProductController productController = loader.getController();
        productController.filterProduct(selectedRank, selectedPrice, selectedBrand, selectedType, selectedSkinTypeNames);

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);

        // Set the stage to full screen
        stage.setFullScreen(true);

        stage.setScene(scene);
        stage.show();
    }
}

