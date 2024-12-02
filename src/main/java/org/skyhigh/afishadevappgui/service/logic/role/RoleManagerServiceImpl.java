package org.skyhigh.afishadevappgui.service.logic.role;

import org.skyhigh.afishadevappgui.common.controller.RoleManagedTableController;
import org.skyhigh.afishadevappgui.common.properties.ApplicationPropertiesReader;
import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.data.datasource.entity.DbUser;
import org.skyhigh.afishadevappgui.data.repository.SystemRoleRepository;
import org.skyhigh.afishadevappgui.data.repository.SystemRoleRepositoryImpl;

public class RoleManagerServiceImpl implements RoleManagerService {
    private SystemRoles currentUsersSystemRole = null;

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
    public boolean checkIfUserCanViewTableByItsEntityClass(RoleManagedTableController roleManagedTableController, DbUser dbUser) throws CommonFlkException {
        if (currentUsersSystemRole == null)
            setCurrentUserRole(dbUser);
        if (currentUsersSystemRole.equals(SystemRoles.ADMIN))
            return true;
        if (currentUsersSystemRole.equals(SystemRoles.ANALYST)) {
            return roleManagedTableController.getAccessibilityForViewingByAnalyst();
        }
        if (currentUsersSystemRole.equals(SystemRoles.DEVELOPER)) {
            return roleManagedTableController.getAccessibilityForViewingByDeveloper();
        }
        if (currentUsersSystemRole.equals(SystemRoles.QA)) {
            return roleManagedTableController.getAccessibilityForViewingByQA();
        }
        if (currentUsersSystemRole.equals(SystemRoles.DEVOPS)) {
            return roleManagedTableController.getAccessibilityForViewingByDevOps();
        }
        return false;
    }

    @Override
    public boolean checkIfUserCanEditTableDataByItsEntityClass(RoleManagedTableController roleManagedTableController, DbUser dbUser) throws CommonFlkException {
        if (currentUsersSystemRole == null)
            setCurrentUserRole(dbUser);
        if (currentUsersSystemRole.equals(SystemRoles.ADMIN))
            return true;
        if (currentUsersSystemRole.equals(SystemRoles.ANALYST)) {
            return roleManagedTableController.getAccessibilityForEditingByAnalyst();
        }
        if (currentUsersSystemRole.equals(SystemRoles.DEVELOPER)) {
            return roleManagedTableController.getAccessibilityForEditingByDeveloper();
        }
        if (currentUsersSystemRole.equals(SystemRoles.QA)) {
            return roleManagedTableController.getAccessibilityForEditingByQA();
        }
        if (currentUsersSystemRole.equals(SystemRoles.DEVOPS)) {
            return roleManagedTableController.getAccessibilityForEditingByDevOps();
        }
        return false;
    }

    @Override
    public boolean checkIfCurrentUserAdmin() throws CommonFlkException {
        if (currentUsersSystemRole == null)
            throw new CommonFlkException(
                    "Ошибка определения роли текущего пользователя: пользователь не найден"
            );
        return currentUsersSystemRole.equals(SystemRoles.ADMIN);
    }

    @Override
    public void setCurrentUserRole(DbUser dbUser) throws CommonFlkException {
        currentUsersSystemRole = SystemRoles.valueOf(systemRoleRepository.getSystemRoleById(dbUser.getSystemRoleId()).getRoleName());
    }
}
