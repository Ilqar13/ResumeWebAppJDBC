// 
// Decompiled by Procyon v0.5.36
// 

package com.company.dao.impl;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import com.company.entity1.Country;
import java.sql.ResultSet;
import com.company.dao.inter.CountryDaoInter;
import com.company.dao.inter.AbstractDAO;

public class CountryDaoImpl extends AbstractDAO implements CountryDaoInter
{
    private Country getCountry(final ResultSet rs) throws Exception {
        final int id = rs.getInt("id");
        final String name = rs.getString("name");
        final String nationality = rs.getString("nationality");
        return new Country(id, name, nationality);
    }
    
    @Override
    public List<Country> getAllCountry() {
        final List<Country> result = new ArrayList<Country>();
        try (final Connection c = this.connection()) {
            final Statement stmt = c.createStatement();
            stmt.execute("select * from Country");
            final ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                result.add(this.getCountry(rs));
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }
    
    @Override
    public Integer getIdByName(final String name) {
        try (final Connection c = this.connection()) {
            final PreparedStatement pstmt = c.prepareStatement("select * from country where name=?");
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
        return null;
    }
    
    @Override
    public Integer getIdByNationality(final String nationality) {
        try (final Connection c = this.connection()) {
            final PreparedStatement pstmt = c.prepareStatement("select * from country where nationality=?");
            pstmt.setString(1, nationality);
            pstmt.execute();
            final ResultSet rs = pstmt.getResultSet();
            if (rs.next()) {
                return rs.getInt("id");
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
