package tv.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import tv.repository.TVChannelObject;
import tv.repository.TVChannelObjectRepository;
import tv.repository.TVControlObject;
import tv.repository.TVControlObjectRepository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by Scott on 7/28/15.
 */
@Component
public class AutoChangeChannel {

    @Autowired
    private TVControlObjectRepository tvControlObjectRepository;

    @Autowired
    private TVChannelObjectRepository tvChannelObjectRepository;

    @Autowired
    private ReportToServer report;

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    private boolean stop = true;

    public boolean isStop() {
        return stop;
    }

    public void setStop(boolean stop) {
        this.stop = stop;
    }

    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() {
        if (isStop()) {return;}

        for(TVControlObject o: tvControlObjectRepository.findAll()) {
            if (o.getLock() == 1) return;
            break;
        }


        System.out.println("The time is now " + dateFormat.format(new Date()));
        // change the TV Channel up
        for (TVControlObject l : tvControlObjectRepository.findAll()) {

            Random random = new Random();
            int i = random.nextInt(10);
            System.out.println("The channel is changed from " + l.getChannelId() + " to "+i);

            l.setChannelId(i);

            tvControlObjectRepository.save(l);

            report.notifyTvChannelObject("http://localhost:8081/notify/tv/channel");

            return;

        }
        System.out.println("Error: Can't find TV Control Object. Please check if TV has registered already.");

    }
}