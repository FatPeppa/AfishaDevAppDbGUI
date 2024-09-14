package org.skyhigh.afishadevappgui.data.datasource.dao;

import lombok.NonNull;
import org.skyhigh.afishadevappgui.common.sort.SortDirection;
import org.skyhigh.afishadevappgui.data.datasource.entity.ProjectAuthor;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

/**
 * DAO для работы с сущностью ProjectAuthor
 */
public interface ProjectAuthorDAO {
    /**
     *
     * @param projectAuthor
     * @throws SQLException Ошибка при работе с БД
     */
    void saveProjectAuthor(@NonNull ProjectAuthor projectAuthor) throws SQLException;

    /**
     *
     * @param projectId
     * @param authorId
     * @throws SQLException Ошибка при работе с БД
     */
    void deleteProjectAuthorByIds(@NonNull UUID projectId, @NonNull UUID authorId) throws SQLException;

    /**
     *
     * @param projectId
     * @throws SQLException Ошибка при работе с БД
     */
    void deleteProjectAuthorsByProjectId(@NonNull UUID projectId) throws SQLException;

    /**
     *
     * @param authorId
     * @throws SQLException Ошибка при работе с БД
     */
    void deleteProjectAuthorsByAuthorId(@NonNull UUID authorId) throws SQLException;

    /**
     *
     * @param oldAuthorId
     * @param projectId
     * @param newAuthorId
     * @throws SQLException Ошибка при работе с БД
     */
    void updateProjectAuthorAuthorId(@NonNull UUID oldAuthorId, @NonNull UUID projectId, @NonNull UUID newAuthorId) throws SQLException;

    /**
     *
     * @param authorId
     * @param oldProjectId
     * @param newProjectId
     * @throws SQLException Ошибка при работе с БД
     */
    void updateProjectAuthorProjectId(@NonNull UUID authorId, @NonNull UUID oldProjectId, @NonNull UUID newProjectId) throws SQLException;

    /**
     *
     * @param projectId
     * @param authorId
     * @return
     * @throws SQLException Ошибка при работе с БД
     */
    ProjectAuthor getProjectAuthorByIds(@NonNull UUID projectId, @NonNull UUID authorId) throws SQLException;

    /**
     *
     * @param sortDirection Режим сортировки
     * @param sortBy Наименование поля, по которому осуществляется сортировка. Если сортировка не осуществляется, заполнено значением null
     * @return
     * @throws SQLException Ошибка при работе с БД
     */
    List<ProjectAuthor> getAllProjectAuthors(@NonNull SortDirection sortDirection, String sortBy) throws SQLException;

    /**
     *
     * @param projectId
     * @param sortDirection Режим сортировки
     * @param sortBy Наименование поля, по которому осуществляется сортировка. Если сортировка не осуществляется, заполнено значением null
     * @return
     * @throws SQLException Ошибка при работе с БД
     */
    List<ProjectAuthor> getProjectAuthorsByProjectId(
            @NonNull UUID projectId,
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
    List<ProjectAuthor> getProjectAuthorsByAuthorId(
            @NonNull UUID authorId,
            @NonNull SortDirection sortDirection,
            String sortBy
    ) throws SQLException;
}
