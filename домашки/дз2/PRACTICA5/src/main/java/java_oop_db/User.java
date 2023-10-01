package java_oop_db;

public class User {

    private Long id;

    private String firstName;

    private String lastName;

    private Integer age;

    private String login;

    public User(Long id, String firstName, String lastName, Integer age, String login) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.login = login;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Integer getAge() {
        return age;
    }

    public String getLogin() {
        return login;
    }
}
