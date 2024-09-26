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
     * Метод сохранения сущности проекта в Системе
     * @param project Сохраняемая сущность проекта типа Project
     * @return Идентификатор сохраненной сущности в БД
     * @throws SQLException Ошибка при работе с БД
     */
    UUID saveProject(@NonNull Project project) throws SQLException;

    /**
     * Метод обновления проекта в Системе
     * @param project Целевая сущность проекта. Поле projectId должно быть заполнено,
     *                иначе будет сформировано исключение NullFieldsInDBEntityException/
     *                Поиск обновляемой записи осуществляется именно по projectId
     * @throws SQLException Ошибка при работе с БД
     */
    void updateProject(@NonNull Project project) throws SQLException;

    /**
     * Метод обновления наименования проекта по идентификатору
     * @param projectId Идентификатор проекта
     * @param projectName Целевое наименование проекта
     * @throws SQLException Ошибка при работе с БД
     */
    void updateProjectNameById(@NonNull UUID projectId, @NonNull String projectName) throws SQLException;

    /**
     * Метод обновления даты последнего внесения изменений в проект по идентификатору
     * @param projectId Идентификатор проекта
     * @param lastChangeDate Целевая дата последнего внесения изменений в проект
     * @throws SQLException Ошибка при работе с БД
     */
    void updateProjectLastChangeDateById(@NonNull UUID projectId, @NonNull LocalDateTime lastChangeDate) throws SQLException;

    /**
     * Метод обновления структуры проекта по идентификатору
     * @param projectId Идентификатор проекта
     * @param structure Целевая структура проекта
     * @throws SQLException Ошибка при работе с БД
     */
    void updateProjectStructureById(@NonNull UUID projectId, @NonNull JSONObject structure) throws SQLException;

    /**
     * Метод обновления содержимого проекта по идентификатору
     * @param projectId Идентификатор проекта
     * @param content Целевое содержимое проекта
     * @throws SQLException Ошибка при работе с БД
     */
    void updateProjectContentById(@NonNull UUID projectId, @NonNull JSONObject content) throws SQLException;

    /**
     * Метод обновления настроек проекта по идентификатору
     * @param projectId Идентификатор проекта
     * @param settings Целевые настройки проекта
     * @throws SQLException Ошибка при работе с БД
     */
    void updateProjectSettingsById(@NonNull UUID projectId, @NonNull JSONObject settings) throws SQLException;

    /**
     * Метод обновления версии проекта по идентификатору
     * @param projectId Идентификатор проекта
     * @param versionNumber Целевая версия проекта
     * @throws SQLException Ошибка при работе с БД
     */
    void updateProjectVersionNumberById(@NonNull UUID projectId, @NonNull String versionNumber) throws SQLException;

    /**
     * Метод удаления проекта по идентификатору
     * @param projectId Идентификатор проекта
     * @throws SQLException Ошибка при работе с БД
     */
    void deleteProjectById(@NonNull UUID projectId) throws SQLException;

    /**
     * Метод получения проекта по идентификатору
     * @param projectId Идентификатор проекта
     * @return Сущность проекта или null в случае, если данные не были найдены
     * @throws SQLException Ошибка при работе с БД
     */
    Project getProjectById(@NonNull UUID projectId) throws SQLException;

    /**
     * Метод получения проекта по наименованию.
     * @param projectName Наименование проекта
     * @return Сущность проекта или null в случае, если данные не были найдены
     * @throws SQLException Ошибка при работе с БД
     */
    Project getProjectByName(@NonNull String projectName) throws SQLException;

    /**
     * Метод получения всех проектов в Системе
     * @param sortDirection Режим сортировки
     * @param sortBy Наименование поля, по которому осуществляется сортировка. Если сортировка не осуществляется, поле игнорируется. В ином случае должно быть не null
     * @return Список, содержащий сущности проекта или пустой в случае, если данные не были найдены
     * @throws SQLException Ошибка при работе с БД
     */
    List<Project> getAllProjects(@NonNull SortDirection sortDirection, String sortBy) throws SQLException;

    /**
     * Метод получения проектов по дате загрузки в Систему
     * @param loadDate Дата загрузки проекта в Систему
     * @param sortDirection Режим сортировки
     * @param sortBy Наименование поля, по которому осуществляется сортировка. Если сортировка не осуществляется, поле игнорируется. В ином случае должно быть не null
     * @return Список, содержащий сущности проекта или пустой в случае, если данные не были найдены
     * @throws SQLException Ошибка при работе с БД
     */
    List<Project> getProjectsByLoadDate(
            @NonNull LocalDateTime loadDate,
            @NonNull SortDirection sortDirection,
            String sortBy
    ) throws SQLException;

    /**
     * Метод получения проектов по диапазону дат загрузки в Систему
     * @param loadDateDiapasonStart Начало диапазона дат загрузки проектов в Систему. Если не заполнено, то рассматривается отрезок до loadDateDiapasonEnd
     * @param loadDateDiapasonEnd Конец диапазона дат загрузки проектов в Систему. Если не заполнено, то рассматривается отрезок с loadDateDiapasonStart
     * @param sortDirection Режим сортировки
     * @param sortBy Наименование поля, по которому осуществляется сортировка. Если сортировка не осуществляется, поле игнорируется. В ином случае должно быть не null
     * @return Список, содержащий сущности проекта или пустой в случае, если данные не были найдены
     * @throws SQLException Ошибка при работе с БД
     */
    List<Project> getProjectsByLoadDateDiapason(
            LocalDateTime loadDateDiapasonStart,
            LocalDateTime loadDateDiapasonEnd,
            @NonNull SortDirection sortDirection,
            String sortBy
    ) throws SQLException;

    /**
     * Метод получения проектов по дате последнего внесения изменений
     * @param lastChangeDate Дата последнего внесения изменений в проекты
     * @param sortDirection Режим сортировки
     * @param sortBy Наименование поля, по которому осуществляется сортировка. Если сортировка не осуществляется, поле игнорируется. В ином случае должно быть не null
     * @return Список, содержащий сущности проекта или пустой в случае, если данные не были найдены
     * @throws SQLException Ошибка при работе с БД
     */
    List<Project> getProjectsByLastChangeDate(
            @NonNull LocalDateTime lastChangeDate,
            @NonNull SortDirection sortDirection,
            String sortBy
    ) throws SQLException;

    /**
     * Метод получения проектов по диапазону дат последнего внесения изменений
     * @param lastChangeDateDiapasonStart Начало диапазона дат последнего изменения в проекты. Если не заполнено, то рассматривается отрезок до lastChangeDateDiapasonEnd
     * @param lastChangeDateDiapasonEnd Конец диапазона дат последнего изменения в проекты. Если не заполнено, то рассматривается отрезок с lastChangeDateDiapasonEnd
     * @param sortDirection Режим сортировки
     * @param sortBy Наименование поля, по которому осуществляется сортировка. Если сортировка не осуществляется, поле игнорируется. В ином случае должно быть не null
     * @return Список, содержащий сущности проекта или пустой в случае, если данные не были найдены
     * @throws SQLException Ошибка при работе с БД
     */
    List<Project> getProjectsByLastChangeDateDiapason(
            LocalDateTime lastChangeDateDiapasonStart,
            LocalDateTime lastChangeDateDiapasonEnd,
            @NonNull SortDirection sortDirection,
            String sortBy
    ) throws SQLException;

    /**
     * Метод получения проектов по номеру версии
     * @param versionNumber Номер версии проектов
     * @param sortDirection Режим сортировки
     * @param sortBy Наименование поля, по которому осуществляется сортировка. Если сортировка не осуществляется, поле игнорируется. В ином случае должно быть не null
     * @return Список, содержащий сущности проекта или пустой в случае, если данные не были найдены
     * @throws SQLException Ошибка при работе с БД
     */
    List<Project> getProjectsByVersionNumber(
            @NonNull String versionNumber,
            @NonNull SortDirection sortDirection,
            String sortBy
    ) throws SQLException;
}
