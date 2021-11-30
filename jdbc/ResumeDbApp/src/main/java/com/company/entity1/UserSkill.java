// 
// Decompiled by Procyon v0.5.36
// 

package com.company.entity1;

public class UserSkill
{
    private Integer id;
    private User user;
    private Skill skill;
    private int power;
    
    public UserSkill() {
    }
    
    public UserSkill(final Integer id, final User user, final Skill skill, final int power) {
        this.id = id;
        this.user = user;
        this.skill = skill;
        this.power = power;
    }
    
    public Integer getId() {
        return this.id;
    }
    
    public void setId(final Integer id) {
        this.id = id;
    }
    
    public User getUser() {
        return this.user;
    }
    
    public void setUser(final User user) {
        this.user = user;
    }
    
    public Skill getSkill() {
        return this.skill;
    }
    
    public void setSkill(final Skill skill) {
        this.skill = skill;
    }
    
    public int getPower() {
        return this.power;
    }
    
    public void setPower(final int power) {
        this.power = power;
    }
    
    @Override
    public String toString() {
        return "UserSkill{id=" + this.id + ", user=" + this.user + ", skill=" + this.skill + ", power=" + this.power + '}';
    }
}
