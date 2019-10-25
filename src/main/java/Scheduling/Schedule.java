package Scheduling;

import data.Student;
import data.Turn;

import java.util.ArrayList;
import java.util.List;

public class Schedule {
    private TimeSlot[][] timeSlot = new TimeSlot[5][4];
    private TimeSlot[][] timeSlots = new TimeSlot[5][4];

    public void getTable(ArrayList<Student> students) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 4; j++) {
                if (timeSlot[i][j] != null) {
                    timeSlot[i][j].clear();
                    timeSlots[i][j].clear();
                } else {
                    timeSlot[i][j] = new TimeSlot();
                    timeSlots[i][j] = new TimeSlot();
                }
            }
        }

        for (Student student : students) {
            List<Turn> turns = student.getSuitableTurns();
            for (Turn turn : turns) {
                int week = turn.getWorkDay();
                int time = turn.getDayTime();
                timeSlot[week - 1][time - 1]
                        .getList()
                        .add(student);
            }
        }
        table();
    }

    private void table() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 4; j++) {
                ArrayList<Student> turns = timeSlot[i][j].getList();
                for (Student turn : turns) {
                    if ((turn.getArrangeCount() != 0) && (timeSlots[i][j].getList().size() < 2)) {
                        timeSlots[i][j].getList().add(turn);
                        turn.changeArrangeCount();
                    }
                }
            }

        }
    }

    public void print() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print("Week" + (i + 1) + "time" + (j + 1) + ":");
                timeSlots[i][j].print();
            }
        }
    }

    private static class TimeSlot {
        private ArrayList<Student> list = new ArrayList<>();

        private void clear() {
            list.clear();
        }

        private ArrayList<Student> getList() {
            return list;
        }

        private void print() {
            for (Student student : list) {
                System.out.print(" " + student.getName());
            }
            System.out.println();
        }
    }

}
