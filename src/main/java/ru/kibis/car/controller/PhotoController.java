package ru.kibis.car.controller;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;
import ru.kibis.car.domain.Ad;
import ru.kibis.car.domain.Photo;
import ru.kibis.car.service.interfaces.AdService;
import ru.kibis.car.service.interfaces.PhotoService;

import java.io.IOException;
import java.util.List;

@Controller
public class PhotoController {
    private static final Logger LOGGER = LogManager.getLogger(PhotoController.class.getName());

    private final PhotoService photoService;
    private final AdService adService;

    @Autowired
    public PhotoController(PhotoService photoService, AdService adService) {
        this.photoService = photoService;
        this.adService = adService;
    }

    @GetMapping(value = "/photo_servlet", produces = "application/json")
    @ResponseBody
    public List findPhotosIdByAdId(@RequestParam("ad_id") int adId) {
        return this.photoService.getImagesIdByAdId(adId);
    }

    @PostMapping(value = "/photo_servlet", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @ResponseBody
    public RedirectView addPhoto(@RequestParam("file") MultipartFile[] files, @RequestParam("ad_id") int id) throws IOException {
        Ad ad = adService.findById(id);
        for (MultipartFile file : files) {
            byte[] bytes = file.getBytes();
            String photoName = file.getName();
            photoService.add(new Photo(bytes, photoName, ad));
        }
        return new RedirectView("index.html");
    }

    @GetMapping("/show_photo_servlet")
    public ResponseEntity<ByteArrayResource> findPhotoByAdId(@RequestParam("id") int id) {
        Photo photo = this.photoService.getById(id);
        String photoName = photo.getName();
        byte[] photoBytes = photo.getPhoto();
        ByteArrayResource resource = new ByteArrayResource(photoBytes);
        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=\"" + photoName + "\"")
                .contentType(MediaType.IMAGE_PNG)
                .contentLength(photoBytes.length)
                .body(resource);
    }
}