package com.techdragons.web.aitwin;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MeerController {

    @GetMapping("/check")
    public boolean check() {
        return true;
    }
}
