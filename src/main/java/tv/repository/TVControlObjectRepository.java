package tv.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import javax.validation.constraints.DecimalMin;
import java.util.List;

/**
 * Created by Scott on 7/15/15.
 */
public interface TVControlObjectRepository extends MongoRepository<TVControlObject, String> {
    // not find any query method needed except findAll()...
}
