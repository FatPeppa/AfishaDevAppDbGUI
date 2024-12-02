package org.skyhigh.afishadevappgui.common.controller;

import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.service.logic.role.RoleManagerService;

public interface RoleManagedTableController {
    boolean getAccessibilityForViewingByAnalyst();
    boolean getAccessibilityForViewingByDeveloper();
    boolean getAccessibilityForViewingByQA();
    boolean getAccessibilityForViewingByDevOps();
    boolean getAccessibilityForEditingByAnalyst();
    boolean getAccessibilityForEditingByDeveloper();
    boolean getAccessibilityForEditingByQA();
    boolean getAccessibilityForEditingByDevOps();
    void setRoleManagerService(RoleManagerService roleManagerService) throws CommonFlkException;
}
