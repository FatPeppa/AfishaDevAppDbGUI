package org.skyhigh.afishadevappgui.data.repository;

import lombok.NonNull;
import org.json.JSONObject;
import org.skyhigh.afishadevappgui.common.db.validation.CommonSystemException;
import org.skyhigh.afishadevappgui.common.properties.ApplicationProperties;
import org.skyhigh.afishadevappgui.common.sort.SortDirection;
import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.data.datasource.AfishaDevAppDb;
import org.skyhigh.afishadevappgui.data.datasource.dao.ProjectDAO;
import org.skyhigh.afishadevappgui.data.datasource.entity.Project;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class ProjectRepositoryImpl implements ProjectRepository {
    private final ProjectDAO projectDAO;

    public ProjectRepositoryImpl(ApplicationProperties applicationProperties) throws CommonFlkException {
        try {
            projectDAO = (new AfishaDevAppDb(applicationProperties)).getProjectDAO();
        } catch (SQLException e) {
            throw new CommonSystemException(
                    "Произошла системная ошибка. Текст ошибки: '" + e.getMessage() + "'",
                    e.getCause(),
                    false
            );
        }
    }

    @Override
    public UUID saveProject(@NonNull Project project) throws CommonFlkException {
        try {
            return projectDAO.saveProject(project);
        } catch (SQLException e) {
            throw new CommonSystemException(
                    "Произошла системная ошибка. Текст ошибки: '" + e.getMessage() + "'",
                    e.getCause(),
                    false
            );
        }
    }

    @Override
    public void updateProject(@NonNull Project project) throws CommonFlkException {
        try {
            projectDAO.updateProject(project);
        } catch (SQLException e) {
            throw new CommonSystemException(
                    "Произошла системная ошибка. Текст ошибки: '" + e.getMessage() + "'",
                    e.getCause(),
                    false
            );
        }
    }

    @Override
    public void updateProjectNameById(@NonNull UUID projectId, @NonNull String projectName) throws CommonFlkException {
        try {
            projectDAO.updateProjectNameById(projectId, projectName);
        } catch (SQLException e) {
            throw new CommonSystemException(
                    "Произошла системная ошибка. Текст ошибки: '" + e.getMessage() + "'",
                    e.getCause(),
                    false
            );
        }
    }

    @Override
    public void updateProjectLastChangeDateById(@NonNull UUID projectId, @NonNull LocalDateTime lastChangeDate) throws CommonFlkException {
        try {
            projectDAO.updateProjectLastChangeDateById(projectId, lastChangeDate);
        } catch (SQLException e) {
            throw new CommonSystemException(
                    "Произошла системная ошибка. Текст ошибки: '" + e.getMessage() + "'",
                    e.getCause(),
                    false
            );
        }
    }

    @Override
    public void updateProjectStructureById(@NonNull UUID projectId, @NonNull JSONObject structure) throws CommonFlkException {
        try {
            projectDAO.updateProjectStructureById(projectId, structure);
        } catch (SQLException e) {
            throw new CommonSystemException(
                    "Произошла системная ошибка. Текст ошибки: '" + e.getMessage() + "'",
                    e.getCause(),
                    false
            );
        }
    }

    @Override
    public void updateProjectContentById(@NonNull UUID projectId, @NonNull JSONObject content) throws CommonFlkException {
        try {
            projectDAO.updateProjectContentById(projectId, content);
        } catch (SQLException e) {
            throw new CommonSystemException(
                    "Произошла системная ошибка. Текст ошибки: '" + e.getMessage() + "'",
                    e.getCause(),
                    false
            );
        }
    }

    @Override
    public void updateProjectSettingsById(@NonNull UUID projectId, @NonNull JSONObject settings) throws CommonFlkException {
        try {
            projectDAO.updateProjectSettingsById(projectId, settings);
        } catch (SQLException e) {
            throw new CommonSystemException(
                    "Произошла системная ошибка. Текст ошибки: '" + e.getMessage() + "'",
                    e.getCause(),
                    false
            );
        }
    }

    @Override
    public void updateProjectVersionNumberById(@NonNull UUID projectId, @NonNull String versionNumber) throws CommonFlkException {
        try {
            projectDAO.updateProjectVersionNumberById(projectId, versionNumber);
        } catch (SQLException e) {
            throw new CommonSystemException(
                    "Произошла системная ошибка. Текст ошибки: '" + e.getMessage() + "'",
                    e.getCause(),
                    false
            );
        }
    }

    @Override
    public void deleteProjectById(@NonNull UUID projectId) throws CommonFlkException {
        try {
            projectDAO.deleteProjectById(projectId);
        } catch (SQLException e) {
            throw new CommonSystemException(
                    "Произошла системная ошибка. Текст ошибки: '" + e.getMessage() + "'",
                    e.getCause(),
                    false
            );
        }
    }

    @Override
    public Project getProjectById(@NonNull UUID projectId) throws CommonFlkException {
        try {
            return projectDAO.getProjectById(projectId);
        } catch (SQLException e) {
            throw new CommonSystemException(
                    "Произошла системная ошибка. Текст ошибки: '" + e.getMessage() + "'",
                    e.getCause(),
                    false
            );
        }
    }

    @Override
    public Project getProjectByName(@NonNull String projectName) throws CommonFlkException {
        try {
            return projectDAO.getProjectByName(projectName);
        } catch (SQLException e) {
            throw new CommonSystemException(
                    "Произошла системная ошибка. Текст ошибки: '" + e.getMessage() + "'",
                    e.getCause(),
                    false
            );
        }
    }

    @Override
    public List<Project> getAllProjects(@NonNull SortDirection sortDirection, String sortBy) throws CommonFlkException {
        try {
            return projectDAO.getAllProjects(sortDirection, sortBy);
        } catch (SQLException e) {
            throw new CommonSystemException(
                    "Произошла системная ошибка. Текст ошибки: '" + e.getMessage() + "'",
                    e.getCause(),
                    false
            );
        }
    }

    @Override
    public List<Project> getProjectsByLoadDate(@NonNull LocalDateTime loadDate, @NonNull SortDirection sortDirection, String sortBy) throws CommonFlkException {
        try {
            return projectDAO.getProjectsByLoadDate(loadDate, sortDirection, sortBy);
        } catch (SQLException e) {
            throw new CommonSystemException(
                    "Произошла системная ошибка. Текст ошибки: '" + e.getMessage() + "'",
                    e.getCause(),
                    false
            );
        }
    }

    @Override
    public List<Project> getProjectsByLoadDateDiapason(LocalDateTime loadDateDiapasonStart, LocalDateTime loadDateDiapasonEnd, @NonNull SortDirection sortDirection, String sortBy) throws CommonFlkException {
        try {
            return projectDAO.getProjectsByLoadDateDiapason(loadDateDiapasonStart, loadDateDiapasonEnd, sortDirection, sortBy);
        } catch (SQLException e) {
            throw new CommonSystemException(
                    "Произошла системная ошибка. Текст ошибки: '" + e.getMessage() + "'",
                    e.getCause(),
                    false
            );
        }
    }

    @Override
    public List<Project> getProjectsByLastChangeDate(@NonNull LocalDateTime lastChangeDate, @NonNull SortDirection sortDirection, String sortBy) throws CommonFlkException {
        try {
            return projectDAO.getProjectsByLastChangeDate(lastChangeDate, sortDirection, sortBy);
        } catch (SQLException e) {
            throw new CommonSystemException(
                    "Произошла системная ошибка. Текст ошибки: '" + e.getMessage() + "'",
                    e.getCause(),
                    false
            );
        }
    }

    @Override
    public List<Project> getProjectsByLastChangeDateDiapason(LocalDateTime lastChangeDateDiapasonStart, LocalDateTime lastChangeDateDiapasonEnd, @NonNull SortDirection sortDirection, String sortBy) throws CommonFlkException {
        try {
            return projectDAO.getProjectsByLastChangeDateDiapason(lastChangeDateDiapasonStart, lastChangeDateDiapasonEnd, sortDirection, sortBy);
        } catch (SQLException e) {
            throw new CommonSystemException(
                    "Произошла системная ошибка. Текст ошибки: '" + e.getMessage() + "'",
                    e.getCause(),
                    false
            );
        }
    }

    @Override
    public List<Project> getProjectsByVersionNumber(@NonNull String versionNumber, @NonNull SortDirection sortDirection, String sortBy) throws CommonFlkException {
        try {
            return projectDAO.getProjectsByVersionNumber(versionNumber, sortDirection, sortBy);
        } catch (SQLException e) {
            throw new CommonSystemException(
                    "Произошла системная ошибка. Текст ошибки: '" + e.getMessage() + "'",
                    e.getCause(),
                    false
            );
        }
    }
}
