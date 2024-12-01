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
import org.skyhigh.afishadevappgui.data.datasource.entity.RequirementType;
import org.skyhigh.afishadevappgui.data.repository.RequirementTypeRepository;
import org.skyhigh.afishadevappgui.data.repository.RequirementTypeRepositoryImpl;

import java.util.List;
import java.util.UUID;

public class RequirementTypeTableController implements RoleManagedTableController {
    private static final boolean isViewableForAnalyst = true;
    private static final boolean isViewableForDeveloper = true;
    private static final boolean isViewableForQA= true;
    private static final boolean isViewableForDevOps = true;
    private static final boolean isEditableForAnalyst = true;
    private static final boolean isEditableForDeveloper = false;
    private static final boolean isEditableForQA= true;
    private static final boolean isEditableForDevOps = false;

    @FXML
    private TableView<RequirementType> requirementTypeTable;

    @FXML
    private TableColumn<RequirementType, UUID> requirementTypeIdRequirementTypeTableColumn;

    @FXML
    private TableColumn<RequirementType, String> requirementTypeNameRequirementTypeTableColumn;

    private final RequirementTypeRepository requirementTypeRepository = new RequirementTypeRepositoryImpl(ApplicationPropertiesReader.getApplicationProperties());

    @Getter
    private RequirementType selectedRequirementType;

    public RequirementTypeTableController() throws CommonFlkException {}

    public void initialize() {
        requirementTypeIdRequirementTypeTableColumn.setCellValueFactory(new PropertyValueFactory<>("requirementTypeId"));
        requirementTypeNameRequirementTypeTableColumn.setCellValueFactory(new PropertyValueFactory<>("requirementTypeName"));
        setRequirementTypeTableSelectedItemPropertyListener();
    }

    public void fillTable() throws CommonFlkException {
        ObservableList<RequirementType> requirementTypesListView = FXCollections.observableArrayList();
        List<RequirementType> requirementTypes = requirementTypeRepository.getAllRequirementTypes(
                SortDirection.NONE,
                null
        );
        requirementTypesListView.addAll(requirementTypes);
        requirementTypeTable.setItems(requirementTypesListView);
    }

    public void fillTable(List<RequirementType> requirementTypes) {
        ObservableList<RequirementType> requirementTypeObservableList = FXCollections.observableArrayList();
        requirementTypeObservableList.addAll(requirementTypes);
        requirementTypeTable.setItems(requirementTypeObservableList);
    }

    private void setRequirementTypeTableSelectedItemPropertyListener() {
        requirementTypeTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null)
                selectedRequirementType = newSelection;
        });
    }

    public void clearSelection() {
        requirementTypeTable.getSelectionModel().clearSelection();
        selectedRequirementType = null;
    }

    public ObservableValue<RequirementType> getObservableSelectedRequirementType() {
        return requirementTypeTable.getSelectionModel().selectedItemProperty();
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
