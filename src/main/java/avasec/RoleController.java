package avasec;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class RoleController {

    private final AtomicLong counter = new AtomicLong();

    @RequestMapping(value="/role", method=GET)
    public Role getRole(@RequestParam(value="id", defaultValue="") String id) {
        return new Role(counter.incrementAndGet(), "charid", "name");
    }
}