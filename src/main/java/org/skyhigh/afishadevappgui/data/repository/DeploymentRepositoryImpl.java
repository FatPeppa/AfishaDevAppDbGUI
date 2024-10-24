package org.skyhigh.afishadevappgui.data.repository;

import lombok.NonNull;
import org.json.JSONObject;
import org.skyhigh.afishadevappgui.common.validation.CommonSystemException;
import org.skyhigh.afishadevappgui.common.properties.ApplicationProperties;
import org.skyhigh.afishadevappgui.common.sort.SortDirection;
import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.data.datasource.AfishaDevAppDb;
import org.skyhigh.afishadevappgui.data.datasource.dao.DeploymentDAO;
import org.skyhigh.afishadevappgui.data.datasource.entity.Deployment;

import java.io.File;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class DeploymentRepositoryImpl implements DeploymentRepository {
    private final DeploymentDAO deploymentDAO;

    public DeploymentRepositoryImpl(ApplicationProperties applicationProperties) throws CommonFlkException {
        try {
            deploymentDAO = (new AfishaDevAppDb(applicationProperties)).getDeploymentDAO();
        } catch (SQLException e) {
            throw new CommonSystemException(
                    "Произошла системная ошибка. Текст ошибки: '" + e.getMessage() + "'",
                    e.getCause(),
                    false
            );
        }
    }

    @Override
    public UUID saveDeployment(@NonNull Deployment deployment) throws CommonFlkException {
        try {
            return deploymentDAO.saveDeployment(deployment);
        } catch (SQLException e) {
            throw new CommonSystemException(
                    "Произошла системная ошибка. Текст ошибки: '" + e.getMessage() + "'",
                    e.getCause(),
                    false
            );
        }
    }

    @Override
    public void deleteDeploymentById(@NonNull UUID deploymentId) throws CommonFlkException {
        try {
            deploymentDAO.deleteDeploymentById(deploymentId);
        } catch (SQLException e) {
            throw new CommonSystemException(
                    "Произошла системная ошибка. Текст ошибки: '" + e.getMessage() + "'",
                    e.getCause(),
                    false
            );
        }
    }

    @Override
    public void updateDeploymentStatusById(@NonNull UUID deploymentId, @NonNull UUID deploymentStatusId) throws CommonFlkException {
        try {
            deploymentDAO.updateDeploymentStatusById(deploymentId, deploymentStatusId);
        } catch (SQLException e) {
            throw new CommonSystemException(
                    "Произошла системная ошибка. Текст ошибки: '" + e.getMessage() + "'",
                    e.getCause(),
                    false
            );
        }
    }

    @Override
    public void updateDeploymentBuiltVersionById(@NonNull UUID deploymentId, String builtVersion) throws CommonFlkException {
        try {
            deploymentDAO.updateDeploymentBuiltVersionById(deploymentId, builtVersion);
        } catch (SQLException e) {
            throw new CommonSystemException(
                    "Произошла системная ошибка. Текст ошибки: '" + e.getMessage() + "'",
                    e.getCause(),
                    false
            );
        }
    }

    @Override
    public void updateDeploymentBuiltSettingsById(@NonNull UUID deploymentId, JSONObject builtSettings) throws CommonFlkException {
        try {
            deploymentDAO.updateDeploymentBuiltSettingsById(deploymentId, builtSettings);
        } catch (SQLException e) {
            throw new CommonSystemException(
                    "Произошла системная ошибка. Текст ошибки: '" + e.getMessage() + "'",
                    e.getCause(),
                    false
            );
        }
    }

    @Override
    public void updateDeploymentBuiltById(@NonNull UUID deploymentId, File built) throws CommonFlkException {
        try {
            deploymentDAO.updateDeploymentBuiltById(deploymentId, built);
        } catch (SQLException e) {
            throw new CommonSystemException(
                    "Произошла системная ошибка. Текст ошибки: '" + e.getMessage() + "'",
                    e.getCause(),
                    false
            );
        }
    }

    @Override
    public void updateDeploymentProjectIdById(@NonNull UUID deploymentId, @NonNull UUID projectId) throws CommonFlkException {
        try {
            deploymentDAO.updateDeploymentProjectIdById(deploymentId, projectId);
        } catch (SQLException e) {
            throw new CommonSystemException(
                    "Произошла системная ошибка. Текст ошибки: '" + e.getMessage() + "'",
                    e.getCause(),
                    false
            );
        }
    }

    @Override
    public void updateDeploymentParamsById(@NonNull UUID deploymentId, @NonNull UUID deploymentStatusId, String deploymentPath, JSONObject settings, String builtVersion, JSONObject builtSettings, File built, @NonNull UUID projectId) throws CommonFlkException {
        try {
            deploymentDAO.updateDeploymentParamsById(
                    deploymentId,
                    deploymentStatusId,
                    deploymentPath,
                    settings,
                    builtVersion,
                    builtSettings,
                    built,
                    projectId
            );
        } catch (SQLException e) {
            throw new CommonSystemException(
                    "Произошла системная ошибка. Текст ошибки: '" + e.getMessage() + "'",
                    e.getCause(),
                    false
            );
        }
    }

    @Override
    public Deployment getDeploymentById(@NonNull UUID deploymentId) throws CommonFlkException {
        try {
            return deploymentDAO.getDeploymentById(deploymentId);
        } catch (SQLException e) {
            throw new CommonSystemException(
                    "Произошла системная ошибка. Текст ошибки: '" + e.getMessage() + "'",
                    e.getCause(),
                    false
            );
        }
    }

    @Override
    public List<Deployment> getDeploymentsByProjectId(@NonNull UUID projectId, @NonNull SortDirection sortDirection, String sortBy) throws CommonFlkException {
        try {
            return deploymentDAO.getDeploymentsByProjectId(projectId, sortDirection, sortBy);
        } catch (SQLException e) {
            throw new CommonSystemException(
                    "Произошла системная ошибка. Текст ошибки: '" + e.getMessage() + "'",
                    e.getCause(),
                    false
            );
        }
    }

    @Override
    public List<Deployment> getDeploymentsByStatusId(@NonNull UUID deploymentStatusId, @NonNull SortDirection sortDirection, String sortBy) throws CommonFlkException {
        try {
            return deploymentDAO.getDeploymentsByStatusId(deploymentStatusId, sortDirection, sortBy);
        } catch (SQLException e) {
            throw new CommonSystemException(
                    "Произошла системная ошибка. Текст ошибки: '" + e.getMessage() + "'",
                    e.getCause(),
                    false
            );
        }
    }

    @Override
    public List<Deployment> getAllDeployments(@NonNull SortDirection sortDirection, String sortBy) throws CommonFlkException {
        try {
            return deploymentDAO.getAllDeployments(sortDirection, sortBy);
        } catch (SQLException e) {
            throw new CommonSystemException(
                    "Произошла системная ошибка. Текст ошибки: '" + e.getMessage() + "'",
                    e.getCause(),
                    false
            );
        }
    }

    @Override
    public List<Deployment> getDeploymentsByBuiltVersion(String builtVersion, @NonNull SortDirection sortDirection, String sortBy) throws CommonFlkException {
        try {
            return deploymentDAO.getDeploymentsByBuiltVersion(builtVersion, sortDirection, sortBy);
        } catch (SQLException e) {
            throw new CommonSystemException(
                    "Произошла системная ошибка. Текст ошибки: '" + e.getMessage() + "'",
                    e.getCause(),
                    false
            );
        }
    }

    @Override
    public List<Deployment> getDeploymentsByPath(String deploymentPath, @NonNull SortDirection sortDirection, String sortBy) throws CommonFlkException {
        try {
            return deploymentDAO.getDeploymentsByPath(deploymentPath, sortDirection, sortBy);
        } catch (SQLException e) {
            throw new CommonSystemException(
                    "Произошла системная ошибка. Текст ошибки: '" + e.getMessage() + "'",
                    e.getCause(),
                    false
            );
        }
    }

    @Override
    public List<Deployment> getDeploymentsByStatusIdAndProjectId(@NonNull UUID deploymentStatusId, @NonNull UUID projectId, @NonNull SortDirection sortDirection, String sortBy) throws CommonFlkException {
        try {
            return deploymentDAO.getDeploymentsByStatusIdAndProjectId(deploymentStatusId, projectId, sortDirection, sortBy);
        } catch (SQLException e) {
            throw new CommonSystemException(
                    "Произошла системная ошибка. Текст ошибки: '" + e.getMessage() + "'",
                    e.getCause(),
                    false
            );
        }
    }

    @Override
    public UUID deployBuilt(@NonNull UUID deploymentId, @NonNull String address, @NonNull String login, String password) throws CommonFlkException {
        try {
            return deploymentDAO.deployBuilt(deploymentId, address, login, password);
        } catch (SQLException e) {
            throw new CommonSystemException(
                    "Произошла системная ошибка. Текст ошибки: '" + e.getMessage() + "'",
                    e.getCause(),
                    false
            );
        }
    }
}
