package example.day002.controller;

public class ExamDto {
    private String name;
    private int age;

    public ExamDto(){}
    public ExamDto(String name, int age) {
        this.name = name;        this.age = age;
    }

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public int getAge() {return age;}
    public void setAge(int age) {this.age = age;}

    @Override
    public String toString() {
        return "ExamDto{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
