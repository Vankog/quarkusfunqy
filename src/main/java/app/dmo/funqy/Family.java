package app.dmo.funqy;

import java.util.List;

/**
 * @author Niko Köbler, https://www.n-k.de, @dasniko
 */
public class Family {
    Person mom;
    Person dad;
    List<String> kids;

    public Family() {}

    public Family(Person mom, Person dad, List<String> kids) {
        this.mom = mom;
        this.dad = dad;
        this.kids = kids;
    }

    public Person getMom() {
        return mom;
    }

    public void setMom(Person mom) {
        this.mom = mom;
    }

    public Person getDad() {
        return dad;
    }

    public void setDad(Person dad) {
        this.dad = dad;
    }

    public List<String> getKids() {
        return kids;
    }

    public void setKids(List<String> kids) {
        this.kids = kids;
    }
}