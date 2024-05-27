package com.Client;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

public class ClientApplication {
    private static final String BASE_URL = "http://localhost:3000/books";

    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();

        // Get all books
        ResponseEntity<String> response = restTemplate.getForEntity(BASE_URL, String.class);
        System.out.println("Get all books: " + response.getBody());

        // Create a new book
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String bookJson = "{\"name\":\"New Book\",\"title\":\"Book Title\",\"language\":\"English\",\"pages\":200}";
        HttpEntity<String> request = new HttpEntity<>(bookJson, headers);
        ResponseEntity<String> postResponse = restTemplate.postForEntity(BASE_URL, request, String.class);
        System.out.println("Create a new book: " + postResponse.getBody());

        // Update a book's name
        String updateUrl = BASE_URL + "/1";
        String updateJson = "\"Updated Book Name\"";
        HttpEntity<String> updateRequest = new HttpEntity<>(updateJson, headers);
        ResponseEntity putResponse = restTemplate.exchange(updateUrl, HttpMethod.PUT, updateRequest, String.class);
        System.out.println("Update a book's name: " + putResponse.getBody());


        // Delete a book
        String deleteUrl = BASE_URL + "/1";
        restTemplate.delete(deleteUrl);
        System.out.println("Delete a book");
    }
}