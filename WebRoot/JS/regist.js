var nameFlag = false;
var passFlag = false;
var repassFlag = false;
var realnameFlag = false;
var mailFlag = false;

function checkName()
{
	var name = document.getElementById("name").value;
	var textBox = document.getElementById("username");
	if(name=="")
	{
		textBox.innerHTML = "×用户名不能为空！";
		textBox.style.color = "red";
	}
	else if(name.length < 2 || name.length > 8){
		textBox.innerHTML = "×用户名长度须在2-8之间！";
		textBox.style.color = "red";
	}
	else{
		textBox.innerHTML = "√";
		textBox.style.color = "green";
		nameFlag = true;
	}
}

function checkPass()
{
	var pass = document.getElementById("pass").value;
	var textBox = document.getElementById("password");
	if(pass==""){
		textBox.innerHTML = "×密码不能为空！";
		textBox.style.color = "red";
	}
	else if(pass.length < 3 || pass.length > 8){
		textBox.innerHTML = "×密码长度须在3-8之间！";
		textBox.style.color = "red";
	}
	else{
		textBox.innerHTML = "√";
		textBox.style.color = "green";
		passFlag = true;
	}
}

function checkRepass()
{
	var pass = document.getElementById("pass").value;
	var repass = document.getElementById("checkpass").value;
	var textBox = document.getElementById("repass");
	if(repass==""){
		textBox.innerHTML = "×重复密码不能为空！";
		textBox.style.color = "red";
	}
	else if(pass!=repass){
		textBox.innerHTML = "×两次输入的密码不一致！";
		textBox.style.color = "red";
	}
	else{
		textBox.innerHTML = "√";
		textBox.style.color = "green";
		repassFlag = true;
	}
}

function checkRealName()
{
	var realname = document.getElementById("realname").value;
	var textBox = document.getElementById("rname");
	if(realname==""){
		textBox.innerHTML = "×真实姓名不能为空";
		textBox.style.color = "red";
	}
	else{
		textBox.innerHTML = "√";
		textBox.style.color = "green";
		realnameFlag = true;
	}
}

function checkMail()
{
	var mail = document.getElementById("email").value;
	var textBox = document.getElementById("mail");
	var innum = mail.indexOf("@");
	if(mail==""){
		textBox.innerHTML = "×邮箱不能为空";
		textBox.style.color = "red";
	}
	else if(innum == "-1"){
		textBox.innerHTML = "×邮箱格式不正确";
		textBox.style.color = "red";
	}
	else{
		textBox.innerHTML = "√";
		textBox.style.color = "green";
		mailFlag = true;
	}
}

function goRegiste(){
	if(nameFlag && passFlag && repassFlag && realnameFlag && mailFlag){
		document.mainForm.action="../test/user_regist.action";
	}
	else {
		document.mainForm.action="#";
	}
}

function doRegiste(){
	if(nameFlag && passFlag && repassFlag && realnameFlag && mailFlag){
		document.mainForm.action="../../test/user_manageRegist.action";
	}
	else {
		document.mainForm.action="#";
	}
}
