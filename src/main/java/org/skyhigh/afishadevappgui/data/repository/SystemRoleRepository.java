package org.skyhigh.afishadevappgui.data.repository;

import lombok.NonNull;
import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.data.datasource.entity.SystemRole;

import java.util.List;

public interface SystemRoleRepository {
    boolean initializeTable() throws CommonFlkException;

    boolean initializeBasicRoles() throws CommonFlkException;

    SystemRole getSystemRoleById(@NonNull Integer id) throws CommonFlkException;

    SystemRole getSystemRoleByName(@NonNull String roleName) throws CommonFlkException;

    List<SystemRole> getAllSystemRoles() throws CommonFlkException;
}
