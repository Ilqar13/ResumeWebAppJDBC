<%-- 
    Document   : user
    Created on : Jul 6, 2021, 6:18:27 PM
    Author     : ILQAR
--%>

<%@page import="java.util.List"%>
<%@page import="com.company.entity1.User"%>
<%@page import="com.company.dao.inter.UserDaoInter"%>
<%@page import="com.company.main.Context"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="UsersSearch/users.css">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css" integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU" crossorigin="anonymous">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
        <script type="text/javascript" src="UsersSearch/users.js"></script>
        <title>JSP Page</title>
          </head>
    <body>
        <div class="container mycontainer">
            
               <div class="col">
            <form action="usersearch" method="GET">
                <div class="form-group">
              <label for="name">name:</label>
              <input  type="text" class="form-control" id="whatIamtyping" placeholder="Name" name="name" value="<%=(request.getParameter("name")==null)?"":request.getParameter("name")%>"/>
           
            <div class="form-group">
            <label for="surname">surname:</label>
            <input type="text" class="form-control" placeholder="Surname" name="surname" value="<%=(request.getParameter("surname")==null)?"":request.getParameter("surname")%>"/>
               </div>
           
               <input type="submit" name="search" value="Search" >
               
           </div>
                           </form>

               </div>
               
        <%
            try{
            List<User> list=(List)request.getAttribute("list");
                %>
            <div class="col" >
                <table class="table">
                    <thead>
                        <tr>
                            <th>name</th>
                            <th>surname</th>
                            <th>nationality</th>
                            <th></th>
                        <tr/>
                    <thead/>
                    <tbody>
                        <%
                        String check=null;
                        for (User u:list) {
                        
                        %>
                        <tr>
                            <td><%=u.getName()%></td>
                            <td><%=u.getSurname()%></td>
                            <td><%=((check=u.getNationality().getName())==null)?"N/A":check%></td>
                            <td style="width: 5px">
                                
                                <button class=" btn-danger"  data-toggle="modal" data-target="#exampleModal" value="<%=u.getId()%>" onClick="setValueToButton(this.value)">
                                    <i class=" fas fa-trash-alt"> </i>
                                </button>
                                  
                                    </td>
                                    <td>
                                    <form action="userdetail" method="GET">
                                        <button class=" btn-secondary" type="submit" name="id" value="<%=u.getId()%>">
                                   <i class="fas fa-pen-square"></i>
                                </button>
                                </form>
                                </td>
                        <tr/>
                           <%}}catch(Exception ex){
                    ex.printStackTrace();
}%>
                    </tbody>
                    </table>
                 
                </div>
              </div>  
           <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Delete</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        Are You sure?
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        <form action="userdetail" method="POST">
          <button class="btn btn-danger" type="submit" name="delete"   id="btndelete">Delete</button>   
        </form>
        </div>
    </div>
  </div>
</div>                

               </body>
</html>