package com.aldinalj.enterprise_project.api.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;

public class ActivityDTO {

    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    @NotNull
    @JsonProperty("weather_code")
    private Integer weatherCode;

    @Size(max = 1000, message = "Description can only be a maximum of 1000 characters")
    private String description;

    @JsonProperty("temp_min")
    @Min(value = -100, message = "Minimum temperature cannot be less than -100°C")
    @Max(value = 50, message = "Minimum temperature cannot exceed 50°C")
    private Integer temperatureMin;

    @JsonProperty("temp_max")
    @Min(value = -100, message = "Maximum temperature cannot be less than -100°C")
    @Max(value = 50, message = "Maximum temperature cannot exceed 50°C")
    private Integer temperatureMax;

    @JsonProperty("price_min")
    @Min(value = 0, message = "Minimum price cannot be less than 0kr")
    @Max(value = 5000, message = "Minimum price cannot be more than 5000kr")
    private Integer priceMin;

    @JsonProperty("price_max")
    @Min(value = 0, message = "Maximum price cannot be less than 0kr")
    @Max(value = 5000, message = "Maximum price cannot be more than 5000kr")
    private Integer priceMax;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getWeatherCode() {
        return weatherCode;
    }

    public void setWeatherCode(Integer weatherCode) {
        this.weatherCode = weatherCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getTemperatureMin() {
        return temperatureMin;
    }

    public void setTemperatureMin(Integer temperatureMin) {
        this.temperatureMin = temperatureMin;
    }

    public Integer getTemperatureMax() {
        return temperatureMax;
    }

    public void setTemperatureMax(Integer temperatureMax) {
        this.temperatureMax = temperatureMax;
    }

    public Integer getPriceMin() {
        return priceMin;
    }

    public void setPriceMin(Integer priceMin) {
        this.priceMin = priceMin;
    }

    public Integer getPriceMax() {
        return priceMax;
    }

    public void setPriceMax(Integer priceMax) {
        this.priceMax = priceMax;
    }

}
