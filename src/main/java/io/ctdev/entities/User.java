package io.ctdev.entities;

public class User {
    private String email;
    private String password;
    private SecurityQuestion securityQuestion;
    private String answer;

    public User(String email, String password, SecurityQuestion securityQuestion, String answer) {
        this.email = email;
        this.password = password;
        this.securityQuestion = securityQuestion;
        this.answer = answer;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public SecurityQuestion getSecurityQuestion() {
        return securityQuestion;
    }

    public String getAnswer() {
        return answer;
    }
}
