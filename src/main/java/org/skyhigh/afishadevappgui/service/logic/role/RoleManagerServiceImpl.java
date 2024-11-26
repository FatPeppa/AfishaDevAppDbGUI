package org.skyhigh.afishadevappgui.service.logic.role;

import org.skyhigh.afishadevappgui.common.properties.ApplicationPropertiesReader;
import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.data.datasource.entity.DbUser;
import org.skyhigh.afishadevappgui.data.repository.SystemRoleRepository;
import org.skyhigh.afishadevappgui.data.repository.SystemRoleRepositoryImpl;

public class RoleManagerServiceImpl implements RoleManagerService {
    private enum SystemRoles {
        ADMIN,
        ANALYST,
        DEVELOPER,
        QA,
        DEVOPS
    }

    private final SystemRoleRepository systemRoleRepository;

    public RoleManagerServiceImpl() throws CommonFlkException {
        systemRoleRepository = new SystemRoleRepositoryImpl(
                ApplicationPropertiesReader.getApplicationProperties()
        );
    }

    @Override
    public boolean checkIfUserCanViewTableByItsEntityClass(Class<?> entityClass, DbUser dbUser) throws CommonFlkException {
        if (SystemRoles.valueOf(systemRoleRepository.getSystemRoleById(dbUser.getSystemRoleId()).getRoleName()).equals(SystemRoles.ADMIN))
            return true;


        return true;
    }

    @Override
    public boolean checkIfUserCanEditTableDataByItsEntityClass(Class<?> entityClass, DbUser dbUser) throws CommonFlkException {
        return true;
    }
}
