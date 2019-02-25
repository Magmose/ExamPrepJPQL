package facade;

import entity.Student;
import entity.Teacher;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import mappers.StudentInfo;

public class FacadeHolder {

    EntityManagerFactory emf;

    public FacadeHolder(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public List<Student> getAllStudents() {
        EntityManager em = emf.createEntityManager();
        try {
            Query q = em.createQuery("SELECT s FROM Student s");
            return (List<Student>) q.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Student> getAllStudentsByOneName(String name) {
        EntityManager em = emf.createEntityManager();
        try {
            Query q = em.createQuery("SELECT s FROM Student s WHERE s.firstname = :firstname");
            q.setParameter("firstname", name);
            return (List<Student>) q.getResultList();
        } finally {
            em.close();
        }
    }

    public Student CreateStudent(String firstname, String lastname) {
        EntityManager em = emf.createEntityManager();
        Student stud = new Student(firstname, lastname);
        try {
            em.getTransaction().begin();
            em.persist(stud);
            em.getTransaction().commit();
            return stud;
        } finally {
            em.close();
        }
    }

    public Student getStudentById(Integer id) {
        EntityManager em = emf.createEntityManager();
        try {
            return (Student) em.createQuery("SELECT s FROM Student s WHERE s.id = :id").setParameter("id", id).getSingleResult();
        } finally {
            em.close();
        }
    }

    /*
    public void assaginStudentToSemester(Student stud, Integer semester) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Query q = em.createQuery("UPDATE Student s SET s.semester.id = :semester WHERE s.id = :id ");
            q.setParameter("semester", semester);
            q.setParameter("id", stud.getId());
       
            q.executeUpdate();
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
     */
    public List<Student> getAllStudentsByOneLastName(String name) {
        EntityManager em = emf.createEntityManager();
        try {
            Query q = em.createQuery("SELECT s FROM Student s WHERE s.lastname = :lastname");
            q.setParameter("lastname", name);
            return (List<Student>) q.getResultList();
        } finally {
            em.close();
        }
    }

    /*
    "SELECT NEW mappers.CustomerSimple(c.contactFirstName, c.contactLastName, c.customerName,COUNT(o.customer.customerNumber)) "
                    + "FROM Customer AS c, OrderClassic AS o WHERE c.customerNumber = o.customer.customerNumber GROUP BY o.customer.customerNumber";
     */
    public Long getAllStudentsBySemester(String name) {
        EntityManager em = emf.createEntityManager();
        try {
            Query q = em.createQuery("SELECT count(s.semester) FROM Student s WHERE s.semester.name = :name");
            q.setParameter("name", name);
            return (Long) q.getSingleResult();
        } finally {
            em.close();
        }
    }

    public Long getAllStudentsForAllSemesters() {
        EntityManager em = emf.createEntityManager();
        try {
            Query q = em.createQuery("SELECT count(s.semester) FROM Student s");
            return (Long) q.getSingleResult();
        } finally {
            em.close();
        }
    }

//Mangler
    public Teacher getTeacherWithMostClass() {
        EntityManager em = emf.createEntityManager();
        try {
            Query q = em.createQuery("SELECT t FROM Teacher t WHERE t.id = (SELECT MAX(tx.id) FROM Teacher tx, Semester s WHERE s.teacherCollection.id = tx.id");
            return (Teacher) q.getSingleResult();
        } finally {
            em.close();
        }
    }

    public List<StudentInfo> getStudentInfos() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<StudentInfo> query = em.createQuery("SELECT NEW mappers.StudentInfo"
                    + "( s.firstname,s.lastname, s.id, s.semester.name, s.semester.description) FROM Student AS s", StudentInfo.class);
            List<StudentInfo> results = query.getResultList();
            return results;
        } finally {
            em.close();
        }
    }
        public StudentInfo getStudentInfoById(Integer id) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<StudentInfo> query = em.createQuery("SELECT NEW mappers.StudentInfo( s.firstname,s.lastname, s.id, s.semester.name, s.semester.description) FROM Student AS s WHERE s.id = "+id, StudentInfo.class);
            StudentInfo results = query.getSingleResult();
            return results;
        } finally {
            em.close();
        }
    }
}
