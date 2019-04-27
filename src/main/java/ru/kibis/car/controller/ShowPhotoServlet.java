package ru.kibis.car.controller;

import ru.kibis.car.model.ad.Photo;
import ru.kibis.car.persistence.PhotoStorage;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ShowPhotoServlet extends HttpServlet {
    private final PhotoStorage photoStorage = PhotoStorage.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
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
}