// 
// Decompiled by Procyon v0.5.36
// 

package com.company.dao.inter;

import com.company.entity1.UserSkill;
import java.util.List;

public interface UserSkillDaoInter
{
    List<UserSkill> getAllSkillByUserId(final int p0);
    
    boolean insertUserSkill(final UserSkill p0);
    
    boolean updateUserPower(final int p0, final int p1, final int p2);
    
    boolean removeUserSkill(final UserSkill p0);
    
    boolean removeAllUserSkills(int userId);
    
    boolean checkUserSkill(final int p0, final int p1);
    
    boolean insertUserSkillList (final List<? extends UserSkill>userSkillList);
    
    List<UserSkill> turnToUserSkillList (final int userId,final String [] skillNames,final String[] skillPercents);
}
