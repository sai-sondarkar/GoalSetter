package edu.itm.goalgetter.Models;

import java.util.Objects;

public class UserModel {

    public String uid;
    public String name;
    public String emailId;
    public String degree;
    public String branch;
    public int ssc;
    public int hsc;
    public int year;
    public int currentYear;

    public long timeStamp;


    public UserModel() {
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public int getSsc() {
        return ssc;
    }

    public void setSsc(int ssc) {
        this.ssc = ssc;
    }

    public int getHsc() {
        return hsc;
    }

    public void setHsc(int hsc) {
        this.hsc = hsc;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getCurrentYear() {
        return currentYear;
    }

    public void setCurrentYear(int currentYear) {
        this.currentYear = currentYear;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserModel userModel = (UserModel) o;
        return Objects.equals(uid, userModel.uid);
    }

    @Override
    public int hashCode() {

        return Objects.hash(uid);
    }
}
