package org.skyhigh.afishadevappgui.service.logic.search;

import lombok.AllArgsConstructor;
import org.skyhigh.afishadevappgui.common.service.ServiceUtils;
import org.skyhigh.afishadevappgui.common.sort.SortDirection;
import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.data.datasource.entity.Requirement;
import org.skyhigh.afishadevappgui.data.repository.RequirementRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
public class RequirementSearchServiceImpl implements RequirementSearchService {
    private final RequirementRepository requirementRepository;

    @Override
    public List<Requirement> searchRequirement(
            UUID requirementId,
            UUID requirementTypeId,
            LocalDateTime loadDateDiapasonStart,
            LocalDateTime loadDateDiapasonEnd,
            LocalDateTime lastChangeDateDiapasonStart,
            LocalDateTime lastChangeDateDiapasonEnd
    ) throws CommonFlkException {
        List<Object> args = new ArrayList<>();
        args.add(requirementId);
        args.add(requirementTypeId);
        args.add(loadDateDiapasonStart);
        args.add(loadDateDiapasonEnd);
        args.add(lastChangeDateDiapasonStart);
        args.add(lastChangeDateDiapasonEnd);

        if (ServiceUtils.countNotNull(args) > 2)
            return null;
        if (requirementId != null) {
            ArrayList<Requirement> requirements = new ArrayList<>();
            requirements.add(requirementRepository.getRequirementById(
                    requirementId
            ));
            return requirements;
        }
        if (requirementTypeId != null)
            return requirementRepository.getRequirementsByTypeId(
                    requirementTypeId,
                    SortDirection.NONE,
                    null
            );
        if ((loadDateDiapasonStart != null || loadDateDiapasonEnd != null) && (lastChangeDateDiapasonEnd != null || lastChangeDateDiapasonStart != null))
            return null;
        if (loadDateDiapasonStart != null || loadDateDiapasonEnd != null)
            return requirementRepository.getRequirementsByLoadDateDiapason(
                    loadDateDiapasonStart,
                    loadDateDiapasonEnd,
                    SortDirection.NONE,
                    null
            );
        if (lastChangeDateDiapasonStart != null || lastChangeDateDiapasonEnd != null)
            return requirementRepository.getRequirementsByLastChangeDateDiapason(
                    lastChangeDateDiapasonStart,
                    lastChangeDateDiapasonEnd,
                    SortDirection.NONE,
                    null
            );
        return requirementRepository.getAllRequirements(
                SortDirection.NONE,
                null
        );
    }
}
