package agk.bootsecurity.db;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import agk.bootsecurity.model.User;

import java.util.Arrays;
import java.util.List;
/*
 * Author - Anand K
 * Subject - This class is executed automatically and records are stored in
 * Temporary in memory database H2
 * This class can be annotated as @Service or @Component
 * */
@Component
public class DbInit implements CommandLineRunner {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public DbInit(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        // Delete all
        this.userRepository.deleteAll();

        // Crete users
        User user1 = new User("bharat",passwordEncoder.encode("bharat123"),"USER","");
        User user2 = new User("anand",passwordEncoder.encode("anand123"),"ADMIN","ACCESS_TEST1,ACCESS_TEST2");
        User user3 = new User("manager",passwordEncoder.encode("manager123"),"MANAGER","ACCESS_TEST1");

        List<User> users = Arrays.asList(user1,user2,user3);

        // Save to database
        this.userRepository.saveAll(users);
    }
}
