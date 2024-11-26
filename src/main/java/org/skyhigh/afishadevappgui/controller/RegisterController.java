package org.skyhigh.afishadevappgui.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.skyhigh.afishadevappgui.AfishaDevGUIApplication;
import org.skyhigh.afishadevappgui.common.properties.ApplicationPropertiesReader;
import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.common.validation.CommonSystemException;
import org.skyhigh.afishadevappgui.data.datasource.entity.DbUser;
import org.skyhigh.afishadevappgui.data.datasource.entity.SystemRole;
import org.skyhigh.afishadevappgui.data.repository.DbUserRepositoryImpl;
import org.skyhigh.afishadevappgui.data.repository.SystemRoleRepository;
import org.skyhigh.afishadevappgui.data.repository.SystemRoleRepositoryImpl;
import org.skyhigh.afishadevappgui.service.logic.authentication.AuthenticationService;
import org.skyhigh.afishadevappgui.service.logic.authentication.AuthenticationServiceImpl;

import java.io.IOException;
import java.util.List;

public class RegisterController {
    @FXML private TextField loginInputRegTxtField;

    @FXML private PasswordField passwordInputRegTxtField;

    @FXML private ChoiceBox<String> systemRoleChoiceBox;

    @FXML
    private Button goBackRegBt;

    @FXML
    private Button RegisterRegBt;

    private SystemRoleRepository systemRoleRepository;

    private List<SystemRole> systemRoles;

    public void initialize() throws CommonFlkException {
        systemRoleRepository = new SystemRoleRepositoryImpl(
                ApplicationPropertiesReader.getApplicationProperties()
        );

        systemRoles = systemRoleRepository.getAllSystemRoles();

        systemRoleChoiceBox.getItems().addAll(
                systemRoles.stream().map(SystemRole::getRoleName).toList()
        );
        systemRoleChoiceBox.getSelectionModel().select(0);
    }

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
        String inputSystemRoleName = systemRoleChoiceBox.getSelectionModel().getSelectedItem();
        SystemRole selectedSystemRole = systemRoles.stream().filter(x -> x.getRoleName().equals(inputSystemRoleName)).findFirst().get();
        AuthenticationService authenticationService = new AuthenticationServiceImpl(
                new DbUserRepositoryImpl(ApplicationPropertiesReader.getApplicationProperties())
        );
        DbUser registeredDbUser =  authenticationService.register(inputLogin, inputPassword, selectedSystemRole);
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
