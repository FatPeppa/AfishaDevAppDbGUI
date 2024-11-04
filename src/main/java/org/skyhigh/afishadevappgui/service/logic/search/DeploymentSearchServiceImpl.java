package org.skyhigh.afishadevappgui.service.logic.search;

import lombok.AllArgsConstructor;
import org.skyhigh.afishadevappgui.common.service.ServiceUtils;
import org.skyhigh.afishadevappgui.common.sort.SortDirection;
import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.data.datasource.entity.Deployment;
import org.skyhigh.afishadevappgui.data.repository.DeploymentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
public class DeploymentSearchServiceImpl implements DeploymentSearchService {
    private final DeploymentRepository deploymentRepository;

    @Override
    public List<Deployment> searchDeployment(
            UUID deploymentId,
            UUID projectId,
            UUID deploymentStatusId,
            String builtVersion,
            String deploymentPath
    ) throws CommonFlkException {
        List<Object> args = new ArrayList<>();
        args.add(deploymentId);
        args.add(projectId);
        args.add(deploymentStatusId);
        args.add(builtVersion);
        args.add(deploymentPath);

        if (ServiceUtils.countNotNull(args) > 2)
            return null;
        if (deploymentStatusId != null && projectId != null)
            return deploymentRepository.getDeploymentsByStatusIdAndProjectId(
                    deploymentStatusId,
                    projectId,
                    SortDirection.NONE,
                    null
            );
        if (deploymentId != null) {
            ArrayList<Deployment> deployments = new ArrayList<>();
            deployments.add(deploymentRepository.getDeploymentById(deploymentId));
            return deployments;
        }
        if (projectId != null)
            return deploymentRepository.getDeploymentsByProjectId(
                    projectId,
                    SortDirection.NONE,
                    null
            );
        if (deploymentStatusId != null)
            return deploymentRepository.getDeploymentsByStatusId(
                    deploymentStatusId,
                    SortDirection.NONE,
                    null
            );
        if (builtVersion != null && !builtVersion.isEmpty())
            return deploymentRepository.getDeploymentsByBuiltVersion(
                    builtVersion,
                    SortDirection.NONE,
                    null
            );
        if (deploymentPath != null && !deploymentPath.isEmpty())
            return deploymentRepository.getDeploymentsByPath(
                    deploymentPath,
                    SortDirection.NONE,
                    null
            );
        return deploymentRepository.getAllDeployments(
                SortDirection.NONE,
                null
        );
    }
}
