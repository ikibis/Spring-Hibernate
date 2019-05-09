package ru.kibis.car.controller;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.kibis.car.domain.Ad;
import ru.kibis.car.domain.Photo;
import ru.kibis.car.service.interfaces.AdService;
import ru.kibis.car.service.interfaces.PhotoService;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Iterator;
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

   /* @PostMapping(value = "/photo_servlet", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @ResponseBody
    public void addPhoto(@RequestParam("files") MultipartFile[] files,
                         @RequestParam("ad_id") int adId) throws IOException {
        try {
            System.out.println(files.length);
            int result = 0;
            Ad ad = adService.findById(adId);
            for (MultipartFile file : files) {
                byte[] bytes = file.getBytes();
                String photoName = file.getName();

                result += photoService.add(new Photo(bytes, photoName, ad));
           }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
        }
        if (result > 0) {
            resp.sendRedirect(String.format("%s/", req.getContextPath()));
        } else {
            req.setAttribute("error", "Cannot be uploaded");
            this.findPhotoByAdId(req, resp);
        }
    }*/

    @PostMapping(value ="/photo_servlet", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public void addPhoto(@RequestParam() MultipartFile[] files) throws IOException {

        //System.out.println(files);
        int result = 0;

            System.out.println(files[0].getBytes());



        /*if (ServletFileUpload.isMultipartContent(req)) {
            FileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            try {
                List<FileItem> items = upload.parseRequest(req);
                Iterator iterator = items.iterator();
                while (iterator.hasNext()) {
                    FileItem item = (FileItem) iterator.next();
                    if (!item.isFormField()) {
                        byte[] bytes = IOUtils.toByteArray(item.getInputStream());
                        String photoName = item.getName();
                        int adId = Integer.valueOf(item.getFieldName());
                        result = this.photoService.add(new Photo(bytes, photoName, this.adService.findById(adId)));
                    }
                }
            } catch (FileUploadException e) {
                LOGGER.error(e.getMessage());
                e.printStackTrace();
            }*/
        }
       /* if (result > 0) {
            resp.sendRedirect(String.format("%s/", req.getContextPath()));
        } else {
            req.setAttribute("error", "Cannot be uploaded");
            this.findPhotoByAdId(req, resp);
        }*/


/*
    @PostMapping("/photo_servlet")
    public void addPhoto(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println(req.getParameter("files"));
        System.out.println(resp);
        int result = 0;
        if (ServletFileUpload.isMultipartContent(req)) {
            FileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            try {
                List<FileItem> items = upload.parseRequest(req);
                Iterator iterator = items.iterator();
                while (iterator.hasNext()) {
                    FileItem item = (FileItem) iterator.next();
                    if (!item.isFormField()) {
                        byte[] bytes = IOUtils.toByteArray(item.getInputStream());
                        String photoName = item.getName();
                        int adId = Integer.valueOf(item.getFieldName());
                        result = this.photoService.add(new Photo(bytes, photoName, this.adService.findById(adId)));
                    }
                }
            } catch (FileUploadException e) {
                LOGGER.error(e.getMessage());
                e.printStackTrace();
            }
        }
        if (result > 0) {
            resp.sendRedirect(String.format("%s/", req.getContextPath()));
        } else {
            req.setAttribute("error", "Cannot be uploaded");
            this.findPhotoByAdId(req, resp);
        }
    }*/

    @GetMapping("/show_photo_servlet")
    public void findPhotoByAdId(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int photoId = Integer.valueOf(req.getParameter("id"));
        Photo photo = this.photoService.getById(photoId);
        String photoName = photo.getName();
        byte[] photoBytes = photo.getPhoto();
        ServletOutputStream outputStream = resp.getOutputStream();
        resp.setContentType("image/png");
        resp.setHeader("Content-Disposition", "attachment; filename=\"" + photoName + "\"");
        resp.setContentLength(photoBytes.length);
        outputStream.write(photoBytes);
        outputStream.flush();
        outputStream.close();
    }
}
