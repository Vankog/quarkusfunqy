package app.dmo.funqy;

/**
 * @author Niko Köbler, https://www.n-k.de, @dasniko
 */
public class Person {
    String first;
    String last;

    public Person() {}

    public Person(String first, String last) {
        this.first = first;
        this.last = last;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }
}