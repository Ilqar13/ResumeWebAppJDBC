// 
// Decompiled by Procyon v0.5.36
// 

package com.company.dao.impl;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;
import com.company.entity1.User;
import com.company.entity1.EmploymentHistory;
import java.sql.ResultSet;
import com.company.dao.inter.EmploymentHistoryDaoInter;
import com.company.dao.inter.AbstractDAO;

public class EmploymentHistoryDaoImpl extends AbstractDAO implements EmploymentHistoryDaoInter
{
    public EmploymentHistory getEmploymentHistory(final ResultSet rs) throws Exception {
        final int userId = rs.getInt("user_id");
        final String header = rs.getString("header");
        final Date beginDate = rs.getDate("begin_date");
        final Date endDate = rs.getDate("end_date");
        final String jobDescription = rs.getString("job_description");
        return new EmploymentHistory(new User(userId), header, beginDate, endDate, jobDescription);
    }
    
    @Override
    public List<EmploymentHistory> getAllEmploymentHistory() {
        final List<EmploymentHistory> result = new ArrayList<EmploymentHistory>();
        try (final Connection c = this.connection()) {
            final Statement stmt = c.createStatement();
            stmt.execute("select * from employment_history");
            final ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                System.out.println(rs.next());
                result.add(this.getEmploymentHistory(rs));
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }
    
    @Override
    public EmploymentHistory getEmploymentHistoryByUserId(final int userID) {
        try (final Connection c = this.connection()) {
            final Statement stmt = c.createStatement();
            stmt.execute("select * from employment_history where user_id=" + userID);
            final ResultSet rs = stmt.getResultSet();
            if (rs.next()) {
                return this.getEmploymentHistory(rs);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    @Override
    public boolean updateEmploymentHistory(final EmploymentHistory eh) throws Exception {
        final Connection c = this.connection();
        final PreparedStatement pstmt = c.prepareStatement("update employment_history set header=?,begin_date=?,end_date=?,job_description=? where user_id=?");
        pstmt.setString(1, eh.getHeader());
        pstmt.setDate(2, eh.getBeginDate());
        pstmt.setDate(3, eh.getEndDate());
        pstmt.setString(4, eh.getJobDescription());
        pstmt.setInt(5, eh.getUser().getId());
        pstmt.execute();
        return true;
    }

    @Override
    public boolean removeEmploymentHistory(int userId) {
        try (final Connection c = this.connection()) {
            final Statement stmt = c.createStatement();
            return stmt.execute("delete from employment_history where user_id=" + userId);
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
         }
    
    
}
