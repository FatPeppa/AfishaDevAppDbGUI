package org.skyhigh.afishadevappgui.data.repository;

import lombok.NonNull;
import org.skyhigh.afishadevappgui.common.sort.SortDirection;
import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.data.datasource.entity.CodeFile;

import java.util.List;
import java.util.UUID;

public interface CodeFileRepository {
    /**
     * Метод сохранения файла CodeFile
     * @param codeFile Сохраняемая сущность CodeFile
     * @return Уникальный идентификатор, присвоенный созданной записи
     * @throws CommonFlkException Ошибка при проверке ФЛК
     */
    UUID saveCodeFile(@NonNull CodeFile codeFile) throws CommonFlkException;

    /**
     * Метод обновления содержимого файла
     * @param codeFileId Идентификатор файла
     * @param content Новое содержимое файла
     * @throws CommonFlkException Ошибка при проверке ФЛК
     */
    void updateCodeFileContentById(@NonNull UUID codeFileId, @NonNull String content) throws CommonFlkException;

    /**
     * Метод удаления файла по его идентификатору
     * @param codeFileId Идентификатор удаляемого файла
     * @throws CommonFlkException Ошибка при проверке ФЛК
     */
    void deleteCodeFileById(@NonNull UUID codeFileId) throws CommonFlkException;

    /**
     * Метод получения файла по идентификатору
     * @param codeFileId Идентификатор файла
     * @return Сущность типа CodeFile или null, если данные не были найдены
     * @throws CommonFlkException Ошибка при проверке ФЛК
     */
    CodeFile getCodeFileById(@NonNull UUID codeFileId) throws CommonFlkException;

    /**
     * Метод получения всех файлов в проекте
     * @param projectId Идентификатор проекта, файлы которого необходимо получить
     * @param sortDirection Режим сортировки
     * @param sortBy Наименование поля, по которому осуществляется сортировка. Если сортировка не осуществляется, поле игнорируется. В ином случае должно быть не null
     * @return Список, содержащий файлы проекта, или пустой список, если данные не были найдены
     * @throws CommonFlkException Ошибка при проверке ФЛК
     */
    List<CodeFile> getAllCodeFilesByProjectId(
            @NonNull UUID projectId,
            @NonNull SortDirection sortDirection,
            String sortBy
    ) throws CommonFlkException;

    /**
     * Метод получения всех файлов в Системе
     * @param sortDirection Режим сортировки
     * @param sortBy Наименование поля, по которому осуществляется сортировка. Если сортировка не осуществляется, поле игнорируется. В ином случае должно быть не null
     * @return Список, содержащий файлы, хранимые в Системе, или пустой список, если данные не были найдены
     * @throws CommonFlkException Ошибка при проверке ФЛК
     */
    List<CodeFile> getAllCodeFiles(@NonNull SortDirection sortDirection, String sortBy) throws CommonFlkException;
}
