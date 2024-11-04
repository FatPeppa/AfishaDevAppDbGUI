package org.skyhigh.afishadevappgui.service.logic.search;

import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.data.datasource.entity.DeploymentStatus;

import java.util.List;
import java.util.UUID;

@FunctionalInterface
public interface DeploymentStatusSearchService {
    /**
     * Метод поиска статусов развертываний. Допустимые комбинации: deploymentStatusId / deploymentStatusName / all null
     * @param deploymentStatusId ID статуса развертывания
     * @param deploymentStatusName Наименование статуса развертывания
     * @return Список искомых значений
     * @throws CommonFlkException Ошибка при проверках ФЛК
     */
    List<DeploymentStatus> searchDeploymentStatus(UUID deploymentStatusId, String deploymentStatusName) throws CommonFlkException;
}
