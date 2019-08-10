package com.inz.airline;

import com.inz.airline.domain.City;
import com.inz.airline.domain.Flight;
import com.inz.airline.domain.Ticket;
import com.inz.airline.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;


@SpringBootApplication
@EnableNeo4jRepositories
public class AirlineApplication {

    public static void main(String[] args) {
        SpringApplication.run(AirlineApplication.class, args);

    }


    @Bean
    CommandLineRunner demo(CityRepository cityRepository, FlightRepository flightRepository, JourneyRepository journeyRepository,
                           TicketRepository ticketRepository, AuthorityRepository authorityRepository, UserRepository userRepository) {
        return args -> {
//            authorityRepository.deleteAll();
//            authorityRepository.save(new Authority("ROLE_USER"));
//            authorityRepository.save(new Authority("ROLE_ADMIN"));
//            userRepository.deleteAll();
            ticketRepository.deleteAll();
            cityRepository.deleteAll();
            cityRepository.save(new City("London","England"));
            cityRepository.save(new City("Kacper","Poland"));
            cityRepository.save(new City("Berlin","Germany"));
            cityRepository.save(new City("Toronto","Canada"));
            cityRepository.save(new City("Roma","Italy"));
            cityRepository.save(new City("Sydney","Australia"));
            cityRepository.save(new City("Istanbul","Turkey"));
            cityRepository.save(new City("Singapore","Singapore"));
            cityRepository.save(new City("Mumbai","India"));
            cityRepository.save(new City("Madrid","Spain"));
            cityRepository.save(new City("Athens","Greece"));
            cityRepository.save(new City("Paris","France"));
            cityRepository.save(new City("Los Angeles","United States of America"));
            cityRepository.save(new City("New York","United States of America"));
            cityRepository.save(new City("Chicago","United States of America"));
       //     cityRepository.findAll().forEach(w->System.out.println(w.getName()));
            flightRepository.deleteAll();
            LocalDateTime dateTime = LocalDateTime.of(2019, Month.MARCH, 1, 20, 1);
            LocalDateTime dateTime2 = LocalDateTime.of(2019, Month.MARCH, 2, 2, 1);
            LocalDateTime dateTime3 = LocalDateTime.of(2019, Month.MARCH, 2, 12, 1);
            LocalDateTime dateTime4 = LocalDateTime.of(2019, Month.MARCH, 3, 20, 1);
            List<Ticket> tickets = new ArrayList<>();
            List<Ticket> tickets2 = new ArrayList<>();
            List<Ticket> tickets3 = new ArrayList<>();
            List<Ticket> tickets4 = new ArrayList<>();


                Ticket t1= new Ticket( "premium economy","AA9", true,5.0);
                Ticket t2 = new Ticket( "economy","AA9", false,5.0);
                Ticket t5 = new Ticket( "economy","AA9", true,5.0);
                Ticket t6 = new Ticket( "economy","AA9", true,5.0);
                Ticket t3 = new Ticket( "business class","AA9", true,5.0);
                Ticket t4 = new Ticket( "first class","AA9", true,5.0);
                tickets.add(t1);
                tickets.add(t2);
                tickets.add(t3);
                tickets.add(t4);
                tickets.add(t5);
                tickets.add(t6);
                Ticket t11= new Ticket( "premium economy","AA11", true,5.0);
                Ticket t22 = new Ticket( "economy","AA11", false,5.0);
                Ticket t55 = new Ticket( "economy","AA11", true,5.0);
                Ticket t66 = new Ticket( "economy","AA11", true,5.0);
                Ticket t33 = new Ticket( "business class","AA11", true,5.0);
                Ticket t44 = new Ticket( "first class","AA11", true,5.0);
                tickets2.add(t11);
                tickets2.add(t22);
                tickets2.add(t33);
                tickets2.add(t44);
                tickets2.add(t55);
                tickets2.add(t66);

            flightRepository.save(new Flight( "AA9", "American Airlines", cityRepository.getByName("New York"), cityRepository.getByName("Los Angeles"), dateTime,dateTime2,500,100, tickets));
           flightRepository.save(new Flight( "AA10", "American Airlines",  cityRepository.getByName("Kacper"),cityRepository.getByName("Roma"), dateTime2,dateTime4,20,500,tickets));
           flightRepository.save(new Flight( "AA11", "American Airlines", cityRepository.getByName("Los Angeles"),cityRepository.getByName("Istanbul"), dateTime3,dateTime4,400, 300,tickets2));
           flightRepository.save(new Flight( "AA12", "American Airlines",  cityRepository.getByName("Los Angeles"),cityRepository.getByName("Istanbul"), dateTime2,dateTime3,199,700,tickets));

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
