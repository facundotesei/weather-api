package challenge.redbee;

import challenge.redbee.services.LocacionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);
    private LocacionService locacionService;

    public ScheduledTasks(LocacionService locacionService) {
        this.locacionService = locacionService;
    }

    @Scheduled(fixedRate = 20000)
    public void pollingWeatherService() {
        log.info(" || ====================== STARTING POLLING SERVICE. ====================== || \n");
        locacionService.updateLocaciones();
        log.info(" || ====================== POLLING SERVICE FINISHED. ====================== || \n\n\n\n");
    }
}

