package challenge.redbee;

import challenge.redbee.domain.Locacion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class Spring5MvcRestApplication {
    public static void main(String[] args) {
        SpringApplication.run(Spring5MvcRestApplication.class, args);
    }
}


//	private static final Logger log = LoggerFactory.getLogger(Spring5MvcRestApplication.class);
//
//	public static void main(String args[]) {
//		RestTemplate restTemplate = new RestTemplate();
//		Locacion locacion = restTemplate.getForObject("http://api.openweathermap.org/data/2.5/weather?appid=ac7c783b90a242b9dcbb219ff24b56b6&q=oregon,us", Locacion.class);
//		log.info(locacion.toString());
//	}}
