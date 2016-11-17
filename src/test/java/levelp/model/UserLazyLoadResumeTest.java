package levelp.model;

import levelp.config.ApplicationConfig;
import levelp.config.EmbeddedDataSourceConfig;
import levelp.config.JpaConfig;
import levelp.config.NoCsrfSecurityConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
@WebAppConfiguration
@ContextConfiguration(classes = {
        ApplicationConfig.class,
        EmbeddedDataSourceConfig.class,
        JpaConfig.class,
        NoCsrfSecurityConfig.class
})
public class UserLazyLoadResumeTest {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ResumeRepository resumeRepository;

    @Autowired
    private ContactRepository contactRepository;

    @Before
    public void setUp() {
        User user = userRepository.findByEmail("1@ya.ru");
        if (user == null) {
            user = new User();
            user.setEmail("1@ya.ru");
            user.setPassword("password");
            userRepository.save(user);
        }
        List<Resume> list = user.getResumes();
        if (list == null) {
            list = new ArrayList<>();
            Resume resume = new Resume();
            resume.user = user;
            list.add(resume);
            user.setResumes(list);
            resumeRepository.save(resume);
        }
    }

    /**
     * При LAZY загрузке загружаются только необходимые данные
     * При EAGER зазгузке загружаются сразу все связанные данные
     */
    @Test
    @Transactional
    public void testLazyResumeLoad() {
        User user = userRepository.findByEmail("1@ya.ru");
        assertNotNull(user);
        List<Resume> list = user.getResumes();
        assertTrue(list.size() > 0);
        for (Resume resume : list) {
            System.out.println("resume.getTitle() = " + resume.getTitle());
        }
        List<Contact> contacts = list.get(0).getContacts();
        for (Contact contact : contacts) {
            System.out.println("contact.text = " + contact.text);
        }
    }

    @Test
    @Transactional
    @Rollback(false) // Чтобы изменения по окончанию теста
    public void testAddContact() {
        User user = userRepository.findByEmail("1@ya.ru");
        assertNotNull(user);
        List<Resume> list = user.getResumes();
        assertTrue(list.size() > 0);
        for (Resume resume : list) {
            System.out.println("resume.getTitle() = " + resume.getTitle());
        }
        Resume resume = list.get(0);
        System.out.println("resume.getId() = " + resume.getId());

        List<Contact> contacts = resume.getContacts();
        Contact newContact = new Contact(resume, ContactType.HOME_PHONE,
                Integer.toString(new Random().nextInt(10000000)));
        contacts.add(newContact);

        contactRepository.save(newContact);

        for (Contact contact : contacts) {
            System.out.println("contact.text = " + contact.text);
        }
    }

}
