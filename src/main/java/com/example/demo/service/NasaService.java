package com.example.demo.service;

import com.example.demo.dto.Photo;
import com.example.demo.dto.Photos;
import com.example.demo.dto.Picture;
import com.example.demo.dto.Response;
import com.example.demo.dto.User;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NasaService {
    private final String url = "https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos";
    private final RestTemplate restTemplate;

    @SneakyThrows
    public Response getPicture(String sol, String camera) {
        Photos photos = restTemplate.getForObject(url.concat("?sol=" + sol + "&api_key=DEMO_KEY"), Photos.class);

        return photos.getPhotos()
            .stream()
            .filter(photo -> photo.getCamera().getName().equalsIgnoreCase(camera))
            .peek(photo -> photo.setSize(getImageSize(photo)))
            .max(Comparator.comparing(Photo::getSize))
            .map(photo -> Response.builder()
                .sol(Long.parseLong(sol))
                .user(new User("Valerii", "Siestov"))
                .camera(photo.getCamera().getName())
                .picture(new Picture(photo.getImg_src(), photo.getSize()))
                .build())
            .orElseThrow();
    }

    private long getImageSize(Photo photo) {
        HttpHeaders httpHeaders = restTemplate.headForHeaders(photo.getImg_src());

        return restTemplate.headForHeaders(httpHeaders.getLocation()).getContentLength();
    }
}
