package org.skyhigh.afishadevappgui.common.controller;

public interface RoleManagedTableController {
    boolean getAccessibilityForViewingByAnalyst();
    boolean getAccessibilityForViewingByDeveloper();
    boolean getAccessibilityForViewingByQA();
    boolean getAccessibilityForViewingByDevOps();
    boolean getAccessibilityForEditingByAnalyst();
    boolean getAccessibilityForEditingByDeveloper();
    boolean getAccessibilityForEditingByQA();
    boolean getAccessibilityForEditingByDevOps();
}
