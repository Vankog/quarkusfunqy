package app.dmo.funqy;

import io.quarkus.funqy.Funq;

import java.util.Collections;
import java.util.Map;

public class GreetingFunction {

    @Funq
    public String greetingFunction(Map<String, String> params){
        return "Hello " + params.get("name");
    }


    @Funq
    public Map<String, String> foo(Map<String, String> params){
        return Collections.singletonMap("bar", params.get("name"));
    }

    @Funq("greet")
    public Map<String, String> greetingAPerson(Person person){
        return Collections.singletonMap(person.first, person.last);
    }


    @Funq
    public String family(Family family){
        return String.format("Hello Mom, %s and Dad, %s, with kids %s",
                family.getMom().getFirst(), family.getDad().getFirst(),
                String.join(", ", family.getKids()));
    }
}
