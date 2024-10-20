package org.skyhigh.afishadevappgui.data.datasource.dao;

import lombok.NonNull;
import org.skyhigh.afishadevappgui.common.sort.SortDirection;
import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.data.datasource.entity.RequirementType;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

/**
 * DAO для работы с сущностью RequirementType
 */
public interface RequirementTypeDAO {
    /**
     * Метод сохранения сущности типа требования
     * @param requirementType Сохраняемая сущность типа требования
     * @return Идентификатор типа требования, сформированный при сохранении сущности в БД
     * @throws SQLException Ошибка при работе с БД
     * @throws CommonFlkException Ошибка при проверке ФЛК
     */
    UUID saveRequirementType(@NonNull RequirementType requirementType) throws SQLException, CommonFlkException;

    /**
     * Метод получения типа требования по идентификатору
     * @param requirementTypeId Идентификатор типа требования
     * @return Сущность типа требования или null в случае, если данные не были найдены
     * @throws SQLException Ошибка при работе с БД
     */
    RequirementType getRequirementTypeById(@NonNull UUID requirementTypeId) throws SQLException;

    /**
     * Метод получения типа требования по наименованию
     * @param requirementTypeName Наименования типа требования
     * @return Сущность типа требования или null в случае, если данные не были найдены
     * @throws SQLException Ошибка при работе с БД
     */
    RequirementType getRequirementTypeByName(@NonNull String requirementTypeName) throws SQLException;

    /**
     * Метод получения всех типов требований в Системе
     * @param sortDirection Режим сортировки
     * @param sortBy Наименование поля, по которому осуществляется сортировка. Если сортировка не осуществляется, поле игнорируется. В ином случае должно быть не null
     * @return Список, содержащий статусы развертываний, хранимые в Системе, или пустой список, если данные не были найдены
     * @throws SQLException Ошибка при работе с БД
     * @throws CommonFlkException Ошибка при проверке ФЛК
     */
    List<RequirementType> getAllRequirementTypes(@NonNull SortDirection sortDirection, String sortBy) throws SQLException, CommonFlkException;

    /**
     * Метод обновления наименования типа требования по идентификатору
     * @param requirementTypeId Идентификатор типа требования
     * @param newRequirementTypeName Целевое наименование типа требования
     * @throws SQLException Ошибка при работе с БД
     */
    void updateRequirementTypeNameById(@NonNull UUID requirementTypeId, @NonNull String newRequirementTypeName) throws SQLException;

    /**
     * Метод удаления типа требования по идентификатору
     * @param requirementTypeId Идентификатор удаляемого типа требования
     * @throws SQLException Ошибка при работе с БД
     */
    void deleteRequirementTypeById(@NonNull UUID requirementTypeId) throws SQLException;
}
