package ExamModels;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ParserCSV {
    public static Exam build(String path) throws IOException {
        List<QuestionsLike> questions = new ArrayList<>();
        HashMap<Integer, Integer> answers = new HashMap<>();

        /*How to get all data of the csv*/
        BufferedReader csvReader = new BufferedReader(new FileReader(path));
        String row;
        String[] data;

        while ((row = csvReader.readLine()) != null) {
            data = row.split(";");
            int questionNumber = questions.size() + 1;
            String QT = data[0];
            List<String> choices = new ArrayList<>(Arrays.asList(data).subList(0, data.length - 1));
            QuestionsLike question = new QuestionsLike(questionNumber, QT, choices);
            answers.put(questionNumber, Integer.parseInt(data[data.length - 1]));
            questions.add(question);
        }

        return new Exam(questions, answers);
    }
}