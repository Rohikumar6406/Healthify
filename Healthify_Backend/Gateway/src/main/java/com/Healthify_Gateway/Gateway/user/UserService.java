package com.Healthify_Gateway.Gateway.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final WebClient userServiceWebClient;

    public Mono<Boolean> validateUser(String userId) {
        log.info("Calling User Service for {}", userId);

//            Boolean result = userServiceWebClient.get()
//                    .uri("/api/users/{userId}/validate", userId)
//                    .retrieve()
//                    .onStatus(status -> status.value() == 404,
//                            response -> {
//                                log.warn("User {} not found during validation", userId);
//                                return response.createException();
//                            })
//                    .onStatus(status -> status.is5xxServerError(),
//                            response -> {
//                                log.error("User service returned 5xx while validating {}", userId);
//                                return response.createException();
//                            })
//                    .bodyToMono(Boolean.class)
//                    .block();
//
//            log.info("User {} validation result: {}", userId, result);
//            return Boolean.TRUE.equals(result);
        return userServiceWebClient.get()
                .uri("/api/users/{userId}/validate", userId)
                .retrieve()
                .bodyToMono(Boolean.class)
                .onErrorResume(WebClientResponseException.class, e -> {

                    if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                        return Mono.error(new RuntimeException("User not found: " + userId));
                    }

                    if (e.getStatusCode() == HttpStatus.BAD_REQUEST) {
                        return Mono.error(new RuntimeException("Unexpected error : " + userId));

                    }
                    return Mono.error(new RuntimeException("Unhandled error: " + e.getStatusCode()));
                });

    }

    public Mono<UserResponse> registerUser(RegisterRequest registerRequest) {
        log.info("Calling User Registration for {}", registerRequest.getEmail());
        return userServiceWebClient.post()
                .uri("/api/users/register")
                .bodyValue(registerRequest)
                .retrieve()
                .bodyToMono(UserResponse.class)
                .onErrorResume(WebClientResponseException.class, e -> {

                    if (e.getStatusCode() == HttpStatus.BAD_REQUEST) {
                        return Mono.error(new RuntimeException("Bad request : " + e.getMessage()));

                    }
                    return Mono.error(new RuntimeException("Unexpected error: " + e.getMessage()));
                });
          }

        }

