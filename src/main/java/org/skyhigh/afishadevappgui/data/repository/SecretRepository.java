package org.skyhigh.afishadevappgui.data.repository;

import lombok.NonNull;
import org.skyhigh.afishadevappgui.common.sort.SortDirection;
import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.data.datasource.entity.Secret;

import java.util.List;
import java.util.UUID;

public interface SecretRepository {
    /**
     * Метод сохранения сущности доступа к развертыванию в Системе
     * @param secret Сохраняемая сущность доступа к развертыванию. Если поле password заполнено, то при сохранении
     *               записи осуществляется только проверка надежности пароля. Если поле password не заполнено, то значение
     *               для данного поля генерируется автоматически в соответствии с правилами заполнения на дату сохранения
     * @return Идентификатор доступа к развертыванию, сформированный при сохранении сущности в Системе
     * @throws CommonFlkException Ошибка при проверке ФЛК
     */
    UUID saveSecret(@NonNull Secret secret) throws CommonFlkException;

    /**
     * Метод обновления доступа к развертыванию
     * @param secret Целевая сущность доступа к развертыванию. Поле secretId должно быть заполнено,
     *               иначе будет сформировано исключение NullFieldsInDBEntityException/
     *               Поиск обновляемой записи осуществляется именно по secretId
     * @throws CommonFlkException Ошибка при проверке ФЛК
     */
    void updateSecret(@NonNull Secret secret) throws CommonFlkException;

    /**
     * Метод обновления идентификатора развертывания доступа к развертыванию по идентификатору
     * @param secretId Идентификатор доступа к развертыванию
     * @param deploymentId Целевой идентификатор развертывания
     * @throws CommonFlkException Ошибка при проверке ФЛК
     */
    void updateSecretDeploymentIdById(@NonNull UUID secretId, @NonNull UUID deploymentId) throws CommonFlkException;

    /**
     * Метод обновления адреса доступа к развертыванию по идентификатору
     * @param secretId Идентификатор доступа к развертыванию
     * @param address Целевой адрес
     * @throws CommonFlkException Ошибка при проверке ФЛК
     */
    void updateSecretAddressById(@NonNull UUID secretId, @NonNull String address) throws CommonFlkException;

    /**
     * Метод обновления логина доступа к развертыванию по идентификатору
     * @param secretId Идентификатор доступа к развертыванию
     * @param login Целевой логин
     * @throws CommonFlkException Ошибка при проверке ФЛК
     */
    void updateSecretLogin(@NonNull UUID secretId, @NonNull String login) throws CommonFlkException;

    /**
     * Метод обновления пароля доступа к развертыванию по идентификатору
     * @param secretId Идентификатор доступа к развертыванию
     * @param password Целевой пароль
     * @throws CommonFlkException Ошибка при проверке ФЛК
     */
    void updateSecretPassword(@NonNull UUID secretId, String password) throws CommonFlkException;

    /**
     * Метод удаления доступа к развертыванию по идентификатору
     * @param secretId Идентификатор доступа к развертыванию
     * @throws CommonFlkException Ошибка при проверке ФЛК
     */
    void deleteSecretById(@NonNull UUID secretId) throws CommonFlkException;

    /**
     * Метод получения доступа к развертыванию по идентификатору
     * @param secretId Идентификатор доступа к развертыванию
     * @return Сущность доступа к развертыванию или null в случае, если данные не были найдены
     * @throws CommonFlkException Ошибка при проверке ФЛК
     */
    Secret getSecretById(UUID secretId) throws CommonFlkException;

    /**
     * Метод получения доступов к развертыванию по идентификатору развертывания
     * @param deploymentId Идентификатор развертывания
     * @param sortDirection Режим сортировки
     * @param sortBy Наименование поля, по которому осуществляется сортировка. Если сортировка не осуществляется, поле игнорируется. В ином случае должно быть не null
     * @return Список, содержащий доступы к развертыванию или пустой в случае, если данные не были найдены
     * @throws CommonFlkException Ошибка при проверке ФЛК
     */
    List<Secret> getSecretsByDeploymentId(
            @NonNull UUID deploymentId,
            @NonNull SortDirection sortDirection,
            String sortBy
    ) throws CommonFlkException;

    /**
     * Метод получения доступов к развертыванию по адресу
     * @param address Адрес
     * @param sortDirection Режим сортировки
     * @param sortBy Наименование поля, по которому осуществляется сортировка. Если сортировка не осуществляется, поле игнорируется. В ином случае должно быть не null
     * @return Список, содержащий доступы к развертыванию или пустой в случае, если данные не были найдены
     * @throws CommonFlkException Ошибка при проверке ФЛК
     */
    List<Secret> getSecretsByAddress(
            @NonNull String address,
            @NonNull SortDirection sortDirection,
            String sortBy
    ) throws CommonFlkException;

    /**
     * Метод получения доступов к развертыванию по логину
     * @param login Логин
     * @param sortDirection Режим сортировки
     * @param sortBy Наименование поля, по которому осуществляется сортировка. Если сортировка не осуществляется, поле игнорируется. В ином случае должно быть не null
     * @return Список, содержащий доступы к развертыванию или пустой в случае, если данные не были найдены
     * @throws CommonFlkException Ошибка при проверке ФЛК
     */
    List<Secret> getSecretsByLogin(
            @NonNull String login,
            @NonNull SortDirection sortDirection,
            String sortBy
    ) throws CommonFlkException;

    /**
     * Метод получения всех доступов к развертываниям в системе
     * @return Список, содержащий объекты Secret, или пустой список, если данные не были найдены
     * @param sortDirection Режим сортировки
     * @param sortBy Наименование поля, по которому осуществляется сортировка. Если сортировка не осуществляется, поле игнорируется. В ином случае должно быть не null
     * @throws CommonFlkException Ошибка при проверке ФЛК
     */
    List<Secret> getAllSecret(@NonNull SortDirection sortDirection, String sortBy) throws CommonFlkException;
}
