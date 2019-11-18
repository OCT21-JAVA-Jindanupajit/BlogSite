package jbc.oct21.jindanupajit.blogapplication.initializer;

import jbc.oct21.jindanupajit.blogapplication.model.BlogEntry;
import jbc.oct21.jindanupajit.blogapplication.model.Category;
import jbc.oct21.jindanupajit.blogapplication.model.SocialMediaType;
import jbc.oct21.jindanupajit.blogapplication.model.User;
import jbc.oct21.jindanupajit.blogapplication.repository.BlogEntryRepository;
import jbc.oct21.jindanupajit.blogapplication.repository.CategoryRepository;
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
    CategoryRepository categoryRepository;

    @Autowired
    BlogEntryRepository blogEntryRepository;

    @Override
    public void run(String... args) throws Exception {
        String loremIpsum =
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean quis dignissim justo. Quisque nisi dolor, accumsan eget lectus nec, faucibus dictum elit. Ut imperdiet egestas ipsum id vehicula. Etiam et viverra felis, et semper lacus. Donec dignissim, dolor nec laoreet porttitor, magna dolor pretium massa, ut pulvinar erat arcu in tortor. Cras egestas faucibus euismod. Proin eget velit massa. Morbi lacinia facilisis eros at sagittis. Donec ipsum ante, bibendum vitae mi a, tempor sagittis nibh.\n" +
                "\n" +
                "Aenean facilisis pellentesque elit, in placerat lectus egestas a. Etiam imperdiet, nulla sed posuere suscipit, enim lectus ullamcorper mi, eu semper justo felis at mauris. Cras ac ipsum ipsum. Duis sem ipsum, ornare scelerisque massa at, iaculis sollicitudin sapien. Phasellus felis enim, finibus in nibh nec, tempor hendrerit diam. Aliquam dolor sem, convallis vitae vehicula in, sodales ut enim. Phasellus interdum rutrum mi, et eleifend velit porta ac. Nulla semper ligula sed velit semper viverra. Nunc eget maximus quam.\n" +
                "\n" +
                "Ut convallis sagittis magna, id ultrices purus molestie a. In accumsan consectetur velit, sed pretium arcu lobortis a. Nulla facilisi. Donec ex ante, rutrum vitae elit eu, fermentum porttitor metus. Mauris varius urna et dui condimentum aliquet. Sed finibus justo ex, id pellentesque neque blandit nec. Aliquam et mauris eu turpis dignissim imperdiet cursus scelerisque metus. Suspendisse gravida justo quam, porttitor egestas risus blandit vitae. Nam consequat, ante in sollicitudin lobortis, nisi quam convallis nulla, ut facilisis elit ipsum ut neque. Quisque interdum quis metus vel posuere. Nullam ultricies nisl eu finibus luctus. Quisque pharetra consectetur risus. Morbi et egestas lectus, tincidunt posuere metus. Ut eleifend, enim in varius rhoncus, arcu arcu blandit mi, mattis vestibulum lacus sapien id diam. Maecenas convallis mauris magna, sed rhoncus purus rhoncus et. Morbi nec risus dignissim, dignissim leo a, vestibulum magna.\n" +
                "\n" +
                "Nulla lacinia sapien et lectus porttitor rhoncus. Etiam volutpat, odio a posuere maximus, dui ipsum accumsan leo, ac efficitur velit est et dolor. Fusce posuere ipsum nec magna dapibus, ac vehicula tortor scelerisque. Sed id enim lectus. Vestibulum semper orci eget risus pharetra, quis mollis dui dapibus. In ultricies sed nibh vitae efficitur. Ut a nisi augue. Pellentesque quis ante in est molestie commodo in nec dolor. Nunc gravida tellus quis magna fermentum, a iaculis lorem lacinia. Phasellus posuere faucibus felis et posuere. Sed sed suscipit diam, vel tincidunt justo. Aliquam ullamcorper ligula sed purus ultrices pellentesque.\n" +
                "\n" +
                "Duis nibh felis, interdum et condimentum nec, tincidunt ac magna. Interdum et malesuada fames ac ante ipsum primis in faucibus. Aliquam aliquam vel nibh aliquet condimentum. Donec nisi purus, interdum consequat pharetra non, semper id orci. Donec suscipit est nec dapibus placerat. Sed et neque ut tellus ullamcorper pretium. Mauris commodo mollis arcu et blandit. Nunc justo nibh, euismod in metus in, feugiat mattis libero. Mauris euismod, lorem id congue tincidunt, nisl augue tempor tortor, nec rutrum ipsum ligula a risus. Phasellus dapibus ante eu ipsum feugiat iaculis. Vestibulum nulla tellus, tempor nec magna et, vulputate gravida purus. Aliquam ullamcorper commodo arcu eu malesuada. In egestas in sem et varius. Aenean ullamcorper orci eu aliquam vestibulum. Etiam tincidunt dolor at mauris fringilla feugiat eget in velit. Quisque suscipit magna id gravida interdum.";

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

        Category cGithub = null, cProgramDesign = null, cCoreJava = null, cOopJava = null, cHtml = null;
        if (categoryRepository.count() == 0) {
            categoryRepository.save(
                    cGithub = new Category("GitHub")
            );
            categoryRepository.save(
                    cProgramDesign = new Category("Program Design & Algorithms")
            );
            categoryRepository.save(
                    cCoreJava = new Category("Core Java")
            );
            categoryRepository.save(
                    cOopJava = new Category("Object-oriented Java")
            );
            categoryRepository.save(
                    cHtml = new Category("HTML/CSS/Bootstrap")
            );
        }

        if (blogEntryRepository.count() == 0) {
            blogEntryRepository.save(
                    new BlogEntry(new Timestamp(System.currentTimeMillis()), cGithub, user, "Basic Github", "Content 1 "+loremIpsum,null)
            );
            blogEntryRepository.save(
                    new BlogEntry(new Timestamp(System.currentTimeMillis()), cProgramDesign, user, "How the IPO is important", "Content 2 "+loremIpsum,null)
            );
            blogEntryRepository.save(
                    new BlogEntry(new Timestamp(System.currentTimeMillis()), cCoreJava, user, "Java ", "Content 3 "+loremIpsum,null)
            );
            blogEntryRepository.save(
                    new BlogEntry(new Timestamp(System.currentTimeMillis()), cOopJava, user, "What is POJO", "Content 4 "+loremIpsum,null)
            );
            blogEntryRepository.save(
                    new BlogEntry(new Timestamp(System.currentTimeMillis()), cHtml, user, "Custom Bootstrap Navbar Background", "Content 5 "+loremIpsum,null)
            );
        }
    }
}
