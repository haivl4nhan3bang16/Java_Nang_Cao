package gson;

import java.util.List;
import java.util.Vector;

public class Score {

    private int score;


    private List<Temp> temps = new Vector<>();

    public static class Temp {

        private String name;

        public Temp(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }


        @Override
        public String toString() {
            return "Kid{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }
    public int getScore() {
        return score;
    }

    public void setScore(int age) {
        this.score = age;
    }

    public List<Temp> getTemps() {
        return temps;
    }
    public void addKid(Temp temp) {
        temps.add(temp);
    }

    @Override
    public String toString() {
        return "Score{" +
                ", score=" + score +
                ", temps=" + temps +
                '}';
    }
}