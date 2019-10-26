package data;

import com.sun.org.apache.xpath.internal.objects.XNull;

import java.util.ArrayList;
import java.util.List;

public class TurnBook extends Turn {
    private String turnText[][] = new String[5][4];
    private String turnResult[][] = new String[5][4];
    private int turnCheck[][] = new int[5][4];
    private int turnSum[][] = new int[5][4];
    private Student student = new Student();
    private List<Turn> turn;
    private Turn turnIndex = new Turn();

    private void create() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 4; j++) {
                turnCheck[i][j] = 0;
                turnSum[i][j] = 0;
                turnResult[i][j] = "";
                turnText[i][j] = "";
            }
        }
    }

    public String getTurnText(int i, int j) {
        return turnText[i][j];
    }

    public String getTurResult(int i, int j) {
        return turnResult[i][j];
    }

    public void placeStudents(ArrayList<Student> students) {
        create();
        for (int i = 0; i < 20; i++) {
            student = students.get(i);
            turn = student.getSuitableTurns();
            for (int j = 0; j < turn.size(); j++) {
                turnIndex = turn.get(j);
                turnSum[turnIndex.getWorkDay() - 1][turnIndex.getDayTime() - 1]++;
                turnText[turnIndex.getWorkDay() - 1][turnIndex.getDayTime() - 1] += student.getName();
            }
        }
    }

    private void deleteStudent() {
        String name = student.getName();
        String person[] = {"Person10", "Person11", "Person12", "Person13", "Person14", "Person15", "Person16", "Person17", "Person18", "Person19"};
        Boolean index[] = new Boolean[10];
        Boolean flag1 = false;
        Boolean flag2 = false;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 10; k++) {
                    index[k] = false;
                }
                if (turnText[i][j].contains(name)) {
                    turnSum[i][j]--;
                    if (name.equals("Person2") && turnText[i][j].contains("Person20")) {
                        turnText[i][j] = turnText[i][j].replaceAll("Person20", "");
                        flag1 = true;
                    }
                    if (name.equals("Person1")) {
                        for (int k = 0; k < 10; k++) {
                            if (turnText[i][j].contains(person[k])) {
                                flag2 = true;
                                index[k] = true;
                                turnText[i][j] = turnText[i][j].replaceAll(person[k], "");
                            }
                        }
                    }
                    if (!turnText[i][j].contains(name)) {
                        turnSum[i][j]++;
                    } else {
                        turnText[i][j] = turnText[i][j].replaceAll(name, "");
                    }
                    if (flag1) {
                        turnText[i][j] += "Person20";
                    }
                    if (flag2) {
                        for (int k = 0; k < 10; k++) {
                            if (index[k]) {
                                turnText[i][j] += person[k];
                            }
                        }
                    }
                }
            }
        }
    }

    public void findByStudents(ArrayList<Student> students, int m, int n) {
        for (int i = 0; i < 20; i++) {
            student = students.get(i);
            turn = student.getSuitableTurns();
            if (turn.size() <= m) {
                for (int j = 0; j < turn.size(); j++) {
                    students.get(i).findTurn();
                    turnIndex = turn.get(j);
                    if (turnCheck[turnIndex.getWorkDay() - 1][turnIndex.getDayTime() - 1] < n) {
                        turnCheck[turnIndex.getWorkDay() - 1][turnIndex.getDayTime() - 1]++;
                        turnResult[turnIndex.getWorkDay() - 1][turnIndex.getDayTime() - 1] += student.getName();
                    }
                }
            }
        }
    }

    public boolean findByTurn(ArrayList<Student> students, int m, int n) {
        boolean flag = true;
        String name = "";
        int index;
        for (int i = 0; i < 20; i++) {
            student = students.get(i);
            if (student.getTurnSum() == m)
                deleteStudent();
        }
        for (int i = 0; i < 5; i++)
            for (int j = 0; j < 4; j++)
                if (turnCheck[i][j] == 0) {
                    if (turnSum[i][j] <= n && turnSum[i][j] != 0) {
                        flag = false;
                        turnResult[i][j] = turnText[i][j];
                        for (int k = 6; k < turnText[i][j].length(); k += 7) {
                            name = turnText[i][j].charAt(k) + "";
                            if (k != turnText[i][j].length() - 1) {
                                if (turnText[i][j].charAt(k + 1) != 'P') {
                                    name += turnText[i][j].charAt(k + 1);
                                    k++;
                                }
                            }
                            index = Integer.valueOf(name);
                            students.get(index - 1).findTurn();
                            turnCheck[i][j]++;
                        }
                    }
                }
        return flag;
    }

    public boolean findAndDeal(ArrayList<Student> students, int m, int n) {
        boolean flag = true;
        int min = Integer.MAX_VALUE, p = 0, q = 0;
        String name = "";
        int index;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 4; j++) {
                if (turnCheck[i][j] == 0 && turnSum[i][j] != 0) {
                    flag = false;
                }
            }
        }
        if (flag) {
            return flag;
        }
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 4; j++) {
                if (turnSum[i][j] > n && min > turnSum[i][j] && turnCheck[i][j] == 0) {
                    p = i;
                    q = j;
                    min = turnSum[i][j];
                }
            }
        }
        min = Integer.MAX_VALUE;
        for (int k = 6; k < turnText[p][q].length(); k += 7) {
            name = turnText[p][q].charAt(k) + "";
            if (k != turnText[p][q].length() - 1) {
                if (turnText[p][q].charAt(k + 1) != 'P') {
                    name += turnText[p][q].charAt(k + 1);
                    k++;
                }
                index = Integer.valueOf(name);
                if (m - students.get(index - 1).getTurnSum() < min) {
                    min = index;
                }
            }
        }
        turnResult[p][q] = students.get(min - 1).getName();
        students.get(min - 1).findTurn();
        turnCheck[p][q]++;
        return flag;
    }

    public void addStudents(ArrayList<Student> students, int m, int n) {
        for (int i = 0; i < 20; i++) {
            student = students.get(i);
            turn = student.getSuitableTurns();
            if (student.getTurnSum() == 0) {
                for (int j = 0; j < turn.size(); j++) {
                    turnIndex = turn.get(j);
                    if (turnCheck[turnIndex.getWorkDay() - 1][turnIndex.getDayTime() - 1] < n) {
                        turnCheck[turnIndex.getWorkDay() - 1][turnIndex.getDayTime() - 1]++;
                        turnResult[turnIndex.getWorkDay() - 1][turnIndex.getDayTime() - 1] += student.getName();
                        student.findTurn();
                        break;
                    }
                }
            }
        }
        for (int i = 0; i < 20; i++) {
            student = students.get(i);
            turn = student.getSuitableTurns();
            if (student.getTurnSum() == 0) {
                for (int j = 0; j < turn.size(); j++) {
                    turnIndex = turn.get(j);
                    if (turnCheck[turnIndex.getWorkDay() - 1][turnIndex.getDayTime() - 1] < n) {
                        turnCheck[turnIndex.getWorkDay() - 1][turnIndex.getDayTime() - 1]++;
                        turnResult[turnIndex.getWorkDay() - 1][turnIndex.getDayTime() - 1] += student.getName();
                        student.findTurn();
                        if (student.getTurnSum() >= n) {
                            break;
                        }
                    }
                }
            }
        }
    }
}
