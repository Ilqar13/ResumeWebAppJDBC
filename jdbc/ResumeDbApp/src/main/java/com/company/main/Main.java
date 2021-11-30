

package com.company.main;

import com.company.dao.impl.UserDaoImpl;
import com.company.dao.impl.UserSkillDaoImpl;
import com.company.dao.inter.UserDaoInter;
import com.company.entity1.Skill;
import com.company.entity1.User;
import com.company.entity1.UserSkill;
import files2.FileUtility;
import java.io.FileInputStream;

public class Main  {
    int count=0;
    public static void main(String[] args) throws Exception {
        new UserSkillDaoImpl().removeAllUserSkills(6);
    }
}
