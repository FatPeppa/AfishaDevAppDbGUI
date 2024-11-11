package org.skyhigh.afishadevappgui.common.controller;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import org.json.JSONException;
import org.json.JSONObject;
import org.skyhigh.afishadevappgui.AfishaDevGUIApplication;
import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.common.validation.CommonUIException;
import org.skyhigh.afishadevappgui.common.validation.CommonUIFormatException;
import org.skyhigh.afishadevappgui.data.validation.entity.inserting.files.Flk10020002Validator;

import java.io.File;
import java.io.StringWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.util.Optional;
import java.util.UUID;

public class ControllerUtils {
    public static UUID getUUIDFromTextField(
            TextField textField,
            String textFieldLogicName
    ) throws CommonUIFormatException {
        if (textField.getText() == null || textField.getText().isEmpty())
            return null;
        try {
            return UUID.fromString(textField.getText());
        } catch (IllegalArgumentException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Ошибка");
            alert.setHeaderText(String.format("Недопустимый формат поля '%s'", textFieldLogicName));
            alert.setContentText("Тип поля должен быть UUID");
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.getDialogPane().setMinWidth(Region.USE_PREF_SIZE);

            alert.showAndWait();

            return null;
        }
    }

    public static Integer getIntegerFromTextField(
            TextField textField,
            String textFieldLogicName
    ) throws CommonUIFormatException  {
        if (textField.getText() == null || textField.getText().isEmpty())
            return null;
        try {
            return Integer.parseInt(textField.getText());
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Ошибка");
            alert.setHeaderText("Недопустимый формат поля");
            alert.setContentText(String.format("Тип поля '%s' должен быть Integer", textFieldLogicName));
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.getDialogPane().setMinWidth(Region.USE_PREF_SIZE);

            alert.showAndWait();

            throw new CommonUIFormatException("Ошибка формата данных");
        }
    }

    public static JSONObject getJSONFromTextField(
            TextField textField,
            String textFieldLogicName
    ) throws CommonUIFormatException  {
        if (textField.getText() == null || textField.getText().isEmpty())
            return null;
        try {
            return new JSONObject(textField.getText());
        } catch (JSONException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Ошибка");
            alert.setHeaderText("Недопустимый формат поля");
            alert.setContentText(String.format("Тип поля '%s' должен быть JSON", textFieldLogicName));
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.getDialogPane().setMinWidth(Region.USE_PREF_SIZE);

            alert.showAndWait();

            throw new CommonUIFormatException("Ошибка формата данных");
        }
    }

    public static File getFileFromTextField(
            TextField textField,
            String textFieldLogicName
    ) throws CommonUIFormatException  {
        if (textField.getText() == null || textField.getText().isEmpty())
            return null;
        try {
            Flk10020002Validator.validate(textField.getText());

            return new File(textField.getText());
        } catch (CommonFlkException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Ошибка");
            alert.setHeaderText("Некорректно заполненный путь к файлу");
            alert.setContentText(String.format("Значение '%s' должно содержать корректно заполненный путь к файлу", textFieldLogicName));
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.getDialogPane().setMinWidth(Region.USE_PREF_SIZE);

            alert.showAndWait();

            throw new CommonUIFormatException("Ошибка формата данных");
        }
    }

    //2024-10-01 23:00:00
    //2024-10-01T23:00:00
    public static LocalDateTime getLocalDateTimeFromTextField(
            TextField textField,
            String textFieldLogicName
    ) throws CommonUIFormatException  {
        String textFieldValue = textField.getText();
        if (textField.getText() == null || textFieldValue.isEmpty())
            return null;
        try{
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            format.setLenient(false);
            format.parse(textFieldValue);
            DateTimeFormatter dateTimeFormatter = new DateTimeFormatterBuilder()
                    .appendPattern("yyyy-MM-dd'T'HH:mm:ss")
                    .appendPattern("[.SSSSSSSSS][.SSSSSS][.SSS]")
                    .toFormatter();
            return LocalDateTime.parse(textFieldValue, dateTimeFormatter);
        } catch(ParseException | DateTimeParseException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Ошибка");
            alert.setHeaderText("Недопустимый формат поля");
            alert.setContentText(String.format("Значение поля '%s' должно соответствовать маске 'ГГГГ-ММ-ДДTЧЧ-МИ-СС.МС', " +
                    "где ГГГГ - год, ММ - месяц, ДД - день, ЧЧ - час, МИ - минута и СС - секунда, МС - миллисекунды, а латинская T - разделитель", textFieldLogicName));
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.getDialogPane().setMinWidth(Region.USE_PREF_SIZE);

            alert.showAndWait();

            throw new CommonUIFormatException("Ошибка формата данных");
        }
    }

    public static String getFieldLocalNameFromItsLabel(Label label) {
        if (label == null || label.getText() == null || label.getText().isEmpty())
            return null;
        return label.getText().replaceAll(":$", "");
    }

    public static void showCommonFlkExceptionAlert(CommonFlkException e) {
        StringWriter errorMsg = new StringWriter();
        errorMsg.write(e.getMessage());

        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Ошибка");
        alert.setHeaderText("Ошибка при проверке ФЛК");
        alert.setContentText(String.format(errorMsg.toString()));
        alert.setResizable(true);
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.getDialogPane().setMinWidth(Region.USE_PREF_SIZE);

        alert.showAndWait();
    }

    public static void showCommonErrorAlert(String description) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Непредвиденная ошибка");
        alert.setHeaderText(String.format("Произошла непредвиденная ошибка: %s", description));
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.getDialogPane().setMinWidth(Region.USE_PREF_SIZE);

        alert.showAndWait();
    }

    public static void showCommonWarningAlert(String description) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Предупреждение");
        alert.setHeaderText(String.format("%s", description));
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.getDialogPane().setMinWidth(Region.USE_PREF_SIZE);

        alert.showAndWait();
    }

    public static void showInformationDialog(String header, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Сообщение");
        alert.setHeaderText(header);
        alert.setContentText(content);

        alert.showAndWait();
    }

    public static void checkDateDiapasonPositive(LocalDateTime begin, LocalDateTime end, String beginLabelName, String endLabelName) throws CommonFlkException {
        if (begin != null && end != null) {
            if (!end.isAfter(begin) && !begin.equals(end))
                throw new CommonUIException(
                        "1004003",
                        String.format(
                                "Диапазон дат {%s, %s} некорректен, значение '%s' должно быть больше '%s'",
                                beginLabelName,
                                endLabelName,
                                endLabelName,
                                beginLabelName
                        )
                );
        }
    }

    public static Boolean showModalForAssigningOperation(String question) {
        Dialog<Boolean> dialog = new Dialog<>();
        dialog.setTitle("Подтверждение");
        dialog.setHeaderText("Необходимо подтверждение");

        dialog.setResizable(true);
        dialog.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        dialog.getDialogPane().setMinWidth(Region.USE_PREF_SIZE);

        dialog.setGraphic(new ImageView(AfishaDevGUIApplication.class.getResource("images/assign-image.png").toString()));

        ButtonType assignOperation = new ButtonType("Да", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelOperation = new ButtonType("Нет", ButtonBar.ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().addAll(assignOperation, cancelOperation);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        grid.add(new Label(question), 0, 0);

        Node assignOperationButton = dialog.getDialogPane().lookupButton(assignOperation);
        Node cancelOperationButton = dialog.getDialogPane().lookupButton(cancelOperation);

        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(dialogButton -> dialogButton == assignOperation);

        Optional<Boolean> result = dialog.showAndWait();

        return result.orElse(null);
    }

    public static void showSuccessfulEntitySaveDialog(String content) {
        showInformationDialog(
                "Запись успешно сохранена",
                content
        );
    }

    public static void showSuccessfulEntityDeletionDialog(String content) {
        showInformationDialog(
                "Запись успешно удалена",
                content
        );
    }

    public static void showSuccessfulEntityUpdatingDialog(String content) {
        showInformationDialog(
                "Запись успешно обновлена",
                content
        );
    }

    ///**
    // * Метод замены текста ФЛК, где указаны системные наименования полей сущности, на соответствующие наименования UI-полей, понятные пользователю
    // * @param flkMessage Текст сообщения об ошибке ФЛК, системные наименования полей в котором необходимо заменить
    // * @param systemToUIFieldsMap Маппер системных наименований полей сущности на UI наименования. Должен иметь вид {"System name of field": "Logical (UI) name of field"}
    // * @return Сообщение об ошибке ФЛК с замененными системными наименованиями полей сущности, или null, в случае ошибки
    //*
    /*public static String changeFlkMessageEntitySystemFieldsNamesForUINames(String flkMessage, Map<String, String> systemToUIFieldsMap) {

    }*/
}
