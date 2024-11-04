package org.skyhigh.afishadevappgui.service.logic.search;

import lombok.AllArgsConstructor;
import org.skyhigh.afishadevappgui.common.sort.SortDirection;
import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.data.datasource.entity.AccessedRole;
import org.skyhigh.afishadevappgui.data.repository.AccessedRoleRepository;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
public class AccessedRoleSearchServiceImpl implements AccessedRoleSearchService {
    private final AccessedRoleRepository accessedRoleRepository;

    @Override
    public List<AccessedRole> searchAccessedRoles(UUID requirementId, String roleName) throws CommonFlkException {
        if (requirementId != null && roleName != null && !roleName.isEmpty()) return List.of(
                accessedRoleRepository.getAccessedRoleByRequirementIdAndName(
                        requirementId,
                        roleName
                )
        );

        if (requirementId != null) return accessedRoleRepository.getAccessedRolesByRequirementId(
                requirementId,
                SortDirection.NONE,
                null
        );

        if (roleName != null && !roleName.isEmpty()) return accessedRoleRepository.getAccessedRolesByRoleName(
                roleName,
                SortDirection.NONE,
                null
        );

        return accessedRoleRepository.getAllAccessedRoles(SortDirection.NONE, null);
    }
}
