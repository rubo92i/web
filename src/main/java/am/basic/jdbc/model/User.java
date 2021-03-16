package am.basic.jdbc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private String surname;

    @Column(unique = true)
    private String username;

    private String password;

    private String code;

    private int status;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "card_id",referencedColumnName = "id")
    private Card card;

//    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
//    @JoinColumn(name = "user_id",referencedColumnName = "id")
//    private List<Comment> comment;


    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private List<Role> roles;


}
