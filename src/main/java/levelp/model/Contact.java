package levelp.model;

import javax.persistence.*;

/**
 *
 */
@Entity
@Table(name = "contact_mvc")
public class Contact {
    public String text;
    @Column(name = "id")
    @Id
    @GeneratedValue
    Long id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Resume resume;

    @Enumerated(EnumType.STRING)
    private ContactType type;

    public Contact() {
    }

    public Contact(Resume resume, ContactType type, String text) {
        this.resume = resume;
        this.type = type;
        this.text = text;
    }
}
