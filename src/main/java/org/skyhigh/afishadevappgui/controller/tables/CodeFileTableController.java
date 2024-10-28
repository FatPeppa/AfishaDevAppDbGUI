package org.skyhigh.afishadevappgui.controller.tables;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.skyhigh.afishadevappgui.data.datasource.entity.CodeFile;

import java.time.LocalDateTime;
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
}
