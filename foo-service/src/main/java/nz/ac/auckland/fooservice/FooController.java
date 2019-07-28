package nz.ac.auckland.fooservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FooController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FooController.class);
    private final FooService fooService;

    public FooController(FooService fooService) {
        this.fooService = fooService;
    }

    @GetMapping
    public String foo(){
        LOGGER.info("FooController#foo received request");
        return fooService.foo();
    }
}
