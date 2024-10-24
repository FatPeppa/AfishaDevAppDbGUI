package org.skyhigh.afishadevappgui.data.repository;

import lombok.NonNull;
import org.skyhigh.afishadevappgui.common.validation.CommonSystemException;
import org.skyhigh.afishadevappgui.common.properties.ApplicationProperties;
import org.skyhigh.afishadevappgui.common.sort.SortDirection;
import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.data.datasource.AfishaDevAppDb;
import org.skyhigh.afishadevappgui.data.datasource.dao.SecretDAO;
import org.skyhigh.afishadevappgui.data.datasource.entity.Secret;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class SecretRepositoryImpl implements SecretRepository {
    private final SecretDAO secretDAO;

    public SecretRepositoryImpl(ApplicationProperties applicationProperties) throws CommonFlkException {
        try {
            secretDAO = (new AfishaDevAppDb(applicationProperties)).getSecretDAO();
        } catch (SQLException e) {
            throw new CommonSystemException(
                    "Произошла системная ошибка. Текст ошибки: '" + e.getMessage() + "'",
                    e.getCause(),
                    false
            );
        }
    }

    @Override
    public UUID saveSecret(@NonNull Secret secret) throws CommonFlkException {
        try {
            return secretDAO.saveSecret(secret);
        } catch (SQLException e) {
            throw new CommonSystemException(
                    "Произошла системная ошибка. Текст ошибки: '" + e.getMessage() + "'",
                    e.getCause(),
                    false
            );
        }
    }

    @Override
    public void updateSecret(@NonNull Secret secret) throws CommonFlkException {
        try {
            secretDAO.saveSecret(secret);
        } catch (SQLException e) {
            throw new CommonSystemException(
                    "Произошла системная ошибка. Текст ошибки: '" + e.getMessage() + "'",
                    e.getCause(),
                    false
            );
        }
    }

    @Override
    public void updateSecretDeploymentIdById(@NonNull UUID secretId, @NonNull UUID deploymentId) throws CommonFlkException {
        try {
            secretDAO.updateSecretDeploymentIdById(secretId, deploymentId);
        } catch (SQLException e) {
            throw new CommonSystemException(
                    "Произошла системная ошибка. Текст ошибки: '" + e.getMessage() + "'",
                    e.getCause(),
                    false
            );
        }
    }

    @Override
    public void updateSecretAddressById(@NonNull UUID secretId, @NonNull String address) throws CommonFlkException {
        try {
            secretDAO.updateSecretAddressById(secretId, address);
        } catch (SQLException e) {
            throw new CommonSystemException(
                    "Произошла системная ошибка. Текст ошибки: '" + e.getMessage() + "'",
                    e.getCause(),
                    false
            );
        }
    }

    @Override
    public void updateSecretLogin(@NonNull UUID secretId, @NonNull String login) throws CommonFlkException {
        try {
            secretDAO.updateSecretLogin(secretId, login);
        } catch (SQLException e) {
            throw new CommonSystemException(
                    "Произошла системная ошибка. Текст ошибки: '" + e.getMessage() + "'",
                    e.getCause(),
                    false
            );
        }
    }

    @Override
    public void updateSecretPassword(@NonNull UUID secretId, String password) throws CommonFlkException {
        try {
            secretDAO.updateSecretPassword(secretId, password);
        } catch (SQLException e) {
            throw new CommonSystemException(
                    "Произошла системная ошибка. Текст ошибки: '" + e.getMessage() + "'",
                    e.getCause(),
                    false
            );
        }
    }

    @Override
    public void deleteSecretById(@NonNull UUID secretId) throws CommonFlkException {
        try {
            secretDAO.deleteSecretById(secretId);
        } catch (SQLException e) {
            throw new CommonSystemException(
                    "Произошла системная ошибка. Текст ошибки: '" + e.getMessage() + "'",
                    e.getCause(),
                    false
            );
        }
    }

    @Override
    public Secret getSecretById(UUID secretId) throws CommonFlkException {
        try {
            return secretDAO.getSecretById(secretId);
        } catch (SQLException e) {
            throw new CommonSystemException(
                    "Произошла системная ошибка. Текст ошибки: '" + e.getMessage() + "'",
                    e.getCause(),
                    false
            );
        }
    }

    @Override
    public List<Secret> getSecretsByDeploymentId(@NonNull UUID deploymentId, @NonNull SortDirection sortDirection, String sortBy) throws CommonFlkException {
        try {
            return secretDAO.getSecretsByDeploymentId(deploymentId, sortDirection, sortBy);
        } catch (SQLException e) {
            throw new CommonSystemException(
                    "Произошла системная ошибка. Текст ошибки: '" + e.getMessage() + "'",
                    e.getCause(),
                    false
            );
        }
    }

    @Override
    public List<Secret> getSecretsByAddress(@NonNull String address, @NonNull SortDirection sortDirection, String sortBy) throws CommonFlkException {
        try {
            return secretDAO.getSecretsByAddress(address, sortDirection, sortBy);
        } catch (SQLException e) {
            throw new CommonSystemException(
                    "Произошла системная ошибка. Текст ошибки: '" + e.getMessage() + "'",
                    e.getCause(),
                    false
            );
        }
    }

    @Override
    public List<Secret> getSecretsByLogin(@NonNull String login, @NonNull SortDirection sortDirection, String sortBy) throws CommonFlkException {
        try {
            return secretDAO.getSecretsByLogin(login, sortDirection, sortBy);
        } catch (SQLException e) {
            throw new CommonSystemException(
                    "Произошла системная ошибка. Текст ошибки: '" + e.getMessage() + "'",
                    e.getCause(),
                    false
            );
        }
    }

    @Override
    public List<Secret> getAllAuthors(@NonNull SortDirection sortDirection, String sortBy) throws CommonFlkException {
        try {
            return secretDAO.getAllAuthors(sortDirection, sortBy);
        } catch (SQLException e) {
            throw new CommonSystemException(
                    "Произошла системная ошибка. Текст ошибки: '" + e.getMessage() + "'",
                    e.getCause(),
                    false
            );
        }
    }
}
