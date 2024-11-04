package org.skyhigh.afishadevappgui.service.logic.search;

import lombok.AllArgsConstructor;
import org.skyhigh.afishadevappgui.common.sort.SortDirection;
import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.data.datasource.entity.RequirementType;
import org.skyhigh.afishadevappgui.data.repository.RequirementTypeRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
public class RequirementTypeSearchServiceImpl implements RequirementTypeSearchService {
    private final RequirementTypeRepository requirementTypeRepository;

    @Override
    public List<RequirementType> searchRequirementType(UUID requirementTypeId, String requirementTypeName) throws CommonFlkException {
        if (requirementTypeId != null && requirementTypeName != null && !requirementTypeName.isEmpty())
            return null;
        if (requirementTypeId != null) {
            ArrayList<RequirementType> requirementTypes = new ArrayList<>();
            requirementTypes.add(requirementTypeRepository.getRequirementTypeById(
                    requirementTypeId
            ));
            return requirementTypes;
        }
        if (requirementTypeName != null && !requirementTypeName.isEmpty()) {
            ArrayList<RequirementType> requirementTypes = new ArrayList<>();
            requirementTypes.add(requirementTypeRepository.getRequirementTypeByName(
                    requirementTypeName
            ));
            return requirementTypes;
        }
        return requirementTypeRepository.getAllRequirementTypes(
                SortDirection.NONE,
                null
        );
    }
}
