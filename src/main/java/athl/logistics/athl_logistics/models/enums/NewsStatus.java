package athl.logistics.athl_logistics.models.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

// Même convention que ProjectStatus/ServiceStatus : stocké en majuscules, exposé en JSON en minuscules.
public enum NewsStatus {
    DRAFT,
    PUBLISHED;

    @JsonValue
    public String toJson() {
        return name().toLowerCase();
    }

    @JsonCreator
    public static NewsStatus fromJson(String value) {
        return NewsStatus.valueOf(value.toUpperCase());
    }
}
