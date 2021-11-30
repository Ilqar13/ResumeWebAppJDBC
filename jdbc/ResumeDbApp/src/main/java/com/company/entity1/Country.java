// 
// Decompiled by Procyon v0.5.36
// 

package com.company.entity1;

public class Country
{
    private int id;
    private String name;
    private String nationality;
    
    public Country() {
    }
    
    public Country(final int id, final String name, final String nationality) {
        this.id = id;
        this.name = name;
        this.nationality = nationality;
    }

    public Country(int id) {
        this.id = id;
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
    
    public String getCountryName() {
        return this.nationality;
    }
    
    public void setCountryName(final String nationality) {
        this.nationality = nationality;
    }
    
    @Override
    public int hashCode() {
        return this.id;
    }
    
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        final Country other = (Country)obj;
        return this.id == other.id;
    }
    
    @Override
    public String toString() {
        return this.name + "{" + this.nationality + "]";
    }
}
