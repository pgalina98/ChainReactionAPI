package hr.pgalina.chain_reaction;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@Slf4j
@SpringBootApplication
public class ChainReactionApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChainReactionApplication.class, args);
	}

	@PostConstruct
	void init() {
		log.info("Entered init in SpringBootApplication [set default TimeZone to 'Europe/Zagreb'].");

		TimeZone.setDefault(TimeZone.getTimeZone("Europe/Zagreb"));
	}
}
