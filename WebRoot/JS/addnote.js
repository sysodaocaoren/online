// JavaScript Document
var subFlag = false;
var optionFlag = false;
function checkSubject()
{
	//ͨ����������ȡ��Ӧ����ʾ���ֱ�ǩ
	var name = document.getElementById("subject").value;	
	var textBox = document.getElementById("mgsubject");
	if(name == ""){
			textBox.innerHTML = "�����ⲻ��Ϊ�գ�";
			textBox.style.color = "red";
		}
	else{
			textBox.innerHTML = "��";
			textBox.style.color = "green";
			subFlag = true;
		}
}
function checkContent()
{
	
	var content = document.getElementById("content").value;	
	var textBox = document.getElementById("mgcontent");		
	if(content == ""){
			textBox.innerHTML = "�����ݲ���Ϊ�գ�";
			textBox.style.color = "red";
		}
	else{
			textBox.innerHTML = "��";
			textBox.style.color = "green";
			optionFlag = true;
		}

}
function getSub(){
	if(optionFlag && subFlag){
		document.mainForm.action="../test/mg_addMessage.action";
		}
	else{
		document.mainForm.action="#";
		}
		
}
