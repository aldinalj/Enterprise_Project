package com.aldinalj.enterprise_project.api.controller;

import com.aldinalj.enterprise_project.api.model.dto.ActivityDTO;
import com.aldinalj.enterprise_project.api.service.ActivityApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/activities")
public class ActivityController {

    ActivityApiService activityApiService;

    @Autowired
    public ActivityController(ActivityApiService activityApiService) {
        this.activityApiService = activityApiService;
    }

    @GetMapping("/all")
    public Flux<ActivityDTO> getActivities(Authentication authentication) {

        if (authentication != null && authentication.isAuthenticated()) {

            return activityApiService.getActivities();
        }

        return Flux.empty();
    }

    @GetMapping("/id/{id}")
    public Mono<ResponseEntity<ActivityDTO>> getActivity(@PathVariable Long id) {

        return activityApiService.getActivityById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/code/{weatherCode}")
    public Flux<ResponseEntity<ActivityDTO>> getActivitiesByWeatherCode(@PathVariable Long weatherCode) {

        return activityApiService.getActivitiesByCode(weatherCode)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}
