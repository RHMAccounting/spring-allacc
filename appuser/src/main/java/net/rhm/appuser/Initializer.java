package net.rhm.appuser;

import net.rhm.appuser.model.entity.AuthServer;
import net.rhm.appuser.model.repository.AuthServerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
class Initializer implements CommandLineRunner {

    private final AuthServerRepository repository;

    public Initializer(AuthServerRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... strings) {

        AuthServer authServer = new AuthServer();
        authServer.setProtocol("OAuth2");
        authServer.setName("Okta");
        repository.save(authServer);

        repository.findAll().forEach(System.out::println);
    }
}