package tv.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by Scott on 7/15/15.
 */
public interface TVChannelObjectRepository extends MongoRepository<TVChannelObject, String> {
    public List<TVChannelObject> findBychannelID(int id);
    public List<TVChannelObject> findBychannelName(String name);
}
