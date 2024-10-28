package org.skyhigh.afishadevappgui.data.datasource.dao;

import lombok.NonNull;
import org.json.JSONObject;
import org.skyhigh.afishadevappgui.common.sort.SortDirection;
import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.data.datasource.entity.Deployment;

import java.io.File;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

/**
 * DAO для работы с сущностью Deployment
 */
public interface DeploymentDAO {
    /**
     * Метод сохранения развертывания
     * @param deployment Объект развертывания
     * @return Уникальный идентификатор, присвоенный записи развертывания в Системе
     * @throws SQLException Ошибка при работе с БД
     * @throws CommonFlkException Ошибка при проверке ФЛК
     */
    UUID saveDeployment(@NonNull Deployment deployment) throws SQLException, CommonFlkException;

    /**
     * Метод удаления записи развертывания по идентификатору
     * @param deploymentId Идентификатор удаляемого развертывания
     * @throws SQLException Ошибка при работе с БД
     */
    void deleteDeploymentById(@NonNull UUID deploymentId) throws SQLException;

    /**
     * Метод обновления статуса развертывания
     * @param deploymentId Идентификатор развертывания
     * @param deploymentStatusId Идентификатор целевого статуса развертывания
     * @throws SQLException Ошибка при работе с БД
     */
    void updateDeploymentStatusById(@NonNull UUID deploymentId, @NonNull UUID deploymentStatusId) throws SQLException;

    /**
     * Метод обновления версии сборки в развертывании
     * @param deploymentId Идентификатор развертывания
     * @param builtVersion Целевая версия сборки
     * @throws SQLException Ошибка при работе с БД
     */
    void updateDeploymentBuiltVersionById(@NonNull UUID deploymentId, String builtVersion) throws SQLException;

    /**
     * Метод обновления настроек сборки в развертывании
     * @param deploymentId Идентификатор развертывания
     * @param builtSettings Целевые настройки сборки в развертывании
     * @throws SQLException Ошибка при работе с БД
     */
    void updateDeploymentBuiltSettingsById(@NonNull UUID deploymentId, JSONObject builtSettings) throws SQLException;

    /**
     * Метод обновления сборки в развертывании
     * @param deploymentId Идентификатор развертывания
     * @param built Целевая сборка в развертывании
     * @throws SQLException Ошибка при работе с БД
     */
    void updateDeploymentBuiltById(@NonNull UUID deploymentId, File built) throws SQLException, CommonFlkException;

    /**
     * Метод обновления проекта, используемого для сборки в развертывании
     * @param deploymentId Идентификатор развертывания
     * @param projectId Идентификатор целевого проекта для сборки в развертывании
     * @throws SQLException Ошибка при работе с БД
     */
    void updateDeploymentProjectIdById(@NonNull UUID deploymentId, @NonNull UUID projectId) throws SQLException;

    /**
     * Метод обновления параметров развертывания
     * @param deploymentId Идентификатор развертывания
     * @param deploymentStatusId Целевой статус развертывания
     * @param deploymentPath Целевой путь развертывания
     * @param settings Целевые настройки развертывания
     * @param builtVersion Целевая версия сборки в развертывании
     * @param builtSettings Целевые настройки сборки в развертывании
     * @param built Целевая сборка в развертывании
     * @param projectId Идентификатор целевого проекта для сборки в развертывании
     * @throws SQLException Ошибка при работе с БД
     */
    void updateDeploymentParamsById(@NonNull UUID deploymentId,
                                    @NonNull UUID deploymentStatusId,
                                    String deploymentPath,
                                    JSONObject settings,
                                    String builtVersion,
                                    JSONObject builtSettings,
                                    File built,
                                    @NonNull UUID projectId
    ) throws SQLException, CommonFlkException;

    /**
     * Метод получения развертывания по идентификатору
     * @param deploymentId Идентификатор развертывания
     * @return Запись развертывания или null, если данные не были найдены
     * @throws SQLException Ошибка при работе с БД
     */
    Deployment getDeploymentById(@NonNull UUID deploymentId) throws SQLException, CommonFlkException;

    /**
     * Метод получения записей развертывания по идентификатору проекта
     * @param projectId Идентификатор проекта для сборки в развертывании
     * @param sortDirection Режим сортировки
     * @param sortBy Наименование поля, по которому осуществляется сортировка. Если сортировка не осуществляется, поле игнорируется. В ином случае должно быть не null
     * @return Список записей развертывания, удовлетворяющих критериям поиска, или пустой список, если данные не были найдены
     * @throws SQLException Ошибка при работе с БД
     * @throws CommonFlkException Ошибка при проверке ФЛК
     */
    List<Deployment> getDeploymentsByProjectId(
            @NonNull UUID projectId,
            @NonNull SortDirection sortDirection,
            String sortBy
    ) throws SQLException, CommonFlkException;

    /**
     * Метод получения записей развертывания по статусу
     * @param deploymentStatusId Идентификатор статуса развертывания
     * @param sortDirection Режим сортировки
     * @param sortBy Наименование поля, по которому осуществляется сортировка. Если сортировка не осуществляется, поле игнорируется. В ином случае должно быть не null
     * @return Список записей развертывания, удовлетворяющих критериям поиска, или пустой список, если данные не были найдены
     * @throws SQLException Ошибка при работе с БД
     * @throws CommonFlkException Ошибка при проверке ФЛК
     */
    List<Deployment> getDeploymentsByStatusId(
            @NonNull UUID deploymentStatusId,
            @NonNull SortDirection sortDirection,
            String sortBy
    ) throws SQLException, CommonFlkException;

    /**
     * Метод получения всех записей развертываний, хранимых в Системе
     * @param sortDirection Режим сортировки
     * @param sortBy Наименование поля, по которому осуществляется сортировка. Если сортировка не осуществляется, поле игнорируется. В ином случае должно быть не null
     * @return Список записей развертывания, удовлетворяющих критериям поиска, или пустой список, если данные не были найдены
     * @throws SQLException Ошибка при работе с БД
     * @throws CommonFlkException Ошибка при проверке ФЛК
     */
    List<Deployment> getAllDeployments(@NonNull SortDirection sortDirection, String sortBy) throws SQLException, CommonFlkException;

    /**
     * Метод получения записей развертываний по версии сборки
     * @param builtVersion Версия сборки в развертываниях
     * @param sortDirection Режим сортировки
     * @param sortBy Наименование поля, по которому осуществляется сортировка. Если сортировка не осуществляется, поле игнорируется. В ином случае должно быть не null
     * @return Список записей развертывания, удовлетворяющих критериям поиска, или пустой список, если данные не были найдены
     * @throws SQLException Ошибка при работе с БД
     * @throws CommonFlkException Ошибка при проверке ФЛК
     */
    List<Deployment> getDeploymentsByBuiltVersion(
            String builtVersion,
            @NonNull SortDirection sortDirection,
            String sortBy
    ) throws SQLException, CommonFlkException;

    /**
     * Метод получения записей развертываний по пути развертывания
     * @param deploymentPath Путь развертывания
     * @param sortDirection Режим сортировки
     * @param sortBy Наименование поля, по которому осуществляется сортировка. Если сортировка не осуществляется, поле игнорируется. В ином случае должно быть не null
     * @return Список записей развертывания, удовлетворяющих критериям поиска, или пустой список, если данные не были найдены
     * @throws SQLException Ошибка при работе с БД
     * @throws CommonFlkException Ошибка при проверке ФЛК
     */
    List<Deployment> getDeploymentsByPath(
            String deploymentPath,
            @NonNull SortDirection sortDirection,
            String sortBy
    ) throws SQLException, CommonFlkException;

    /**
     * Метод получения записей развертывания по идентификатору проекта и статусу сборки
     * @param deploymentStatusId Идентификатор статуса развертывания
     * @param projectId Идентификатор проекта для сборки в развертывании
     * @param sortDirection Режим сортировки
     * @param sortBy Наименование поля, по которому осуществляется сортировка. Если сортировка не осуществляется, поле игнорируется. В ином случае должно быть не null
     * @return Список записей развертывания, удовлетворяющих критериям поиска, или пустой список, если данные не были найдены
     * @throws SQLException Ошибка при работе с БД
     * @throws CommonFlkException Ошибка при проверке ФЛК
     */
    List<Deployment> getDeploymentsByStatusIdAndProjectId(
            @NonNull UUID deploymentStatusId,
            @NonNull UUID projectId,
            @NonNull SortDirection sortDirection,
            String sortBy
    ) throws SQLException, CommonFlkException;

    /**
     * Метод развертывания сборки (меняет статус развертывания, создает сущность сикрета)
     * @param deploymentId Идентификатор развертывания
     * @param address Адрес доступа к развертыванию
     * @param login Логин доступа к развертыванию
     * @param password Пароль доступа к развертыванию. Должен соответствовать текущим (на дату сохранения)
     *                 правилам заполнения паролей. Если пуст, то генерируется автоматически
     * @return Идентификатор доступа к развертыванию в Системе (если доступ к развертыванию сформирован успешно), или null в случае ошибки поиска сформированного сикрета
     * @throws SQLException Ошибка при работе с БД
     * @throws CommonFlkException Ошибка при проверке ФЛК
     */
    UUID deployBuilt(
        @NonNull UUID deploymentId,
        @NonNull String address,
        @NonNull String login,
        String password
    ) throws SQLException, CommonFlkException;
}