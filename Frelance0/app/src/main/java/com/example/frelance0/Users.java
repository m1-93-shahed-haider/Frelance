package com.example.frelance0;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.io.Serializable;
import java.io.Serializable;

public class Users implements Serializable {


    private String FullName;
    private String FirstName;
    private String LastName;
    private String Email;
    private String Skill1;
    private String Skill2;
    private String Skill3;
    private String Skill4;
    private String Software1;
    private String Software2;
    private String Software3;
    private String Software4;
    private String Website;
    private String Instagram;
    private String Behance;
    private String JobTitle;

    public Users(String fullName, String firstName, String lastName, String email, String skill1, String skill2, String skill3, String skill4, String software1, String software2, String software3, String software4, String website, String instagram, String behance, String jobTitle, int phoneNum) {
        FullName = fullName;
        FirstName = firstName;
        LastName = lastName;
        Email = email;
        Skill1 = skill1;
        Skill2 = skill2;
        Skill3 = skill3;
        Skill4 = skill4;
        Software1 = software1;
        Software2 = software2;
        Software3 = software3;
        Software4 = software4;
        Website = website;
        Instagram = instagram;
        Behance = behance;
        JobTitle = jobTitle;
        PhoneNum = phoneNum;

    }

    private int PhoneNum;
    private int img;

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getSkill1() {
        return Skill1;
    }

    public void setSkill1(String skill1) {
        Skill1 = skill1;
    }

    public String getSkill2() {
        return Skill2;
    }

    public void setSkill2(String skill2) {
        Skill2 = skill2;
    }

    public String getSkill3() {
        return Skill3;
    }

    public void setSkill3(String skill3) {
        Skill3 = skill3;
    }

    public String getSkill4() {
        return Skill4;
    }

    public void setSkill4(String skill4) {
        Skill4 = skill4;
    }

    public String getSoftware1() {
        return Software1;
    }

    public void setSoftware1(String software1) {
        Software1 = software1;
    }

    public String getSoftware2() {
        return Software2;
    }

    public void setSoftware2(String software2) {
        Software2 = software2;
    }

    public String getSoftware3() {
        return Software3;
    }

    public void setSoftware3(String software3) {
        Software3 = software3;
    }

    public String getSoftware4() {
        return Software4;
    }

    public void setSoftware4(String software4) {
        Software4 = software4;
    }

    public String getWebsite() {
        return Website;
    }

    public void setWebsite(String website) {
        Website = website;
    }

    public String getInstagram() {
        return Instagram;
    }

    public void setInstagram(String instagram) {
        Instagram = instagram;
    }

    public String getBehance() {
        return Behance;
    }

    public void setBehance(String behance) {
        Behance = behance;
    }

    public String getJobTitle() {
        return JobTitle;
    }

    public void setJobTitle(String jobTitle) {
        JobTitle = jobTitle;
    }

    public int getPhoneNum() {
        return PhoneNum;
    }

    public void setPhoneNum(int phoneNum) {
        PhoneNum = phoneNum;
    }


}
