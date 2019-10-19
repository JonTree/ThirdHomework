import data.Data;
import data.Student;
import data.Turn;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Data data = new Data();
        ArrayList<Student> students = data.datas.get(0);
        Student student = students.get(0);
        Turn turn = student.getSuitableTurns().get(0);
        turn.getDayTime();
        turn.getWorkDay();
        //TODO 在这里开始你的程序
    }
}
