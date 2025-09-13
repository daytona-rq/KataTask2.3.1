package academy.kata.model;

import Utils.Text;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;



@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotBlank(message = Text.validationNameNotBlank)
    @Size(min = 2, max = 50, message = Text.validationNameLength)
    @Column(name = "name", nullable = false)
    private String name;

    @Min(value = 0, message = Text.validationAgeNotInRange)
    @Max(value = 150, message = Text.validationAgeNotInRange)
    @Column(name = "age", nullable = false)
    private int age;

    @Column(name = "has_car")
    private boolean hasCar = false;

    public User(String name, int age, boolean hasCar) {
        this.name = name;
        this.age = age;
        this.hasCar = hasCar;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isHasCar() {
        return hasCar;
    }

    public void setHasCar(boolean hasCar) {
        this.hasCar = hasCar;
    }

}
