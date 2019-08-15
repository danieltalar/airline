package com.inz.airline;

import com.inz.airline.domain.Flight;
import com.inz.airline.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

import java.time.LocalDateTime;
import java.time.Month;


@SpringBootApplication
@EnableNeo4jRepositories
public class AirlineApplication {

    public static void main(String[] args) {
        SpringApplication.run(AirlineApplication.class, args);

    }


    @Bean
    CommandLineRunner demo(CityRepository cityRepository, FlightRepository flightRepository, JourneyRepository journeyRepository,
                           TicketRepository ticketRepository, AuthorityRepository authorityRepository, UserRepository userRepository, BookingRepository bookingRepository) {
        return args -> {
//            authorityRepository.deleteAll();
//            authorityRepository.save(new Authority("ROLE_USER"));
//            authorityRepository.save(new Authority("ROLE_ADMIN"));
//            userRepository.deleteAll();
            bookingRepository.deleteAll();
            journeyRepository.deleteAll();
            ticketRepository.deleteAll();
//            cityRepository.deleteAll();
//            cityRepository.save(new City("London","England"));
//            cityRepository.save(new City("Kacper","Poland"));
//            cityRepository.save(new City("Berlin","Germany"));
//            cityRepository.save(new City("Toronto","Canada"));
//            cityRepository.save(new City("Roma","Italy"));
//            cityRepository.save(new City("Sydney","Australia"));
//            cityRepository.save(new City("Istanbul","Turkey"));
//            cityRepository.save(new City("Singapore","Singapore"));
//            cityRepository.save(new City("Mumbai","India"));
//            cityRepository.save(new City("Madrid","Spain"));
//            cityRepository.save(new City("Athens","Greece"));
//            cityRepository.save(new City("Paris","France"));
//            cityRepository.save(new City("Los Angeles","United States of America"));
//            cityRepository.save(new City("New York","United States of America"));
//            cityRepository.save(new City("Chicago","United States of America"));
       //     cityRepository.findAll().forEach(w->System.out.println(w.getName()));
            flightRepository.deleteAll();
            LocalDateTime dateTime = LocalDateTime.of(2019, Month.MARCH, 1, 20, 1);
            LocalDateTime dateTime2 = LocalDateTime.of(2019, Month.MARCH, 2, 2, 1);
            LocalDateTime dateTime3 = LocalDateTime.of(2019, Month.MARCH, 2, 12, 1);
            LocalDateTime dateTime4 = LocalDateTime.of(2019, Month.MARCH, 3, 20, 1);

            flightRepository.save(Flight.builder().code("AA9").carrier("American Airlines").cityFrom(cityRepository.getByName("New York")).cityTo(cityRepository.getByName("Los Angeles")).start(dateTime).end(dateTime2).avaiableSeatsBussinesClass(20).avaiableSeatsEconomy(30).avaiableSeatsFirstClass(10).avaiableSeatsPremiumEconomy(10).basePrice(Double.valueOf(300)).build());
           flightRepository.save( Flight.builder().code("AA10").carrier("American Airlines").cityFrom(cityRepository.getByName("Madrid")).cityTo(cityRepository.getByName("Roma")).start(dateTime2).end(dateTime4).avaiableSeatsBussinesClass(20).avaiableSeatsEconomy(30).avaiableSeatsFirstClass(10).avaiableSeatsPremiumEconomy(10).basePrice(Double.valueOf(300)).build());
           flightRepository.save(Flight.builder().code("AA11").carrier("American Airlines").cityFrom(cityRepository.getByName("Los Angeles")).cityTo(cityRepository.getByName("Istanbul")).start(dateTime3).end(dateTime4).avaiableSeatsBussinesClass(20).avaiableSeatsEconomy(30).avaiableSeatsFirstClass(10).avaiableSeatsPremiumEconomy(10).basePrice(Double.valueOf(200)).build());
           flightRepository.save(Flight.builder().code("AA12").carrier("American Airlines").cityFrom(cityRepository.getByName("Los Angeles")).cityTo(cityRepository.getByName("Istanbul")).start(dateTime2).end(dateTime3).avaiableSeatsBussinesClass(20).avaiableSeatsEconomy(30).avaiableSeatsFirstClass(10).avaiableSeatsPremiumEconomy(10).basePrice(Double.valueOf(300)).build());



            //   List<JourneyData> listOfJourneys = journeyRepository.findListOfJourneys("New York", "Istanbul");

//            flightRepository.findAll().forEach(f->System.out.println(f.toString()));
  //          listOfJourneys.forEach(l-> System.out.println(l.getFlight_codes()));


            //    flyingFromRepository.save(new FlyingFrom(cityRepository.getByName("New York"), flightRepository.findById("AA9").get()));
   //     flyingToRepository.save(new FlyingTo(cityRepository.getByName("Athens"), flightRepository.findById("AA9").get()));
      //  flyingFromRepository.save(new FlyingFrom(cityRepository.getByName("Paris"), flightRepository.findById("AA10").get()));
     //   flyingToRepository.save(new FlyingTo(cityRepository.getByName("Madrid"), flightRepository.findById("AA10").get()));

//        flyingFromRepository.findAll().forEach(w->System.out.println("Lot z " + w.getCity()));
       //     System.out.println("LOT o kodzie: " + flightRepository.getByCode("AA9").getCode() +" leci z  "+ flightRepository.getByCode("AA9").getCityFrom() + " do " +flightRepository.getByCode("AA9").getCityTo());
          /*  List<Flight> listOfFlights = flightRepository.findListOfFlights("Los Angeles", "Istanbul");
        listOfFlights.forEach(flight -> System.out.println(flight));

        journeyRepository.save(new Journey(LocalDate.of(2002, Month.MARCH, 1), listOfFlights));
        journeyRepository.findAll().forEach(w-> System.out.println(w));*/
            //   System.out.println( flightRepository.getByCode("AA10").toString());


        };
    }

}
