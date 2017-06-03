package com.allstate.springboot.domain;

import java.math.BigDecimal;

public class Policy {

    private long policyNumber;

    private String firstName;

    private String lastName;

    private int age;

    private BigDecimal premium;

    public Policy() {
    }

    public Policy(long policyNumber, String firstName, String lastName, int age) {
        this.policyNumber = policyNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public long getPolicyNumber() {
        return policyNumber;
    }

    public void setPolicyNumber(long policyNumber) {
        this.policyNumber = policyNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public BigDecimal getPremium() {
        return premium;
    }

    public void setPremium(BigDecimal premium) {
        this.premium = premium;
    }
}
