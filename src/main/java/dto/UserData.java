package dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UserData {
    public String name;
    public String cource;
    public String email;
    public int age;

    public UserData() {

    }

    public String getName() {
        return name;
    }

    public String getCource() {
        return cource;
    }

    public String getEmail() {
        return email;
    }

    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCource(String cource) {
        this.cource = cource;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
