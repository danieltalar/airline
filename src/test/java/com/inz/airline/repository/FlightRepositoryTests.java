package com.inz.airline.repository;

import com.inz.airline.domain.City;
import com.inz.airline.domain.Flight;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.neo4j.DataNeo4jTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.time.Month;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataNeo4jTest
public class FlightRepositoryTests {


    @Autowired
    private FlightRepository flightRepository;


    @Test
    public void whenFindByCode_thenReturnFlight() {
        // given
        LocalDateTime dateTime = LocalDateTime.of(2019, Month.MARCH, 1, 20, 1);
        LocalDateTime dateTime2 = LocalDateTime.of(2019, Month.MARCH, 2, 2, 1);
        String code ="AA9";
        Flight flight= Flight.builder().code("AA9").carrier("American Airlines").cityFrom(new City("Poznan","Poland")).cityTo(new City("Krakow","Poland")).start(dateTime).end(dateTime2).avaiableSeatsBussinesClass(20).avaiableSeatsEconomy(30).avaiableSeatsFirstClass(10).avaiableSeatsPremiumEconomy(10).basePrice(Double.valueOf(300)).build();
        flightRepository.save(flight);

        // when
        Flight found = flightRepository.getByCode(code);

        // then
        assertThat(found.getCode())
                .isEqualTo(flight.getCode());

        assertThat(found.getBasePrice()).isEqualTo(flight.getBasePrice());
    }


}
