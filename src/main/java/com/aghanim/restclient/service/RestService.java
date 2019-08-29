package com.aghanim.restclient.service;

import com.aghanim.restclient.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class RestService {

    @Autowired
    private RestTemplate restTemplate;

    private static final String REQUEST_URL = "http://localhost:8080/api/v1/users";

    public List<User> getUsers() {
        ResponseEntity<User[]> entity = restTemplate.getForEntity(REQUEST_URL, User[].class);
        return Arrays.asList(entity.getBody());
    }

    public User getUser(long id) {
        User user = restTemplate.getForObject(REQUEST_URL + "/{id}",
                User.class,
                Long.toString(id));
        return user;
    }

    public ResponseEntity<User> addUser(User newUser) {
        MultiValueMap<String, String> headers = new HttpHeaders();
        HttpEntity<User> entity = new HttpEntity<>(newUser, headers);
        return restTemplate.postForEntity(REQUEST_URL, entity, User.class);
    }

    public ResponseEntity<User> updateUser(User updatedUser) {
        return restTemplate.exchange(REQUEST_URL + "/{id}",
                HttpMethod.PUT,
                new HttpEntity<>(updatedUser),
                User.class,
                Long.toString(updatedUser.getId()));
    }

    public ResponseEntity<Void> deleteUser(long id) {
        return restTemplate.exchange(REQUEST_URL + "/{id}",
                HttpMethod.DELETE,
                null,
                Void.class,
                Long.toString(id));
    }
}
