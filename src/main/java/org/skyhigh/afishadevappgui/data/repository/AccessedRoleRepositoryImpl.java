package org.skyhigh.afishadevappgui.data.repository;

import lombok.NonNull;
import org.skyhigh.afishadevappgui.common.validation.CommonSystemException;
import org.skyhigh.afishadevappgui.common.properties.ApplicationProperties;
import org.skyhigh.afishadevappgui.common.sort.SortDirection;
import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.data.datasource.AfishaDevAppDb;
import org.skyhigh.afishadevappgui.data.datasource.dao.AccessedRoleDAO;
import org.skyhigh.afishadevappgui.data.datasource.entity.AccessedRole;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class AccessedRoleRepositoryImpl implements AccessedRoleRepository {
    private final AccessedRoleDAO accessedRoleDAO;

    public AccessedRoleRepositoryImpl(ApplicationProperties applicationProperties) throws CommonFlkException {
        try {
            accessedRoleDAO = (new AfishaDevAppDb(applicationProperties)).getAccessedRoleDAO();
        } catch (SQLException e) {
            throw new CommonSystemException(
                    "Произошла системная ошибка. Текст ошибки: '" + e.getMessage() + "'",
                    e.getCause(),
                    false
            );
        }
    }

    @Override
    public void saveAccessedRole(@NonNull AccessedRole accessedRole) throws CommonFlkException {
        try {
            accessedRoleDAO.saveAccessedRole(accessedRole);
        } catch (SQLException e) {
            throw new CommonSystemException(
                    "Произошла системная ошибка. Текст ошибки: '" + e.getMessage() + "'",
                    e.getCause(),
                    false
            );
        }
    }

    @Override
    public void updateAccessedRoleNameByIdAndName(@NonNull UUID requirementId, @NonNull String oldAccessedRoleName, @NonNull String newAccessedRoleName) throws CommonFlkException {
        try {
            accessedRoleDAO.updateAccessedRoleNameByIdAndName(requirementId, oldAccessedRoleName, newAccessedRoleName);
        } catch (SQLException e) {
            throw new CommonSystemException(
                    "Произошла системная ошибка. Текст ошибки: '" + e.getMessage() + "'",
                    e.getCause(),
                    false
            );
        }
    }

    @Override
    public List<AccessedRole> getAllAccessedRoles(@NonNull SortDirection sortDirection, String sortBy) throws CommonFlkException {
        try {
            return accessedRoleDAO.getAllAccessedRoles(sortDirection, sortBy);
        } catch (SQLException e) {
            throw new CommonSystemException(
                    "Произошла системная ошибка. Текст ошибки: '" + e.getMessage() + "'",
                    e.getCause(),
                    false
            );
        }
    }

    @Override
    public List<AccessedRole> getAccessedRolesByRequirementId(@NonNull UUID requirementId, @NonNull SortDirection sortDirection, String sortBy) throws CommonFlkException {
        try {
            return accessedRoleDAO.getAccessedRolesByRequirementId(requirementId, sortDirection, sortBy);
        } catch (SQLException e) {
            throw new CommonSystemException(
                    "Произошла системная ошибка. Текст ошибки: '" + e.getMessage() + "'",
                    e.getCause(),
                    false
            );
        }
    }

    @Override
    public List<AccessedRole> getAccessedRolesByRoleName(@NonNull String accessedRoleName, @NonNull SortDirection sortDirection, String sortBy) throws CommonFlkException {
        try {
            return accessedRoleDAO.getAccessedRolesByRoleName(accessedRoleName, sortDirection, sortBy);
        } catch (SQLException e) {
            throw new CommonSystemException(
                    "Произошла системная ошибка. Текст ошибки: '" + e.getMessage() + "'",
                    e.getCause(),
                    false
            );
        }
    }

    @Override
    public AccessedRole getAccessedRoleByRequirementIdAndName(@NonNull UUID requirementId, @NonNull String accessedRoleName) throws CommonFlkException {
        try {
            return accessedRoleDAO.getAccessedRoleByRequirementIdAndName(requirementId, accessedRoleName);
        } catch (SQLException e) {
            throw new CommonSystemException(
                    "Произошла системная ошибка. Текст ошибки: '" + e.getMessage() + "'",
                    e.getCause(),
                    false
            );
        }
    }

    @Override
    public void deleteAccessedRoleByRequirementIdAndName(@NonNull UUID requirementId, @NonNull String accessedRoleName) throws CommonFlkException {
        try {
            accessedRoleDAO.deleteAccessedRoleByRequirementIdAndName(requirementId, accessedRoleName);
        } catch (SQLException e) {
            throw new CommonSystemException(
                    "Произошла системная ошибка. Текст ошибки: '" + e.getMessage() + "'",
                    e.getCause(),
                    false
            );
        }
    }

    @Override
    public void deleteAllAccessedRolesByRequirementId(@NonNull UUID requirementId) throws CommonFlkException {
        try {
            accessedRoleDAO.deleteAllAccessedRolesByRequirementId(requirementId);
        } catch (SQLException e) {
            throw new CommonSystemException(
                    "Произошла системная ошибка. Текст ошибки: '" + e.getMessage() + "'",
                    e.getCause(),
                    false
            );
        }
    }
}
