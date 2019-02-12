package net.rhm.appuser;

import net.rhm.appuser.model.User;
import net.rhm.appuser.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Collections;
import java.util.stream.Stream;

@Component
class Initializer implements CommandLineRunner {

    private final UserRepository repository;

    public Initializer(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... strings) {
        Stream.of("Bill", "Jack", "Isidor",
                "Cat.").forEach(name ->
                repository.save(new User(name,name, name))
        );

        repository.findAll().forEach(System.out::println);
    }
}