package am.basic.jdbc.model;

import lombok.Data;

@Data
public class Comment {


    private long id;

    private String title;

    private String content;

    private long userId;


}
