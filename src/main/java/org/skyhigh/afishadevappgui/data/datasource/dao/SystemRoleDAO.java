package org.skyhigh.afishadevappgui.data.datasource.dao;

import lombok.NonNull;
import org.skyhigh.afishadevappgui.data.datasource.entity.SystemRole;

import java.sql.SQLException;
import java.util.List;

public interface SystemRoleDAO {
    boolean initializeTable() throws SQLException;

    boolean initializeBasicRoles() throws SQLException;

    SystemRole getSystemRole(@NonNull Integer systemRoleId) throws SQLException;

    SystemRole getSystemRoleByName(@NonNull String roleName) throws SQLException;

    List<SystemRole> getAllSystemRoles() throws SQLException;
}
