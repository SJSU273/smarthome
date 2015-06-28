package LWM2MServer.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import LWM2MServer.models.IoTClient;

/**
 * Created by Scott on 6/24/15.
 */
public interface IoTClientRepository extends MongoRepository<IoTClient, String> {
    public IoTClient findByEndpointClientName(String s);

}
