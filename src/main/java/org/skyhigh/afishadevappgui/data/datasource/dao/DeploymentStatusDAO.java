package org.skyhigh.afishadevappgui.data.datasource.dao;

import lombok.NonNull;
import org.skyhigh.afishadevappgui.common.sort.SortDirection;
import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.data.datasource.entity.DeploymentStatus;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

/**
 * DAO для работы с сущностью DeploymentStatus
 */
public interface DeploymentStatusDAO {
    /**
     * Метод сохранения типа статуса развертывания
     * @param deploymentStatus Тип статуса развертывания для сохранения
     * @return Уникальный идентификатор, присвоенный сохраненной в Системе записи типа статуса развертывания
     * @throws SQLException Ошибка при работе с БД
     * @throws CommonFlkException Ошибка при проверке ФЛК
     */
    UUID saveDeploymentStatus(@NonNull DeploymentStatus deploymentStatus) throws SQLException, CommonFlkException;

    /**
     * Метод получения типа статуса развертывания
     * @param deploymentStatusId Идентификатор типа статуса развертывания
     * @return Тип статуса развертывания
     * @throws SQLException Ошибка при работе с БД
     */
    DeploymentStatus getDeploymentStatusById(@NonNull UUID deploymentStatusId) throws SQLException;

    /**
     * Метод получения типа статуса развертывания по его наименованию
     * @param statusName Наименования типа статуса развертывания
     * @return Тип статуса развертывания
     * @throws SQLException Ошибка при работе с БД
     */
    DeploymentStatus getDeploymentStatusByName(@NonNull String statusName) throws SQLException;

    /**
     * Метод получения всех статусов развертываний в Системе
     * @param sortDirection Режим сортировки
     * @param sortBy Наименование поля, по которому осуществляется сортировка. Если сортировка не осуществляется, поле игнорируется. В ином случае должно быть не null
     * @return Список, содержащий статусы развертываний, хранимые в Системе, или пустой список, если данные не были найдены
     * @throws SQLException Ошибка при работе с БД
     * @throws CommonFlkException Ошибка при проверке ФЛК
     */
    List<DeploymentStatus> getAllDeploymentStatuses(@NonNull SortDirection sortDirection, String sortBy) throws SQLException, CommonFlkException;

    /**
     * Метод обновления наименования типа статуса развертывания
     * @param deploymentStatusId Идентификатор типа статуса развертывания
     * @param newStatusName Целевое наименование типа статуса развертывания
     * @throws SQLException Ошибка при работе с БД
     */
    void updateDeploymentStatusNameById(@NonNull UUID deploymentStatusId, @NonNull String newStatusName) throws SQLException;

    /**
     * Метод удаления типа статуса развертывания из Системы по его идентификатору
     * @param deploymentStatusId Идентификатор удаляемого типа статуса развертывания
     * @throws SQLException Ошибка при работе с БД
     */
    void deleteDeploymentStatusById(@NonNull UUID deploymentStatusId) throws SQLException;
}
