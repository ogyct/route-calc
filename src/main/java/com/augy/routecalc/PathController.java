package com.augy.routecalc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PathController {

    @Autowired
    private PathService pathService;

    @GetMapping("/routing/{source}/{destination}")
    public RouteResponse getPath(@PathVariable String source, @PathVariable String destination) {
        return new RouteResponse(pathService.getPath(source, destination));
    }
}
