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

    <title>Public Hall of Material</title>

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
          <a class="navbar-brand" href="index.html">MYTA</a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
          <ul class="nav navbar-nav">
            <li onClick="clickAbout();"><a href="#about">ABOUT US</a></li>
            <li onClick="clickContact();"><a href="#contact">CONTACT US</a></li>
            <li onClick="clickSNK();"><a href="#acceptance">TERM OF SERVICE</a></li>
          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </nav>

    <div class="container">
		<div class="starter-template" id="editable-starter-template">
			<h3> silahkan masukkan berita anda </h3>
			<hr/>
			<div id="form-container-new">
			<center>
				<form action="upload.jsp" method="POST" enctype="multipart/form-data" role="form">
					<div class="form-group">
					<label for="textInput"></label>
					<input type="text" class="form-control" id="textInput" name="textInput" placeholder="text di sini">
				  </div>
				  <div class="form-group">
					<input type="file" id="fileSelect" name="file">
				  </div>
					<button type="submit" class="btn btn-default" name="submit">Submit</button>
				</form>
			</center>
			</div>
		  <div id="result-container">
			  <div class="result-set">
				  &nbsp;
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
	<script src="assets/js/pages/manipulator.js"></script>
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="assets/js/ie10-viewport-bug-workaround.js"></script>
  </body>
</html>

