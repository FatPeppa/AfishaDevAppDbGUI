package org.skyhigh.afishadevappgui.controller.rowinteraction;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.skyhigh.afishadevappgui.common.controller.ControllerUtils;
import org.skyhigh.afishadevappgui.common.properties.ApplicationPropertiesReader;
import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.common.validation.CommonUIException;
import org.skyhigh.afishadevappgui.common.validation.CommonUIFormatException;
import org.skyhigh.afishadevappgui.data.datasource.entity.DbUser;
import org.skyhigh.afishadevappgui.data.repository.SystemRoleRepository;
import org.skyhigh.afishadevappgui.data.repository.SystemRoleRepositoryImpl;

import java.util.ArrayList;
import java.util.List;

public class RowDbUserController {
    @FXML
    TextField userIdField;

    @FXML
    TextField loginField;

    @FXML
    TextField authorIdField;

    @FXML
    TextField passwordField;

    @FXML
    TextField roleField;

    @FXML
    Label userIdLabel;

    @FXML
    Label loginLabel;

    @FXML
    Label authorIdLabel;

    @FXML
    Label passwordLabel;

    @FXML
    Label roleLabel;

    private SystemRoleRepository systemRoleRepository;

    public void initialize() throws CommonFlkException {
        systemRoleRepository = new SystemRoleRepositoryImpl(
                ApplicationPropertiesReader.getApplicationProperties()
        );
    }

    public void autoFillFields(DbUser dbUser) throws CommonFlkException {
        userIdField.setText(String.valueOf(dbUser.getUserId()));
        authorIdField.setText(dbUser.getAuthorId().toString());
        loginField.setText(dbUser.getUserLogin());
        passwordField.setText(dbUser.getUserPass());
        roleField.setText(systemRoleRepository.getSystemRoleById(dbUser.getSystemRoleId()).getRoleName());
    }

    public void clearFields() {
        userIdField.setText(null);
        authorIdField.setText(null);
        loginField.setText(null);
        passwordField.setText(null);
    }

    public DbUser getDbUser() throws CommonFlkException {
        List<String> emptyField = getEmptyNecessaryFields();
        if (emptyField.isEmpty()) {
            try {
                return new DbUser(
                        ControllerUtils.getIntegerFromTextField(
                                userIdField,
                                ControllerUtils.getFieldLocalNameFromItsLabel(userIdLabel)
                        ),
                        ControllerUtils.getUUIDFromTextField(
                                authorIdField,
                                ControllerUtils.getFieldLocalNameFromItsLabel(authorIdLabel)
                        ),
                        loginField.getText(),
                        passwordField.getText(),
                        ControllerUtils.getIntegerFromTextField(
                                roleField,
                                ControllerUtils.getFieldLocalNameFromItsLabel(roleLabel)
                        )
                );
            } catch (CommonUIFormatException e) {
                return null;
            }
        } else {
            throw new CommonUIException(
                    "1004000",
                    "При сохранении/изменении пользователя должны быть заполнены обязательные атрибуты",
                    emptyField
            );
        }
    }

    public Integer getUserId() throws CommonUIFormatException {
        return userIdField == null || userIdField.getText().isEmpty() ? null : ControllerUtils.getIntegerFromTextField(
                userIdField,
                ControllerUtils.getFieldLocalNameFromItsLabel(userIdLabel)
        );
    }

    private List<String> getEmptyNecessaryFields() {
        List<String> emptyFields = new ArrayList<>();
        //if (userIdField.getText() == null || userIdField.getText().isEmpty())
        //    emptyFields.add(ControllerUtils.getFieldLocalNameFromItsLabel(userIdLabel));
        //if (authorIdField.getText() == null || authorIdField.getText().isEmpty())
        //    emptyFields.add(ControllerUtils.getFieldLocalNameFromItsLabel(authorIdLabel));
        if (loginField.getText() == null || loginField.getText().isEmpty())
            emptyFields.add(ControllerUtils.getFieldLocalNameFromItsLabel(loginLabel));
        if (passwordField.getText() == null || passwordField.getText().isEmpty())
            emptyFields.add(ControllerUtils.getFieldLocalNameFromItsLabel(passwordLabel));
        return emptyFields;
    }
}
