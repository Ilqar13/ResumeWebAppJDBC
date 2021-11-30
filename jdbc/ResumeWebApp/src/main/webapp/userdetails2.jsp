

<%@page import="files2.FileUtility"%>
<%@page import="java.io.File"%>
<%@page import="java.sql.Date"%>
<%@page import="com.company.entity1.Country"%>
<%@page import="com.company.entity1.Skill"%>
<%@page import="com.company.main.Context"%>
<%@page import="java.util.List"%>
<%@page import="com.company.entity1.UserSkill"%>
<%@page import="com.company.entity1.EmploymentHistory"%>
<%@page import="com.company.entity1.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head >
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
        <link rel="stylesheet" href="userdetails2.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
        <title>JSP Page</title>
    </head>
    <body >
        <script type="text/javascript" src="userdetails2.js" defer></script>
 <%
            User user=(User) request.getAttribute("user");
            EmploymentHistory employmentHistory=(EmploymentHistory) request.getAttribute("employmenthistory");
            List<UserSkill> userSkillList=(List) request.getAttribute("userskill");
            List<Skill> skillList=Context.instanceSkillDao().getAllSkill();
            List<Country> countryList=Context.instanceCountryDao().getAllCountry();
            String contextPath=(String)request.getAttribute("contextPath");
                      %>
<div class="container">
    <form action="userdetail" method="POST" enctype="multipart/form-data">
		<div class="main-body">
			<div class="row">
                              
				<div class="col-lg-4">
					<div class="card">
						<div class="card-body">
							<div class="d-flex flex-column align-items-center text-center">     
                                                            <div id="profile-container">
                                                                <%
                                                                    String filePath=contextPath.concat(user.getUserImageName());
                                                                    if(contextPath!=null && !new File(filePath).exists()){
                                                                     FileUtility.writeBytes(filePath,FileUtility.readBytesFromInputStream(user.getUserImage().getBinaryStream()));
                                                                    }
                                                                    %>
                                                             <image id="profileImage" name="userimage" src="<%=user.getUserImageName()%>" />
                                                                  </div>
                                                                   <input id="imageUpload" type="file"  name="profile_photo" placeholder="Photo">
                                                                   
								<div class="mt-3">
									<h4><%=user.getName()+" "+user.getSurname()%></h4>
									<p class="text-secondary mb-1">Full Stack Developer</p>
									<p class="text-muted font-size-sm"><%=user.getAddress()%></p>
								</div>
                             
                                                               </div> 
                                                               
							<hr class="my-4">
							<ul class="list-group list-group-flush">
								<li class="list-group-item d-flex justify-content-between align-items-center flex-wrap">
									<h6 class="mb-0"><svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-globe me-2 icon-inline"><circle cx="12" cy="12" r="10"></circle><line x1="2" y1="12" x2="22" y2="12"></line><path d="M12 2a15.3 15.3 0 0 1 4 10 15.3 15.3 0 0 1-4 10 15.3 15.3 0 0 1-4-10 15.3 15.3 0 0 1 4-10z"></path></svg>Website</h6>
									<span class="text-secondary">https://bootdey.com</span>
								</li>
								<li class="list-group-item d-flex justify-content-between align-items-center flex-wrap">
									<h6 class="mb-0"><svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-github me-2 icon-inline"><path d="M9 19c-5 1.5-5-2.5-7-3m14 6v-3.87a3.37 3.37 0 0 0-.94-2.61c3.14-.35 6.44-1.54 6.44-7A5.44 5.44 0 0 0 20 4.77 5.07 5.07 0 0 0 19.91 1S18.73.65 16 2.48a13.38 13.38 0 0 0-7 0C6.27.65 5.09 1 5.09 1A5.07 5.07 0 0 0 5 4.77a5.44 5.44 0 0 0-1.5 3.78c0 5.42 3.3 6.61 6.44 7A3.37 3.37 0 0 0 9 18.13V22"></path></svg>Github</h6>
									<span class="text-secondary">bootdey</span>
								</li>
								<li class="list-group-item d-flex justify-content-between align-items-center flex-wrap">
									<h6 class="mb-0"><svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-twitter me-2 icon-inline text-info"><path d="M23 3a10.9 10.9 0 0 1-3.14 1.53 4.48 4.48 0 0 0-7.86 3v1A10.66 10.66 0 0 1 3 4s-4 9 5 13a11.64 11.64 0 0 1-7 2c9 5 20 0 20-11.5a4.5 4.5 0 0 0-.08-.83A7.72 7.72 0 0 0 23 3z"></path></svg>Twitter</h6>
									<span class="text-secondary">@bootdey</span>
								</li>
								<li class="list-group-item d-flex justify-content-between align-items-center flex-wrap">
									<h6 class="mb-0"><svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-instagram me-2 icon-inline text-danger"><rect x="2" y="2" width="20" height="20" rx="5" ry="5"></rect><path d="M16 11.37A4 4 0 1 1 12.63 8 4 4 0 0 1 16 11.37z"></path><line x1="17.5" y1="6.5" x2="17.51" y2="6.5"></line></svg>Instagram</h6>
									<span class="text-secondary">bootdey</span>
								</li>
								<li class="list-group-item d-flex justify-content-between align-items-center flex-wrap">
									<h6 class="mb-0"><svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-facebook me-2 icon-inline text-primary"><path d="M18 2h-3a5 5 0 0 0-5 5v3H7v4h3v8h4v-8h3l1-4h-4V7a1 1 0 0 1 1-1h3z"></path></svg>Facebook</h6>
									<span class="text-secondary">bootdey</span>
								</li>
							</ul>
						</div>
                                            
					</div>
				</div>
                         
    
				<div class="col-lg-8">
                                        <div class="card">
                                             <input type="hidden" name="updatedid" value="<%=user.getId()%>">
						<div class="card-body">
							<div class="row mb-3">
								<div class="col-sm-3">
									<h6 class="mb-0">Full Name</h6>
								</div>
								<div class="col-sm-9 text-secondary">
									<input type="text" class="form-control" name="fullname" value="<%=user.getName()+" "+user.getSurname()%>">
								</div>
                                                                
							</div>
							<div class="row mb-3">
								<div class="col-sm-3">
									<h6 class="mb-0">Email</h6>
								</div>
								<div class="col-sm-9 text-secondary">
									<input type="text" class="form-control" name="email" value="<%=user.getEmail()%>">
								</div>
							</div>
							<div class="row mb-3">
								<div class="col-sm-3">
									<h6 class="mb-0">Phone</h6>
								</div>
								<div class="col-sm-9 text-secondary">
									<input type="text" class="form-control" name="phone" value="<%=user.getPhone()%>">
								</div>
							</div>
                                                     
							<div class="row mb-3">
								<div class="col-sm-3">
									<h6 class="mb-0">Address</h6>
								</div>
								<div class="col-sm-9 text-secondary">
									<input type="text" class="form-control" name="address" value="<%= user.getAddress()%>">
								</div>
							</div>
                                                   
                                                    <div class="row mb-3">
								<div class="col-sm-3">
									<h6 class="mb-0">BirthDate</h6>
								</div>
								<div class="col-sm-9 text-secondary">
									<input type="text" class="form-control" name="birthdate" value="<%= user.getBirthDate()%>">
								</div>
							</div>
                                                    <div class="row mb-3">
								<div class="col-sm-3">
									<h6 class="mb-0">BirthPlace</h6>
								</div>
								<div class="col-sm-9 text-secondary">
                                                                  <select name="birthplace"  class="browser-default custom-select" >
                                                                    <%  for(Country c:countryList){
                                                                        if(user.getBirthplace().getName()!=null){
                                                                        if(user.getBirthplace().getName().equals(c.getName())){%>
                                                                            <option value=<%=c.getName().replace(" ","0")%> selected><%=c.getName()%></option>
                                                                        <%}}%>
                                                                       <option value=<%=c.getName().replace(" ","0")%>><%=c.getName()%></option>
                                                                    <%}%>
                                                                    </select>
									</div>
							</div>
                                                     <div class="row mb-3">
								<div class="col-sm-3">
									<h6 class="mb-0">Nationality</h6>
								</div>
								<div class="col-sm-9 text-secondary">                 
                                                                    <select name="nationality" class="browser-default custom-select" >
                                                                   <%  for(Country c:countryList){
                                                                 if(user.getCountry().getCountryName()!=null){
                                                                if(user.getCountry().getCountryName().equals(c.getName())){%>
                                                                    <option value=<%=c.getName().replace(" ","0")%> selected><%=c.getName()%></option>
                                                                <%}}%>
                                                               <option value=<%=c.getName().replace(" ","0")%>><%=c.getName()%></option>
                                                            <%}%>
                                                                    </select>
									</div>
							</div>
                                                    
							<div class="row">
								<div class="col-sm-3"></div>
								<div class="col-sm-9 text-secondary">
									<input type="submit" class="btn btn-primary px-4" value="Save Changes">
								</div>
							</div>
						</div>
					</div>
                                        
                                    <div class="card">
                                        <div class="card-body">
                                            <div class="row mb-3">
								<div class="col-sm-3">
                                                                    <h5 class="d-flex align-items-center mb-3">About me</h5>
									
								</div>
								<div class="col-sm-12 text-secondary">
                                                                    <textarea  name="aboutme" class="form-control rounded-0" rows="15" >
                                                                        <%=user.getProfileDesc()%>
                                                                          </textarea>
									</div>
							</div>
                                        </div>
                                    </div>
                                    <div class="card">
                                        <% String header=null;
                                           Date beginDate=null;
                                           Date endDate=null;
                                           String jobDescription=null;
                                            if(employmentHistory!=null){
                                                header=employmentHistory.getHeader();
                                                beginDate=employmentHistory.getBeginDate();
                                                endDate=employmentHistory.getEndDate();
                                                jobDescription=employmentHistory.getJobDescription();
                                             }%>
                                            <div class="card-body">
                                           <h5 class="d-flex align-items-center mb-3">Employment History</h5>
                                                <div class="row mb-3">
                                                                    <div class="col-sm-3">
                                                                            <h6 class="mb-0">Header</h6>
                                                                    </div>
                                                                    <div class="col-sm-9 text-secondary">
                                                                            <input type="text" class="form-control" name="header" value="<%=(header!=null && !header.isEmpty())?header:"N/A"%>">
                                                                    </div>
                                                            </div>
                                                <div class="row mb-3">
                                                                    <div class="col-sm-3">
                                                                            <h6 class="mb-0">Begin date</h6>
                                                                    </div>
                                                                    <div class="col-sm-9 text-secondary">
                                                                            <input type="text" class="form-control" name="begindate" value="<%=(beginDate!=null)?beginDate.toString():"N/A"%>">
                                                                    </div>
                                                            </div>
                                                 <div class="row mb-3">
                                                                    <div class="col-sm-3">
									<h6 class="mb-0">End Date</h6>
								</div>
								<div class="col-sm-9 text-secondary">
									<input type="text" class="form-control" name="enddate" value="<%=(endDate!=null)?endDate.toString():"N/A"%>">
								</div>
                                                 
                                                 </div>	
                                            <div class="row mb-3">
								<div class="col-sm-3">
									<h6 class="mb-0">Job Description</h6>
								</div>
								<div class="col-sm-9 text-secondary">
                                                                    <textarea  name="jobdescription" class="form-control rounded-0" rows="3" >
                                                                      <%=(jobDescription!=null && !jobDescription.isEmpty())?jobDescription:"N/A"%>
                                                                          </textarea>
							</div>
                                                 
                                                 </div>	
                                        </div>
                                         </div>
                                            
					<div class="row">
						<div class="col-sm-12">
                                                    
							<div class="card">
								<div class="card-body" id="alluserskills">
									<h5 class="d-flex align-items-center mb-3">Project Status</h5>
                                                                        <div id="userskills">
                                                                        <%  Integer count=0;
                                                                            for (UserSkill us:userSkillList) {
                                                                                count++;
                                                                                %>
                                                                          <div id="<%=count%>" class="databaseSkill">
									<label for="userskill"  ><%=us.getSkill().getName()%></label>
                                                                        <input type="hidden" name="skilllabel" value="<%=us.getSkill().getId()%>">
                                                                        <input type="text" name="percentofskill"  class="no-outline" onkeyup="alterPercent(document.getElementById(<%=count%>))" value="<%=us.getPower()%>%">
                                                                        <button type="button" name="minus" onClick="minusSkill(document.getElementById(<%=count%>))" class="btnmns">Minus</button>
                                                                       <div class="progress mb-3 " style="height: 5px">
										<div class="progress-bar bg-primary" role="progressbar" style="width: <%=us.getPower()%>%"></div>
									</div>
                                                                        </div>
                                                                                <%}%> 
                                                                                </div>
                                                                                <select class="browser-default custom-select col-sm-9" name="skills" id="skillsforadd"> 
                                                                                    <%
                                                                                        boolean test=false;
                                                                                        for (Skill s:Context.instanceSkillDao().getAllSkill()) {
                                                                                            for (UserSkill us:userSkillList) {
                                                                                     if(s.getName().equals(us.getSkill().getName())){
                                                                                                test=true;
                                                                                          }
                                                                                          }
                                                                                         if(test==false){%>
                                                                                         <option name="<%=s.getId()%>"><%=s.getName()%></option>
                                                                                             <%}else{
                                                                                               test=false;
                                                                                              }
                                                                                              }%>

                                                                                </select><button type="button" name="add" value="Add" id="addbtn"  class="col-sm-3" onclick="addSkill()">Add</button>
                                                                               `
								</div>
							</div>
                                                                               
						</div>
                                        </div>
                                     
                                </div>
                     
                                                                                              
                        </div>                                  
		</div>
            </form>                                                          
	</div>
    </body>
</html>
