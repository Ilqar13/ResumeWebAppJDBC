// 
// Decompiled by Procyon v0.5.36
// 

package com.company.entity1;

import java.sql.Date;

public class EmploymentHistory
{
    private User user;
    private String header;
    private Date beginDate;
    private Date endDate;
    private String jobDescription;
    
    public EmploymentHistory() {
    }
    
    public EmploymentHistory(final User user, final String header, final Date beginDate, final Date endDate, final String jobDescription) {
        this.user = user;
        this.header = header;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.jobDescription = jobDescription; 
    }
    
    
    public String getHeader() {
        return this.header;
    }
    
    public void setHeader(final String header) {
        this.header = header;
    }
    
    public Date getBeginDate() {
        return this.beginDate;
    }
    
    public void setBeginDate(final Date beginDate) {
        this.beginDate = beginDate;
    }
    
    public Date getEndDate() {
        return this.endDate;
    }
    
    public void setEndDate(final Date endDate) {
        this.endDate = endDate;
    }
    
    public String getJobDescription() {
        return this.jobDescription;
    }
    
    public void setJobDescription(final String jobDescription) {
        this.jobDescription = jobDescription;
    }
    
    public User getUser() {
        return this.user;
    }
    
    public void setUser(final User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "EmploymentHistory{" + "user=" + user + ", header=" + header + ", beginDate=" + beginDate + ", endDate=" + endDate + ", jobDescription=" + jobDescription + '}';
    }
    
   
}
