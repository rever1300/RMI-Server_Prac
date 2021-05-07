package ExamModels;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FinalExamsFile {

    public static void storeExam(String path, HashMap<String, Exam> studentExam){
        FileWriter fileWriter;

        try{
            fileWriter = new FileWriter(path);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            String fullLine;

            for(Map.Entry<String, Exam> student : studentExam.entrySet()){
                String studentID = student.getKey();
                String mark = Integer.toString(student.getValue().getMark());
                fullLine = studentID + " --> " + mark + "\n";
                bufferedWriter.write(fullLine);
            }

            bufferedWriter.close(); fileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
