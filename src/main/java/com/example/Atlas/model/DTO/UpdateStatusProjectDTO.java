package com.example.Atlas.model.DTO;

import com.example.Atlas.model.enums.ProjectStatus;
import com.fasterxml.jackson.annotation.JsonProperty;

public record UpdateStatusProjectDTO(@JsonProperty("status") ProjectStatus projectStatus) {
}
