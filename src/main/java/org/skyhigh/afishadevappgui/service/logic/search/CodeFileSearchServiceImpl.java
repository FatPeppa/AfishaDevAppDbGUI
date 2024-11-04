package org.skyhigh.afishadevappgui.service.logic.search;

import lombok.AllArgsConstructor;
import org.skyhigh.afishadevappgui.common.sort.SortDirection;
import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.data.datasource.entity.CodeFile;
import org.skyhigh.afishadevappgui.data.repository.CodeFileRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
public class CodeFileSearchServiceImpl implements CodeFileSearchService {
    private final CodeFileRepository codeFileRepository;

    @Override
    public List<CodeFile> searchCodeFiles(UUID codeFileId, UUID projectId) throws CommonFlkException {
        if (codeFileId != null && projectId != null)
            return null;
        if (codeFileId != null) {
            ArrayList<CodeFile> codeFiles = new ArrayList<>();
            codeFiles.add(codeFileRepository.getCodeFileById(codeFileId));
            return codeFiles;
        }
        if (projectId != null)
            return codeFileRepository.getAllCodeFilesByProjectId(projectId, SortDirection.NONE, null);
        return codeFileRepository.getAllCodeFiles(SortDirection.NONE, null);
    }
}
