package com.augy.routecalc;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

@Service
public class PathService {
    @Value("classpath:countries.json")
    private Resource resourceFile;
    private Graph graph;
    @Autowired
    private ObjectMapper objectMapper;

    @PostConstruct
    @SneakyThrows
    public void init() {
        graph = new Graph();
        var countries = Arrays.asList(objectMapper.readValue(resourceFile.getFile(), Country[].class));

        countries.forEach(country -> {
            var borders = country.getBorders();
            borders.forEach(border -> graph.addEdge(country.getCountryCode(), border));
        });
    }

    public List<String> getPath(String source, String destination) {
        return graph.shortestPath(source, destination);
    }
}
