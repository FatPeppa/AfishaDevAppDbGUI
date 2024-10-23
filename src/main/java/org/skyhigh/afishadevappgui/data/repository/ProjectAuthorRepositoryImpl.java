package org.skyhigh.afishadevappgui.data.repository;

import lombok.NonNull;
import org.skyhigh.afishadevappgui.common.db.validation.CommonSystemException;
import org.skyhigh.afishadevappgui.common.properties.ApplicationProperties;
import org.skyhigh.afishadevappgui.common.sort.SortDirection;
import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.data.datasource.AfishaDevAppDb;
import org.skyhigh.afishadevappgui.data.datasource.dao.ProjectAuthorDAO;
import org.skyhigh.afishadevappgui.data.datasource.entity.ProjectAuthor;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class ProjectAuthorRepositoryImpl implements ProjectAuthorRepository {
    private final ProjectAuthorDAO projectAuthorDAO;

    public ProjectAuthorRepositoryImpl(ApplicationProperties applicationProperties) throws CommonFlkException {
        try {
            projectAuthorDAO = (new AfishaDevAppDb(applicationProperties)).getProjectAuthorDAO();
        } catch (SQLException e) {
            throw new CommonSystemException(
                    "Произошла системная ошибка. Текст ошибки: '" + e.getMessage() + "'",
                    e.getCause(),
                    false
            );
        }
    }

    @Override
    public void saveProjectAuthor(@NonNull ProjectAuthor projectAuthor) throws CommonFlkException {
        try {
            projectAuthorDAO.saveProjectAuthor(projectAuthor);
        } catch (SQLException e) {
            throw new CommonSystemException(
                    "Произошла системная ошибка. Текст ошибки: '" + e.getMessage() + "'",
                    e.getCause(),
                    false
            );
        }
    }

    @Override
    public void deleteProjectAuthorByIds(@NonNull UUID projectId, @NonNull UUID authorId) throws CommonFlkException {
        try {
            projectAuthorDAO.deleteProjectAuthorByIds(projectId, authorId);
        } catch (SQLException e) {
            throw new CommonSystemException(
                    "Произошла системная ошибка. Текст ошибки: '" + e.getMessage() + "'",
                    e.getCause(),
                    false
            );
        }
    }

    @Override
    public void deleteProjectAuthorsByProjectId(@NonNull UUID projectId) throws CommonFlkException {
        try {
            projectAuthorDAO.deleteProjectAuthorsByProjectId(projectId);
        } catch (SQLException e) {
            throw new CommonSystemException(
                    "Произошла системная ошибка. Текст ошибки: '" + e.getMessage() + "'",
                    e.getCause(),
                    false
            );
        }
    }

    @Override
    public void deleteProjectAuthorsByAuthorId(@NonNull UUID authorId) throws CommonFlkException {
        try {
            projectAuthorDAO.deleteProjectAuthorsByAuthorId(authorId);
        } catch (SQLException e) {
            throw new CommonSystemException(
                    "Произошла системная ошибка. Текст ошибки: '" + e.getMessage() + "'",
                    e.getCause(),
                    false
            );
        }
    }

    @Override
    public void updateProjectAuthorAuthorId(@NonNull UUID oldAuthorId, @NonNull UUID projectId, @NonNull UUID newAuthorId) throws CommonFlkException {
        try {
            projectAuthorDAO.updateProjectAuthorAuthorId(oldAuthorId, projectId, newAuthorId);
        } catch (SQLException e) {
            throw new CommonSystemException(
                    "Произошла системная ошибка. Текст ошибки: '" + e.getMessage() + "'",
                    e.getCause(),
                    false
            );
        }
    }

    @Override
    public void updateProjectAuthorProjectId(@NonNull UUID authorId, @NonNull UUID oldProjectId, @NonNull UUID newProjectId) throws CommonFlkException {
        try {
            projectAuthorDAO.updateProjectAuthorProjectId(authorId, oldProjectId, newProjectId);
        } catch (SQLException e) {
            throw new CommonSystemException(
                    "Произошла системная ошибка. Текст ошибки: '" + e.getMessage() + "'",
                    e.getCause(),
                    false
            );
        }
    }

    @Override
    public ProjectAuthor getProjectAuthorByIds(@NonNull UUID projectId, @NonNull UUID authorId) throws CommonFlkException {
        try {
            return projectAuthorDAO.getProjectAuthorByIds(projectId, authorId);
        } catch (SQLException e) {
            throw new CommonSystemException(
                    "Произошла системная ошибка. Текст ошибки: '" + e.getMessage() + "'",
                    e.getCause(),
                    false
            );
        }
    }

    @Override
    public List<ProjectAuthor> getAllProjectAuthors(@NonNull SortDirection sortDirection, String sortBy) throws CommonFlkException {
        try {
            return projectAuthorDAO.getAllProjectAuthors(sortDirection, sortBy);
        } catch (SQLException e) {
            throw new CommonSystemException(
                    "Произошла системная ошибка. Текст ошибки: '" + e.getMessage() + "'",
                    e.getCause(),
                    false
            );
        }
    }

    @Override
    public List<ProjectAuthor> getProjectAuthorsByProjectId(@NonNull UUID projectId, @NonNull SortDirection sortDirection, String sortBy) throws CommonFlkException {
        try {
            return projectAuthorDAO.getProjectAuthorsByProjectId(projectId, sortDirection, sortBy);
        } catch (SQLException e) {
            throw new CommonSystemException(
                    "Произошла системная ошибка. Текст ошибки: '" + e.getMessage() + "'",
                    e.getCause(),
                    false
            );
        }
    }

    @Override
    public List<ProjectAuthor> getProjectAuthorsByAuthorId(@NonNull UUID authorId, @NonNull SortDirection sortDirection, String sortBy) throws CommonFlkException {
        try {
            return projectAuthorDAO.getProjectAuthorsByAuthorId(authorId, sortDirection, sortBy);
        } catch (SQLException e) {
            throw new CommonSystemException(
                    "Произошла системная ошибка. Текст ошибки: '" + e.getMessage() + "'",
                    e.getCause(),
                    false
            );
        }
    }
}
