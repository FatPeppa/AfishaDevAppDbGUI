package org.skyhigh.afishadevappgui.controller.filters;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.skyhigh.afishadevappgui.common.controller.ControllerUtils;
import org.skyhigh.afishadevappgui.common.properties.ApplicationPropertiesReader;
import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.common.validation.CommonUIFormatException;
import org.skyhigh.afishadevappgui.data.datasource.entity.Project;
import org.skyhigh.afishadevappgui.data.datasource.entity.RequirementType;
import org.skyhigh.afishadevappgui.data.repository.ProjectRepository;
import org.skyhigh.afishadevappgui.data.repository.ProjectRepositoryImpl;
import org.skyhigh.afishadevappgui.data.repository.RequirementTypeRepository;
import org.skyhigh.afishadevappgui.data.repository.RequirementTypeRepositoryImpl;
import org.skyhigh.afishadevappgui.service.logic.role.RoleManagerService;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.skyhigh.afishadevappgui.common.controller.ControllerUtils.getFieldLocalNameFromItsLabel;

public class RequirementFiltersController {
    @FXML
    TextField requirementIdInputField;

    @FXML
    TextField requirementTypeIdInputField;

    @FXML
    TextField loadDateStartInputField;

    @FXML
    TextField loadDateEndInputField;

    @FXML
    TextField lastChangeDateStartInputField;

    @FXML
    TextField lastChangeDateEndInputField;

    @FXML
    Label requirementIdLabel;

    @FXML
    Label requirementTypeIdLabel;

    @FXML
    Label loadDateBeginLabel;

    @FXML
    Label loadDateEndLabel;

    @FXML
    Label lastChangeDateBeginLabel;

    @FXML
    Label lastChangeDateEndLabel;

    private RoleManagerService roleManagerService;

    public void initialize() {
        setRequirementIdInputFieldChangeListener();
        setRequirementTypeIdInputFieldChangeListener();
        setLoadDateStartInputFieldChangeListener();
        setLoadDateEndInputFieldChangeListener();
        setLastChangeDateStartInputFieldChangeListener();
        setLastChangeDateEndInputFieldChangeListener();
    }

    public UUID getRequirementId() {
        try {
            return ControllerUtils.getUUIDFromTextField(requirementIdInputField, getFieldLocalNameFromItsLabel(requirementIdLabel));
        } catch (CommonUIFormatException e) {
            return null;
        }
    }

    public UUID getRequirementTypeId() throws CommonFlkException {
        try {
            if (requirementTypeIdInputField.getText() == null || requirementTypeIdInputField.getText().isEmpty())
                return null;
            if (roleManagerService != null && !roleManagerService.checkIfCurrentUserAdmin()) {
                String requirementTypeName = requirementTypeIdInputField.getText();
                RequirementTypeRepository requirementTypeRepository = new RequirementTypeRepositoryImpl(
                        ApplicationPropertiesReader.getApplicationProperties()
                );
                RequirementType requirementType = requirementTypeRepository.getRequirementTypeByName(requirementTypeName);
                if (requirementType == null)
                    throw new CommonFlkException("Тип требования с именем '" + requirementTypeName + "' не существует");
                return requirementType.getRequirementTypeId();
            }
            return ControllerUtils.getUUIDFromTextField(requirementTypeIdInputField, getFieldLocalNameFromItsLabel(requirementTypeIdLabel));
        } catch (CommonUIFormatException e) {
            return null;
        }
    }

    public LocalDateTime getLoadDateStart() {
        try {
            return ControllerUtils.getLocalDateTimeFromTextField(loadDateStartInputField, getFieldLocalNameFromItsLabel(loadDateBeginLabel));
        } catch (CommonUIFormatException e) {
            return null;
        }
    }

    public LocalDateTime getLoadDateEnd() {
        try {
            return ControllerUtils.getLocalDateTimeFromTextField(loadDateEndInputField, getFieldLocalNameFromItsLabel(loadDateEndLabel));
        } catch (CommonUIFormatException e) {
            return null;
        }
    }

    public LocalDateTime getLastChangeDateStart() {
        try {
            return ControllerUtils.getLocalDateTimeFromTextField(lastChangeDateStartInputField, getFieldLocalNameFromItsLabel(lastChangeDateBeginLabel));
        } catch (CommonUIFormatException e) {
            return null;
        }
    }

    public LocalDateTime getLastChangeDateEnd() {
        try {
            return ControllerUtils.getLocalDateTimeFromTextField(lastChangeDateEndInputField, getFieldLocalNameFromItsLabel(lastChangeDateEndLabel));
        } catch (CommonUIFormatException e) {
            return null;
        }
    }

    private void setRequirementIdInputFieldChangeListener() {
        requirementIdInputField.textProperty().addListener((observable, oldValue, newValue) -> {
            requirementTypeIdInputField.setDisable(newValue != null && !newValue.isEmpty());
            loadDateStartInputField.setDisable(newValue != null && !newValue.isEmpty());
            loadDateEndInputField.setDisable(newValue != null && !newValue.isEmpty());
            lastChangeDateStartInputField.setDisable(newValue != null && !newValue.isEmpty());
            lastChangeDateEndInputField.setDisable(newValue != null && !newValue.isEmpty());
        });
    }

    private void setRequirementTypeIdInputFieldChangeListener() {
        requirementTypeIdInputField.textProperty().addListener((observable, oldValue, newValue) -> {
            requirementIdInputField.setDisable(newValue != null && !newValue.isEmpty());
            loadDateStartInputField.setDisable(newValue != null && !newValue.isEmpty());
            loadDateEndInputField.setDisable(newValue != null && !newValue.isEmpty());
            lastChangeDateStartInputField.setDisable(newValue != null && !newValue.isEmpty());
            lastChangeDateEndInputField.setDisable(newValue != null && !newValue.isEmpty());
        });
    }

    private void setLoadDateStartInputFieldChangeListener() {
        loadDateStartInputField.textProperty().addListener((observable, oldValue, newValue) -> {
            requirementIdInputField.setDisable(newValue != null && !newValue.isEmpty());
            requirementTypeIdInputField.setDisable(newValue != null && !newValue.isEmpty());
            lastChangeDateStartInputField.setDisable(newValue != null && !newValue.isEmpty());
            lastChangeDateEndInputField.setDisable(newValue != null && !newValue.isEmpty());
        });
    }

    private void setLoadDateEndInputFieldChangeListener() {
        loadDateEndInputField.textProperty().addListener((observable, oldValue, newValue) -> {
            requirementIdInputField.setDisable(newValue != null && !newValue.isEmpty());
            requirementTypeIdInputField.setDisable(newValue != null && !newValue.isEmpty());
            lastChangeDateStartInputField.setDisable(newValue != null && !newValue.isEmpty());
            lastChangeDateEndInputField.setDisable(newValue != null && !newValue.isEmpty());
        });
    }

    private void setLastChangeDateStartInputFieldChangeListener() {
        lastChangeDateStartInputField.textProperty().addListener((observable, oldValue, newValue) -> {
            requirementIdInputField.setDisable(newValue != null && !newValue.isEmpty());
            requirementTypeIdInputField.setDisable(newValue != null && !newValue.isEmpty());
            loadDateStartInputField.setDisable(newValue != null && !newValue.isEmpty());
            loadDateEndInputField.setDisable(newValue != null && !newValue.isEmpty());
        });
    }

    private void setLastChangeDateEndInputFieldChangeListener() {
        lastChangeDateEndInputField.textProperty().addListener((observable, oldValue, newValue) -> {
            requirementIdInputField.setDisable(newValue != null && !newValue.isEmpty());
            requirementTypeIdInputField.setDisable(newValue != null && !newValue.isEmpty());
            loadDateStartInputField.setDisable(newValue != null && !newValue.isEmpty());
            loadDateEndInputField.setDisable(newValue != null && !newValue.isEmpty());
        });
    }

    public void setRoleManagerService(RoleManagerService roleManagerService) throws CommonFlkException {
        this.roleManagerService = roleManagerService;

        if (!roleManagerService.checkIfCurrentUserAdmin()) {
            requirementTypeIdInputField.setPromptText("Введите, пожалуйста, тип требования (Строка)");
            requirementTypeIdLabel.setText("Тип требования:");
        }
    }
}
