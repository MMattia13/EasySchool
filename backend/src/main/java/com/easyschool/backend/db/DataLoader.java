package com.easyschool.backend.db;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.easyschool.backend.repository.*;
import com.easyschool.backend.model.*;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private CredentialRepository credentialRepository;

    @Autowired
    private ExamRepository examRepository;

    @Autowired
    private FileRepository fileRepository;

    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    private ProgramRepository programRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private SchoolClassRepository schoolClassRepository;

    @Autowired
    private SchoolRepository schoolRepository;

    @Autowired
    private ScoreRepository scoreRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private UserRepository userRepository;

    List<Score> scores = new ArrayList<>();
    List<Lesson> lessons = new ArrayList<>();
    List<File> files = new ArrayList<>();
    List<Subject> subjects = new ArrayList<>();
    List<Exam> exams= new ArrayList<>();

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Loading initial data...");

        // Example data for Credential
        Credential dataLoaderCredential1 = new Credential();
        dataLoaderCredential1.setEmail("john.doe@example.com");
        dataLoaderCredential1.setPassword("password1");
        Credential dataLoaderCredential2 = new Credential();
        dataLoaderCredential2.setEmail("jane.smith@example.com");
        dataLoaderCredential2.setPassword("password2");
        Credential dataLoaderCredential3 = new Credential();
        dataLoaderCredential3.setEmail("alice.johnson@example.com");
        dataLoaderCredential3.setPassword("password3");
        credentialRepository.save(dataLoaderCredential1);
        credentialRepository.save(dataLoaderCredential2);
        credentialRepository.save(dataLoaderCredential3);

        // Example data for Exam
        Exam dataLoaderExam1 = new Exam();
        dataLoaderExam1.setName("Math Exam");
        dataLoaderExam1.setDescription("This is a Math exam.");
        dataLoaderExam1.setDate("2025-05-01");
        dataLoaderExam1.setTime("10:00 AM");
        dataLoaderExam1.setDuration("2 hours");
        Exam dataLoaderExam2 = new Exam();
        dataLoaderExam2.setName("Science Exam");
        dataLoaderExam2.setDescription("This is a Science exam.");
        dataLoaderExam2.setDate("2025-05-02");
        dataLoaderExam2.setTime("11:00 AM");
        dataLoaderExam2.setDuration("1.5 hours");
        dataLoaderExam2.setType("Online");
        Exam dataLoaderExam3 = new Exam();
        dataLoaderExam3.setName("History Exam");
        dataLoaderExam3.setDate("2025-05-03");
        dataLoaderExam3.setTime("1:00 PM");
        dataLoaderExam3.setDuration("3 hours");
        exams.add(dataLoaderExam1);
        exams.add(dataLoaderExam2);
        exams.add(dataLoaderExam3);
        examRepository.save(dataLoaderExam1);
        examRepository.save(dataLoaderExam2);
        examRepository.save(dataLoaderExam3);

        // Example data for File
        File dataLoaderFile1 = new File();
        dataLoaderFile1.setName("file1.pdf");
        dataLoaderFile1.setType("application/pdf");
        dataLoaderFile1.setDescription("This is a PDF file.");
        dataLoaderFile1.setDateCreated("2025-04-01");
        dataLoaderFile1.setDateModified("2025-04-02");
        File dataLoaderFile2 = new File();
        dataLoaderFile2.setName("file2.docx");
        dataLoaderFile2.setType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
        dataLoaderFile2.setDescription("This is a Word document.");
        dataLoaderFile2.setDateCreated("2025-04-03");
        dataLoaderFile2.setDateModified("2025-04-04");
        File dataLoaderFile3 = new File();
        dataLoaderFile3.setName("file3.jpg");
        dataLoaderFile3.setType("image/jpeg");
        dataLoaderFile3.setDescription("This is an image file.");
        dataLoaderFile3.setDateCreated("2025-04-05");
        dataLoaderFile3.setDateModified("2025-04-06");
        files.add(dataLoaderFile1);
        files.add(dataLoaderFile2);
        files.add(dataLoaderFile3);
        fileRepository.save(dataLoaderFile1);
        fileRepository.save(dataLoaderFile2);
        fileRepository.save(dataLoaderFile3);

        // Example data for Lesson
        Lesson dataLoaderLesson1 = new Lesson();
        dataLoaderLesson1.setName("Math Lesson");
        Lesson dataLoaderLesson2 = new Lesson();
        dataLoaderLesson2.setName("Science Lesson");
        dataLoaderLesson2.setDescription("This is a Science lesson.");
        Lesson dataLoaderLesson3 = new Lesson();
        dataLoaderLesson3.setName("History Lesson");
        dataLoaderLesson1.setDescription("This is a Math lesson.");
        lessons.add(dataLoaderLesson1);
        lessons.add(dataLoaderLesson2);
        lessons.add(dataLoaderLesson3);
        lessonRepository.save(dataLoaderLesson1);
        lessonRepository.save(dataLoaderLesson2);
        lessonRepository.save(dataLoaderLesson3);

        // Example data for Program
        Program dataLoaderProgram1 = new Program();
        dataLoaderProgram1.setName("Math Program");
        Program dataLoaderProgram2 = new Program();
        dataLoaderProgram2.setName("Science Program");
        Program dataLoaderProgram3 = new Program();
        dataLoaderProgram3.setName("History Program");
        programRepository.save(dataLoaderProgram1);
        programRepository.save(dataLoaderProgram2);
        programRepository.save(dataLoaderProgram3);

        // Example data for Role
        Role dataLoaderRole1 = new Role();
        dataLoaderRole1.setName("ADMIN");
        Role dataLoaderRole2 = new Role();
        dataLoaderRole2.setName("MODERATOR");
        Role dataLoaderRole3 = new Role();
        dataLoaderRole3.setName("USER");
        roleRepository.save(dataLoaderRole1);
        roleRepository.save(dataLoaderRole2);
        roleRepository.save(dataLoaderRole3);

        // Example data for School
        School dataLoaderSchool1 = new School();
        dataLoaderSchool1.setName("School 1");
        School dataLoaderSchool2 = new School();
        dataLoaderSchool2.setName("School 2");
        School dataLoaderSchool3 = new School();
        dataLoaderSchool3.setName("School 3");
        schoolRepository.save(dataLoaderSchool1);
        schoolRepository.save(dataLoaderSchool2);
        schoolRepository.save(dataLoaderSchool3);

        // Example data for SchoolClass
        SchoolClass dataLoaderClass1 = new SchoolClass();
        dataLoaderClass1.setName("Class 1");
        SchoolClass dataLoaderClass2 = new SchoolClass();
        dataLoaderClass2.setName("Class 2");
        SchoolClass dataLoaderClass3 = new SchoolClass();
        dataLoaderClass3.setName("Class 3");
        schoolClassRepository.save(dataLoaderClass1);
        schoolClassRepository.save(dataLoaderClass2);
        schoolClassRepository.save(dataLoaderClass3);

        // Example data for Score
        Score dataLoaderScore1 = new Score();
        dataLoaderScore1.setValue(95);
        Score dataLoaderScore2 = new Score();
        dataLoaderScore2.setValue(88);
        Score dataLoaderScore3 = new Score();
        dataLoaderScore3.setValue(76);
        scores.add(dataLoaderScore1);
        scores.add(dataLoaderScore2);
        scores.add(dataLoaderScore3);
        scoreRepository.save(dataLoaderScore1);
        scoreRepository.save(dataLoaderScore2);
        scoreRepository.save(dataLoaderScore3);

        // Example data for Subject
        Subject dataLoaderSubject1 = new Subject();
        dataLoaderSubject1.setName("Mathematics");
        Subject dataLoaderSubject2 = new Subject();
        dataLoaderSubject2.setName("Science");
        Subject dataLoaderSubject3 = new Subject();
        dataLoaderSubject3.setName("History");
        subjects.add(dataLoaderSubject1);
        subjects.add(dataLoaderSubject2);
        subjects.add(dataLoaderSubject3);
        subjectRepository.save(dataLoaderSubject1);
        subjectRepository.save(dataLoaderSubject2);
        subjectRepository.save(dataLoaderSubject3);

        // Example data for User
        User dataLoaderUser1 = new User();
        dataLoaderUser1.setName("John");
        dataLoaderUser1.setSurname("Doe");
        dataLoaderUser1.setCredentials(dataLoaderCredential1);
        dataLoaderUser1.setRole(dataLoaderRole1);
        dataLoaderUser1.setFile(files);
        User dataLoaderUser2 = new User();
        dataLoaderUser2.setName("Jane");
        dataLoaderUser2.setSurname("Smith");
        dataLoaderUser2.setCredentials(dataLoaderCredential2);
        dataLoaderUser2.setRole(dataLoaderRole2);
        dataLoaderUser2.setSubject(subjects);
        User dataLoaderUser3 = new User();
        dataLoaderUser3.setName("Alice");
        dataLoaderUser3.setSurname("Johnson");
        dataLoaderUser3.setCredentials(dataLoaderCredential3);
        dataLoaderUser3.setRole(dataLoaderRole3);
        dataLoaderUser3.setExams(exams);
        dataLoaderUser3.setScore(scores);
        dataLoaderUser3.setLesson(lessons);
        userRepository.save(dataLoaderUser1);
        userRepository.save(dataLoaderUser2);
        userRepository.save(dataLoaderUser3);
    }
}
