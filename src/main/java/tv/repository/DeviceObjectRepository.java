package tv.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by Scott on 7/15/15.
 */
public interface DeviceObjectRepository extends MongoRepository<DeviceObject, String>{
    public List<DeviceObject> findByManufacturer(String manufactureName);
}
