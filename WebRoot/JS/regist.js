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
		textBox.innerHTML = "���û�������Ϊ�գ�";
		textBox.style.color = "red";
	}
	else if(name.length < 2 || name.length > 8){
		textBox.innerHTML = "���û�����������2-8֮�䣡";
		textBox.style.color = "red";
	}
	else{
		textBox.innerHTML = "��";
		textBox.style.color = "green";
		nameFlag = true;
	}
}

function checkPass()
{
	var pass = document.getElementById("pass").value;
	var textBox = document.getElementById("password");
	if(pass==""){
		textBox.innerHTML = "�����벻��Ϊ�գ�";
		textBox.style.color = "red";
	}
	else if(pass.length < 3 || pass.length > 8){
		textBox.innerHTML = "�����볤������3-8֮�䣡";
		textBox.style.color = "red";
	}
	else{
		textBox.innerHTML = "��";
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
		textBox.innerHTML = "���ظ����벻��Ϊ�գ�";
		textBox.style.color = "red";
	}
	else if(pass!=repass){
		textBox.innerHTML = "��������������벻һ�£�";
		textBox.style.color = "red";
	}
	else{
		textBox.innerHTML = "��";
		textBox.style.color = "green";
		repassFlag = true;
	}
}

function checkRealName()
{
	var realname = document.getElementById("realname").value;
	var textBox = document.getElementById("rname");
	if(realname==""){
		textBox.innerHTML = "����ʵ��������Ϊ��";
		textBox.style.color = "red";
	}
	else{
		textBox.innerHTML = "��";
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
		textBox.innerHTML = "�����䲻��Ϊ��";
		textBox.style.color = "red";
	}
	else if(innum == "-1"){
		textBox.innerHTML = "�������ʽ����ȷ";
		textBox.style.color = "red";
	}
	else{
		textBox.innerHTML = "��";
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
