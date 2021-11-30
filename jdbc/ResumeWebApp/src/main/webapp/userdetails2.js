
function addSkill(){
    var label=document.createElement("label");
    var div1=document.createElement("div");
    var div2=document.createElement("div");
    var div3=document.createElement("div");
    var input1=document.createElement("input");
    var input2=document.createElement("input");
    var button=document.createElement("button");
    var userskills=document.getElementById("userskills");
    var skills=document.getElementById("skillsforadd");
    var skillname=skills.value;
    div1.className="progress mb-3";
    div1.style="height: 5px";
    div2.className="progress-bar bg-primary";
    div2.role="progressbar";
    div2.style="width: 0%";
    div3.className="databaseSkill";
    div3.id=randomNumber(11,1000000);
    input1.type="text";
    input1.name="percentofskill";
    input1.className="no-outline";
    input1.onkeyup=function(){alterPercent(document.getElementById(div3.id));};
    input1.style="border: 0";
    input2.type="hidden";
    input2.name="skilllabel";
    input2.value=skills.options[skills.selectedIndex].getAttribute("name");
    button.type="button";
    button.name="minus";
    button.className="btnmns";
    button.innerHTML="Minus";
    label.name="skilllabel";
    label.innerHTML=skillname;
    button.onclick=function(){minusSkill(document.getElementById(div3.id));};
    div1.appendChild(div2);
    div3.appendChild(label);
    div3.appendChild(button);
    div3.appendChild(input1);
    div3.appendChild(input2);
    div3.appendChild(div1);
    userskills.appendChild(div3);
    skills.remove(skills.selectedIndex);
    if(skills.length===0){
        skills.style.display="none";
        document.getElementById("addbtn").style.display="none";
    }
}
function minusSkill(obj){
  obj.remove();
var skills = document.getElementById("skillsforadd");
var option = document.createElement("option");
option.text = obj.getElementsByTagName("label")[0].innerHTML;
skills.add(option,skills[0]);
    if(skills.style.display==="none"){
        skills.style.display="inline";
        document.getElementById("addbtn").style.display="inline";
    }
}

function randomNumber(min,max){
    var randnum=Math.floor(Math.random()*(max-min)+min);
    if(!document.getElementById(randnum)){
        return randnum;
    }
    else{
       while(true){
           if(!document.getElementById(++randnum)){
               return;
           }
       }
    }
}

function alterPercent(div){
    var progress=div.getElementsByClassName("progress")[0];
    var progressBar=progress.getElementsByClassName("progress-bar")[0];
    progressBar.style.width =div.getElementsByClassName("no-outline")[0].value;
}

$("#profileImage").click(function(e) {
    $("#imageUpload").click();
});
function fasterPreview( uploader ) {
    
    if ( uploader.files && uploader.files[0] ){
        const reader =new FileReader();
             $('#profileImage').attr('src',
             window.URL.createObjectURL(uploader.files[0]) );
           }
}

$("#imageUpload").change(function(){
    fasterPreview( this );
});

