/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.resume.controller;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.company.dao.inter.UserDaoInter;
import com.company.entity1.User;
import com.company.main.Context;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




@WebServlet(name = "LoginController", urlPatterns = {"/login"})
public class LoginController extends HttpServlet {

    
    private static UserDaoInter userDao=Context.instanceUserDao();
    private static BCrypt.Verifyer verifyer =BCrypt.verifyer();
            
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        request.getRequestDispatcher("Login/login.jsp").forward(request, response);
    }
    
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
       if(request.getParameter("login")!=null){
            try{
                 User user=userDao.getByGmail(request.getParameter("gmail"));
                     if(user!=null && verifyer.verify(request.getParameter("password").toCharArray(),user.getPassword().toCharArray()).verified){
                          request.getSession().setAttribute("loggedInUser", user);
                          response.sendRedirect("/ResumeWebApp/usersearch");   
                     }else{
                         response.sendRedirect("login");
                     }
            }catch(Exception ex){
                response.sendRedirect("error?msg="+ex.getMessage());
            }
    }
    }

}
