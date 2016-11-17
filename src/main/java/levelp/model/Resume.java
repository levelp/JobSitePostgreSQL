package levelp.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Резюме
 */
@Entity
@Table(name = "resume_mvc")
@NamedQuery(name = "Resume.findByEmail",
        query = "select r from Resume r where " +
                "r.user.email = :email")
public class Resume {
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    public User user;

    /**
     * Заголовок резюме
     */
    String title;

    @Column(name = "id")
    @Id
    @GeneratedValue
    private Long id;

    /**
     * Контакты
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "resume")
    private List<Contact> contacts = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Contact> getContacts() {
        return contacts;
    }
}
