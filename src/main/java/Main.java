import scheduling.Schedule;
import data.Data;

public class Main {
    public static void main(String[] args) {
        Data data = new Data();
        Schedule s = new Schedule();
        for (int i = 0; i < data.datas.size(); i++) {
            System.out.println("根据data" + (i + 1) + "得出下表:");
            s.getTable(data.datas.get(i));
            s.print();
        }
    }
}
