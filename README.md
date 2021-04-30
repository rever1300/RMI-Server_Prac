# RMI-Teacher_Student_Prac (SERVER)
This code describes the functionality of an exam simulator using RMI (client-server approach).

##### The procedure is given by the next steps:

* The professor uploads a csv file that contains the questions, the choices and the correct answer in this format:

        Question?;choice1;choice2;choice3;correct_answer_number
    
* The professor strats the exam session:
    * The professor needs to know how many students are in the session.

* The professor indicates the start of the exam:
    * So, the students will not be able to enter to the session once is started (A message will be shown).
    
* The server will be starting to send the questions one-by-one without showing the correct answer.

* The students chose the answer and send it to the server:
    * It is possible that one student takes more time than others to answer the questions, therefore there should not be any problem to other students.

* When a student finishes, he will recieve the grade and his interaction with the server will finish:
    * If an student disconnects while the exam session, the exam will remain as it is and the grade will be shown (current exam).

* When the professor decides to finish the session, all students will receive the grade even if they had not finish the exam.

* All grades will be stored by the professor.