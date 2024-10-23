package org.skyhigh.afishadevappgui.data.repository;

import lombok.NonNull;
import org.skyhigh.afishadevappgui.common.db.validation.CommonSystemException;
import org.skyhigh.afishadevappgui.common.properties.ApplicationProperties;
import org.skyhigh.afishadevappgui.common.sort.SortDirection;
import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.data.datasource.AfishaDevAppDb;
import org.skyhigh.afishadevappgui.data.datasource.dao.DeploymentStatusDAO;
import org.skyhigh.afishadevappgui.data.datasource.entity.DeploymentStatus;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class DeploymentStatusRepositoryImpl implements DeploymentStatusRepository {
    private final DeploymentStatusDAO deploymentStatusDAO;

    public DeploymentStatusRepositoryImpl(ApplicationProperties applicationProperties) throws CommonFlkException {
        try {
            deploymentStatusDAO = (new AfishaDevAppDb(applicationProperties)).getDeploymentStatusDAO();
        } catch (SQLException e) {
            throw new CommonSystemException(
                    "Произошла системная ошибка. Текст ошибки: '" + e.getMessage() + "'",
                    e.getCause(),
                    false
            );
        }
    }

    @Override
    public UUID saveDeploymentStatus(@NonNull DeploymentStatus deploymentStatus) throws CommonFlkException {
        try {
            return deploymentStatusDAO.saveDeploymentStatus(deploymentStatus);
        } catch (SQLException e) {
            throw new CommonSystemException(
                    "Произошла системная ошибка. Текст ошибки: '" + e.getMessage() + "'",
                    e.getCause(),
                    false
            );
        }
    }

    @Override
    public DeploymentStatus getDeploymentStatusById(@NonNull UUID deploymentStatusId) throws CommonFlkException {
        try {
            return deploymentStatusDAO.getDeploymentStatusById(deploymentStatusId);
        } catch (SQLException e) {
            throw new CommonSystemException(
                    "Произошла системная ошибка. Текст ошибки: '" + e.getMessage() + "'",
                    e.getCause(),
                    false
            );
        }
    }

    @Override
    public DeploymentStatus getDeploymentStatusByName(@NonNull String statusName) throws CommonFlkException {
        try {
            return deploymentStatusDAO.getDeploymentStatusByName(statusName);
        } catch (SQLException e) {
            throw new CommonSystemException(
                    "Произошла системная ошибка. Текст ошибки: '" + e.getMessage() + "'",
                    e.getCause(),
                    false
            );
        }
    }

    @Override
    public List<DeploymentStatus> getAllDeploymentStatuses(@NonNull SortDirection sortDirection, String sortBy) throws CommonFlkException {
        try {
            return deploymentStatusDAO.getAllDeploymentStatuses(sortDirection, sortBy);
        } catch (SQLException e) {
            throw new CommonSystemException(
                    "Произошла системная ошибка. Текст ошибки: '" + e.getMessage() + "'",
                    e.getCause(),
                    false
            );
        }
    }

    @Override
    public void updateDeploymentStatusNameById(@NonNull UUID deploymentStatusId, @NonNull String newStatusName) throws CommonFlkException {
        try {
            deploymentStatusDAO.updateDeploymentStatusNameById(deploymentStatusId, newStatusName);
        } catch (SQLException e) {
            throw new CommonSystemException(
                    "Произошла системная ошибка. Текст ошибки: '" + e.getMessage() + "'",
                    e.getCause(),
                    false
            );
        }
    }

    @Override
    public void deleteDeploymentStatusById(@NonNull UUID deploymentStatusId) throws CommonFlkException {
        try {
            deploymentStatusDAO.deleteDeploymentStatusById(deploymentStatusId);
        } catch (SQLException e) {
            throw new CommonSystemException(
                    "Произошла системная ошибка. Текст ошибки: '" + e.getMessage() + "'",
                    e.getCause(),
                    false
            );
        }
    }
}
