// 
// Decompiled by Procyon v0.5.36
// 

package com.company.entity1;

import java.sql.Blob;
import java.util.List;
import java.sql.Date;

public class User
{
    private int id;
    private String name;
    private String surname;
    private String phone;
    private String email;
    private String profileDesc;
    private String address;
    private Date birthDate;
    private Country nationality;
    private Country birthplace;
    private List<UserSkill> skills;
    private Blob userImage;
    private String userImageName;
    private String password;
    public User() {
    }
    
    public User(final int id) {
        this.id = id;
    }
    
    public User(final int id, final String name, final String surname, final String phone, final String email, final String profileDesc, final String address, final Date birthDate, final Country nationality, final Country birthplace,final Blob userImage,final String userImageName,String password) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.email = email;
        this.profileDesc = profileDesc;
        this.address = address;
        this.birthDate = birthDate;
        this.nationality = nationality;
        this.birthplace = birthplace;
        this.userImage=userImage;
        this.userImageName=userImageName;
        this.password=password;
    }
    
    public String getAddress() {
        return this.address;
    }
    
    public void setAddress(final String address) {
        this.address = address;
    }
    
    public String getProfileDesc() {
        return this.profileDesc;
    }
    
    public void setProfileDesc(final String profileDesc) {
        this.profileDesc = profileDesc;
    }
    
    
    public Date getBirthDate() {
        return this.birthDate;
    }
    
    public Country getNationality() {
        return this.nationality;
    }
    
    public void setNationality(final Country nationality) {
        this.nationality = nationality;
    }
    
    public List<UserSkill> getSkills() {
        return this.skills;
    }
    
    public void setSkills(final List<UserSkill> skills) {
        this.skills = skills;
    }
    
    public void setBirthDate(final Date birthDate) {
        this.birthDate = birthDate;
    }
    
    public Country getCountry() {
        return this.nationality;
    }
    
    public void setCountry(final Country nationality) {
        this.nationality = nationality;
    }
    
    public Country getBirthplace() {
        return this.birthplace;
    }
    
    public void setBirthplace(final Country birthplace) {
        this.birthplace = birthplace;
    }
    
    public String getPhone() {
        return this.phone;
    }
    
    public void setPhone(final String phone) {
        this.phone = phone;
    }
    
    public int getId() {
        return this.id;
    }
    
    public void setId(final int id) {
        this.id = id;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setName(final String name) {
        this.name = name;
    }
    
    public String getSurname() {
        return this.surname;
    }
    
    public void setSurname(final String surname) {
        this.surname = surname;
    }
    
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(final String email) {
        this.email = email;
    }

    public Blob getUserImage() {
        return userImage;
    }

    public void setUserImage(Blob userImage) {
        this.userImage = userImage;
    }

     public String getUserImageName(){
        return userImageName;
    }

    public void setUserImageName(String userImageName) {
        this.userImageName = userImageName;
    }
    
    public String getPassword(){
        return this.password;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", name=" + name + ", surname=" + surname + ", phone=" + phone + ", email=" + email + ", profileDesc=" + profileDesc + ", address=" + address + ", birthDate=" + birthDate + ", nationality=" + nationality + ", birthplace=" + birthplace + ", skills=" + skills + ", userImage=" + userImage + ", userImageName=" + userImageName + '}';
    }
    
    
    
}
