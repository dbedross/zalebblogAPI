package com.zaleb.zalebblog;

import com.zaleb.zalebblog.entities.Category;
import com.zaleb.zalebblog.entities.Credentials;
import com.zaleb.zalebblog.entities.User;
import com.zaleb.zalebblog.repositories.CategoryRepository;
import com.zaleb.zalebblog.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@AllArgsConstructor
public class Seeder implements CommandLineRunner {

    private UserRepository userRepository;
    private CategoryRepository categoryRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // Only seed if the database is empty
        if (userRepository.count() > 0) {
            return;
        }

        User calebUser = new User();
        calebUser.setName("caleb");
        Credentials creds1 = new Credentials();
        creds1.setUsername("mrwok");
        creds1.setPassword(passwordEncoder.encode("password"));
        calebUser.setCredentials(creds1);

        User zachUser = new User();
        zachUser.setName("zach");
        Credentials creds2 = new Credentials();
        creds2.setUsername("lilzkeepaswitch");
        creds2.setPassword(passwordEncoder.encode("password"));
        zachUser.setCredentials(creds2);
        zachUser.setRole("AUTHOR");

        User dev = new User();
        dev.setName("daniel");
        Credentials creds3 = new Credentials();
        creds3.setUsername("dev");
        creds3.setPassword(passwordEncoder.encode("password"));
        dev.setCredentials(creds3);
        dev.setRole("ADMIN");

        userRepository.saveAllAndFlush(Arrays.asList(new User[]{calebUser, zachUser, dev}));

        Category featuredCategory = new Category();
        featuredCategory.setName("Featured");

        Category movieCategory = new Category();
        movieCategory.setName("Movies");

        categoryRepository.saveAllAndFlush(Arrays.asList(new Category[]{featuredCategory, movieCategory}));

    }
}
