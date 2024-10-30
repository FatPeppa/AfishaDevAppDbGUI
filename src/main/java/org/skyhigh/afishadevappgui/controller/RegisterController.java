package org.skyhigh.afishadevappgui.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.skyhigh.afishadevappgui.AfishaDevGUIApplication;
import org.skyhigh.afishadevappgui.common.properties.ApplicationPropertiesReader;
import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.common.validation.CommonSystemException;
import org.skyhigh.afishadevappgui.data.datasource.entity.DbUser;
import org.skyhigh.afishadevappgui.data.repository.DbUserRepositoryImpl;
import org.skyhigh.afishadevappgui.service.logic.AuthenticationService;
import org.skyhigh.afishadevappgui.service.logic.AuthenticationServiceImpl;

import java.io.IOException;

public class RegisterController {
    @FXML private TextField loginInputRegTxtField;

    @FXML private TextField passwordInputRegTxtField;

    @FXML
    private Button goBackRegBt;

    @FXML
    private Button RegisterRegBt;

    @FXML
    protected void onGoBackRegBtClicked() throws IOException {
        FXMLLoader loader = new FXMLLoader(AfishaDevGUIApplication.class.getResource("hello-view.fxml"));
        Parent root = loader.load();
        //RegisterController controller = loader.getController();

        Stage stage = (Stage) goBackRegBt.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void onRegisterRegBtClicked() throws IOException, CommonFlkException {
        String inputLogin = loginInputRegTxtField.getText();
        String inputPassword = passwordInputRegTxtField.getText();
        AuthenticationService authenticationService = new AuthenticationServiceImpl(
                new DbUserRepositoryImpl(ApplicationPropertiesReader.getApplicationProperties())
        );
        DbUser registeredDbUser =  authenticationService.register(inputLogin, inputPassword);
        if (registeredDbUser == null)
            throw new CommonSystemException(
                    "Произошла непредвиденная ошибка. Пользователь не найден",
                    true
            );
        FXMLLoader loader = new FXMLLoader(AfishaDevGUIApplication.class.getResource("main-view.fxml"));
        Parent root = loader.load();
        MainController controller = loader.getController();
        controller.initialize();
        controller.setCurrentDbUser(registeredDbUser);
        controller.setDefaultTableViewAndFilters();

        Stage stage = (Stage) RegisterRegBt.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
