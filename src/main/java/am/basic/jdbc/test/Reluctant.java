package am.basic.jdbc.test;

public class Reluctant {

    private Reluctant internalinstance = new Reluctant();

    public Reluctant() throws Exception {
        throw new Exception("I'm not coming out");
    }

    public static void main(String[] args) {
        try {
            Reluctant b = new Reluctant();

            System.out.println("END");
        } catch (Exception x) {

            System.out.println("l told you so");
        }

    }
}

