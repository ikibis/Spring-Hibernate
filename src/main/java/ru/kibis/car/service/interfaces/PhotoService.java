package ru.kibis.car.service.interfaces;

import ru.kibis.car.domain.Photo;

import java.util.List;

public interface PhotoService {

    int add(Photo photo);

    int deleteById(int id);

    Photo getById(int id);

    List<Integer> getImagesIdByAdId(int adId);
}
