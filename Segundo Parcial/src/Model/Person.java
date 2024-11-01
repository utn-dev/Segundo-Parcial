package Model;

import java.util.Objects;

public class Person {
    private String fullName;
    private Integer age;
    private String neighbourhood;
    private String dni;
    private String occupation;
    private Integer kit;

    public Person() {
    }

    public Person(String fullName, Integer age, String neighbourhood, String dni, String occupation) {
        this.fullName = fullName;
        this.age = age;
        this.neighbourhood = neighbourhood;
        this.dni = dni;
        this.occupation = occupation;
    }


    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getNeighbourhood() {
        return neighbourhood;
    }

    public void setNeighbourhood(String neighbourhood) {
        this.neighbourhood = neighbourhood;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public Integer getKit() {
        return kit;
    }

    public void setKit(Integer kit) {
        this.kit = kit;
    }

    @Override
    public String toString() {
        return "Person{" +
                "fullName='" + fullName + '\'' +
                ", age=" + age +
                ", neighbourhood='" + neighbourhood + '\'' +
                ", dni='" + dni + '\'' +
                ", occupation='" + occupation + '\'' +
                ", kit=" + kit +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(dni, person.dni);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(dni);
    }
}
