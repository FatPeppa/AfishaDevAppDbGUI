package org.skyhigh.afishadevappgui.service.logic.search;

import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.data.datasource.entity.Deployment;

import java.util.List;
import java.util.UUID;

@FunctionalInterface
public interface DeploymentSearchService {
    /**
     * Метод поиска развертываний. Допустимые комбинации: deploymentId / deploymentStatusId + projectId / projectId / deploymentStatusId / builtVersion / deploymentPath / all null
     * @param deploymentId ID развертывания
     * @param projectId ID проекта
     * @param deploymentStatusId ID статуса развертывания
     * @param builtVersion Номер версии сборки
     * @param deploymentPath Путь развертывания
     * @return Список искомых значений
     * @throws CommonFlkException Ошибка при проверках ФЛК
     */
    List<Deployment> searchDeployment(
            UUID deploymentId,
            UUID projectId,
            UUID deploymentStatusId,
            String builtVersion,
            String deploymentPath
    ) throws CommonFlkException;
}
