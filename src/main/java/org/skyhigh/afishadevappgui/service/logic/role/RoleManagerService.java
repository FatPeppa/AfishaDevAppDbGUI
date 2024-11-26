package org.skyhigh.afishadevappgui.service.logic.role;

import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.data.datasource.entity.DbUser;

public interface RoleManagerService {
    boolean checkIfUserCanViewTableByItsEntityClass(Class<?> entityClass, DbUser dbUser) throws CommonFlkException;
    boolean checkIfUserCanEditTableDataByItsEntityClass(Class<?> entityClass, DbUser dbUser) throws CommonFlkException;
}
