package com.example.demo.dto;

/**
 * Data‑transfer object that exposes only the fields we want to send to the client.
 * Extend or trim as your business rules evolve.
 */
public class UserDetailDto {

    // ── UserDetail core fields ────────────────────────────────────────────────
    private Long    id;
    private Integer age;
    private String  hobby;
    private String  car;

    // ── Flattened User entity fields ─────────────────────────────────────────
    private String  userName;
    private String  userEmail;

    // ── Constructors ─────────────────────────────────────────────────────────
    public UserDetailDto() { }

    public UserDetailDto(Long id,
                         Integer age,
                         String hobby,
                         String car,
                         String userName,
                         String userEmail) {
        this.id         = id;
        this.age        = age;
        this.hobby      = hobby;
        this.car        = car;
        this.userName   = userName;
        this.userEmail  = userEmail;
    }

    // ── Getters & Setters ────────────────────────────────────────────────────
    public Long getId()               { return id; }
    public void setId(Long id)        { this.id = id; }

    public Integer getAge()           { return age; }
    public void setAge(Integer age)   { this.age = age; }

    public String getHobby()          { return hobby; }
    public void setHobby(String hobby){ this.hobby = hobby; }

    public String getCar()            { return car; }
    public void setCar(String car)    { this.car = car; }

    public String getUserName()       { return userName; }
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail()      { return userEmail; }
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    // ── Builder‑style convenience (optional) ────────────────────────────────
    public UserDetailDto withId(Long id) {
        this.id = id; return this;
    }
    public UserDetailDto withAge(Integer age) {
        this.age = age; return this;
    }
    public UserDetailDto withHobby(String hobby) {
        this.hobby = hobby; return this;
    }
    public UserDetailDto withCar(String car) {
        this.car = car; return this;
    }
    public UserDetailDto withUserName(String userName) {
        this.userName = userName; return this;
    }
    public UserDetailDto withUserEmail(String userEmail) {
        this.userEmail = userEmail; return this;
    }
}
