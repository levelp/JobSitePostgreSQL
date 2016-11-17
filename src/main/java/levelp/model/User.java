package levelp.model;

import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@SuppressWarnings("serial")
@Entity // Данный класс соответствует таблице в Базе Данных
@Table(name = "`user`")
@NamedQuery(name = User.FIND_BY_EMAIL,
        query = "select a from User a where a.email = :email")
// a.email.length() < 3
public class User implements java.io.Serializable {

    public static final String FIND_BY_EMAIL = "User.findByEmail";

    @Id
    @GeneratedValue
    private Long id;

    // nullable - может ли быть null
    @Column(name = "EMAIL", unique = true, nullable = false)
    // , columnDefinition = "VARCHAR(1000) DEFAULT 'No@mail.ru'"
    private String email;

    @JsonIgnore
    private String password;

    @Column(name = "SURNAME")
    private String surname;

    @Column(name = "NAME")
    private String name;

    @Column(name = "MIDDLE_NAME")
    private String middleName; // MySql: middle_name

    /**
     * Поле без Column всё равно попадает в БД
     */
    // @Transient -  не сохранять
    private String role = "ROLE_USER";

    @Enumerated(EnumType.STRING) // Хранить элементы перечисления в БД как строки (для наглядности)
    @Column(name = "SEX")
    private Sex sex = Sex.MALE;

    // enum XX { DEBIT (0), CREDIT(1) }
    // enum XX { DEBIT (0), DEBIT_EX(1), CREDIT(2) }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<Resume> resumes;

    //@OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    //private List<Resume> resumes;

    protected User() {

    }

    public User(String email, String password, String role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<Resume> getResumes() {
        return resumes;
    }

    public void setResumes(List<Resume> resumes) {
        this.resumes = resumes;
    }
}
