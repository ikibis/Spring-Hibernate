package ru.kibis.car.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kibis.car.domain.Photo;
import ru.kibis.car.repository.PhotoRepository;
import ru.kibis.car.service.interfaces.PhotoService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PhotoServiceImpl implements PhotoService {

    private final PhotoRepository photoRepository;

    @Autowired
    public PhotoServiceImpl(PhotoRepository photoRepository) {
        this.photoRepository = photoRepository;
    }

    @Override
    public int add(Photo photo) {
        this.photoRepository.save(photo);
        return photo.getId();
    }

    @Override
    public int deleteById(int id) {
        this.photoRepository.deleteById(id);
        return id;
    }

    @Override
    public Photo getById(int id) {
        return this.photoRepository.findById(id);
    }

    @Override
    public List<Integer> getImagesIdByAdId(int adId) {
        List<Photo> photos = this.photoRepository.findAllByAdId(adId);
        return photos.stream()
                .map(Photo::getId)
                .collect(Collectors.toList());
    }
}