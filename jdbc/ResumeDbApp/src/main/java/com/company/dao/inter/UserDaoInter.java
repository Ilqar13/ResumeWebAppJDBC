
package com.company.dao.inter;
import com.company.entity1.User;
import java.util.List;

public interface UserDaoInter
{
    List<User> getAll(String name,String surname);
    
    User getById(final String userId);
    
    boolean addUser(final User user);
    
    boolean updateUser(final User user);
    
    boolean removeUser(final int user);
    
    User getByGmail(final String userGmail);
}
