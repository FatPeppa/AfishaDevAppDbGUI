package org.skyhigh.afishadevappgui.controller.filters;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.skyhigh.afishadevappgui.common.controller.ControllerUtils;

import java.time.LocalDateTime;

import static org.skyhigh.afishadevappgui.common.controller.ControllerUtils.getFieldLocalNameFromItsLabel;

public class PasswordGenRuleFiltersController {
    @FXML
    TextField passwordGenRuleIdInputField;

    @FXML
    TextField actualizationDateInputField;

    @FXML
    Label ruleIdLabel;

    @FXML
    Label actualizationDateLabel;

    public void initialize() {
        setPasswordGenRuleIdInputFieldChangeListener();
        setActualizationDateInputFieldChangeListener();
    }

    public Integer getPasswordGenRuleId() {
        Integer parsedPasswordGenRuleId = ControllerUtils.getIntegerFromTextField(passwordGenRuleIdInputField, getFieldLocalNameFromItsLabel(ruleIdLabel));

        if (parsedPasswordGenRuleId != null && parsedPasswordGenRuleId < 0) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Ошибка");
            alert.setHeaderText(String.format("Недопустимый формат поля '%s'", getFieldLocalNameFromItsLabel(ruleIdLabel)));
            alert.setContentText("Значение поля должно быть больше или равно нулю");

            alert.showAndWait();

            return null;
        }
        return parsedPasswordGenRuleId;
    }

    public LocalDateTime getActualizationDate() {
        return ControllerUtils.getLocalDateTimeFromTextField(actualizationDateInputField, getFieldLocalNameFromItsLabel(actualizationDateLabel));
    }

    private void setPasswordGenRuleIdInputFieldChangeListener() {
        passwordGenRuleIdInputField.textProperty().addListener((observable, oldValue, newValue) ->
                actualizationDateInputField.setDisable(newValue != null && !newValue.isEmpty())
        );
    }

    private void setActualizationDateInputFieldChangeListener() {
        actualizationDateInputField.textProperty().addListener((observable, oldValue, newValue) ->
                passwordGenRuleIdInputField.setDisable(newValue != null && !newValue.isEmpty())
        );
    }
}
