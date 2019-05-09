package ru.kibis.car.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.kibis.car.model.ad.Ad;
import ru.kibis.car.model.car.Manufacturer;
import ru.kibis.car.model.user.User;

import java.util.Date;
import java.util.List;
@Repository
public interface AdDataRepository extends CrudRepository<Ad, Integer> {
    List<Ad> findAll();

    Ad findById(int id);

    @Query("from Ad where id in ( select ad.id from Photo )")
    List<Ad> findAdsWithPhoto();

    @Query("from Ad where createDate = :today")
    List<Ad> findAdsAtLastDay(@Param("today") Date today);

    @Query("from Ad where car.brand = :brand")
    List<Ad> findAdsByBrand(@Param("brand") Manufacturer brand);

    List<Ad> findAllByUser(User user);
}
