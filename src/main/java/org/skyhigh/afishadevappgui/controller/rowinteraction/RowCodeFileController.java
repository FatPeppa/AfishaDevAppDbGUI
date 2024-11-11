package org.skyhigh.afishadevappgui.controller.rowinteraction;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.skyhigh.afishadevappgui.common.controller.ControllerUtils;
import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.common.validation.CommonUIException;
import org.skyhigh.afishadevappgui.common.validation.CommonUIFormatException;
import org.skyhigh.afishadevappgui.data.datasource.entity.CodeFile;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class RowCodeFileController {
    @FXML
    TextField codeFileIdField;

    @FXML
    TextField projectIdField;

    @FXML
    TextField contentField;

    @FXML
    TextField codeFileUploadDateField;

    @FXML
    Label codeFileIdLabel;

    @FXML
    Label projectIdLabel;

    @FXML
    Label contentLabel;

    @FXML
    Label uploadDateLabel;

    public void initialize() throws CommonFlkException {}

    public void autoFillFields(CodeFile codeFile) {
        codeFileIdField.setText(codeFile.getCodeFileId().toString());
        projectIdField.setText(codeFile.getProjectId().toString());
        projectIdField.setEditable(false);
        projectIdField.setStyle("-fx-background-color: #e6e6e6; -fx-border-color: black;");
        contentField.setText(codeFile.getFileContent());
        if (codeFile.getLoadDate() != null) {codeFileUploadDateField.setText(codeFile.getLoadDate().toString());}
    }

    public void clearFields() {
        codeFileIdField.setText(null);
        projectIdField.setText(null);
        projectIdField.setEditable(true);
        projectIdField.setStyle(null);
        contentField.setText(null);
        codeFileUploadDateField.setText(null);
    }

    public CodeFile getCodeFile() throws CommonFlkException {
        List<String> emptyField = getEmptyNecessaryFields();
        if (emptyField.isEmpty())
            try {
                return new CodeFile(
                        ControllerUtils.getUUIDFromTextField(
                                codeFileIdField,
                                ControllerUtils.getFieldLocalNameFromItsLabel(codeFileIdLabel)
                        ),
                        ControllerUtils.getUUIDFromTextField(
                                projectIdField,
                                ControllerUtils.getFieldLocalNameFromItsLabel(projectIdLabel)
                        ),
                        contentField.getText(),
                        codeFileUploadDateField.getText() == null ? null : ControllerUtils.getLocalDateTimeFromTextField(
                                codeFileUploadDateField,
                                ControllerUtils.getFieldLocalNameFromItsLabel(uploadDateLabel)
                        )
                );
            } catch (CommonUIFormatException e) {
                return null;
            }
        else {
            throw new CommonUIException(
                    "1004000",
                    "При сохранении/изменении файла с кодом должны быть заполнены обязательные атрибуты",
                    emptyField
            );
        }
    }

    public UUID getCodeFileId() throws CommonUIFormatException {
        return ControllerUtils.getUUIDFromTextField(
                codeFileIdField,
                ControllerUtils.getFieldLocalNameFromItsLabel(codeFileIdLabel)
        );
    }

    private List<String> getEmptyNecessaryFields() {
        List<String> emptyFields = new ArrayList<>();
        //if (codeFileIdField.getText() == null || codeFileIdField.getText().isEmpty())
        //    emptyFields.add(ControllerUtils.getFieldLocalNameFromItsLabel(codeFileIdLabel));
        if (projectIdField.getText() == null || projectIdField.getText().isEmpty())
            emptyFields.add(ControllerUtils.getFieldLocalNameFromItsLabel(projectIdLabel));
        if (contentField.getText() == null || contentField.getText().isEmpty())
            emptyFields.add(ControllerUtils.getFieldLocalNameFromItsLabel(contentLabel));
        return emptyFields;
    }
}