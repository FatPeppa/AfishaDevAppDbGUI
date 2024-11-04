package org.skyhigh.afishadevappgui.service.logic.search;

import lombok.AllArgsConstructor;
import org.skyhigh.afishadevappgui.common.sort.SortDirection;
import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.data.datasource.entity.RequirementAuthor;
import org.skyhigh.afishadevappgui.data.repository.RequirementAuthorRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
public class RequirementAuthorSearchServiceImpl implements RequirementAuthorSearchService {
    private final RequirementAuthorRepository requirementAuthorRepository;

    @Override
    public List<RequirementAuthor> searchRequirementAuthor(UUID authorId, UUID requirementId) throws CommonFlkException {
        if (requirementId != null && authorId != null) {
            ArrayList<RequirementAuthor> requirementAuthors = new ArrayList<RequirementAuthor>();
            requirementAuthors.add(requirementAuthorRepository.getRequirementAuthorByIds(
                    requirementId, authorId
            ));
            return requirementAuthors;
        }
        if (requirementId != null)
            return requirementAuthorRepository.getRequirementAuthorsByRequirementId(
                    requirementId,
                    SortDirection.NONE,
                    null
            );
        if (authorId != null)
            return requirementAuthorRepository.getRequirementAuthorsByAuthorId(
                    authorId,
                    SortDirection.NONE,
                    null
            );
        return requirementAuthorRepository.getAllRequirementAuthors(
                SortDirection.NONE,
                null
        );
    }
}
