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
import ru.kibis.car.model.ad.Photo;
import ru.kibis.car.persistence.PhotoStorage;
import ru.kibis.car.persistence.StorageWrapper;
import ru.kibis.car.service.ValidateServiceAd;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Iterator;
import java.util.List;

public class PhotoServlet extends HttpServlet {

    private final PhotoStorage photoStorage = PhotoStorage.getInstance();
    private final ValidateServiceAd validateService = ValidateServiceAd.getInstance();
    private static final Logger LOGGER = LogManager.getLogger(StorageWrapper.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int adId = Integer.valueOf(req.getParameter("ad_id"));
        List<String> photoIdArray = photoStorage.getImagesIdByAdId(adId);
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writeValueAsString(photoIdArray);
        resp.setContentType("text/json");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append(jsonInString);
        writer.flush();
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
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
            doGet(req, resp);
        }
    }
}

