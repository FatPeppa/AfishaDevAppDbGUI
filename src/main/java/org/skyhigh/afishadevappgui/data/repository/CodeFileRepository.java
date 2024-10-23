package org.skyhigh.afishadevappgui.data.repository;

import lombok.NonNull;
import org.skyhigh.afishadevappgui.common.sort.SortDirection;
import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.data.datasource.entity.CodeFile;

import java.util.List;
import java.util.UUID;

public interface CodeFileRepository {
    UUID saveCodeFile(@NonNull CodeFile codeFile) throws CommonFlkException;
    void updateCodeFileContentById(@NonNull UUID codeFileId, @NonNull String content) throws CommonFlkException;
    void deleteCodeFileById(@NonNull UUID codeFileId) throws CommonFlkException;
    CodeFile getCodeFileById(@NonNull UUID codeFileId) throws CommonFlkException;
    List<CodeFile> getAllCodeFilesByProjectId(
            @NonNull UUID projectId,
            @NonNull SortDirection sortDirection,
            String sortBy
    ) throws CommonFlkException;
    List<CodeFile> getAllCodeFiles(@NonNull SortDirection sortDirection, String sortBy) throws CommonFlkException;
}
