package org.skyhigh.afishadevappgui.data.datasource.dao;

import lombok.NonNull;
import org.skyhigh.afishadevappgui.common.sort.SortDirection;
import org.skyhigh.afishadevappgui.data.datasource.entity.CodeFile;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public interface CodeFileDAO {
    UUID saveCodeFile(@NonNull CodeFile codeFile) throws SQLException;
    void updateCodeFileContentById(@NonNull UUID codeFileId, @NonNull String content) throws SQLException;
    void updateCodeFileProjectId(@NonNull UUID codeFileId, @NonNull UUID projectId) throws SQLException;
    CodeFile getCodeFileById(@NonNull UUID codeFileId) throws SQLException;
    List<CodeFile> getAllCodeFilesByProjectId(@NonNull UUID projectId, @NonNull SortDirection sortDirection) throws SQLException;
    List<CodeFile> getAllCodeFiles(@NonNull SortDirection sortDirection) throws SQLException;
}
