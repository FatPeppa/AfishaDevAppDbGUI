package org.skyhigh.afishadevappgui.data.datasource.dao;

import lombok.NonNull;
import org.json.JSONObject;
import org.skyhigh.afishadevappgui.common.sort.SortDirection;
import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.data.datasource.entity.Requirement;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * DAO для работы с сущностью Requirement
 */
public interface RequirementDAO {
    /**
     * Метод сохранения сущности требования в Системе
     * @param requirement Сохраняемая сущность требования
     * @return Идентификатор требования, сформированный при сохранении сущности в БД
     * @throws SQLException Ошибка при работе с БД
     * @throws CommonFlkException Ошибка при проверке ФЛК
     */
    UUID saveRequirement(@NonNull Requirement requirement) throws SQLException, CommonFlkException;

    /**
     * Метод обновления требования в Системе
     * @param requirement елевая сущность требования. Поле requirementId должно быть заполнено,
     *                иначе будет сформировано исключение NullFieldsInDBEntityException/
     *                Поиск обновляемой записи осуществляется именно по requirementId
     * @throws SQLException Ошибка при работе с БД
     * @throws CommonFlkException Ошибка при проверке ФЛК
     */
    void updateRequirement(@NonNull Requirement requirement) throws SQLException, CommonFlkException;

    /**
     * Метод обновления типа требования по идентификатору
     * @param requirementId Идентификатор требования
     * @param requirementTypeId Идентификатор типа требования
     * @throws SQLException Ошибка при работе с БД
     */
    void updateRequirementTypeIdById(@NonNull UUID requirementId, @NonNull UUID requirementTypeId) throws SQLException;

    /**
     * Метод обновления даты последнего внесения изменений по идентификатору требования
     * @param requirementId Идентификатор требования
     * @param lastChangeDate Дата последнего внесения изменений в требование
     * @throws SQLException Ошибка при работе с БД
     */
    void updateLastChangeDateById(@NonNull UUID requirementId, @NonNull LocalDateTime lastChangeDate) throws SQLException;

    /**
     * Метод обновления содержимого требования по идентификатору
     * @param requirementId Идентификатор требования
     * @param content Целевое содержимое требования
     * @throws SQLException Ошибка при работе с БД
     */
    void updateContentById(@NonNull UUID requirementId, @NonNull JSONObject content) throws SQLException;

    /**
     * Метод удаления требования по идентификатору
     * @param requirementId Идентификатор удаляемого требования
     * @throws SQLException Ошибка при работе с БД
     */
    void deleteRequirementById(@NonNull UUID requirementId) throws SQLException;

    /**
     * Метод получения требования по идентификатору
     * @param requirementId Идентификатор требования
     * @return Сущность требования или null в случае, если данные не были найдены
     * @throws SQLException Ошибка при работе с БД
     */
    Requirement getRequirementById(@NonNull UUID requirementId) throws SQLException;

    /**
     * Метод получения требований по типу
     * @param requirementTypeId Идентификатор типа требования
     * @param sortDirection Режим сортировки
     * @param sortBy Наименование поля, по которому осуществляется сортировка. Если сортировка не осуществляется, поле игнорируется. В ином случае должно быть не null
     * @return Список, содержащий сущности требований или пустой в случае, если данные не были найдены
     * @throws SQLException Ошибка при работе с БД
     * @throws CommonFlkException Ошибка при проверке ФЛК
     */
    List<Requirement> getRequirementsByTypeId(
            @NonNull UUID requirementTypeId,
            @NonNull SortDirection sortDirection,
            String sortBy
    ) throws SQLException, CommonFlkException;

    /**
     * Метод получения требований по дате загрузки в Систему
     * @param loadDate Дата загрузки требования в Систему
     * @param sortDirection Режим сортировки
     * @param sortBy Наименование поля, по которому осуществляется сортировка. Если сортировка не осуществляется, поле игнорируется. В ином случае должно быть не null
     * @return Список, содержащий сущности требований или пустой в случае, если данные не были найдены
     * @throws SQLException Ошибка при работе с БД
     * @throws CommonFlkException Ошибка при проверке ФЛК
     */
    List<Requirement> getRequirementsByLoadDate(
            @NonNull LocalDateTime loadDate,
            @NonNull SortDirection sortDirection,
            String sortBy
    ) throws SQLException, CommonFlkException;

    /**
     * Метод получения требований по диапазону дат загрузки в Систему
     * @param loadDateStart Начало диапазона дат загрузки требований в Систему. Если не заполнено, то рассматривается отрезок до loadDateEnd
     * @param loadDateEnd Конец диапазона дат загрузки требований в Систему. Если не заполнено, то рассматривается отрезок с loadDateStart
     * @param sortDirection Режим сортировки
     * @param sortBy Наименование поля, по которому осуществляется сортировка. Если сортировка не осуществляется, поле игнорируется. В ином случае должно быть не null
     * @return Список, содержащий сущности требований или пустой в случае, если данные не были найдены
     * @throws SQLException Ошибка при работе с БД
     * @throws CommonFlkException Ошибка при проверке ФЛК
     */
    List<Requirement> getRequirementsByLoadDateDiapason(
            LocalDateTime loadDateStart,
            LocalDateTime loadDateEnd,
            @NonNull SortDirection sortDirection,
            String sortBy
    ) throws SQLException, CommonFlkException;

    /**
     * Метод получения требований по дате последнего внесения изменений
     * @param lastChangeDate Дата последнего внесения изменений
     * @param sortDirection Режим сортировки
     * @param sortBy Наименование поля, по которому осуществляется сортировка. Если сортировка не осуществляется, поле игнорируется. В ином случае должно быть не null
     * @return Список, содержащий сущности требований или пустой в случае, если данные не были найдены
     * @throws SQLException Ошибка при работе с БД
     * @throws CommonFlkException Ошибка при проверке ФЛК
     */
    List<Requirement> getRequirementsByLastChangeDate(
            @NonNull LocalDateTime lastChangeDate,
            @NonNull SortDirection sortDirection,
            String sortBy
    ) throws SQLException, CommonFlkException;

    /**
     * Метод получения требований по диапазону дат последнего внесения изменений
     * @param lastChangeDateStart Начало диапазона дат загрузки требований в Систему. Если не заполнено, то рассматривается отрезок до lastChangeDateEnd
     * @param lastChangeDateEnd Конец диапазона дат загрузки требований в Систему. Если не заполнено, то рассматривается отрезок с lastChangeDateStart
     * @param sortDirection Режим сортировки
     * @param sortBy Наименование поля, по которому осуществляется сортировка. Если сортировка не осуществляется, поле игнорируется. В ином случае должно быть не null
     * @return Список, содержащий сущности требований или пустой в случае, если данные не были найдены
     * @throws SQLException Ошибка при работе с БД
     * @throws CommonFlkException Ошибка при проверке ФЛК
     */
    List<Requirement> getRequirementsByLastChangeDateDiapason(
            LocalDateTime lastChangeDateStart,
            LocalDateTime lastChangeDateEnd,
            @NonNull SortDirection sortDirection,
            String sortBy
    ) throws SQLException, CommonFlkException;

    /**
     * Метод получения всех требований в Системе
     * @param sortDirection Режим сортировки
     * @param sortBy Наименование поля, по которому осуществляется сортировка. Если сортировка не осуществляется, поле игнорируется. В ином случае должно быть не null
     * @return Список, содержащий сущности требований или пустой в случае, если данные не были найдены
     * @throws SQLException Ошибка при работе с БД
     * @throws CommonFlkException Ошибка при проверке ФЛК
     */
    List<Requirement> getAllRequirements(
            @NonNull SortDirection sortDirection,
            String sortBy
    ) throws SQLException, CommonFlkException;
}
