// 
// Decompiled by Procyon v0.5.36
// 

package com.company.dao.impl;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import com.company.entity1.Skill;
import java.sql.ResultSet;
import com.company.dao.inter.SkillDaoInter;
import com.company.dao.inter.AbstractDAO;

public class SkillDaoImpl extends AbstractDAO implements SkillDaoInter
{
    private Skill getSkill(final ResultSet rs) throws Exception {
        final int id = rs.getInt("id");
        final String name = rs.getString("name");
        return new Skill(id, name);
    }
    
    @Override
    public List<Skill> getAllSkill() {
        final List<Skill> result = new ArrayList<Skill>();
        try (final Connection c = this.connection()) {
            final Statement stmt = c.createStatement();
            stmt.execute("select * from Skill");
            final ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                result.add(this.getSkill(rs));
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }
    
    @Override
    public Skill getById(final int userId) {
        Skill usr = null;
        try {
            final Connection conn = this.connection();
            final PreparedStatement stmt = conn.prepareStatement("SELECT * FROM skill WHERE ID = ?");
            stmt.setInt(1, userId);
            stmt.execute();
            final ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                final int id = rs.getInt("Id");
                final String name = rs.getString("name");
                usr = new Skill(id, name);
            }
        }
        catch (Exception ex) {}
        return usr;
    }
    
    @Override
    public boolean updateSkill(final Skill u) {
        boolean b = true;
        try {
            final Connection conn = this.connection();
            final PreparedStatement stmt = conn.prepareStatement("UPDATE skill SET name=? WHERE id= ?");
            stmt.setString(1, u.getName());
            stmt.setInt(2, u.getId());
            b = stmt.execute();
        }
        catch (Exception ex) {
            System.err.println(ex);
            b = false;
        }
        return b;
    }
    
    @Override
    public boolean insertSkill(final Skill skl) {
        final boolean b = true;
        try {
            final Connection conn = this.connection();
            final PreparedStatement stmt = conn.prepareStatement("insert skill (id,name) VALUES (?,?);");
            stmt.setInt(1, skl.getId());
            stmt.setString(2, skl.getName());
            return stmt.execute();
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    @Override
    public int getIdByName(final String name) {
        try (final Connection c = this.connection()) {
            final PreparedStatement pstmt = c.prepareStatement("Select id from skill where name=?");
            pstmt.setString(1, name);
            pstmt.execute();
            final ResultSet rs = pstmt.getResultSet();
            if (rs.next()) {
                return rs.getInt("id");
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return -1;
    }
    
    @Override
    public boolean removeSkill(final int id) {
        try {
            final Connection conn = this.connection();
            final PreparedStatement stmt = conn.prepareStatement("DELETE FROM skill WHERE id=?;");
            stmt.setInt(1, id);
            return stmt.execute();
        }
        catch (Exception ex) {
            System.err.println(ex);
            return false;
        }
    }
    
    @Override
    public List<Skill> getByName(final String sname) {
        final List<Skill> list = new ArrayList<Skill>();
        try {
            final Connection conn = this.connection();
            final PreparedStatement stmt = conn.prepareStatement("SELECT * FROM skill WHERE name LIKE ?;");
            stmt.setString(1, sname);
            stmt.execute();
            final ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                final int id = rs.getInt("Id");
                final String name = rs.getString("name");
                list.add(new Skill(id, name));
            }
        }
        catch (Exception ex) {
            System.err.println("Houston, we have a problem");
        }
        return list;
    }
    
    @Override
    public int countSkill() {
        try (final Connection c = this.connection()) {
            final PreparedStatement pstmt = c.prepareStatement("Select COUNT(name) amount from skill");
            pstmt.execute();
            final ResultSet rs = pstmt.getResultSet();
            if (rs.next()) {
                return rs.getInt("amount");
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return -1;
    }
    
    @Override
    public boolean checkId(final int id) {
        try (final Connection c = this.connection()) {
            final PreparedStatement pstmt = c.prepareStatement("Select COUNT(name) amount from skill where id=?");
            pstmt.setInt(1, id);
            pstmt.execute();
            final ResultSet rs = pstmt.getResultSet();
            if (rs.next()) {
                return rs.getInt("amount") == 0;
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }
}
