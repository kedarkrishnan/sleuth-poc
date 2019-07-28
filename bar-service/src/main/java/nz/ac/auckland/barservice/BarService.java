package nz.ac.auckland.barservice;

import brave.Span;
import brave.Tracer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.sleuth.annotation.NewSpan;
import org.springframework.cloud.sleuth.annotation.SpanTag;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class BarService {

    private static Logger LOGGER = LoggerFactory.getLogger(BarService.class);

    private final Tracer tracer;

    private final RestTemplate restTemplate;

    public BarService(Tracer tracer, RestTemplate restTemplate) {
        this.tracer = tracer;
        this.restTemplate = restTemplate;
    }

    public String getFooMessage() {
        LOGGER.info("Inside getFooMessage");
        String fooMessage = getMessageFromFooService();
        LOGGER.info("Exiting getFooMessage");
        return fooMessage;
    }


    private String getMessageFromFooService(){
        Span barLogicSpan = tracer.nextSpan().name("fooLogic-span");
        Tracer.SpanInScope ws = tracer.withSpanInScope(barLogicSpan.start());
        barLogicSpan.customizer().tag("id","abcd");
        barLogicSpan.tag("id","abcd");
        barLogicSpan.annotate("message");
        LOGGER.info("Calling foo-service to get message");
        String fooMessage = restTemplate.getForObject("http://localhost:9090", String.class);
        LOGGER.info("Received message='{}' from fooService", fooMessage);

        barLogicSpan.finish();
        ws.close();

        return fooMessage;
    }
}
