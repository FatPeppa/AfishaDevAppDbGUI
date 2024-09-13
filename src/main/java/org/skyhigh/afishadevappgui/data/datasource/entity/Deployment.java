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
}
