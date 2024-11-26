package org.skyhigh.afishadevappgui.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.skyhigh.afishadevappgui.AfishaDevGUIApplication;
import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.service.logic.role.BasicRoleInitializationService;
import org.skyhigh.afishadevappgui.service.logic.role.BasicRoleInitializationServiceImpl;

import java.io.IOException;

public class HelloController {
    @FXML
    private Button registerChooseBt;

    @FXML
    private Button authenticateChooseBt;

    @FXML
    protected void onRegisterChooseBtClicked() throws IOException, CommonFlkException {
        BasicRoleInitializationService basicRoleInitializationService = new BasicRoleInitializationServiceImpl();
        basicRoleInitializationService.initialize();

        FXMLLoader loader = new FXMLLoader(AfishaDevGUIApplication.class.getResource("register-view.fxml"));
        Parent root = loader.load();
        //RegisterController controller = loader.getController();

        Stage stage = (Stage) registerChooseBt.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void onAuthenticateChooseBt() throws IOException, CommonFlkException {
        BasicRoleInitializationService basicRoleInitializationService = new BasicRoleInitializationServiceImpl();
        basicRoleInitializationService.initialize();

        FXMLLoader loader = new FXMLLoader(AfishaDevGUIApplication.class.getResource("authenticate-view.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) authenticateChooseBt.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}