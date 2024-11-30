package com.aldinalj.enterprise_project.api.service;

import com.aldinalj.enterprise_project.api.model.dto.ActivityDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ActivityApiService {

    private final WebClient webClient;

    @Autowired
    public ActivityApiService(WebClient webClient) {
        this.webClient = webClient;
    }

    public Flux<ActivityDTO> getActivities() {

        return webClient.get()
                .uri("/activities")
                .retrieve()
                .bodyToFlux(ActivityDTO.class);
    }

    public Mono<ActivityDTO> getActivityById(Long id) {

        return webClient.get()
                .uri("/activity/{id}", id)
                .retrieve()
                .bodyToMono(ActivityDTO.class);
    }

    public Flux<ActivityDTO> getActivitiesByCode(Long weatherCode) {

        return webClient.get()
                .uri("/activities/{code}", weatherCode)
                .retrieve()
                .bodyToFlux(ActivityDTO.class);
    }
}
