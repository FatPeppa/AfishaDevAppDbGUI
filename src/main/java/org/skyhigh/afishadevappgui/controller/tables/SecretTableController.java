package org.skyhigh.afishadevappgui.controller.tables;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.Getter;
import org.skyhigh.afishadevappgui.common.controller.RoleManagedTableController;
import org.skyhigh.afishadevappgui.common.properties.ApplicationPropertiesReader;
import org.skyhigh.afishadevappgui.common.sort.SortDirection;
import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.data.datasource.entity.Secret;
import org.skyhigh.afishadevappgui.data.repository.SecretRepository;
import org.skyhigh.afishadevappgui.data.repository.SecretRepositoryImpl;

import java.util.List;
import java.util.UUID;

public class SecretTableController implements RoleManagedTableController {
    private static final boolean isViewableForAnalyst = false;
    private static final boolean isViewableForDeveloper = false;
    private static final boolean isViewableForQA = true;
    private static final boolean isViewableForDevOps = true;
    private static final boolean isEditableForAnalyst = false;
    private static final boolean isEditableForDeveloper = false;
    private static final boolean isEditableForQA= true;
    private static final boolean isEditableForDevOps = true;

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

    @Override
    public boolean getAccessibilityForViewingByAnalyst() {
        return isViewableForAnalyst;
    }

    @Override
    public boolean getAccessibilityForViewingByDeveloper() {
        return isViewableForDeveloper;
    }

    @Override
    public boolean getAccessibilityForViewingByQA() {
        return isViewableForQA;
    }

    @Override
    public boolean getAccessibilityForViewingByDevOps() {
        return isViewableForDevOps;
    }

    @Override
    public boolean getAccessibilityForEditingByAnalyst() {
        return isEditableForAnalyst;
    }

    @Override
    public boolean getAccessibilityForEditingByDeveloper() {
        return isEditableForDeveloper;
    }

    @Override
    public boolean getAccessibilityForEditingByQA() {
        return isEditableForQA;
    }

    @Override
    public boolean getAccessibilityForEditingByDevOps() {
        return isEditableForDevOps;
    }
}
