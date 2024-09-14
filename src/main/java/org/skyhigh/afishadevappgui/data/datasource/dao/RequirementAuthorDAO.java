package org.skyhigh.afishadevappgui.data.datasource.dao;

import lombok.NonNull;
import org.skyhigh.afishadevappgui.common.sort.SortDirection;
import org.skyhigh.afishadevappgui.data.datasource.entity.ProjectAuthor;
import org.skyhigh.afishadevappgui.data.datasource.entity.RequirementAuthor;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

/**
 * DAO для работы с сущностью RequirementAuthor
 */
public interface RequirementAuthorDAO {
    /**
     *
     * @param requirementAuthor
     * @throws SQLException Ошибка при работе с БД
     */
    void saveRequirementAuthor(@NonNull RequirementAuthor requirementAuthor) throws SQLException;

    /**
     *
     * @param requirementId
     * @param authorId
     * @throws SQLException Ошибка при работе с БД
     */
    void deleteRequirementAuthorByIds(@NonNull UUID requirementId, @NonNull UUID authorId) throws SQLException;

    /**
     *
     * @param requirementId
     * @throws SQLException Ошибка при работе с БД
     */
    void deleteRequirementAuthorsByRequirementId(@NonNull UUID requirementId) throws SQLException;

    /**
     *
     * @param authorId
     * @throws SQLException Ошибка при работе с БД
     */
    void deleteRequirementAuthorsByAuthorId(@NonNull UUID authorId) throws SQLException;

    /**
     *
     * @param oldAuthorId
     * @param requirementId
     * @param newAuthorId
     * @throws SQLException Ошибка при работе с БД
     */
    void updateRequirementAuthorAuthorId(
            @NonNull UUID oldAuthorId,
            @NonNull UUID requirementId,
            @NonNull UUID newAuthorId
    ) throws SQLException;

    /**
     *
     * @param authorId
     * @param oldRequirementId
     * @param newRequirementId
     * @throws SQLException Ошибка при работе с БД
     */
    void updateRequirementAuthorRequirementId(
            @NonNull UUID authorId,
            @NonNull UUID oldRequirementId,
            @NonNull UUID newRequirementId
    ) throws SQLException;

    /**
     *
     * @param requirementId
     * @param authorId
     * @return
     * @throws SQLException Ошибка при работе с БД
     */
    RequirementAuthor getRequirementAuthorByIds(@NonNull UUID requirementId, @NonNull UUID authorId) throws SQLException;

    /**
     *
     * @param sortDirection Режим сортировки
     * @param sortBy Наименование поля, по которому осуществляется сортировка. Если сортировка не осуществляется, заполнено значением null
     * @return
     * @throws SQLException Ошибка при работе с БД
     */
    List<RequirementAuthor> getAllRequirementAuthors(@NonNull SortDirection sortDirection, String sortBy) throws SQLException;

    /**
     *
     * @param requirementId
     * @param sortDirection Режим сортировки
     * @param sortBy Наименование поля, по которому осуществляется сортировка. Если сортировка не осуществляется, заполнено значением null
     * @return
     * @throws SQLException Ошибка при работе с БД
     */
    List<RequirementAuthor> getRequirementAuthorsByRequirementId(
            @NonNull UUID requirementId,
            @NonNull SortDirection sortDirection,
            String sortBy
    ) throws SQLException;

    /**
     *
     * @param authorId
     * @param sortDirection Режим сортировки
     * @param sortBy Наименование поля, по которому осуществляется сортировка. Если сортировка не осуществляется, заполнено значением null
     * @return
     * @throws SQLException Ошибка при работе с БД
     */
    List<RequirementAuthor> getRequirementAuthorsByAuthorId(
            @NonNull UUID authorId,
            @NonNull SortDirection sortDirection,
            String sortBy
    ) throws SQLException;
}
