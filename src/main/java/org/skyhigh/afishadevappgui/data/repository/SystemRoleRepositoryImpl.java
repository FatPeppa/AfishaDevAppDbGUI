package org.skyhigh.afishadevappgui.data.repository;

import lombok.NonNull;
import org.skyhigh.afishadevappgui.common.properties.ApplicationProperties;
import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.common.validation.CommonSystemException;
import org.skyhigh.afishadevappgui.data.datasource.AfishaDevAppDb;
import org.skyhigh.afishadevappgui.data.datasource.dao.SecretDAO;
import org.skyhigh.afishadevappgui.data.datasource.dao.SystemRoleDAO;
import org.skyhigh.afishadevappgui.data.datasource.entity.SystemRole;

import java.sql.SQLException;
import java.util.List;

public class SystemRoleRepositoryImpl implements SystemRoleRepository {
    private final SystemRoleDAO systemRoleDAO;

    public SystemRoleRepositoryImpl(ApplicationProperties applicationProperties) throws CommonFlkException {
        try {
            systemRoleDAO = (new AfishaDevAppDb(applicationProperties)).getSystemRoleDAO();
        } catch (SQLException e) {
            throw new CommonSystemException(
                    "Произошла системная ошибка. Текст ошибки: '" + e.getMessage() + "'",
                    e.getCause(),
                    false
            );
        }
    }

    @Override
    public boolean initializeTable() throws CommonFlkException {
        try {
            return systemRoleDAO.initializeTable();
        } catch (SQLException e) {
            throw new CommonSystemException(
                    "Произошла системная ошибка. Текст ошибки: '" + e.getMessage() + "'",
                    e.getCause(),
                    false
            );
        }
    }

    @Override
    public boolean initializeBasicRoles() throws CommonFlkException {
        try {
            return systemRoleDAO.initializeBasicRoles();
        } catch (SQLException e) {
            throw new CommonSystemException(
                    "Произошла системная ошибка. Текст ошибки: '" + e.getMessage() + "'",
                    e.getCause(),
                    false
            );
        }
    }

    @Override
    public SystemRole getSystemRoleById(@NonNull Integer id) throws CommonFlkException {
        try {
            return systemRoleDAO.getSystemRole(id);
        } catch (SQLException e) {
            throw new CommonSystemException(
                    "Произошла системная ошибка. Текст ошибки: '" + e.getMessage() + "'",
                    e.getCause(),
                    false
            );
        }
    }

    @Override
    public SystemRole getSystemRoleByName(@NonNull String roleName) throws CommonFlkException {
        try {
            return systemRoleDAO.getSystemRoleByName(roleName);
        } catch (SQLException e) {
            throw new CommonSystemException(
                    "Произошла системная ошибка. Текст ошибки: '" + e.getMessage() + "'",
                    e.getCause(),
                    false
            );
        }
    }

    @Override
    public List<SystemRole> getAllSystemRoles() throws CommonFlkException {
        try {
            return systemRoleDAO.getAllSystemRoles();
        } catch (SQLException e) {
            throw new CommonSystemException(
                    "Произошла системная ошибка. Текст ошибки: '" + e.getMessage() + "'",
                    e.getCause(),
                    false
            );
        }
    }
}
