package org.skyhigh.afishadevappgui.service.logic.search;

import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.data.datasource.entity.CodeFile;

import java.util.List;
import java.util.UUID;

@FunctionalInterface
public interface CodeFileSearchService {
    /**
     * Метод поиска файлов с кодом. Допустимые комбинации: codeFileId / projectId / all null
     * @param codeFileId ID файла с кодом
     * @param projectId ID проекта
     * @return Список искомых значений
     * @throws CommonFlkException Ошибка при проверке ФЛК
     */
    List<CodeFile> searchCodeFiles(UUID codeFileId, UUID projectId) throws CommonFlkException;
}
