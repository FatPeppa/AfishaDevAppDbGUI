package org.skyhigh.afishadevappgui.data.repository;

import lombok.NonNull;
import org.skyhigh.afishadevappgui.common.db.validation.CommonSystemException;
import org.skyhigh.afishadevappgui.common.properties.ApplicationProperties;
import org.skyhigh.afishadevappgui.common.sort.SortDirection;
import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.data.datasource.AfishaDevAppDb;
import org.skyhigh.afishadevappgui.data.datasource.dao.CodeFileDAO;
import org.skyhigh.afishadevappgui.data.datasource.entity.CodeFile;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class CodeFileRepositoryImpl implements CodeFileRepository {
    private final CodeFileDAO codeFileDAO;

    public CodeFileRepositoryImpl(ApplicationProperties applicationProperties) throws CommonFlkException {
        try {
            codeFileDAO = (new AfishaDevAppDb(applicationProperties)).getCodeFileDAO();
        } catch (SQLException e) {
            throw new CommonSystemException(
                    "Произошла системная ошибка. Текст ошибки: '" + e.getMessage() + "'",
                    e.getCause(),
                    false
            );
        }
    }

    @Override
    public UUID saveCodeFile(@NonNull CodeFile codeFile) throws CommonFlkException {
        try {
            return codeFileDAO.saveCodeFile(codeFile);
        } catch (SQLException e) {
            throw new CommonSystemException(
                    "Произошла системная ошибка. Текст ошибки: '" + e.getMessage() + "'",
                    e.getCause(),
                    false
            );
        }
    }

    @Override
    public void updateCodeFileContentById(@NonNull UUID codeFileId, @NonNull String content) throws CommonFlkException {
        try {
            codeFileDAO.updateCodeFileContentById(codeFileId, content);
        } catch (SQLException e) {
            throw new CommonSystemException(
                    "Произошла системная ошибка. Текст ошибки: '" + e.getMessage() + "'",
                    e.getCause(),
                    false
            );
        }
    }

    @Override
    public void deleteCodeFileById(@NonNull UUID codeFileId) throws CommonFlkException {
        try {
            codeFileDAO.deleteCodeFileById(codeFileId);
        } catch (SQLException e) {
            throw new CommonSystemException(
                    "Произошла системная ошибка. Текст ошибки: '" + e.getMessage() + "'",
                    e.getCause(),
                    false
            );
        }
    }

    @Override
    public CodeFile getCodeFileById(@NonNull UUID codeFileId) throws CommonFlkException {
        try {
            return codeFileDAO.getCodeFileById(codeFileId);
        } catch (SQLException e) {
            throw new CommonSystemException(
                    "Произошла системная ошибка. Текст ошибки: '" + e.getMessage() + "'",
                    e.getCause(),
                    false
            );
        }
    }

    @Override
    public List<CodeFile> getAllCodeFilesByProjectId(@NonNull UUID projectId, @NonNull SortDirection sortDirection, String sortBy) throws CommonFlkException {
        try {
            return codeFileDAO.getAllCodeFilesByProjectId(projectId, sortDirection, sortBy);
        } catch (SQLException e) {
            throw new CommonSystemException(
                    "Произошла системная ошибка. Текст ошибки: '" + e.getMessage() + "'",
                    e.getCause(),
                    false
            );
        }
    }

    @Override
    public List<CodeFile> getAllCodeFiles(@NonNull SortDirection sortDirection, String sortBy) throws CommonFlkException {
        try {
            return codeFileDAO.getAllCodeFiles(sortDirection, sortBy);
        } catch (SQLException e) {
            throw new CommonSystemException(
                    "Произошла системная ошибка. Текст ошибки: '" + e.getMessage() + "'",
                    e.getCause(),
                    false
            );
        }
    }
}
