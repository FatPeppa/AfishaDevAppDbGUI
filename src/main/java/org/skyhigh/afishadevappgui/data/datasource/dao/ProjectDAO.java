package org.skyhigh.afishadevappgui.data.datasource.dao;

import lombok.NonNull;
import org.json.JSONObject;
import org.skyhigh.afishadevappgui.common.sort.SortDirection;
import org.skyhigh.afishadevappgui.data.datasource.entity.Project;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * DAO для работы с сущностью Project
 */
public interface ProjectDAO {
    /**
     *
     * @param project
     * @return
     * @throws SQLException Ошибка при работе с БД
     */
    UUID saveProject(@NonNull Project project) throws SQLException;

    /**
     *
     * @param project
     * @throws SQLException Ошибка при работе с БД
     */
    void updateProject(@NonNull Project project) throws SQLException;

    /**
     *
     * @param projectId
     * @param projectName
     * @throws SQLException Ошибка при работе с БД
     */
    void updateProjectNameById(@NonNull UUID projectId, @NonNull String projectName) throws SQLException;

    /**
     *
     * @param projectId
     * @param lastChangeDate
     * @throws SQLException Ошибка при работе с БД
     */
    void updateProjectLastChangeDateById(@NonNull UUID projectId, @NonNull LocalDateTime lastChangeDate) throws SQLException;

    /**
     *
     * @param projectId
     * @param structure
     * @throws SQLException Ошибка при работе с БД
     */
    void updateProjectStructureById(@NonNull UUID projectId, @NonNull JSONObject structure) throws SQLException;

    /**
     *
     * @param projectId
     * @param content
     * @throws SQLException Ошибка при работе с БД
     */
    void updateProjectContentById(@NonNull UUID projectId, @NonNull JSONObject content) throws SQLException;

    /**
     *
     * @param projectId
     * @param settings
     * @throws SQLException Ошибка при работе с БД
     */
    void updateProjectSettingsById(@NonNull UUID projectId, @NonNull JSONObject settings) throws SQLException;

    /**
     *
     * @param projectId
     * @param versionNumber
     * @throws SQLException Ошибка при работе с БД
     */
    void updateProjectVersionNumberById(@NonNull UUID projectId, @NonNull String versionNumber) throws SQLException;

    /**
     *
     * @param projectId
     * @throws SQLException Ошибка при работе с БД
     */
    void deleteProjectById(@NonNull UUID projectId) throws SQLException;

    /**
     *
     * @param projectId
     * @return
     * @throws SQLException Ошибка при работе с БД
     */
    Project getProjectById(@NonNull UUID projectId) throws SQLException;

    /**
     *
     * @param projectName
     * @return
     * @throws SQLException Ошибка при работе с БД
     */
    Project getProjectByName(@NonNull String projectName) throws SQLException;

    /**
     *
     * @param sortDirection Режим сортировки
     * @param sortBy Наименование поля, по которому осуществляется сортировка. Если сортировка не осуществляется, заполнено значением null
     * @return
     * @throws SQLException Ошибка при работе с БД
     */
    List<Project> getAllProjects(@NonNull SortDirection sortDirection, String sortBy) throws SQLException;

    /**
     *
     * @param loadDate
     * @param sortDirection Режим сортировки
     * @param sortBy Наименование поля, по которому осуществляется сортировка. Если сортировка не осуществляется, заполнено значением null
     * @return
     * @throws SQLException Ошибка при работе с БД
     */
    List<Project> getProjectsByLoadDate(
            @NonNull LocalDateTime loadDate,
            @NonNull SortDirection sortDirection,
            String sortBy
    ) throws SQLException;

    /**
     *
     * @param loadDateDiapasonStart
     * @param loadDateDiapasonEnd
     * @param sortDirection Режим сортировки
     * @param sortBy Наименование поля, по которому осуществляется сортировка. Если сортировка не осуществляется, заполнено значением null
     * @return
     * @throws SQLException Ошибка при работе с БД
     */
    List<Project> getProjectsByLoadDateDiapason(
            LocalDateTime loadDateDiapasonStart,
            LocalDateTime loadDateDiapasonEnd,
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
    List<Project> getProjectsByLastChangeDate(
            @NonNull LocalDateTime lastChangeDate,
            @NonNull SortDirection sortDirection,
            String sortBy
    ) throws SQLException;

    /**
     *
     * @param lastChangeDateDiapasonStart
     * @param lastChangeDateDiapasonEnd
     * @param sortDirection Режим сортировки
     * @param sortBy Наименование поля, по которому осуществляется сортировка. Если сортировка не осуществляется, заполнено значением null
     * @return
     * @throws SQLException Ошибка при работе с БД
     */
    List<Project> getProjectsByLastChangeDateDiapason(
            LocalDateTime lastChangeDateDiapasonStart,
            LocalDateTime lastChangeDateDiapasonEnd,
            @NonNull SortDirection sortDirection,
            String sortBy
    ) throws SQLException;

    /**
     *
     * @param versionNumber
     * @param sortDirection Режим сортировки
     * @param sortBy Наименование поля, по которому осуществляется сортировка. Если сортировка не осуществляется, заполнено значением null
     * @return
     * @throws SQLException Ошибка при работе с БД
     */
    List<Project> getProjectsByVersionNumber(
            @NonNull String versionNumber,
            @NonNull SortDirection sortDirection,
            String sortBy
    ) throws SQLException;
}
