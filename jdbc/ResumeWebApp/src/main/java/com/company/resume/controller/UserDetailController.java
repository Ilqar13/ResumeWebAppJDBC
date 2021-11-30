
package com.company.resume.controller;

import com.company.dao.inter.CountryDaoInter;
import com.company.dao.inter.EmploymentHistoryDaoInter;
import com.company.dao.inter.UserDaoInter;
import com.company.dao.inter.UserSkillDaoInter;
import com.company.entity1.Country;
import com.company.entity1.EmploymentHistory;
import com.company.entity1.User;
import com.company.main.Context;
import com.company.util.FileNameUtil;
import files2.FileUtility;
import java.io.File;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Date;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.sql.rowset.serial.SerialBlob;

@MultipartConfig
@WebServlet(name = "UserController", urlPatterns = {"/userdetail"})
public class UserDetailController extends HttpServlet {
private static ServletContext context=null;
private static UserDaoInter userDao=Context.instanceUserDao();
private static CountryDaoInter countryDao=Context.instanceCountryDao();
private static EmploymentHistoryDaoInter employmentHistoryDao=Context.instanceEmploymentHistoryDao();
private static UserSkillDaoInter userSkillDao=Context.instanceUserSkillDao();
public void init(ServletConfig config) throws ServletException {
super.init(config);
context= config.getServletContext();
}


    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        
      try {
          String updatedId=request.getParameter("updatedid");
          String deletedId=request.getParameter("delete");
        if(updatedId!=null){
         updateUser(request,response);
         response.sendRedirect("userdetail?id="+updatedId);
         }else if(deletedId!=null){
             deleteUser(response,deletedId);
        }
        } catch (Exception ex) {
        response.sendRedirect("error.jsp?msg="+ex.getMessage());
           }
          
    }
 
   

     @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
            try{
            String userIdStr=request.getParameter("id");
            if(userIdStr==null || userIdStr.trim().isEmpty()){
            throw new IllegalArgumentException("id is not specified");
              }
                UserDaoInter userDao=Context.instanceUserDao();
               User user=userDao.getById(request.getParameter("id"));
                if(user==null){
                    throw new IllegalArgumentException("There is no user with this id");
                }
                FileNameUtil.createFolder(context.getRealPath("/"),"profilepictures");
                request.setAttribute("user", user);
                request.setAttribute("employmenthistory",Context.instanceEmploymentHistoryDao().getEmploymentHistoryByUserId(user.getId()));
                request.setAttribute("userskill", Context.instanceUserSkillDao().getAllSkillByUserId(user.getId()));
                request.setAttribute("contextPath",context.getRealPath("/"));
                request.getRequestDispatcher("userdetails2.jsp").forward(request, response);
            }catch(Exception ex){
                ex.printStackTrace();
                response.sendRedirect("error?msg="+ex.getMessage());
            }
    }
    
    private static void updateUser(HttpServletRequest request, HttpServletResponse response) throws IOException, Exception{
          User user =userDao.getById(request.getParameter("updatedid"));
           user.setName((String)request.getParameter("fullname").split(" ")[0]);
           user.setSurname((String)request.getParameter("fullname").split(" ")[1]);
           user.setEmail(request.getParameter("email"));
           user.setPhone(request.getParameter("phone"));
           user.setAddress(request.getParameter("address"));
           user.setBirthDate(Date.valueOf(request.getParameter("birthdate")));
           user.setBirthplace(new Country(countryDao.getIdByName(request.getParameter("birthplace").replace("0"," "))));
           user.setCountry(new Country(countryDao.getIdByName(request.getParameter("nationality").replace("0"," "))));
           user.setProfileDesc(request.getParameter("aboutme").trim());
           employmentHistoryDao.updateEmploymentHistory(new EmploymentHistory(user,request.getParameter("header"),
           Date.valueOf(request.getParameter("begindate")),
           Date.valueOf(request.getParameter("enddate")),request.getParameter("jobdescription")));
            String [] skillnames=request.getParameterValues("skilllabel");
           if(skillnames!=null){
            String [] skillpercents=request.getParameterValues("percentofskill");
            userSkillDao.removeAllUserSkills(user.getId());
           userSkillDao.insertUserSkillList(userSkillDao.turnToUserSkillList(user.getId(), skillnames, skillpercents));
               }else{
               userSkillDao.removeAllUserSkills(user.getId());
           }
            Part part=request.getPart("profile_photo");
                 if( part.getInputStream().available()>0 && new File(context.getRealPath("/").concat(user.getUserImageName())).delete()){
                    String path=context.getRealPath("/");
             String fileName=FileNameUtil.produceFileName(path,".jpg",user.getName());
             byte [] fileBytes=FileUtility.readBytesFromInputStream(part.getInputStream());
            FileUtility.writeBytes(path.concat(fileName),FileUtility.readBytesFromInputStream(part.getInputStream()));
                 user.setUserImageName(fileName);
                 user.setUserImage(new SerialBlob(fileBytes));
                 }
           userDao.updateUser(user);
    } 
    
    
    private static void deleteUser(HttpServletResponse response,String deletedId) throws IOException{
         User user=userDao.getById(deletedId);
         employmentHistoryDao.removeEmploymentHistory(Integer.parseInt(deletedId));
         userSkillDao.removeAllUserSkills(Integer.parseInt(deletedId));
         userDao.removeUser(Integer.parseInt(deletedId));
         response.sendRedirect("usersearch?name="+user.getName()+"&surname="+user.getSurname()+"&search=Search");
    }

}
