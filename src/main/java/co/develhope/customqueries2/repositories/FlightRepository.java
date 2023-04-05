package co.develhope.customqueries2.repositories;

import co.develhope.customqueries2.entities.Flight;
import co.develhope.customqueries2.entities.FlightStatusEnum;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {


    List<Flight> getFlightsOrderInAscByFromAirport(Pageable pageable);

    List<Flight> getFlightsByStatusP1OrP2(FlightStatusEnum p1, FlightStatusEnum p2);


}
