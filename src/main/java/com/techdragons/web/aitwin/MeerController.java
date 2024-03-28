package com.techdragons.web.aitwin;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MeerController {

    @GetMapping("/api/v1/auth/check")
    public boolean check() {
        return true;
    }
}
