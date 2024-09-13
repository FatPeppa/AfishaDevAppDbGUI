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
    private UUID requirementType;
    private LocalDateTime loadDate;
    private LocalDateTime lastChangeDate;
    private JSONObject content;
}
