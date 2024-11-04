package org.skyhigh.afishadevappgui.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.skyhigh.afishadevappgui.AfishaDevGUIApplication;
import org.skyhigh.afishadevappgui.common.properties.ApplicationPropertiesReader;
import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.common.validation.CommonSystemException;
import org.skyhigh.afishadevappgui.data.datasource.entity.DbUser;
import org.skyhigh.afishadevappgui.data.repository.DbUserRepositoryImpl;
import org.skyhigh.afishadevappgui.service.logic.authentication.AuthenticationService;
import org.skyhigh.afishadevappgui.service.logic.authentication.AuthenticationServiceImpl;

import java.io.IOException;

public class AuthenticateController {
    @FXML private TextField loginInputAuthTxtField;
    @FXML private PasswordField passwordInputAuthTxtField;

    @FXML
    private Button goBackAuthBt;

    @FXML
    private Button AuthenticateAuthBt;

    @FXML
    protected void onBackAuthBtClicked() throws IOException {
        FXMLLoader loader = new FXMLLoader(AfishaDevGUIApplication.class.getResource("hello-view.fxml"));
        Parent root = loader.load();
        //RegisterController controller = loader.getController();

        Stage stage = (Stage) goBackAuthBt.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void onAuthenticateAuthBtClicked() throws IOException, CommonFlkException {
        DbUser authenticatedUser = login();
        FXMLLoader loader = new FXMLLoader(AfishaDevGUIApplication.class.getResource("main-view.fxml"));
        Parent root = loader.load();
        MainController controller = loader.getController();
        controller.initialize();
        controller.setCurrentDbUser(authenticatedUser);
        controller.setDefaultTableViewAndFilters();

        Stage stage = (Stage) AuthenticateAuthBt.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private DbUser login() throws CommonFlkException {
        String inputLogin = loginInputAuthTxtField.getText();
        String inputPassword = passwordInputAuthTxtField.getText();
        AuthenticationService authenticationService = new AuthenticationServiceImpl(
                new DbUserRepositoryImpl(ApplicationPropertiesReader.getApplicationProperties())
        );
        DbUser authenticatedUser =  authenticationService.authenticate(inputLogin, inputPassword);
        if (authenticatedUser == null)
            throw new CommonSystemException(
                    "Произошла непредвиденная ошибка. Пользователь не найден",
                    true
            );
        return authenticatedUser;
    }
}
