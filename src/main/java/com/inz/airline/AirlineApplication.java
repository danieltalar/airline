package com.inz.airline;

import com.inz.airline.domain.City;
import com.inz.airline.repository.CityRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;


@SpringBootApplication
@EnableNeo4jRepositories
public class AirlineApplication {

    public static void main(String[] args) {
        SpringApplication.run(AirlineApplication.class, args);

    }


//     (:City{name:"Mumbai", country:"India"}),
//            (:City{name:"Chicago",
//            country:"United States of America"}),
//            (:City{name:"Las Vegas", country:"United States
//        of America"}),(:City{name:"Los Angeles",
//        country:"United States of America"}),
//            (:City{name:"Toronto", country:"Canada"}),
//            (:City{name:"London", country:"United Kingdom"}),
//            (:City{name:"Madrid", country:"Spain"}),
//            (:City{name:"Paris", country:"France"}),
//            (:City{name:"Athens", country:"Greece"}),
//            (:City{name:"Rome", country:"Italy"}),
//            (:City{name:"Istanbul", country:"Turkey"}),
//            (:City{name:"Singapore", country:"Singapore"}),
//            (:City{name:"Sydney", country:"Australia"}),
//            (:City{name:"Melbourne", country:"Australia"});

    @Bean
    CommandLineRunner demo(CityRepository cityRepository) {
        return args -> {
        cityRepository.deleteAll();
        cityRepository.save(new City("London","England"));
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
        cityRepository.findAll().forEach(w->System.out.println(w.getName()));

        };
    }

}
