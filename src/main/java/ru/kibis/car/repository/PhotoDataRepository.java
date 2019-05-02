package ru.kibis.car.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.kibis.car.model.ad.Photo;

import java.util.List;

@Repository
public interface PhotoDataRepository extends CrudRepository<Photo, Integer> {
    List<Photo> findAll();

    Photo findById(int id);

    List<Photo> findAllByAdId(int id);
}
