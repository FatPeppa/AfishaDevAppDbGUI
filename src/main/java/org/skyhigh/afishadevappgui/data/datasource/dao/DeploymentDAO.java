package org.skyhigh.afishadevappgui.data.datasource.dao;

import lombok.NonNull;
import org.json.JSONObject;
import org.skyhigh.afishadevappgui.common.sort.SortDirection;
import org.skyhigh.afishadevappgui.data.datasource.entity.Deployment;

import java.io.File;
import java.util.List;
import java.util.UUID;

public interface DeploymentDAO {
    UUID saveDeployment(@NonNull Deployment deployment);
    void deleteDeploymentById(@NonNull UUID deploymentId);
    void updateDeploymentStatusById(@NonNull UUID deploymentId, @NonNull UUID deploymentStatusId);
    void updateDeploymentBuiltVersionById(@NonNull UUID deploymentId, String builtVersion);
    void updateDeploymentBuiltSettingsById(@NonNull UUID deploymentId, JSONObject builtSettings);
    void updateDeploymentBuiltById(@NonNull UUID deploymentId, File built);
    void updateDeploymentProjectIdById(@NonNull UUID deploymentId, @NonNull UUID projectId);
    void updateDeploymentParamsById(@NonNull UUID deploymentId,
                                    @NonNull UUID deploymentStatusId,
                                    String deploymentPath,
                                    JSONObject settings,
                                    String builtVersion,
                                    JSONObject builtSettings,
                                    File built,
                                    @NonNull UUID projectId
    );
    Deployment getDeploymentById(@NonNull UUID deploymentId);
    List<Deployment> getDeploymentsByProjectId(@NonNull UUID projectId, @NonNull SortDirection sortDirection);
    List<Deployment> getDeploymentsByStatusId(@NonNull UUID deploymentStatusId, @NonNull SortDirection sortDirection);
    List<Deployment> getAllDeployments(@NonNull SortDirection sortDirection);
    List<Deployment> getDeploymentsByBuiltVersion(String builtVersion, @NonNull SortDirection sortDirection);
    List<Deployment> getDeploymentsByPath(String deploymentPath, @NonNull SortDirection sortDirection);
    List<Deployment> getDeploymentsByStatusIdAndProjectId(
            @NonNull UUID deploymentStatusId,
            @NonNull UUID projectId,
            @NonNull SortDirection sortDirection
    );
}
