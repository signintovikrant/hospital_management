<%@page import="java.sql.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<style type="text/css">
.frm {
background: rgb(199, 220, 247);
   
    margin-top: 3%;
    margin-bottom: 5%;
    border-radius: 10px;
    box-shadow: 0 14px 28px rgba(0,0,0,0.25),0 10px
    10px rgba(0,0,0,0.22);
    position: relative;
    overflow: hidden;
    width: 400px;
    max-width: 100%;
    min-height: 440px;
font-family: 'Zilla Slab', serif;
font-size: 26px;    
background: rgb(199, 220, 247);
    display: flex;
    flex-direction: column;
    padding: 0 50px;
    height: 100%;
    justify-content: center;
    align-items: center;
    text-align: center;
}

.frm input{
  
    background: #eee;
    border: none;
    padding: 12px 15px;
    margin: 8px 0;
    width: 100%;
    font-family: 'Zilla Slab', serif;
    font-size: 26px;
}

body {
	background: url("/resources/DocHomeImg/blurhos.jpg");
	background-repeat: no-repeat;
	background-size: cover;
}

h2 {
	margin-top:3%;
	font-family: 'Zilla Slab', serif;
	text-align: center;
	font-size: 33px;
}
.frm input[type=submit]{
	border-radius: 20px;
    border: 1px solid #046d4a;
    background: #0b0c58;   
    color: white;
font-family: 'Zilla Slab', serif;  
    padding: 12px 45px;
    letter-spacing: 1px;
    height: 50px;
    text-transform: uppercase;
    transition: transform 80ms ease-in-out;
    width: 250px;
}
</style>
<title>Insert title here</title>


</head>
<body>
	<div align="center">
		<form action="/retrieve_patient" method="post" class="frm">
			Email Id <input type="text" name="email" placeholder="Enter Email Id">
			<input type="submit" value="Search">
				<p align="center">
		<font color="red">${msg}</font>
	</p>
		</form>
	</div>

</body>
</html>