package org.skyhigh.afishadevappgui.data.datasource.dao;

import lombok.NonNull;
import org.skyhigh.afishadevappgui.common.sort.SortDirection;
import org.skyhigh.afishadevappgui.data.datasource.entity.CodeFile;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

/**
 * DAO для работы с сущностью CodeFile
 */
public interface CodeFileDAO {
    /**
     * Метод сохранения файла CodeFile
     * @param codeFile Сохраняемая сущность CodeFile
     * @return Уникальный идентификатор, присвоенный созданной записи
     * @throws SQLException Ошибка при работе с БД
     */
    UUID saveCodeFile(@NonNull CodeFile codeFile) throws SQLException;

    /**
     * Метод обновления содержимого файла
     * @param codeFileId Идентификатор файла
     * @param content Новое содержимое файла
     * @throws SQLException Ошибка при работе с БД
     */
    void updateCodeFileContentById(@NonNull UUID codeFileId, @NonNull String content) throws SQLException;

    /**
     * Метод обновления проекта файла (перенос файла из одного проекта в другой)
     * @param codeFileId Идентификатор файла
     * @param projectId Идентификатор целевого проекта, к которому прикрепляется файл
     * @throws SQLException Ошибка при работе с БД
     */
    void updateCodeFileProjectId(@NonNull UUID codeFileId, @NonNull UUID projectId) throws SQLException;

    /**
     * Метод удаления файла по его идентификатору
     * @param codeFileId Идентификатор удаляемого файла
     * @throws SQLException Ошибка при работе с БД
     */
    void deleteCodeFileById(@NonNull UUID codeFileId) throws SQLException;

    /**
     * Метод получения файла по идентфиикатору
     * @param codeFileId Идентификатор файла
     * @return Сущность типа CodeFile или null, если данные не были найдены
     * @throws SQLException Ошибка при работе с БД
     */
    CodeFile getCodeFileById(@NonNull UUID codeFileId) throws SQLException;

    /**
     * Метод получения всех файлов в проекте
     * @param projectId Идентификатор проекта, файлы которого необходимо получить
     * @param sortDirection Режим сортировки
     * @param sortBy Наименование поля, по которому осуществляется сортировка. Если сортировка не осуществляется, поле игнорируется. В ином случае должно быть не null
     * @return Список, содержащий файлы проекта, или пустой список, если данные не были найдены
     * @throws SQLException Ошибка при работе с БД
     */
    List<CodeFile> getAllCodeFilesByProjectId(@NonNull UUID projectId, @NonNull SortDirection sortDirection, String sortBy) throws SQLException;

    /**
     * Метод получения всех файлов в Системе
     * @param sortDirection Режим сортировки
     * @param sortBy Наименование поля, по которому осуществляется сортировка. Если сортировка не осуществляется, поле игнорируется. В ином случае должно быть не null
     * @return Список, содержащий файлы, хранимые в Системе, или пустой список, если данные не были найдены
     * @throws SQLException Ошибка при работе с БД
     */
    List<CodeFile> getAllCodeFiles(@NonNull SortDirection sortDirection, String sortBy) throws SQLException;
}
