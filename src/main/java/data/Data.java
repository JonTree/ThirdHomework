package data;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class Data {
    public ArrayList<ArrayList<Student>> datas = new ArrayList<>();
    private Gson gson = new Gson();

    public Data() {
        File file = new File("Data");
        File[] files = file.listFiles();
        for (int i = 0; i < files.length; i++) {
            String data = null;
            try {
                data = read(files[i].getAbsolutePath());
            } catch (IOException e) {
                e.printStackTrace();
            }
            datas.add(gson.fromJson(data, new TypeToken<ArrayList<Student>>(){}.getType()));
        }
    }

    private String read(String fileName) throws IOException {
        FileInputStream inStream = new FileInputStream(fileName);
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, len);
        }
        byte[] data = outStream.toByteArray();
        return new String(data);
    }
}
