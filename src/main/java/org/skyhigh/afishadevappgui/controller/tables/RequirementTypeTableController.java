package org.skyhigh.afishadevappgui.controller.tables;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.skyhigh.afishadevappgui.common.properties.ApplicationPropertiesReader;
import org.skyhigh.afishadevappgui.common.sort.SortDirection;
import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.data.datasource.entity.RequirementType;
import org.skyhigh.afishadevappgui.data.repository.RequirementTypeRepository;
import org.skyhigh.afishadevappgui.data.repository.RequirementTypeRepositoryImpl;

import java.util.List;
import java.util.UUID;

public class RequirementTypeTableController {
    @FXML
    private TableView<RequirementType> requirementTypeTable;

    @FXML
    private TableColumn<RequirementType, UUID> requirementTypeIdRequirementTypeTableColumn;

    @FXML
    private TableColumn<RequirementType, String> requirementTypeNameRequirementTypeTableColumn;

    private final RequirementTypeRepository requirementTypeRepository = new RequirementTypeRepositoryImpl(ApplicationPropertiesReader.getApplicationProperties());

    public RequirementTypeTableController() throws CommonFlkException {}

    public void initialize() {
        requirementTypeIdRequirementTypeTableColumn.setCellValueFactory(new PropertyValueFactory<>("requirementTypeId"));
        requirementTypeNameRequirementTypeTableColumn.setCellValueFactory(new PropertyValueFactory<>("requirementTypeName"));
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
}
