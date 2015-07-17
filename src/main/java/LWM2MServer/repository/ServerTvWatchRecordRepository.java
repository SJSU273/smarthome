package LWM2MServer.repository;

import LWM2MServer.models.ServerTvWatchRecord;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by Scott on 7/17/15.
 */
public interface ServerTvWatchRecordRepository extends MongoRepository<ServerTvWatchRecord, String> {

    List<ServerTvWatchRecord> findByEPN(String epn);
}
