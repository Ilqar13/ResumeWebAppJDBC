
package com.company.dao.impl;

import at.favre.lib.crypto.bcrypt.BCrypt;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.Date;
import com.company.entity1.Country;
import com.company.entity1.User;
import java.sql.ResultSet;
import com.company.dao.inter.UserDaoInter;
import com.company.dao.inter.AbstractDAO;
import java.sql.Blob;

public class UserDaoImpl extends AbstractDAO implements UserDaoInter
{
    private static User getUser(final ResultSet rs) throws Exception {
        final int id = rs.getInt("id");
        final String name = rs.getString("name");
        final String surname = rs.getString("surname");
        final String phone = rs.getString("phone");
        final String email = rs.getString("email");
        final String profileDescription = rs.getString("profile_description").trim();
        final String address = rs.getString("address");
        final int nationalityId = rs.getInt("nationality_id");
        final int birthplaceId = rs.getInt("birthplace_id");
        final String nationalityStr = rs.getString("nationality");
        final String birthplaceStr = rs.getString("birthplace");
        final Date birthdate = rs.getDate("birthDate");
        final Country nationality = new Country(nationalityId, birthplaceStr, nationalityStr);
        final Country birthplace = new Country(birthplaceId, birthplaceStr, nationalityStr);
        final Blob userImage=rs.getBlob("user_image");
        final String userImageName=rs.getString("user_image_name");
        final String password=rs.getString("password");
        return new User(id, name, surname, phone, email, profileDescription, address, birthdate, nationality, birthplace,userImage,userImageName,password);
    }
    
    @Override
    public User getById(final String userId) {
        try (final Connection c = this.connection()) {
            final PreparedStatement pstmt = c.prepareStatement("SELECT u.*,  n.nationality AS nationality, c.name AS birthplace  FROM USER u LEFT JOIN country n ON u.nationality_id = n.id LEFT JOIN country c ON u.birthplace_id = c.id where u.id=?");
            pstmt.setString(1, userId);
            pstmt.execute();
            final ResultSet rs = pstmt.getResultSet();
            if (rs.next()) {
                return this.getUser(rs);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    @Override
    public User getByGmail(String userGmail) {
        try (final Connection c = this.connection()) {
            final PreparedStatement pstmt = c.prepareStatement("SELECT u.*,  n.nationality AS nationality, c.name AS birthplace  FROM USER u LEFT JOIN country n ON u.nationality_id = n.id LEFT JOIN country c ON u.birthplace_id = c.id where u.email=?");
            pstmt.setString(1, userGmail);
            pstmt.execute();
            final ResultSet rs = pstmt.getResultSet();
            if (rs.next()) {
                return this.getUser(rs);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
      
    
    
    @Override
    public List<User> getAll(String name,String surname) {
        final List<User> result = new ArrayList<User>();
        try {
            final Connection c = this.connection();
           
            String sql="SELECT u.*,  n.nationality AS"
                    + " nationality, c.name AS birthplace  "
                    + "FROM USER u LEFT JOIN country n ON u.nationality_id = "
                    + "n.id LEFT JOIN country c ON u.birthplace_id = c.id where 1=1";
            if(name!=null && !name.isEmpty()){
            sql+=" and u.name=?";
            }
            if(surname!=null  && !surname.isEmpty()){
            sql+=" and u.surname=?";
            }
            System.out.println(sql);
            final PreparedStatement pstmt = c.prepareStatement(sql);
            int count=1;
            String check=sql;
            
            while(check.contains("?")){
              if(check.contains("u.name=?")){
                 pstmt.setString(count,name.trim());
                 check=check.replace("u.name=?", "zero");
                 count++;
              }
              else if(check.contains("u.surname=?")){
                  pstmt.setString(count,surname.trim());
                  check=check.replace("u.surname=?", "zero");
                 count++;
              }
             
            }
            if(count==1){
                return null;
            }
            pstmt.execute();
            final ResultSet rs = pstmt.getResultSet();
            while (rs.next()) {         
                result.add(this.getUser(rs));
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }
    
    @Override
    public boolean updateUser(final User u) {
        try (final Connection c = this.connection()) {
            final PreparedStatement pstmt = c.prepareStatement("update user set name =?,surname=?,phone=?,email=?,profile_description=?,address=?,birthdate=?,birthplace_id=?,nationality_id=?,user_image=?,user_image_name=? where id=?");
            pstmt.setString(1, u.getName());
            pstmt.setString(2, u.getSurname());
            pstmt.setString(3, u.getPhone());
            pstmt.setString(4, u.getEmail());
            pstmt.setString(5, u.getProfileDesc());
            pstmt.setString(6, u.getAddress());
            pstmt.setDate(7, u.getBirthDate());
            pstmt.setInt(8, u.getBirthplace().getId());
            pstmt.setInt(9, u.getNationality().getId());
            pstmt.setBlob(10,u.getUserImage());
            pstmt.setString(11,u.getUserImageName());
            pstmt.setInt(12, u.getId());
            return pstmt.execute();
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    @Override
    public boolean removeUser(final int id) {
        try (final Connection c = this.connection()) {
            final Statement stmt = c.createStatement();
            return stmt.execute("delete from user where id=" + id);
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    @Override
    public boolean addUser(final User u) {
        try (final Connection c = this.connection()) {
            final PreparedStatement pstmt = c.prepareStatement("insert into user(id,name,surname,phone,email,profile_description,password) values(?,?,?,?,?,?,?)");
            pstmt.setInt(1, u.getId());
            pstmt.setString(2, u.getName());
            pstmt.setString(3, u.getSurname());
            pstmt.setString(4, u.getPhone());
            pstmt.setString(5, u.getEmail());
            pstmt.setString(6, u.getProfileDesc());
            pstmt.setString(7, BCrypt.withDefaults().hashToString(4, "12345".toCharArray()));
            return pstmt.execute();
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    private static Integer checkStringForInteger(String s){
           Integer nationality_id=null;
            try{
                return  nationality_id=(Integer)Integer.parseInt(s);
            }catch(NumberFormatException nfex){
                return null;
            }
    }

}
