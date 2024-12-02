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
public class Requirement {
    private UUID requirementId;
    private UUID requirementTypeId;
    private LocalDateTime loadDate;
    private LocalDateTime lastChangeDate;
    private JSONObject content;

    private String requirementTypeName;

    public Requirement(UUID requirementId, UUID requirementTypeId, LocalDateTime loadDate, LocalDateTime lastChangeDate, JSONObject content) {
        this.requirementId = requirementId;
        this.requirementTypeId = requirementTypeId;
        this.loadDate = loadDate;
        this.lastChangeDate = lastChangeDate;
        this.content = content;
    }
}
