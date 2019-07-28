package nz.ac.auckland.fooservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class FooService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FooService.class);

    public String foo() {
        LOGGER.info("FooService#foo");
        return "foo @ " + System.currentTimeMillis();
    }
}
