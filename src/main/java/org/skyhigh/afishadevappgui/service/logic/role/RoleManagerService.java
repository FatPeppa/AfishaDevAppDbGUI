package org.skyhigh.afishadevappgui.service.logic.role;

import org.skyhigh.afishadevappgui.common.controller.RoleManagedTableController;
import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.data.datasource.entity.DbUser;

public interface RoleManagerService {
    boolean checkIfUserCanViewTableByItsEntityClass(RoleManagedTableController roleManagedTableController, DbUser dbUser) throws CommonFlkException;
    boolean checkIfUserCanEditTableDataByItsEntityClass(RoleManagedTableController roleManagedTableController, DbUser dbUser) throws CommonFlkException;
    boolean checkIfCurrentUserAdmin() throws CommonFlkException;
    void setCurrentUserRole(DbUser dbUser) throws CommonFlkException;
}
