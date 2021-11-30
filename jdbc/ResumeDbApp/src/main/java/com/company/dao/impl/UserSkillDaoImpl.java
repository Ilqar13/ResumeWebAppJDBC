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
import com.company.entity1.User;
import com.company.entity1.UserSkill;
import java.sql.ResultSet;
import com.company.dao.inter.UserSkillDaoInter;
import com.company.dao.inter.AbstractDAO;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserSkillDaoImpl extends AbstractDAO implements UserSkillDaoInter
{
    private UserSkill getUserSkill(final ResultSet rs) throws Exception {
   
        final Integer userId = rs.getInt("user_id");
        final Integer skillId = rs.getInt("skill_id");
        final String skillName = rs.getString("skill_name");
        final Integer power = rs.getInt("power");
        if(skillName==null){
            return null;
        }
        return new UserSkill(null, new User(userId), new Skill(skillId, skillName), power);
    }
    
    @Override
    public List<UserSkill> getAllSkillByUserId(final int userId) {
        final List<UserSkill> result = new ArrayList<UserSkill>();
        try (final Connection c = this.connection()) {
            final Statement pstmt = c.createStatement();
            pstmt.execute("SELECT\n u.*,\ns.id as skill_id,s.`name` as skill_name,\nus.power, \nus.user_id \nFROM\nUSER u\nleft JOIN user_skill us ON u.id = us.user_id\nleft JOIN skill s ON us.skill_id = s.id \nwhere u.id=" + userId);
            final ResultSet rs = pstmt.getResultSet();
            while (rs.next()) {
                UserSkill us=this.getUserSkill(rs);
                if(us==null){
                    return result;
                }
                result.add(us);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }
    
    @Override
    public boolean insertUserSkill(final UserSkill u) {
        boolean b = true;
        try {
            final Connection conn = this.connection();
            final PreparedStatement pstmt = conn.prepareStatement("INSERT INTO user_skill (user_id , skill_id ,power) VALUES ( ? , ? , ? ) ;");
            pstmt.setInt(1, u.getUser().getId());
            pstmt.setInt(2, u.getSkill().getId());
            pstmt.setInt(3, u.getPower());
            b = pstmt.execute();
        }
        catch (Exception ex) {
            System.err.println(ex);
            b = false;
        }
        return b;
    }
    
    @Override
    public boolean insertUserSkillList(List<? extends UserSkill> userSkillList) {
        try (final Connection conn = this.connection();){
            StringBuilder builder=new StringBuilder("INSERT INTO user_skill (user_id , skill_id ,power) VALUES ");
            int count=1;
            for (int i = 0; i <userSkillList.size() ; i++) {
           
               if(i==userSkillList.size()-1){
                   builder.append("( ? , ? , ? );");
               }else{
                    builder.append("( ? , ? , ? ),");
               }
              
            }
           final PreparedStatement pstmt = conn.prepareStatement(builder.toString()); 
            for (int i=0;i<userSkillList.size();i++) {
            pstmt.setInt(count++, userSkillList.get(i).getUser().getId());
            pstmt.setInt(count++, userSkillList.get(i).getSkill().getId());
            pstmt.setInt(count++, userSkillList.get(i).getPower());
            }
            
            return pstmt.execute();
        }
        catch (Exception ex) {
           ex.printStackTrace();
            System.out.println("lop");
           return false;
        }
    }
    
    @Override
    public boolean updateUserPower(final int power, final int user_id, final int skill_id) {
        boolean b = true;
        try {
            final Connection conn = this.connection();
            final PreparedStatement pstmt = conn.prepareStatement("UPDATE user_skill SET power =?  where user_id=? and skill_id=?;");
            pstmt.setInt(1, power);
            pstmt.setInt(2, user_id);
            pstmt.setInt(3, skill_id);
            b = pstmt.execute();
        }
        catch (Exception ex) {
            System.err.println(ex);
            b = false;
        }
        return b;
    }
    
    @Override
    public boolean removeUserSkill(final UserSkill userSkill) {
        try {
            final Connection conn = this.connection();
            final PreparedStatement pstmt = conn.prepareStatement("DELETE FROM user_skill WHERE user_id=? and skill_id=?");
            pstmt.setInt(1, userSkill.getUser().getId());
            pstmt.setInt(2, userSkill.getSkill().getId());
            return pstmt.execute();
        }
        catch (Exception ex) {
            System.out.println(ex);
            return false;
        }
    }
    
    @Override
    public boolean removeAllUserSkills(int userId) {
        try(final Connection conn = this.connection();){
        final PreparedStatement pstmt = conn.prepareStatement("DELETE FROM user_skill WHERE user_id=?");
        pstmt.setInt(1, userId);
        return pstmt.execute();            
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
            }
        }
    
    @Override
    public boolean checkUserSkill(final int user_id, final int skill_id) {
        try (final Connection c = this.connection()) {
            final PreparedStatement pstmt = c.prepareStatement("Select COUNT(id) amount from user_skill where user_id=? and skill_id=?");
            pstmt.setInt(1, user_id);
            pstmt.setInt(2, skill_id);
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

    

    @Override
    public List<UserSkill> turnToUserSkillList(int userId, String[] skillIds, String[] skillPercents) {
        List<UserSkill> userSkillList=new ArrayList<>();
        for (int i = 0; i < skillIds.length; i++) {
               if(!skillPercents[i].isEmpty())
            userSkillList.add(new UserSkill(null,new User(userId),new Skill(Integer.parseInt(skillIds[i].replace("x", "")),null),Integer.parseInt(skillPercents[i].replace("%","").trim())));
        }
        return userSkillList;
    }

    

}
