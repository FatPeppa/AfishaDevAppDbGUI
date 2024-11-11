package org.skyhigh.afishadevappgui.service.logic.deploy;

import lombok.NonNull;
import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.data.datasource.entity.Deployment;

import java.util.UUID;

public interface DeployService {
    boolean checkIfTheBuiltIsDeployed(
            @NonNull UUID deploymentId,
            @NonNull String address
    ) throws CommonFlkException;

    boolean checkIfTheDeploymentIsAbleToBeDeployed(@NonNull UUID deploymentId) throws CommonFlkException;

    boolean checkIfTheDeploymentIsAbleToBeDeployed(@NonNull Deployment deployment) throws CommonFlkException;
}
