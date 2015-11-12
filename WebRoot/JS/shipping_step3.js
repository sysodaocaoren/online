// JavaScript Document
function sendOrder() {
	if (b1 && b2) {
		if (checkTel() || checkMovPho()) {
			var objForm = document.forms["send_Order"];
			objForm.submit();
		} else {
			alert("请填写您的电话信息,手机号码等于11位或者是固话不小于7");
		}

	} else {
		alert("信息填写不完整，请重新填写！");
	}
}
var b1 = false;
var b2 = false;
function checkDate() {
	b1 = false;
	var objTime = document.getElementById("time");
	var date = document.getElementById("selectDay").value;
	var hours = document.getElementById("selectHour").value;
	var minu = document.getElementById("selectMinu").value;
	var nowDate = new Date();
	var nowMonth = (nowDate.getMonth() < 9) ? ("0" + (nowDate.getMonth() + 1))
			: (nowDate.getMonth() + 1);
	var nowD = (nowDate.getDate() < 10) ? ("0" + nowDate.getDate()) : (nowDate
			.getDate());
	var nowDay = nowDate.getFullYear() + "-" + nowMonth + "-" + nowD;
	if (nowDay == date) {
		if (hours < nowDate.getHours()
				|| (hours == nowDate.getHours() && (minu <= nowDate
						.getMinutes()))) {
			objTime.innerHTML = "×";
			objTime.className = "red";
			return;
		}

	}

	if (date != -1 && hours != -1 && minu != -1) {
		document.getElementById("order_date").value = date + " " + hours + ":"
				+ minu + ":00";
		objTime.innerHTML = "√";
		objTime.className = "green";
		b1 = true;
	}

}
function checkEmpty() {
	//var str = document.getElementById("order_address").innerHTML;
	var obj = document.getElementById("address");
	//b2 = false;
	//if (str.length > 10) {
		obj.innerHTML = "√";
		obj.className = "green";
		b2 = true;
		return;
	//} else if (str.length > 0) {
		obj.innerHTML = "地址不够详细";
	//} else {
	//	obj.innerHTML = "×";
	//}
	//obj.className = "red";
}
function checkTel() {

	var str = document.getElementById("order_tel").value;
	var patrn = /^[0-9]{7,12}$/;
	return (!patrn.exec(str)) ? false : true;
}
function checkMovPho() {
	var str = document.getElementById("userInfo.movPho").value;
	var patrn = /^[0-9]{11}$/;

	return (!patrn.exec(str)) ? false : true;
}
