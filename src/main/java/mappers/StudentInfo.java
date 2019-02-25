package mappers;

public class StudentInfo {

    public String fullName;
    public long studentId;
    public String classNameThisSemester;
    public String classDescription;

    public StudentInfo(String firstName, String lastName, long studentId, String classNameThisSemester, String classDescription) {
        this.fullName = firstName +" "+ lastName;
        this.studentId = studentId;
        this.classNameThisSemester = classNameThisSemester;
        this.classDescription = classDescription;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    public String getClassNameThisSemester() {
        return classNameThisSemester;
    }

    public void setClassNameThisSemester(String classNameThisSemester) {
        this.classNameThisSemester = classNameThisSemester;
    }

    public String getClassDescription() {
        return classDescription;
    }

    public void setClassDescription(String classDescription) {
        this.classDescription = classDescription;
    }
    
}
