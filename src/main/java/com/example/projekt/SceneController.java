package com.example.projekt;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class SceneController {

    private Stage stage;
    private Scene scene;
    private Parent root;
    private TreeView<String> treeView;

    public void switchToImageViewer(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("image-viewer-view.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToMainMenu(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("main-menu-view.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public List<String> getImageName() {
        List<String> imageNames = new ArrayList<>();
        try {
            DirectoryStream<Path> directoryStream = Files.newDirectoryStream(Paths.get("src/main/java/Images"));
            for (Path path : directoryStream) {
                String fileName = path.toString().substring(21);
                imageNames.add(fileName);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        System.out.println("File Count:"+imageNames.size());
        System.out.println(imageNames);

        return imageNames;
    }

    public void initializeList() {
        TreeItem<String> rootItem = new TreeItem<>("Images");

        treeView.setRoot(rootItem);

        List<String> listOfImages = getImageName();
        TreeItem<String> branchItem;
        for (String image : listOfImages) {
            branchItem = new TreeItem<>(image);
            rootItem.getChildren().add(branchItem);
        }
    }

    public void exitProgram() {
        System.exit(0);
    }

    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {

    }


}