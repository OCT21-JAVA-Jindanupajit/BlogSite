package jbc.oct21.jindanupajit.blogapplication.initializer;

import jbc.oct21.jindanupajit.blogapplication.model.BlogEntry;
import jbc.oct21.jindanupajit.blogapplication.model.SocialMediaType;
import jbc.oct21.jindanupajit.blogapplication.model.User;
import jbc.oct21.jindanupajit.blogapplication.repository.BlogEntryRepository;
import jbc.oct21.jindanupajit.blogapplication.repository.SocialMediaTypeRepository;
import jbc.oct21.jindanupajit.blogapplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class DatabaseInitializer implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;

    @Autowired
    SocialMediaTypeRepository socialMediaTypeRepository;

    @Autowired
    BlogEntryRepository blogEntryRepository;

    @Override
    public void run(String... args) throws Exception {
        User user =  null;
        if (userRepository.count() == 0) {
            userRepository.save(
                  user =  new User("Krissada", "Bio..", null, null)
            );
        } else
            user = userRepository.findByName("Krissada");


        if (socialMediaTypeRepository.count() == 0) {
            socialMediaTypeRepository.save(
              new SocialMediaType("LinkedIn", "")
            );
            socialMediaTypeRepository.save(
                    new SocialMediaType("GitHub", "")
            );
            socialMediaTypeRepository.save(
                    new SocialMediaType("Twitter", "")
            );
            socialMediaTypeRepository.save(
                    new SocialMediaType("Facebook", "")
            );
        }

        if (blogEntryRepository.count() == 0) {
            blogEntryRepository.save(
                    new BlogEntry(new Timestamp(System.currentTimeMillis()), null, user, "Title 1", "Content 1")
            );
            blogEntryRepository.save(
                    new BlogEntry(new Timestamp(System.currentTimeMillis()), null, user, "Title 2", "Content 2")
            );
            blogEntryRepository.save(
                    new BlogEntry(new Timestamp(System.currentTimeMillis()), null, user, "Title 3", "Content 3")
            );
        }
    }
}
