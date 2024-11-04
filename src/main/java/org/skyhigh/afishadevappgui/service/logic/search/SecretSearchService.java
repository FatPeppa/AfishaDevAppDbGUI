package org.skyhigh.afishadevappgui.service.logic.search;

import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.data.datasource.entity.Secret;

import java.util.List;
import java.util.UUID;

@FunctionalInterface
public interface SecretSearchService {
    /**
     * Метод поиска доступов к развертываниям. Допустимые комбинации: secretId / deploymentId / address / login / all null
     * @param secretId ID доступа к развертыванию
     * @param deploymentId ID развертывания
     * @param address Адрес доступа
     * @param login Логин доступа
     * @return Список искомых записей
     * @throws CommonFlkException Ошибка при выполнении проверок ФЛК
     */
    List<Secret> searchSecret(
            UUID secretId,
            UUID deploymentId,
            String address,
            String login
    ) throws CommonFlkException;
}
