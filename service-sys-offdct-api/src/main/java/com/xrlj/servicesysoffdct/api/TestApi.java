package com.xrlj.servicesysoffdct.api;

import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RequestMapping("/test")
public interface TestApi {

    @GetMapping("/test1")
    String test();

    @GetMapping("/test2")
    String test2() throws IOException;

    @GetMapping("/test3")
    String test3() throws IOException;
}
