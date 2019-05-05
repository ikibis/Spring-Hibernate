package ru.kibis.car.service.interfaces;

import ru.kibis.car.domain.Photo;

import java.util.List;

public interface PhotoService {

    public int add(Photo photo);

    public int deleteById(int id);

    public List<Photo> getAll();

    public Photo getById(int id);

    public List<Integer> getImagesIdByAdId(int adId);
}
