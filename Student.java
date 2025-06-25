public class Student {
    private int id;
    private String name;
    private double marks;

    public Student(int id, String name, double marks) {
        this.id = id;
        this.name = name;
        this.marks = marks;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getMarks() { return marks; }
    public void setMarks(double marks) { this.marks = marks; }

    public String getGrade() {
        double m = this.marks;
        if (m >= 90) return "A+";
        else if (m >= 80) return "A";
        else if (m >= 70) return "B";
        else if (m >= 60) return "C";
        else if (m >= 50) return "D";
        else return "F";
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Marks: " + marks + ", Grade: " + getGrade();
    }
}
