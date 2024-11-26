package org.skyhigh.afishadevappgui.service.logic.role;

import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;

@FunctionalInterface
public interface BasicRoleInitializationService {
    void initialize() throws CommonFlkException;
}
