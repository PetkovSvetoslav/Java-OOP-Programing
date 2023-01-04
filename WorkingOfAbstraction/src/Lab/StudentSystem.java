package Lab;

import java.util.HashMap;
import java.util.Map;

public class StudentSystem {
    private Map<String, Student> studentsByName;

    public StudentSystem() {
        this.studentsByName = new HashMap<>();
    }

    public Map<String, Student> getStudentsByName() {
        return this.studentsByName;
    }

    public String parseCommand(String[] args) {
        String output = null;
        String command = args[0];

        if (command.equals("Create")) {
            addStudent(createNewStudent(args));
        } else if (command.equals("Show")) {
            output = getStudentInfo(args[1]);
        }
        return output;
    }

    private String getStudentInfo(String name) {
        if (contains(name)) {
            return this.studentsByName.get(name).toString();
        }
        return null;
    }

    public boolean contains(String name) {
        return this.studentsByName.containsKey(name);
    }

    public void addStudent(Student student) {
        if (!contains(student.getName())) {
            this.studentsByName.put(student.getName(), student);
        }
    }

    public Student createNewStudent(String[] studentData) {
        String name = studentData[1];
        int age = Integer.parseInt(studentData[2]);
        double grade = Double.parseDouble(studentData[3]);

        return new Student(name, age, grade);
    }
}
