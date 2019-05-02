package ru.kibis.car.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import ru.kibis.car.model.ad.Photo;
import ru.kibis.car.repository.PhotoDataRepository;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PhotoStorage {
    private static final ApplicationContext CONTEXT = new ClassPathXmlApplicationContext("spring-context.xml");
    private static final PhotoDataRepository PHOTO_DATA_REPOSITORY = CONTEXT.getBean(PhotoDataRepository.class);

    @Autowired
    private PhotoStorage() {
    }

    public int add(Photo photo) {
        PHOTO_DATA_REPOSITORY.save(photo);
        return photo.getId();
    }

    public int delete(int id) {
        PHOTO_DATA_REPOSITORY.deleteById(id);
        return id;
    }

    public List<Photo> getAll() {
        return PHOTO_DATA_REPOSITORY.findAll();
    }

    public Photo getById(int id) {
        return PHOTO_DATA_REPOSITORY.findById(id);
    }

    public Photo getByName(String name) {
        return null;
    }

    public List<Integer> getImagesIdByAdId(int adId) {
        List<Photo> photos = PHOTO_DATA_REPOSITORY.findAllByAdId(adId);
        return photos.stream()
                .map(Photo::getId)
                .collect(Collectors.toList());
    }
}
