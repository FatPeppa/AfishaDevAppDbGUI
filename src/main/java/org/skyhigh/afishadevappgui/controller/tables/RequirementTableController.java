package org.skyhigh.afishadevappgui.controller.tables;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.json.JSONObject;
import org.skyhigh.afishadevappgui.common.properties.ApplicationPropertiesReader;
import org.skyhigh.afishadevappgui.common.sort.SortDirection;
import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.data.datasource.entity.Requirement;
import org.skyhigh.afishadevappgui.data.repository.RequirementRepository;
import org.skyhigh.afishadevappgui.data.repository.RequirementRepositoryImpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class RequirementTableController {
    @FXML
    private TableView<Requirement> requirementTable;

    @FXML
    private TableColumn<Requirement, UUID> requirementIdRequirementTableColumn;

    @FXML
    private TableColumn<Requirement, UUID> requirementTypeIdRequirementTableColumn;

    @FXML
    private TableColumn<Requirement, LocalDateTime> loadDateRequirementTableColumn;

    @FXML
    private TableColumn<Requirement, LocalDateTime> lastChangeDateRequirementTableColumn;

    @FXML
    private TableColumn<Requirement, JSONObject> contentRequirementTableColumn;

    private final RequirementRepository requirementRepository = new RequirementRepositoryImpl(ApplicationPropertiesReader.getApplicationProperties());

    public RequirementTableController() throws CommonFlkException {}

    public void initialize() {
        requirementIdRequirementTableColumn.setCellValueFactory(new PropertyValueFactory<>("requirementId"));
        requirementTypeIdRequirementTableColumn.setCellValueFactory(new PropertyValueFactory<>("requirementTypeId"));
        loadDateRequirementTableColumn.setCellValueFactory(new PropertyValueFactory<>("loadDate"));
        lastChangeDateRequirementTableColumn.setCellValueFactory(new PropertyValueFactory<>("lastChangeDate"));
        contentRequirementTableColumn.setCellValueFactory(new PropertyValueFactory<>("content"));
    }

    public void fillTable() throws CommonFlkException {
        ObservableList<Requirement> requirementsListView = FXCollections.observableArrayList();
        List<Requirement> requirements = requirementRepository.getAllRequirements(
                SortDirection.NONE,
                null
        );
        requirementsListView.addAll(requirements);
        requirementTable.setItems(requirementsListView);
    }
}
