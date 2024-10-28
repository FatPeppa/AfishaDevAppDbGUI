package org.skyhigh.afishadevappgui.controller.tables;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.skyhigh.afishadevappgui.data.datasource.entity.Author;

import java.util.UUID;

public class AuthorTableController {
    @FXML
    private TableView<Author> authorTable;

    @FXML
    private TableColumn<Author, UUID> authorIdAuthorColumn;

    @FXML
    private TableColumn<Author, String> loginAuthorTableColumn;
}
