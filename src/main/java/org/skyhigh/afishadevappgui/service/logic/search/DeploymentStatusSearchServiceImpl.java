package org.skyhigh.afishadevappgui.service.logic.search;

import lombok.AllArgsConstructor;
import org.skyhigh.afishadevappgui.common.sort.SortDirection;
import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.data.datasource.entity.DeploymentStatus;
import org.skyhigh.afishadevappgui.data.repository.DeploymentStatusRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
public class DeploymentStatusSearchServiceImpl implements DeploymentStatusSearchService {
    private final DeploymentStatusRepository deploymentStatusRepository;

    @Override
    public List<DeploymentStatus> searchDeploymentStatus(UUID deploymentStatusId, String deploymentStatusName) throws CommonFlkException {
        if (deploymentStatusId != null && deploymentStatusName != null && !deploymentStatusName.isEmpty())
            return null;
        if (deploymentStatusId != null) {
            ArrayList<DeploymentStatus> deploymentStatusList = new ArrayList<>();
            deploymentStatusList.add(deploymentStatusRepository.getDeploymentStatusById(
                    deploymentStatusId
            ));
            return deploymentStatusList;
        }
        if (deploymentStatusName != null && !deploymentStatusName.isEmpty()) {
            ArrayList<DeploymentStatus> deploymentStatusList = new ArrayList<>();
            deploymentStatusList.add(deploymentStatusRepository.getDeploymentStatusByName(
                    deploymentStatusName
            ));
            return deploymentStatusList;
        }
        return deploymentStatusRepository.getAllDeploymentStatuses(
                SortDirection.NONE,
                null
        );
    }
}
