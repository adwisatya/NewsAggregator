function clickAbout(){
	document.getElementById("editable-starter-template").innerHTML = "<h1>TENTANG</h1><h2>Public Hall of Material</h2><p width=\"10px\" class=\"lead\">Public Hall of Material adalah website publik untuk berkolaborasi dalam memperkaya pengetahuan tentang material dan kegunaannya dalam kehidupan sehari-hari. Semakin banyak material yang dibagi, semakin bertambah pula referensi yang dapat dimanfaatkan.</p>";
}

function clickHome(){
	document.getElementById("editable-starter-template").innerHTML = "<h1>Silahkan Login</h1><section class=\"login-form-wrap\"><h1>MATERIALIST</h1><form class=\"login-form\" action=\"POST\" action=\"#\"><label>	<input type=\"email\" name=\"email\" required placeholder=\"Email\"></label><label>	<input type=\"password\" name=\"password\" required placeholder=\"Password\"></label><input type=\"submit\" value=\"Login\">	<label>	<br>OR<input type=\"submit\" value=\"Log in with Facebook\"></form>	</section>";
}


function clickContact(){
	document.getElementById("editable-starter-template").innerHTML= "<h1>HUBUNGI KAMI</h1><h3>admin@phom.material.itb.ac.id</h3><iframe src=\"https://www.google.com/maps/embed?pb=!1m27!1m12!1m3!1d3961.0040568389354!2d107.60909199853852!3d-6.890116254488398!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!4m12!1i0!3e6!4m5!1s0x2e68e65767c9b183%3A0x2478e3dcdce37961!2sInstitut+Teknologi+Bandung%2C+Jl.+Tamansari+64%2C+Jawa+Barat+40116%2C+Indonesia!3m2!1d-6.89148!2d107.610659!4m3!3m2!1d-6.889632!2d107.6094037!5e0!3m2!1sen!2ssg!4v1415038291896\" width=\"600\" height=\"450\" frameborder=\"0\" style=\"border:0\"></iframe>";
}
function clickSNK(){
	document.getElementById("editable-starter-template").innerHTML="<h1>SYARAT DAN KETENTUAN</h1><h3>Dalam menggunakan MYTA News Aggregator, pengguna tidak dipungut biaya.</li></ol>";
}
function singleClicked(){
	document.getElementById("inputForm").innerHTML="<form id=\"form-input\" action=\"upload\" method=\"POST\" role=\"form\"><label><input type=\"radio\" name=\"newsInput\" value=\"single\" onclick=\"singleClicked();\">Single News</label><label><input type=\"radio\" name=\"newsInput\" value=\"multi\" onclick=\"multiClicked();\">Multi News</label><label><input type=\"radio\" name=\"newsInput\" value=\"link\" onclick=\"linkClicked();\">Link News</label></label><input type=\"text\" class=\"form-control\" id=\"textInput\" name=\"textInput\" placeholder=\"text di sini\"><button type=\"submit\" class=\"btn btn-default\" name=\"submit\" onclick=\"\">Submit</button></form>";
	document.getElementById("result-set").innerHTML="";
}
function multiClicked(){
	document.getElementById("inputForm").innerHTML="<form id=\"form-input\" action=\"upload\" method=\"POST\" role=\"form\" enctype=\"multipart/form-data\"><label><input type=\"radio\" name=\"newsInput\" value=\"single\" onclick=\"singleClicked();\">Single News</label><label><input type=\"radio\" name=\"newsInput\" value=\"multi\" onclick=\"multiClicked();\">Multi News</label><label><input type=\"radio\" name=\"newsInput\" value=\"link\" onclick=\"linkClicked();\">Link News</label><div class=\"form-group\"><input type=\"file\" id=\"fileSelect\" name=\"file\"><button type=\"submit\" class=\"btn btn-default\" name=\"submit\" onclick=\"\">Submit</button></form>";
	document.getElementById("result-set").innerHTML="";

}
function linkClicked(){
	document.getElementById("inputForm").innerHTML="<form id=\"form-input\" action=\"upload\" method=\"POST\" role=\"form\"><label><input type=\"radio\" name=\"newsInput\" value=\"single\" onclick=\"singleClicked();\">Single News</label><label><input type=\"radio\" name=\"newsInput\" value=\"multi\" onclick=\"multiClicked();\">Multi News</label><label><input type=\"radio\" name=\"newsInput\" value=\"link\" onclick=\"linkClicked();\">Link News</label></label><input type=\"text\" class=\"form-control\" id=\"linkInput\" name=\"linkInput\" placeholder=\"link di sini\"><button type=\"submit\" class=\"btn btn-default\" name=\"submit\" onclick=\"\">Submit</button></form>";
	document.getElementById("result-set").innerHTML="";

}

function cekFile(){
	document.getElementById("form-input").removeAttribute("enctype");
}