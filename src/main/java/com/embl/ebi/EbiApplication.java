package com.embl.ebi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author hariprasanth.l This Micro service application is used to store,
 *         update, retrieve and delete the person details.
 */
@SpringBootApplication
public class EbiApplication {

	/**
	 * Logger object
	 */
	private final static Logger LOG = LoggerFactory.getLogger(EbiApplication.class);

	public static void main(String[] args) {
		try {
			SpringApplication.run(EbiApplication.class, args);
		} finally {
			LOG.info("EbiApplication is initialized successfully, Person Data service is ready to use...");
		}
	}

}
