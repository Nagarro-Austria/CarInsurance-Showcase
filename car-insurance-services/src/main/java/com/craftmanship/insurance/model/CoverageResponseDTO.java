package com.craftmanship.insurance.model;

import java.time.LocalDate;
import java.util.Objects;

public final class CoverageResponseDTO {
    private Long id;
    private LocalDate validFrom;
    private String description;

    public CoverageResponseDTO() {
    }

    public CoverageResponseDTO(Long id, LocalDate validFrom, String description) {
        this.id = id;
        this.validFrom = validFrom;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(LocalDate validFrom) {
        this.validFrom = validFrom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (CoverageResponseDTO) obj;
        return Objects.equals(this.id, that.id) &&
                Objects.equals(this.validFrom, that.validFrom) &&
                Objects.equals(this.description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, validFrom, description);
    }

    @Override
    public String toString() {
        return "CoverageResponseDTO[" +
                "id=" + id + ", " +
                "validFrom=" + validFrom + ", " +
                "description=" + description + ']';
    }

}
