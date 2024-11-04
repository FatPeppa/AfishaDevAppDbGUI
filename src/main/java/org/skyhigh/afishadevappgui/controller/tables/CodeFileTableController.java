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
import org.skyhigh.afishadevappgui.data.datasource.entity.CodeFile;
import org.skyhigh.afishadevappgui.data.repository.CodeFileRepository;
import org.skyhigh.afishadevappgui.data.repository.CodeFileRepositoryImpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class CodeFileTableController {
    @FXML
    private TableView<CodeFile> codeFileTable;

    @FXML
    private TableColumn<CodeFile, UUID> codeFileIdTableColumn;

    @FXML
    private TableColumn<CodeFile, UUID> projectIdCodeFileTableColumn;

    @FXML
    private TableColumn<CodeFile, String> fileContentCodeFileTableColumn;

    @FXML
    private TableColumn<CodeFile, LocalDateTime> loadDateCodeFileTableColumn;

    private final CodeFileRepository codeFileRepository = new CodeFileRepositoryImpl(ApplicationPropertiesReader.getApplicationProperties());

    public CodeFileTableController() throws CommonFlkException {}

    public void initialize() {
        codeFileIdTableColumn.setCellValueFactory(new PropertyValueFactory<>("codeFileId"));
        projectIdCodeFileTableColumn.setCellValueFactory(new PropertyValueFactory<>("projectId"));
        fileContentCodeFileTableColumn.setCellValueFactory(new PropertyValueFactory<>("fileContent"));
        loadDateCodeFileTableColumn.setCellValueFactory(new PropertyValueFactory<>("loadDate"));
    }

    public void fillTable() throws CommonFlkException {
        ObservableList<CodeFile> codeFilesListView = FXCollections.observableArrayList();
        List<CodeFile> codeFiles = codeFileRepository.getAllCodeFiles(
                SortDirection.NONE,
                null
        );
        codeFilesListView.addAll(codeFiles);
        codeFileTable.setItems(codeFilesListView);
    }

    public void fillTable(List<CodeFile> codeFiles) {
        ObservableList<CodeFile> codeFileObservableList = FXCollections.observableArrayList();
        codeFileObservableList.addAll(codeFiles);
        codeFileTable.setItems(codeFileObservableList);
    }
}
