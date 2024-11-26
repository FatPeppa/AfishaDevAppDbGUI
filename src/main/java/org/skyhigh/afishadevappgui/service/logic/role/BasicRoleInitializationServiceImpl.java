package org.skyhigh.afishadevappgui.service.logic.role;

import org.skyhigh.afishadevappgui.common.controller.ControllerUtils;
import org.skyhigh.afishadevappgui.common.properties.ApplicationPropertiesReader;
import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.common.validation.CommonUIException;
import org.skyhigh.afishadevappgui.data.repository.SystemRoleRepository;
import org.skyhigh.afishadevappgui.data.repository.SystemRoleRepositoryImpl;

public class BasicRoleInitializationServiceImpl implements BasicRoleInitializationService{
    SystemRoleRepository systemRoleRepository;

    private void initializeSystemRoleRepository() throws CommonFlkException {
        if (systemRoleRepository == null)
            systemRoleRepository = new SystemRoleRepositoryImpl(
                    ApplicationPropertiesReader.getApplicationProperties()
            );
    }

    @Override
    public void initialize() throws CommonFlkException {
        try {
            initializeSystemRoleRepository();
        } catch (CommonFlkException e) {
            throw new CommonUIException(
                    e.getMessage()
            );
        }
        boolean tableSchemaWasInitialized = systemRoleRepository.initializeTable();
        boolean tableRowsWereInitialized = systemRoleRepository.initializeBasicRoles();
        if (tableSchemaWasInitialized && tableRowsWereInitialized)
            ControllerUtils.showInformationDialog("Уведомление", "Были инициализированы структура и наполнение таблицы системных ролей");
        if (tableSchemaWasInitialized)
            ControllerUtils.showInformationDialog("Уведомление", "Была инициализирована структура таблицы системных ролей");
        if (tableRowsWereInitialized)
            ControllerUtils.showInformationDialog("Уведомление", "Было инициализировано наполнение таблицы системных ролей");
    }
}
