package org.skyhigh.afishadevappgui.service.logic.search;

import lombok.AllArgsConstructor;
import org.skyhigh.afishadevappgui.common.service.ServiceUtils;
import org.skyhigh.afishadevappgui.common.sort.SortDirection;
import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.data.datasource.entity.Secret;
import org.skyhigh.afishadevappgui.data.repository.SecretRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
public class SecretSearchServiceImpl implements SecretSearchService {
    private final SecretRepository secretRepository;

    @Override
    public List<Secret> searchSecret(
            UUID secretId,
            UUID deploymentId,
            String address,
            String login
    ) throws CommonFlkException {
        List<Object> args = new ArrayList<>();
        args.add(secretId);
        args.add(deploymentId);
        args.add(address);
        args.add(login);

        if (ServiceUtils.countNotNull(args) > 1)
            return null;
        if (secretId != null) {
            ArrayList<Secret> secrets = new ArrayList<>();
            secrets.add(secretRepository.getSecretById(
                    secretId
            ));
            return secrets;
        }
        if (deploymentId != null)
            return secretRepository.getSecretsByDeploymentId(
                    deploymentId,
                    SortDirection.NONE,
                    null
            );
        if (address != null && !address.isEmpty())
            return secretRepository.getSecretsByAddress(
                    address,
                    SortDirection.NONE,
                    null
            );
        if (login != null && !login.isEmpty())
            return secretRepository.getSecretsByLogin(
                    login,
                    SortDirection.NONE,
                    null
            );
        return secretRepository.getAllSecret(
                SortDirection.NONE,
                null
        );
    }
}
