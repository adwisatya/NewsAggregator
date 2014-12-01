<%-- 
    Document   : index
    Created on : Nov 29, 2014, 1:54:01 PM
    Author     : adwisatya
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>MYTA News Aggregator</title>

    <!-- Bootstrap core CSS -->
    <link href="assets/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="assets/css/starter-template.css" rel="stylesheet">
    <link href="assets/css/login-form.css" rel="stylesheet">
    <link href="assets/css/image-description.css" rel="stylesheet">

    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
    <script src="assets/js/ie-emulation-modes-warning.js"></script>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>
  <body>
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="index.jsp">MYTA</a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
          <ul class="nav navbar-nav">
			<li onClick="clickSingle();"><a href="#">Single Check</a></li>
			<li onClick="clickMulti();"><a href="#">Multi Check</a></li>
			<li onClick="clickLink();"><a href="#">Link Check</a></li>

            <li onClick="clickAbout();"><a href="#">ABOUT US</a></li>
            <li onClick="clickContact();"><a href="#contact">CONTACT US</a></li>
            <li onClick="clickSNK();"><a href="#acceptance">TERM OF SERVICE</a></li>
          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </nav>

    <div class="container">
		<div class="starter-template" id="editable-starter-template">
			<h3> Selamat datang di MYTA News Aggregator </h3>
			<hr/>
			<div id="form-container-new">
			<center>
				<div class="form-group" id="form-group-edit">
					<form id="form-input" action="upload" method="POST" role="form">
						<input type="hidden" name="update" value="nol">
						<input type="text" class="form-control" id="textInput" name="textInput" placeholder="judul di sini"><br/>
						<textarea placeholder="Konten berita" row="20" class="form-control" id="textInput" name="textInputKonten"></textarea><br/>
						<input type="hidden" name="islink" value="nol">
						<button type="submit" class="btn btn-default" name="submit" onclick="">Submit</button>
					</form>
				</div>
			</center>
			</div>
		  <div id="result-container">
			  <div id="result-set">
				  <%
					//bagian ini yang nanti bakal nampilin hasil klasifikasi.
					//hasil klasifikasi diparsing dari FileUploadHandler lewat request attribut message
					if(request.getAttribute("message") != null){
						out.println(request.getAttribute("message"));
					}  
				  %>
			  </div>
		  </div>
		</div>
    </div><!-- /.container -->
    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="assets/js/jquery.min.js"></script>
    <script src="assets/js/bootstrap.min.js"></script>
	<script src="assets/js/bootstrap.file-input.js"></script>
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="assets/js/ie10-viewport-bug-workaround.js"></script>
	<script>
	function clickAbout(){
		document.getElementById("form-group-edit").innerHTML = "<h1>TENTANG</h1><h2>Public Hall of Material</h2><p width=\"10px\" class=\"lead\">Public Hall of Material adalah website publik untuk berkolaborasi dalam memperkaya pengetahuan tentang material dan kegunaannya dalam kehidupan sehari-hari. Semakin banyak material yang dibagi, semakin bertambah pula referensi yang dapat dimanfaatkan.</p>";
	}
	function clickContact(){
		document.getElementById("form-group-edit").innerHTML= "<h1>HUBUNGI KAMI</h1><h3>admin@phom.material.itb.ac.id</h3><iframe src=\"https://www.google.com/maps/embed?pb=!1m27!1m12!1m3!1d3961.0040568389354!2d107.60909199853852!3d-6.890116254488398!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!4m12!1i0!3e6!4m5!1s0x2e68e65767c9b183%3A0x2478e3dcdce37961!2sInstitut+Teknologi+Bandung%2C+Jl.+Tamansari+64%2C+Jawa+Barat+40116%2C+Indonesia!3m2!1d-6.89148!2d107.610659!4m3!3m2!1d-6.889632!2d107.6094037!5e0!3m2!1sen!2ssg!4v1415038291896\" width=\"600\" height=\"450\" frameborder=\"0\" style=\"border:0\"></iframe>";
	}
	function clickSNK(){
		document.getElementById("form-group-edit").innerHTML="<h1>SYARAT DAN KETENTUAN</h1><h3>Dalam menggunakan MYTA News Aggregator, pengguna tidak dipungut biaya.</li></ol>";
	}
	function clickSingle(){
		document.getElementById("form-group-edit").innerHTML="<form id=\"form-input\" action=\"upload\" method=\"POST\" role=\"form\"><input type=\"hidden\" name=\"update\" value=\"nol\"><input type=\"text\" class=\"form-control\" id=\"textInput\" name=\"textInput\" placeholder=\"judul di sini\"><br/><textarea class=\"form-control\" id=\"textInput\" name=\"textInputKonten\"></textarea><br/><input type=\"hidden\" name=\"islink\" value=\"nol\"><button type=\"submit\" class=\"btn btn-default\" name=\"submit\" onclick=\"\">Submit</button></form>";
	}
	function clickMulti(){
		document.getElementById("form-group-edit").innerHTML="<form id=\"form-input\" action=\"upload\" method=\"POST\" role=\"form\" enctype=\"multipart/form-data\"><input type=\"hidden\" name=\"update\" value=\"nol\"><input type=\"file\" id=\"fileSelect\" name=\"file\"><br/><button type=\"submit\" class=\"btn btn-default\" name=\"submit\" onclick=\"\">Submit</button></form>";
	}
	function clickLink(){
		document.getElementById("form-group-edit").innerHTML="<form id=\"form-input\" action=\"upload\" method=\"POST\" role=\"form\"><input type=\"hidden\" name=\"update\" value=\"nol\"><input type=\"text\" class=\"form-control\" id=\"textInput\" name=\"textInput\" placeholder=\"link di sini\"><br/><input type=\"hidden\" name=\"islink\" value=\"satu\"><button type=\"submit\" class=\"btn btn-default\" name=\"submit\" onclick=\"\">Submit</button></form>";
	}
	</script>
  </body>
</html>

