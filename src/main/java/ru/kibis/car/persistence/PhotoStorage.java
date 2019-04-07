package ru.kibis.car.persistence;

import ru.kibis.car.model.ad.Photo;

import java.util.List;

public class PhotoStorage {
    private static final PhotoStorage INSTANCE = new PhotoStorage();
    private static final StorageWrapper WRAPPER = StorageWrapper.getINSTANCE();

    public static PhotoStorage getInstance() {
        return INSTANCE;
    }

    private PhotoStorage() {
    }

    public int add(Photo photo) {
        return WRAPPER.tx(session -> {
            session.save(photo);
            return photo.getId();
        });
    }

    public int update(Photo photo) {
        return WRAPPER.tx(session -> {
            session.update(photo);
            return photo.getId();
        });
    }

    public int delete(int id) {
        return WRAPPER.tx(session -> {
            Photo photo = session.get(Photo.class, id);
            session.delete(photo);
            return photo.getId();
        });
    }

    public List<Photo> getAll() {
        return WRAPPER.tx(session -> session.createQuery("from Photo ").list());
    }

    public Photo getById(int id) {
        return WRAPPER.tx(session -> session.get(Photo.class, id));
    }

    public Photo getByName(String name) {
        return null;
    }

    public List<String> getImagesIdByAdId(int id) {
        return WRAPPER.tx(session ->
                        session.createQuery("select id from Photo where ad.id = : adId")
                                .setParameter("adId", id).list()
                );
    }
}
