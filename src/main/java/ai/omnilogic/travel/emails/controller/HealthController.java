package ai.omnilogic.travel.emails.controller;

import ai.omnilogic.travel.emails.utils.Utils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/healthz")
public class HealthController {
    public HealthController(HttpServletRequest request) {
        super();
    }

    @GetMapping("/test")
    public ResponseEntity test(){
        return ResponseEntity.ok().body(String.format("API version 0.19.0, %s", Utils.dateNow()));
    }

    @GetMapping("/ready")
    public ResponseEntity ready(){
        return this.test();
    }
}
