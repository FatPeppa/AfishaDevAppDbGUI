package org.skyhigh.afishadevappgui.service.logic.deploy;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.skyhigh.afishadevappgui.common.sort.SortDirection;
import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.common.validation.CommonSystemException;
import org.skyhigh.afishadevappgui.data.datasource.entity.Deployment;
import org.skyhigh.afishadevappgui.data.datasource.entity.Secret;
import org.skyhigh.afishadevappgui.data.repository.DeploymentRepository;
import org.skyhigh.afishadevappgui.data.repository.SecretRepository;
import org.skyhigh.afishadevappgui.data.validation.entity.inserting.files.Flk10020000Validator;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
public class DeployServiceImpl implements DeployService {
    DeploymentRepository deploymentRepository;
    SecretRepository secretRepository;

    @Override
    public boolean checkIfTheBuiltIsDeployed(@NonNull UUID deploymentId, @NonNull String address) throws CommonFlkException {
        try {
            Deployment deployment = deploymentRepository.getDeploymentById(deploymentId);
            if (deployment == null)
                throw new CommonSystemException(
                        "Произошла системная ошибка. Текст ошибки: 'Запись развертывания с указанным ID не найдена'",
                        false
                );
            if (!checkIfTheDeploymentIsAbleToBeDeployed(deployment))
                return false;
            List<Secret> secrets = secretRepository.getSecretsByDeploymentId(deploymentId, SortDirection.NONE, null);
            if (secrets == null || secrets.isEmpty() )
                return false;
            else
                return secrets.stream().anyMatch(secret -> secret.getAddress().equals(address));
        } catch (CommonFlkException e) {
            throw new CommonSystemException(
                    "Произошла системная ошибка. Текст ошибки: " + e.getMessage(),
                    e.getCause(),
                    false
            );
        }
    }

    @Override
    public boolean checkIfTheDeploymentIsAbleToBeDeployed(@NonNull UUID deploymentId) throws CommonFlkException {
        Deployment deployment = deploymentRepository.getDeploymentById(deploymentId);
        if (deployment == null)
            throw new CommonSystemException(
                    "Произошла системная ошибка. Текст ошибки: Запись развертывания не найдена",
                    false
            );
        return checkIfTheDeploymentIsAbleToBeDeployed(deployment);
    }

    @Override
    public boolean checkIfTheDeploymentIsAbleToBeDeployed(@NonNull Deployment deployment) throws CommonFlkException {
        if (deployment.getDeploymentPath() == null
                || deployment.getDeploymentPath().isEmpty()
                || deployment.getBuilt() == null
                || deployment.getBuiltSettings() == null
                || deployment.getBuiltSettings().isEmpty()
                || deployment.getBuiltVersion() == null
                || deployment.getBuiltVersion().isEmpty()
                || deployment.getSettings() == null
                || deployment.getSettings().isEmpty()) {
            Flk10020000Validator.validate(deployment);
            return false;
        }
        return true;
    }
}
