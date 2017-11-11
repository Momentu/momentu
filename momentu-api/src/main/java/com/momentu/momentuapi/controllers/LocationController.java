package com.momentu.momentuapi.controllers;

import com.momentu.momentuapi.entities.Location;
import com.momentu.momentuapi.repos.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.PersistentEntityResourceAssembler;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RepositoryRestController
@RequestMapping(name="/")
public class LocationController {

    @Autowired
    private LocationRepository locationRepository;

    @RequestMapping(value = "states", method = RequestMethod.GET)
    @ResponseBody
    public List<State> states(HttpServletRequest request
            , PersistentEntityResourceAssembler persistentEntityResourceAssembler) {
        Iterable<Location> locations = locationRepository.findAll();
        List<State> distinctLocations = StreamSupport.stream(locations.spliterator(), false)
                .filter(distinctByKey(l -> l.getState()))
                .map(l -> new State(l.getState()))
                .collect(Collectors.toList());

        return distinctLocations;
    }

    public <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Map<Object,Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

    private class State {

        private String state;

        public State(String state) {
            this.state = state;
        }

        public String getState() {
            return this.state;
        }
    }
}