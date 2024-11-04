package org.skyhigh.afishadevappgui.service.logic.search;

import lombok.AllArgsConstructor;
import org.skyhigh.afishadevappgui.common.sort.SortDirection;
import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.data.datasource.entity.ProjectAuthor;
import org.skyhigh.afishadevappgui.data.repository.ProjectAuthorRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
public class ProjectAuthorSearchServiceImpl implements ProjectAuthorSearchService {
    private final ProjectAuthorRepository projectAuthorRepository;

    @Override
    public List<ProjectAuthor> searchProjectAuthor(UUID authorId, UUID projectId) throws CommonFlkException {
        if (authorId != null && projectId != null) {
            ArrayList<ProjectAuthor> projectAuthors = new ArrayList<>();
            projectAuthors.add(projectAuthorRepository.getProjectAuthorByIds(projectId, authorId));
            return projectAuthors;
        }
        if (authorId != null)
            return projectAuthorRepository.getProjectAuthorsByAuthorId(
                    authorId,
                    SortDirection.NONE,
                    null
            );
        if (projectId != null)
            return projectAuthorRepository.getProjectAuthorsByProjectId(
                    projectId,
                    SortDirection.NONE,
                    null
            );
        return projectAuthorRepository.getAllProjectAuthors(
                SortDirection.NONE,
                null
        );
    }
}
