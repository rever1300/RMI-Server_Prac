package ExamModels;

import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;

public class Exam {
    private final List<QuestionsLike> questionsLikes;
    private final HashMap<Integer, Integer> answers;
    private int mark;

    /*This is for knowing if there's more questions to send*/
    private final ListIterator<QuestionsLike> questionsLikeListIterator;

    public Exam(List<QuestionsLike> questionsLikes, HashMap<Integer, Integer> answers) {
        this.questionsLikes = questionsLikes;
        this.answers = answers;
        this.mark = 0;

        this.questionsLikeListIterator = questionsLikes.listIterator();
    }
    public Exam(List<QuestionsLike> questionsLikes, HashMap<Integer, Integer> answers, int mark){
        this.questionsLikes = questionsLikes;
        this.answers = answers;
        this.mark = mark;

        this.questionsLikeListIterator = questionsLikes.listIterator();
    }

    public Integer getMark() {
        return this.mark;
    }

    public boolean hasNext() {
        return this.questionsLikeListIterator.hasNext();
    }

    public QuestionsLike nextQuestion() {
        return this.questionsLikeListIterator.next();
    }

    public boolean answerCorrect(QuestionsLike questionsLike){
        return this.answers.get(questionsLike.getQuestionNum()).equals(questionsLike.getAnswer());
    }

    public void markUp(){
        this.mark++;
    }

    public void toAnswer(QuestionsLike questionsLike){
        if(answerCorrect(questionsLike)) markUp();
    }

    public Exam examInstance(){
        return new Exam(this.questionsLikes, this.answers, this.mark);
    }
}



