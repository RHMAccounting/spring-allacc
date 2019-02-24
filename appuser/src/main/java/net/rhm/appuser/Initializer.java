package net.rhm.appuser;

import net.rhm.appuser.model.entity.AuthServer;
import net.rhm.appuser.model.repository.AuthServerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
class Initializer implements CommandLineRunner {

    private final AuthServerRepository repository;
    private static final Logger LOGGER = LoggerFactory.getLogger(Initializer.class);

    public Initializer(AuthServerRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... strings) {

        try {
            AuthServer authServer = new AuthServer();
            authServer.setProtocol("OAuth2");
            authServer.setName("Okta");
            repository.save(authServer);
        } catch(Exception e) {
            LOGGER.error("Exception caught while initializing...");
        }

        repository.findAll().forEach(System.out::println);
    }
}