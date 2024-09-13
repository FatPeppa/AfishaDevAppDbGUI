package org.skyhigh.afishadevappgui.data.datasource.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.json.JSONObject;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Project {
    private UUID projectId;
    private String projectName;
    private LocalDateTime loadDate;
    private LocalDateTime lastChangeDate;
    private JSONObject structure;
    private JSONObject settings;
    private String versionNumber;
}
