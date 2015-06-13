package smarthome;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Scott on 6/12/15.
 */
@RestController
public class ReportController {
    @RequestMapping("/report")
    public InfoReport report() {
        return new InfoReport(100, "Hello");
    }
}
