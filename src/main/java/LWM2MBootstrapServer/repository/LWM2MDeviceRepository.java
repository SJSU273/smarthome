package LWM2MBootstrapServer.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import tv.repository.LWM2MServerObject;

/**
 * Created by Scott on 6/26/15.
 */
public interface LWM2MDeviceRepository extends MongoRepository<LWM2MDevice, String> {
    public LWM2MDevice findById(String id);
}
