package data;

import java.util.List;

public class Student {

    private String name;
    private List<Turn> suitableTurns;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Turn> getSuitableTurns() {
        return suitableTurns;
    }

    public void setSuitableTurns(List<Turn> suitableTurns) {
        this.suitableTurns = suitableTurns;
    }
}
