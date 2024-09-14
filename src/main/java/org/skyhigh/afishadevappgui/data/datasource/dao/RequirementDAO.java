package org.skyhigh.afishadevappgui.data.datasource.dao;

import lombok.NonNull;
import org.json.JSONObject;
import org.skyhigh.afishadevappgui.common.sort.SortDirection;
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
     *
     * @param requirement
     * @return
     * @throws SQLException Ошибка при работе с БД
     */
    UUID saveRequirement(@NonNull Requirement requirement) throws SQLException;

    /**
     *
     * @param requirement
     * @throws SQLException Ошибка при работе с БД
     */
    void updateRequirement(@NonNull Requirement requirement) throws SQLException;

    /**
     *
     * @param requirementId
     * @param requirementTypeId
     * @throws SQLException Ошибка при работе с БД
     */
    void updateRequirementTypeIdById(@NonNull UUID requirementId, @NonNull UUID requirementTypeId) throws SQLException;

    /**
     *
     * @param requirementId
     * @param lastChangeDate
     * @throws SQLException Ошибка при работе с БД
     */
    void updateLastChangeDateById(@NonNull UUID requirementId, @NonNull LocalDateTime lastChangeDate) throws SQLException;

    /**
     *
     * @param requirementId
     * @param content
     * @throws SQLException Ошибка при работе с БД
     */
    void updateContentById(@NonNull UUID requirementId, @NonNull JSONObject content) throws SQLException;

    /**
     *
     * @param requirementId
     * @throws SQLException Ошибка при работе с БД
     */
    void deleteRequirementById(@NonNull UUID requirementId) throws SQLException;

    /**
     *
     * @param requirementId
     * @return
     * @throws SQLException Ошибка при работе с БД
     */
    Requirement getRequirementById(@NonNull UUID requirementId) throws SQLException;

    /**
     *
     * @param requirementTypeId
     * @param sortDirection Режим сортировки
     * @param sortBy Наименование поля, по которому осуществляется сортировка. Если сортировка не осуществляется, заполнено значением null
     * @return
     * @throws SQLException Ошибка при работе с БД
     */
    List<Requirement> getRequirementsByTypeId(
            @NonNull UUID requirementTypeId,
            @NonNull SortDirection sortDirection,
            String sortBy
    ) throws SQLException;

    /**
     *
     * @param loadDate
     * @param sortDirection Режим сортировки
     * @param sortBy Наименование поля, по которому осуществляется сортировка. Если сортировка не осуществляется, заполнено значением null
     * @return
     * @throws SQLException Ошибка при работе с БД
     */
    List<Requirement> getRequirementsByLoadDate(
            @NonNull LocalDateTime loadDate,
            @NonNull SortDirection sortDirection,
            String sortBy
    ) throws SQLException;

    /**
     *
     * @param loadDateStart
     * @param loadDateEnd
     * @param sortDirection Режим сортировки
     * @param sortBy Наименование поля, по которому осуществляется сортировка. Если сортировка не осуществляется, заполнено значением null
     * @return
     * @throws SQLException Ошибка при работе с БД
     */
    List<Requirement> getRequirementsByLoadDateDiapason(
            LocalDateTime loadDateStart,
            LocalDateTime loadDateEnd,
            @NonNull SortDirection sortDirection,
            String sortBy
    ) throws SQLException;

    /**
     *
     * @param lastChangeDate
     * @param sortDirection Режим сортировки
     * @param sortBy Наименование поля, по которому осуществляется сортировка. Если сортировка не осуществляется, заполнено значением null
     * @return
     * @throws SQLException Ошибка при работе с БД
     */
    List<Requirement> getRequirementsByLastChangeDate(
            @NonNull LocalDateTime lastChangeDate,
            @NonNull SortDirection sortDirection,
            String sortBy
    ) throws SQLException;

    /**
     *
     * @param lastChangeDateStart
     * @param lastChangeDateEnd
     * @param sortDirection Режим сортировки
     * @param sortBy Наименование поля, по которому осуществляется сортировка. Если сортировка не осуществляется, заполнено значением null
     * @return
     * @throws SQLException Ошибка при работе с БД
     */
    List<Requirement> getRequirementsByLastChangeDateDiapason(
            LocalDateTime lastChangeDateStart,
            LocalDateTime lastChangeDateEnd,
            @NonNull SortDirection sortDirection,
            String sortBy
    ) throws SQLException;

    /**
     *
     * @param sortDirection Режим сортировки
     * @param sortBy Наименование поля, по которому осуществляется сортировка. Если сортировка не осуществляется, заполнено значением null
     * @return
     * @throws SQLException Ошибка при работе с БД
     */
    List<Requirement> getAllRequirements(
            @NonNull SortDirection sortDirection,
            String sortBy
    ) throws SQLException;
}
