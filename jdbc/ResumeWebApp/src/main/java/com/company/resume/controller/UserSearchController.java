
package com.company.resume.controller;

import com.company.dao.inter.UserDaoInter;
import com.company.entity1.User;
import com.company.main.Context;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "UserSearchController", urlPatterns = {"/usersearch"})
public class UserSearchController extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {    
       try{
        if(request.getParameter("search")!=null){
        UserDaoInter userDao=Context.instanceUserDao();
        List<User> list=userDao.getAll(request.getParameter("name"),request.getParameter("surname"));
        request.setAttribute("list", list);
        }
        request.getRequestDispatcher("UsersSearch/users.jsp").forward(request, response);
       }catch(Exception ex){
           ex.printStackTrace();
       }
    }  
}
