package smarthome;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Scott on 6/12/15.
 */
@RestController
public class ReportController {

    DataHouse dataHouse = new DataHouse();

    @RequestMapping("/report")
    public InfoReport report(@RequestBody InfoReport r) {

        if (r == null) return new InfoReport(0, "There is no any data in your request!");

        //save to database
        if (dataHouse.storeData(r)) {
            //retrieve from database
            return dataHouse.retrieveData(r.getId());
        } else {
            return new InfoReport(0, "There is some error in database!");
        }
     }
}
