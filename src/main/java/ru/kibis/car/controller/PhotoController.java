package ru.kibis.car.controller;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.kibis.car.model.ad.Photo;
import ru.kibis.car.persistence.PhotoStorage;
import ru.kibis.car.persistence.StorageWrapper;
import ru.kibis.car.service.ValidateServiceAd;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

@Controller
public class PhotoController {
    private final PhotoStorage photoStorage = PhotoStorage.getInstance();
    private final ValidateServiceAd validateService = ValidateServiceAd.getInstance();
    private static final Logger LOGGER = LogManager.getLogger(StorageWrapper.class.getName());

    @RequestMapping(value = "/photo_servlet", method = RequestMethod.GET)
    public void findPhotosIdByAdId(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int adId = Integer.valueOf(req.getParameter("ad_id"));
        List<String> photoIdArray = photoStorage.getImagesIdByAdId(adId);
        this.send(photoIdArray, resp);
    }

    @RequestMapping(value = "/photo_servlet", method = RequestMethod.POST)
    public void addPhoto(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int result = 0;
        if (ServletFileUpload.isMultipartContent(req)) {
            FileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            try {
                List<FileItem> items = upload.parseRequest(req);
                Iterator iterator = items.iterator();
                while (iterator.hasNext()) {
                    FileItem item = (FileItem) iterator.next();
                    System.out.println(item);
                    if (!item.isFormField()) {
                        byte[] bytes = IOUtils.toByteArray(item.getInputStream());
                        String photoName = item.getName();
                        int adId = Integer.valueOf(item.getFieldName());
                        result = photoStorage.add(new Photo(bytes, photoName, validateService.findById(adId)));
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
    }

    @RequestMapping(value = "/show_photo_servlet", method = RequestMethod.GET)
    public void findPhotoByAdId(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int photoId = Integer.valueOf(req.getParameter("id"));
        Photo photo = photoStorage.getById(photoId);
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

    private void send(Object object, HttpServletResponse resp) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writeValueAsString(object);
        resp.setContentType("text/json");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append(jsonInString);
        writer.flush();
    }
}
