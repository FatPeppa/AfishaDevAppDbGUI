package org.skyhigh.afishadevappgui.controller.rowinteraction;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.skyhigh.afishadevappgui.common.controller.ControllerUtils;
import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.common.validation.CommonUIException;
import org.skyhigh.afishadevappgui.common.validation.CommonUIFormatException;
import org.skyhigh.afishadevappgui.data.datasource.entity.ProjectAuthor;

import java.util.ArrayList;
import java.util.List;

public class RowProjectAuthorController {
    @FXML
    TextField projectIdField;

    @FXML
    TextField authorIdField;

    @FXML
    Label projectIdLabel;

    @FXML
    Label authorIdLabel;

    public void initialize() throws CommonFlkException {}

    public void autoFillFields(ProjectAuthor projectAuthor) {
        projectIdField.setText(projectAuthor.getProjectId().toString());
        authorIdField.setText(projectAuthor.getAuthorId().toString());
    }

    public void clearFields() {
        projectIdField.setText(null);
        authorIdField.setText(null);
    }

    public ProjectAuthor getProjectAuthor() throws CommonFlkException {
        List<String> emptyField = getEmptyNecessaryFields();
        if (emptyField.isEmpty())
            try {
                return new ProjectAuthor(
                        ControllerUtils.getUUIDFromTextField(
                                projectIdField,
                                ControllerUtils.getFieldLocalNameFromItsLabel(projectIdLabel)
                        ),
                        ControllerUtils.getUUIDFromTextField(
                                authorIdField,
                                ControllerUtils.getFieldLocalNameFromItsLabel(authorIdLabel)
                        )
                );
            } catch (CommonUIFormatException e) {
                return null;
            }
        else {
            throw new CommonUIException(
                    "1004000",
                    "При сохранении/удалении автора проекта должны быть заполнены обязательные атрибуты",
                    emptyField
            );
        }
    }

    private List<String> getEmptyNecessaryFields() {
        List<String> emptyFields = new ArrayList<>();
        if (projectIdField.getText() == null || projectIdField.getText().isEmpty())
            emptyFields.add(ControllerUtils.getFieldLocalNameFromItsLabel(projectIdLabel));
        if (authorIdField.getText() == null || authorIdField.getText().isEmpty())
            emptyFields.add(ControllerUtils.getFieldLocalNameFromItsLabel(authorIdLabel));
        return emptyFields;
    }
}
