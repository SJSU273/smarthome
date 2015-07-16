package tv.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by Scott on 7/15/15.
 */
public interface LWM2MSecurityObjectRepository extends MongoRepository<LWM2MSecurityObject, String> {
    public List<LWM2MSecurityObject> findByLWM2MServerURI(String uri);
}
