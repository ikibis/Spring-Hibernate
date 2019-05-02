package ru.kibis.car.repository;

import org.springframework.data.repository.CrudRepository;
import ru.kibis.car.model.car.Engine;

public interface EngineDataRepository extends CrudRepository<Engine, Integer> {
}
