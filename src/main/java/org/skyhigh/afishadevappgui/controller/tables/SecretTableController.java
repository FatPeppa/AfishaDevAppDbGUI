package org.skyhigh.afishadevappgui.controller.tables;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.Getter;
import org.skyhigh.afishadevappgui.common.properties.ApplicationPropertiesReader;
import org.skyhigh.afishadevappgui.common.sort.SortDirection;
import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.data.datasource.entity.Secret;
import org.skyhigh.afishadevappgui.data.repository.SecretRepository;
import org.skyhigh.afishadevappgui.data.repository.SecretRepositoryImpl;

import java.util.List;
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

    private final SecretRepository secretRepository = new SecretRepositoryImpl(ApplicationPropertiesReader.getApplicationProperties());

    @Getter
    private Secret selectedSecret;

    public SecretTableController() throws CommonFlkException {}

    public void initialize() {
        secretIdSecretTableColumn.setCellValueFactory(new PropertyValueFactory<>("secretId"));
        deploymentIdSecretTableColumn.setCellValueFactory(new PropertyValueFactory<>("deploymentId"));
        addressSecretTableColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        loginSecretTableColumn.setCellValueFactory(new PropertyValueFactory<>("login"));
        passwordSecretTableColumn.setCellValueFactory(new PropertyValueFactory<>("password"));
        setSecretTableSelectedItemPropertyListener();
    }

    public void fillTable() throws CommonFlkException {
        ObservableList<Secret> secretsListView = FXCollections.observableArrayList();
        List<Secret> secrets = secretRepository.getAllSecret(
                SortDirection.NONE,
                null
        );
        secretsListView.addAll(secrets);
        secretTable.setItems(secretsListView);
    }

    public void fillTable(List<Secret> secrets) {
        ObservableList<Secret> secretObservableList = FXCollections.observableArrayList();
        secretObservableList.addAll(secrets);
        secretTable.setItems(secretObservableList);
    }

    private void setSecretTableSelectedItemPropertyListener() {
        secretTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null)
                selectedSecret = newSelection;
        });
    }

    public void clearSelection() {
        secretTable.getSelectionModel().clearSelection();
        selectedSecret = null;
    }

    public ObservableValue<Secret> getObservableSelectedSecret() {
        return secretTable.getSelectionModel().selectedItemProperty();
    }
}
