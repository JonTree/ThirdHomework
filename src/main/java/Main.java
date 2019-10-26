import data.Data;
import data.Student;
import data.Turn;
import data.TurnBook;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final int m = 2;
    private static final int n = 2;

    public static void main(String[] args) {
        Data data = new Data();
        ArrayList<Student> students;
        TurnBook turnBook = new TurnBook();
        Boolean flag;
        Boolean flag2;
        //读取四组数据
        for (int k = 1; k <= 4; k++) {
            students = data.datas.get(k-1);
            flag=false;
            flag2=false;
            //先将学生放入时间段的二维数组中
            turnBook.placeStudents(students);
            //安排可值班时间段小于等于m的学生
            turnBook.findByStudents(students, m,n);
            System.out.println("\n\nNumber"+k+":");
            while (!flag2) {
                flag = false;
                while (!flag) {
                    //安排可值班学生数小于等于n的时间段 并 删除已安排值班数达到m的学生
                    flag = turnBook.findByTurn(students, m, n);
                }
                //安排可值班学生数大于n的时间段
                flag2 = turnBook.findAndDeal(students, m, n);
            }
            turnBook.addStudents(students, m, n);
            //安排没有值班的学生并尽可能保证时间段中学生数接近n
            for (int i = 1; i <= 5; i++) {
                System.out.println("星期" + i);
                for (int j = 1; j <= 4; j++) {
                    System.out.println(j + "轮：  " + turnBook.getTurResult(i - 1, j - 1));
                }
            }
        }
    }
}
