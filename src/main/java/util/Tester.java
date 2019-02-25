package util;

import entity.Student;
import entity.Teacher;
import facade.FacadeHolder;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import mappers.StudentInfo;

public class Tester {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPA_examPreparationJpql_jar_1.0-SNAPSHOTPU");
        FacadeHolder facade = new FacadeHolder(emf);
//        getAllStudents(facade);
//        getAllStudentsByOneName(facade);       
//        createStudent(facade);
//        getAllStudentsByOneLastName(facade);
//        getAllStudentsBySemester(facade);
//        getTotalAmountInSemesters(facade);
//        getAllStudentInfos(facade);
//        getStudentInfoById(facade);
    }

    private static void getStudentInfoById(FacadeHolder facade) {
        StudentInfo si = facade.getStudentInfoById(1);
        System.out.println("FullName:" + si.getFullName() + ". Id:"+ si.getStudentId() + ". ClassName:"
                + si.getClassNameThisSemester()+ ". Desc:"+ si.getClassDescription() );
    }

    private static void getAllStudentInfos(FacadeHolder facade) {
        List<StudentInfo> cs = facade.getStudentInfos();
        for (int i = 0; i < cs.size(); i++) {
            System.out.println("FullName:" + cs.get(i).getFullName() + ". Id:"+ cs.get(i).getStudentId() + ". ClassName:" 
                    + cs.get(i).getClassNameThisSemester() + ". Desc:"+ cs.get(i).getClassDescription());
        }
    }

    private static void createStudent(FacadeHolder facade) {
        Student stud = facade.CreateStudent("Magnus", "Tester");
        System.out.println("Name: " + stud.getFirstname());
    }

    private static void getAllStudentsByOneName(FacadeHolder facade) {
        List<Student> studentsName = facade.getAllStudentsByOneName("Anders");
        for (int i = 0; i < studentsName.size(); i++) {
            System.out.println(studentsName.get(i).getFirstname());
        }
    }

    private static void getAllStudentsByOneLastName(FacadeHolder facade) {
        List<Student> studentsName = facade.getAllStudentsByOneLastName("And");
        for (int i = 0; i < studentsName.size(); i++) {
            System.out.println(studentsName.get(i).getFirstname() + " " + studentsName.get(i).getLastname());
        }
    }

    private static void getAllStudentsBySemester(FacadeHolder facade) {
        Long studentsInClass = facade.getAllStudentsBySemester("CLcos-v14e");
        System.out.println(studentsInClass);
    }

    private static void getTotalAmountInSemesters(FacadeHolder facade) {
        Long allStudents = facade.getAllStudentsForAllSemesters();
        System.out.println(allStudents);
    }

    private static void getAllStudents(FacadeHolder facade) {
        List<Student> students = facade.getAllStudents();
        for (int i = 0; i < students.size(); i++) {
            System.out.println(students.get(i).getFirstname());
        }
    }

}
