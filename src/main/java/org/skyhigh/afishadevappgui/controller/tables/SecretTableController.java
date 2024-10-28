package org.skyhigh.afishadevappgui.controller.tables;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.skyhigh.afishadevappgui.data.datasource.entity.Secret;

import java.util.UUID;

public class SecretTableController {
    @FXML
    private TableView<Secret> secretTable;

    @FXML
    private TableColumn<Secret, UUID> secretIdSecretTableColumn;

    @FXML
    private TableColumn<Secret, UUID> deploymentIdSecretTableColumn;

    @FXML
    private TableColumn<Secret, String> addressSecretTableColumn;

    @FXML
    private TableColumn<Secret, String> loginSecretTableColumn;

    @FXML
    private TableColumn<Secret, String> passwordSecretTableColumn;
}
