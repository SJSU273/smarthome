package smarthome;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;

/**
 * Created by Scott on 6/12/15.
 */
@RestController
public class ReportController {

    DataHouse2 dataHouse = new DataHouse2();

    @RequestMapping("/report")
    public LinkedList<InfoReport> report(@RequestBody InfoReport r) {

     //   if (r == null) return new LinkedList<InfoReport>(new InfoReport(0, "There is no any data in your request!"));

        //save to database
        dataHouse.storeData(r);
        return dataHouse.retrieveData(r.getId());

     }
}
