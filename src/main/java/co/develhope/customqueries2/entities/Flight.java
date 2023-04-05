package co.develhope.customqueries2.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "flights")
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idFlight;

    @Column
    private String description;

    @Column
    private String fromAirport;

    @Column
    private String toAirport;

    @Enumerated(EnumType.STRING)
    private FlightStatusEnum status;

    private static List<FlightStatusEnum> flightStatusEnumList =
            Collections.unmodifiableList(Arrays.asList(FlightStatusEnum.values()));
    private static int size = flightStatusEnumList.size();
    private static Random random = new Random();

    public static FlightStatusEnum generateRandomFlightStatusEnum() {

        return flightStatusEnumList.get(random.nextInt(size));
    }



}