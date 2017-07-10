<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Login</title>
<!--Import Google Icon Font-->
<link href="http://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<!--Import materialize.css-->
<link type="text/css" rel="stylesheet" href="css/materialize.min.css"
	media="screen,projection" />
	
<link type="text/css" rel="stylesheet" href="css/login.css"
	media="screen,projection" />
<!--Let browser know website is optimized for mobile-->

</head>

<body>

	<div class="section"></div>
	<main>
	<center>
		<img class="responsive-img" style="width: 250px;" src="imgs/logo.png" />
		<div class="section"></div>

		<h5 class="indigo-text">Please, login into your account</h5>
		<div class="section"></div>

		<div class="container">
			<div class="z-depth-1 grey lighten-4 row"
				style="display: inline-block; padding: 32px 48px 0px 48px; border: 1px solid #EEE;">

				<form class="col s12" method="post">
					<div class='row'>
						<div class='col s12'></div>
					</div>
					<div class="mierror"></div>
					<div class='row'>
						<div class='input-field col s12'>
							<i class="material-icons prefix">email</i> <input
								class='validate' type='email' name='email' id='email' /> <label
								for="email" data-error="wrong" data-success="right">Enter
								your Email</label>
						</div>
					</div>

					<div class='row'>
						<div class='input-field col s12'>
							<i class="material-icons prefix">vpn_key</i> <input
								class='validate' type='password' name='password' id='password' />
							<label for='password'>Enter your password</label>
						</div>
						<label style='float: right;'> <a class='pink-text'
							href='#!'><b>Forgot Password?</b></a>
						</label>
					</div>

					<br />
					<center>
						<div class='row'>
							<button type='submit' name='btn_login'
								class='col s12 btn btn-large waves-effect indigo'>Login</button>
						</div>
					</center>
				</form>
			</div>
		</div>
	</center>

	<div class="section"></div>
	<div class="section"></div>
</body>
<!--Import jQuery before materialize.js-->
<script type="text/javascript"
	src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="js/materialize.min.js"></script>
<script src="js/login.js"></script>
</html>