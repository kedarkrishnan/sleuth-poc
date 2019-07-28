package nz.ac.auckland.barservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BarController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BarController.class);
    private final BarService barService;

    public BarController(BarService barService) {
        this.barService = barService;
    }

    @GetMapping
    public String bar(){
        LOGGER.info("BarController#bar");
        String message = barService.getFooMessage();

        return "bar @ " + System.currentTimeMillis() + " " + message;
    }
}
