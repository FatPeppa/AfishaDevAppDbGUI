package org.skyhigh.afishadevappgui.controller.rowinteraction;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.skyhigh.afishadevappgui.common.controller.ControllerUtils;
import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.common.validation.CommonUIException;
import org.skyhigh.afishadevappgui.common.validation.CommonUIFormatException;
import org.skyhigh.afishadevappgui.data.datasource.entity.Author;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class RowAuthorController {
    @FXML
    TextField authorIdField;

    @FXML
    TextField authorLoginField;

    @FXML
    Label authorIdLabel;

    @FXML
    Label loginLabel;

    private String authorIdFieldPrompt;
    private String authorLoginFieldPrompt;

    private String authorIdFieldBasicStyle;
    private String authorLoginFieldBasicStyle;

    public void initialize() throws CommonFlkException {
        authorIdFieldPrompt = authorIdField.getPromptText();
        authorLoginFieldPrompt = authorLoginField.getPromptText();
        authorIdFieldBasicStyle = authorIdField.getStyle();
        authorLoginFieldBasicStyle = authorLoginField.getStyle();
    }

    public void autoFillFields(Author author) {
        authorIdField.setText(author.getAuthorId().toString());
        if (author.getLogin() != null) {authorLoginField.setText(author.getLogin());}
    }

    public void clearFields() {
        authorIdField.setText(null);
        authorLoginField.setText(null);
    }

    public Author getAuthor() throws CommonFlkException {
        List<String> emptyField = getEmptyNecessaryFields();
        if (emptyField.isEmpty())
            try {
                return new Author(
                        ControllerUtils.getUUIDFromTextField(
                                authorIdField,
                                ControllerUtils.getFieldLocalNameFromItsLabel(authorIdLabel)
                        ),
                        authorLoginField.getText()
                );
            } catch (CommonUIFormatException e) {
                return null;
            }
        else {
            throw new CommonUIException(
                    "1004000",
                    "При сохранении/изменении автора должны быть заполнены обязательные атрибуты",
                    emptyField
            );
        }
    }

    public UUID getAuthorId() throws CommonUIFormatException {
        return ControllerUtils.getUUIDFromTextField(
                authorIdField,
                ControllerUtils.getFieldLocalNameFromItsLabel(authorIdLabel)
        );
    }

    private List<String> getEmptyNecessaryFields() {
        List<String> emptyFields = new ArrayList<>();
        //if (authorIdField.getText() == null || authorIdField.getText().isEmpty())
        //    emptyFields.add(ControllerUtils.getFieldLocalNameFromItsLabel(authorIdLabel));
        if (authorLoginField.getText() == null || authorLoginField.getText().isEmpty())
            emptyFields.add(ControllerUtils.getFieldLocalNameFromItsLabel(loginLabel));
        return emptyFields;
    }

    public void setFieldsEditable(boolean editable) {
        if (editable) {
            authorIdField.setPromptText(authorIdFieldPrompt);
            authorIdField.setEditable(false);
            authorIdField.setStyle(authorIdFieldBasicStyle);

            authorLoginField.setPromptText(authorLoginFieldPrompt);
            authorLoginField.setEditable(true);
            authorLoginField.setStyle(authorLoginFieldBasicStyle);
        } else {
            authorIdField.setPromptText("Заполняется автоматически");
            authorIdField.setEditable(false);
            authorIdField.setStyle("-fx-background-color: #e6e6e6; -fx-border-color: black;");

            authorLoginField.setPromptText("Заполняется автоматически");
            authorLoginField.setEditable(false);
            authorLoginField.setStyle("-fx-background-color: #e6e6e6; -fx-border-color: black;");
        }
    }
}
