package org.skyhigh.afishadevappgui.controller.rowinteraction;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.skyhigh.afishadevappgui.common.controller.ControllerUtils;
import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.common.validation.CommonUIException;
import org.skyhigh.afishadevappgui.common.validation.CommonUIFormatException;
import org.skyhigh.afishadevappgui.data.datasource.entity.Deployment;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class RowDeploymentController {
    @FXML
    TextField deploymentIdField;

    @FXML
    TextField addressField;

    @FXML
    TextField statusIdField;

    @FXML
    TextField settingsField;

    @FXML
    TextField builtVersionField;

    @FXML
    TextField builtSettingsField;

    @FXML
    TextField builtField;

    @FXML
    TextField projectIdField;

    @FXML
    Label deploymentIdLabel;

    @FXML
    Label statusIdLabel;

    @FXML
    Label addressLabel;

    @FXML
    Label settingsLabel;

    @FXML
    Label builtSettingsLabel;

    @FXML
    Label builtVersionLabel;

    @FXML
    Label builtLabel;

    @FXML
    Label projectIdLabel;

    public void initialize() throws CommonFlkException {}

    public void autoFillFields(Deployment deployment) {
        deploymentIdField.setText(deployment.getDeploymentId().toString());
        if (deployment.getDeploymentPath() != null) {addressField.setText(deployment.getDeploymentPath());}
        if (deployment.getSettings() != null) {settingsField.setText(deployment.getSettings().toString());}
        if (deployment.getBuiltSettings() != null) {builtSettingsField.setText(deployment.getBuiltSettings().toString());}
        if (deployment.getBuiltVersion() != null) {builtVersionField.setText(deployment.getBuiltVersion());}
        if (deployment.getBuilt() != null) {builtField.setText(deployment.getBuilt().toString());}
        statusIdField.setText(deployment.getDeploymentStatusId().toString());
        projectIdField.setText(deployment.getProjectId().toString());
    }

    public void clearFields() {
        deploymentIdField.setText(null);
        addressField.setText(null);
        settingsField.setText(null);
        builtSettingsField.setText(null);
        builtVersionField.setText(null);
        builtField.setText(null);
        statusIdField.setText(null);
        projectIdField.setText(null);
    }

    public Deployment getDeployment() throws CommonFlkException {
        List<String> emptyField = getEmptyNecessaryFields();
        if (emptyField.isEmpty())
            try {
                return new Deployment(
                        ControllerUtils.getUUIDFromTextField(
                                deploymentIdField,
                                ControllerUtils.getFieldLocalNameFromItsLabel(deploymentIdLabel)
                        ),
                        ControllerUtils.getUUIDFromTextField(
                                statusIdField,
                                ControllerUtils.getFieldLocalNameFromItsLabel(statusIdLabel)
                        ),
                        addressField.getText(),
                        settingsField.getText() == null || settingsField.getText().isEmpty() ? null : ControllerUtils.getJSONFromTextField(
                                settingsField,
                                ControllerUtils.getFieldLocalNameFromItsLabel(settingsLabel)
                        ),
                        builtVersionField.getText(),
                        builtSettingsField.getText() == null || settingsField.getText().isEmpty() ? null : ControllerUtils.getJSONFromTextField(
                                builtSettingsField,
                                ControllerUtils.getFieldLocalNameFromItsLabel(builtSettingsLabel)
                        ),
                        builtField.getText() == null || builtField.getText().isEmpty() ? null : ControllerUtils.getFileFromTextField(
                                builtField,
                                ControllerUtils.getFieldLocalNameFromItsLabel(builtLabel)
                        ),
                        ControllerUtils.getUUIDFromTextField(
                                projectIdField,
                                ControllerUtils.getFieldLocalNameFromItsLabel(projectIdLabel)
                        )
                );
            } catch (CommonUIFormatException e) {
                return null;
            }
        else {
            throw new CommonUIException(
                    "1004000",
                    "При сохранении/изменении развертывания должны быть заполнены обязательные атрибуты",
                    emptyField
            );
        }
    }

    public UUID getDeploymentId() throws CommonUIFormatException {
        return ControllerUtils.getUUIDFromTextField(
                deploymentIdField,
                ControllerUtils.getFieldLocalNameFromItsLabel(deploymentIdLabel)
        );
    }

    private List<String> getEmptyNecessaryFields() {
        List<String> emptyFields = new ArrayList<>();
        //if (deploymentIdField.getText() == null || deploymentIdField.getText().isEmpty())
        //    emptyFields.add(ControllerUtils.getFieldLocalNameFromItsLabel(deploymentIdLabel));
        if (statusIdField.getText() == null || statusIdField.getText().isEmpty())
            emptyFields.add(ControllerUtils.getFieldLocalNameFromItsLabel(statusIdLabel));
        if (projectIdField.getText() == null || projectIdField.getText().isEmpty())
            emptyFields.add(ControllerUtils.getFieldLocalNameFromItsLabel(projectIdLabel));
        return emptyFields;
    }
}
