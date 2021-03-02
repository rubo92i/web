package am.basic.jdbc.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {



    private long id;

    private String name;

    private String surname;

    private String username;

    private String password;

    private String code;

    private  int status;


}
