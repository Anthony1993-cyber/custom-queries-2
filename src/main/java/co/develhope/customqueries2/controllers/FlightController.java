package co.develhope.customqueries2.controllers;

import co.develhope.customqueries2.entities.Flight;
import co.develhope.customqueries2.entities.FlightStatusEnum;
import co.develhope.customqueries2.repositories.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@RestController
@RequestMapping("/flights")
public class FlightController {

    @Autowired
    private FlightRepository flightRepository;

    @PostMapping("")
    public List<Flight> createFlights(@RequestParam(required = false) Optional<Integer> n) {
        List<Flight> flightsList = new ArrayList<>();
        Random random = new Random();
        if(n.isPresent()){
            for(long i=0; i<(n.get() + 1); i++) {
                Flight flight = new Flight(
                        i,
                        "flight " + i,
                        "fromAirport " + random.ints(1,50),
                        "toAirport " + random.ints(1,50),
                        Flight.generateRandomFlightStatusEnum());
                flightsList.add(flightRepository.save(flight));
            }
        }else{
            for(long i = 0; i < 501; i++){
                Flight flight = new Flight(
                        i,
                        "flight " + i,
                        "fromAirport " + random.ints(1,500),
                        "toAirport " + random.ints(1,500),
                        Flight.generateRandomFlightStatusEnum());
                flightsList.add(flightRepository.save(flight));
            }
        }
        return flightsList;
    }

    @GetMapping("")
    public List<Flight> getAllFlightsInAscOrderByFromAirport(@RequestParam int page, @RequestParam int size){
        Pageable pageable = PageRequest.of(page, size);
        List<Flight> flightsPageableList = flightRepository.getFlightsOrderInAscByFromAirport(pageable);
        return flightsPageableList;
    }

    @GetMapping("/on-time")
    public List<Flight> getAllFlightsOnTime(){
        List<Flight> flightsOnTimeList = (List<Flight>) flightRepository.findAll().stream().filter(flight -> flight.getStatus().equals(FlightStatusEnum.ONTIME));
        return flightsOnTimeList;
    }

    @GetMapping("/status")
    public List<Flight> getAllFlightsWithFlightStatusEnumP1OrP2(@RequestParam FlightStatusEnum p1, @RequestParam FlightStatusEnum p2){
        List<Flight> p1OrP2Flights = flightRepository.getFlightsByStatusP1OrP2(p1,p2);
        return p1OrP2Flights;
    }





}