package org.skyhigh.afishadevappgui.data.repository;

import lombok.NonNull;
import org.json.JSONObject;
import org.skyhigh.afishadevappgui.common.sort.SortDirection;
import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.data.datasource.entity.Project;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface ProjectRepository {
    /**
     * Метод сохранения сущности проекта в Системе
     * @param project Сохраняемая сущность проекта типа Project
     * @return Идентификатор сохраненной сущности в БД
     * @throws CommonFlkException Ошибка при проверке ФЛК
     */
    UUID saveProject(@NonNull Project project) throws CommonFlkException;

    /**
     * Метод обновления проекта в Системе
     * @param project Целевая сущность проекта. Поле projectId должно быть заполнено,
     *                иначе будет сформировано исключение NullFieldsInDBEntityException/
     *                Поиск обновляемой записи осуществляется именно по projectId
     * @throws CommonFlkException Ошибка при проверке ФЛК
     */
    void updateProject(@NonNull Project project) throws CommonFlkException;

    /**
     * Метод обновления наименования проекта по идентификатору
     * @param projectId Идентификатор проекта
     * @param projectName Целевое наименование проекта
     * @throws CommonFlkException Ошибка при проверке ФЛК
     */
    void updateProjectNameById(@NonNull UUID projectId, @NonNull String projectName) throws CommonFlkException;

    /**
     * Метод обновления даты последнего внесения изменений в проект по идентификатору
     * @param projectId Идентификатор проекта
     * @param lastChangeDate Целевая дата последнего внесения изменений в проект
     * @throws CommonFlkException Ошибка при проверке ФЛК
     */
    void updateProjectLastChangeDateById(@NonNull UUID projectId, @NonNull LocalDateTime lastChangeDate) throws CommonFlkException;

    /**
     * Метод обновления структуры проекта по идентификатору
     * @param projectId Идентификатор проекта
     * @param structure Целевая структура проекта
     * @throws CommonFlkException Ошибка при проверке ФЛК
     */
    void updateProjectStructureById(@NonNull UUID projectId, @NonNull JSONObject structure) throws CommonFlkException;

    /**
     * Метод обновления содержимого проекта по идентификатору
     * @param projectId Идентификатор проекта
     * @param content Целевое содержимое проекта
     * @throws CommonFlkException Ошибка при проверке ФЛК
     */
    void updateProjectContentById(@NonNull UUID projectId, @NonNull JSONObject content) throws CommonFlkException;

    /**
     * Метод обновления настроек проекта по идентификатору
     * @param projectId Идентификатор проекта
     * @param settings Целевые настройки проекта
     * @throws CommonFlkException Ошибка при проверке ФЛК
     */
    void updateProjectSettingsById(@NonNull UUID projectId, @NonNull JSONObject settings) throws CommonFlkException;

    /**
     * Метод обновления версии проекта по идентификатору
     * @param projectId Идентификатор проекта
     * @param versionNumber Целевая версия проекта
     * @throws CommonFlkException Ошибка при проверке ФЛК
     */
    void updateProjectVersionNumberById(@NonNull UUID projectId, @NonNull String versionNumber) throws CommonFlkException;

    /**
     * Метод удаления проекта по идентификатору
     * @param projectId Идентификатор проекта
     * @throws CommonFlkException Ошибка при проверке ФЛК
     */
    void deleteProjectById(@NonNull UUID projectId) throws CommonFlkException;

    /**
     * Метод получения проекта по идентификатору
     * @param projectId Идентификатор проекта
     * @return Сущность проекта или null в случае, если данные не были найдены
     * @throws CommonFlkException Ошибка при проверке ФЛК
     */
    Project getProjectById(@NonNull UUID projectId) throws CommonFlkException;

    /**
     * Метод получения проекта по наименованию.
     * @param projectName Наименование проекта
     * @return Сущность проекта или null в случае, если данные не были найдены
     * @throws CommonFlkException Ошибка при проверке ФЛК
     */
    Project getProjectByName(@NonNull String projectName) throws CommonFlkException;

    /**
     * Метод получения всех проектов в Системе
     * @param sortDirection Режим сортировки
     * @param sortBy Наименование поля, по которому осуществляется сортировка. Если сортировка не осуществляется, поле игнорируется. В ином случае должно быть не null
     * @return Список, содержащий сущности проекта или пустой в случае, если данные не были найдены
     * @throws CommonFlkException Ошибка при проверке ФЛК
     */
    List<Project> getAllProjects(@NonNull SortDirection sortDirection, String sortBy) throws CommonFlkException;

    /**
     * Метод получения проектов по дате загрузки в Систему
     * @param loadDate Дата загрузки проекта в Систему
     * @param sortDirection Режим сортировки
     * @param sortBy Наименование поля, по которому осуществляется сортировка. Если сортировка не осуществляется, поле игнорируется. В ином случае должно быть не null
     * @return Список, содержащий сущности проекта или пустой в случае, если данные не были найдены
     * @throws CommonFlkException Ошибка при проверке ФЛК
     */
    List<Project> getProjectsByLoadDate(
            @NonNull LocalDateTime loadDate,
            @NonNull SortDirection sortDirection,
            String sortBy
    ) throws CommonFlkException;

    /**
     * Метод получения проектов по диапазону дат загрузки в Систему
     * @param loadDateDiapasonStart Начало диапазона дат загрузки проектов в Систему. Если не заполнено, то рассматривается отрезок до loadDateDiapasonEnd
     * @param loadDateDiapasonEnd Конец диапазона дат загрузки проектов в Систему. Если не заполнено, то рассматривается отрезок с loadDateDiapasonStart
     * @param sortDirection Режим сортировки
     * @param sortBy Наименование поля, по которому осуществляется сортировка. Если сортировка не осуществляется, поле игнорируется. В ином случае должно быть не null
     * @return Список, содержащий сущности проекта или пустой в случае, если данные не были найдены
     * @throws CommonFlkException Ошибка при проверке ФЛК
     */
    List<Project> getProjectsByLoadDateDiapason(
            LocalDateTime loadDateDiapasonStart,
            LocalDateTime loadDateDiapasonEnd,
            @NonNull SortDirection sortDirection,
            String sortBy
    ) throws CommonFlkException;

    /**
     * Метод получения проектов по дате последнего внесения изменений
     * @param lastChangeDate Дата последнего внесения изменений в проекты
     * @param sortDirection Режим сортировки
     * @param sortBy Наименование поля, по которому осуществляется сортировка. Если сортировка не осуществляется, поле игнорируется. В ином случае должно быть не null
     * @return Список, содержащий сущности проекта или пустой в случае, если данные не были найдены
     * @throws CommonFlkException Ошибка при проверке ФЛК
     */
    List<Project> getProjectsByLastChangeDate(
            @NonNull LocalDateTime lastChangeDate,
            @NonNull SortDirection sortDirection,
            String sortBy
    ) throws CommonFlkException;

    /**
     * Метод получения проектов по диапазону дат последнего внесения изменений
     * @param lastChangeDateDiapasonStart Начало диапазона дат последнего изменения в проекты. Если не заполнено, то рассматривается отрезок до lastChangeDateDiapasonEnd
     * @param lastChangeDateDiapasonEnd Конец диапазона дат последнего изменения в проекты. Если не заполнено, то рассматривается отрезок с lastChangeDateDiapasonEnd
     * @param sortDirection Режим сортировки
     * @param sortBy Наименование поля, по которому осуществляется сортировка. Если сортировка не осуществляется, поле игнорируется. В ином случае должно быть не null
     * @return Список, содержащий сущности проекта или пустой в случае, если данные не были найдены
     * @throws CommonFlkException Ошибка при проверке ФЛК
     */
    List<Project> getProjectsByLastChangeDateDiapason(
            LocalDateTime lastChangeDateDiapasonStart,
            LocalDateTime lastChangeDateDiapasonEnd,
            @NonNull SortDirection sortDirection,
            String sortBy
    ) throws CommonFlkException;

    /**
     * Метод получения проектов по номеру версии
     * @param versionNumber Номер версии проектов
     * @param sortDirection Режим сортировки
     * @param sortBy Наименование поля, по которому осуществляется сортировка. Если сортировка не осуществляется, поле игнорируется. В ином случае должно быть не null
     * @return Список, содержащий сущности проекта или пустой в случае, если данные не были найдены
     * @throws CommonFlkException Ошибка при проверке ФЛК
     */
    List<Project> getProjectsByVersionNumber(
            @NonNull String versionNumber,
            @NonNull SortDirection sortDirection,
            String sortBy
    ) throws CommonFlkException;
}
