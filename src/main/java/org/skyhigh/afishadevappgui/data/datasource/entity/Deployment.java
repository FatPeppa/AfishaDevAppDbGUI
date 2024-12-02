package org.skyhigh.afishadevappgui.data.datasource.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.json.JSONObject;

import java.io.File;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Deployment {
    private UUID deploymentId;
    private UUID deploymentStatusId;
    private String deploymentPath;
    private JSONObject settings;
    private String builtVersion;
    private JSONObject builtSettings;
    private File built;
    private UUID projectId;

    private String deploymentStatusName;
    private String projectName;

    public Deployment(UUID deploymentId, UUID deploymentStatusId, String deploymentPath, JSONObject settings, String builtVersion, JSONObject builtSettings, File built, UUID projectId) {
        this.deploymentId = deploymentId;
        this.deploymentStatusId = deploymentStatusId;
        this.deploymentPath = deploymentPath;
        this.settings = settings;
        this.builtVersion = builtVersion;
        this.builtSettings = builtSettings;
        this.projectId = projectId;
        this.built = built;
    }
}
