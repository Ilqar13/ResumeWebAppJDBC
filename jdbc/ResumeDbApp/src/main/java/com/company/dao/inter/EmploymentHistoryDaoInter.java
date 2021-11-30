// 
// Decompiled by Procyon v0.5.36
// 

package com.company.dao.inter;

import com.company.entity1.EmploymentHistory;
import java.util.List;

public interface EmploymentHistoryDaoInter
{
    List<EmploymentHistory> getAllEmploymentHistory();
    
    EmploymentHistory getEmploymentHistoryByUserId(final int p0);
    
    boolean updateEmploymentHistory(final EmploymentHistory p0) throws Exception;
    
    boolean removeEmploymentHistory(final int userId);
}
