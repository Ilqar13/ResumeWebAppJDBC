// 
// Decompiled by Procyon v0.5.36
// 

package com.company.entity1;

public class Skill
{
    private int id;
    private String name;
    
    public Skill() {
    }
    
    public Skill(final int id, final String name) {
        this.id = id;
        this.name = name;
    }

    public Skill(int id) {
        id=id;
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
    
    @Override
    public String toString() {
        return this.name;
    }
}
