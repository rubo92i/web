package am.basic.jdbc.aold;


import am.basic.jdbc.model.Comment;
import am.basic.jdbc.model.User;
import am.basic.jdbc.util.DataSource;
import com.google.common.base.CaseFormat;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MainReflection {


    public static void main(String[] args) throws SQLException, InterruptedException, IllegalAccessException, NoSuchMethodException, InstantiationException, InvocationTargetException {
        User user = new User();
        user.setName("Ruben");
        user.setSurname("Manukyan");
        user.setUsername("dfsdf");


        Annotation[] annotations = user.getClass().getDeclaredAnnotations();
        for (Annotation annotation : annotations) {
            System.out.println(annotation.annotationType().getName() + annotation.annotationType().getSimpleName());
        }
        Comment comment = new Comment();
        comment.setContent("TestContent");
        comment.setTitle("Test Title");

        printObject(user);
        printObject(comment);

        System.out.println(generateInsertQuery(user));

        System.out.println(generateInsertQuery(comment));

    }


    public static void printObject(Object o) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException, InstantiationException {
        Class clazz = o.getClass();
        Field[] fields = clazz.getDeclaredFields();
        System.out.print(clazz.getSimpleName() + " [ ");

        for (Field field : fields) {
            field.setAccessible(true);
            System.out.print(field.getName() + " : " + field.get(o) + ", ");
        }
        System.out.println(" ]");

        Constructor constructor = clazz.getDeclaredConstructor();
        constructor.setAccessible(true);
        Object a = constructor.newInstance();

        Method method = clazz.getDeclaredMethod("toString");
        System.out.println(method.invoke(a));
    }


    public static String generateInsertQuery(Object object) throws SQLException, IllegalAccessException {

        Class clazz = object.getClass();
        Field[] fields = clazz.getDeclaredFields();
        String insert = " INSERT INTO " + clazz.getSimpleName().toLowerCase() + "(";
        for (int i = 0; i < fields.length - 1; i++) {
            insert += CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, fields[i].getName()) + ", ";
        }

        insert += CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, fields[fields.length - 1].getName()) + " )";
        insert += " VALUES( ";
        for (int i = 0; i < fields.length - 1; i++) {
            insert += "?, ";
        }
        insert += "? ) ;";


        PreparedStatement preparedStatement = DataSource.getConnection().prepareStatement(insert);
        for (int i = 1; i <= fields.length; i++) {
            fields[i - 1].setAccessible(true);
            preparedStatement.setObject(i, fields[i - 1].get(object));
        }

         preparedStatement.executeUpdate();
        return insert;
    }
}
