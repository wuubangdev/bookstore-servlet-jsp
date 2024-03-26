<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</head>
<body>
	<div class="m-auto" style="width: 750px">
		<form action="xuLyDangNhap.jsp" method="post">
		  <div class="mb-3">
		    <label for="userName" class="form-label">User name</label>
		    <input type="text" class="form-control" id="userName" name="userName">
		  </div>
		  <div class="mb-3">
		    <label for="inputPassword" class="form-label">Password</label>
		    <input type="password" class="form-control" id="inputPassword" name="inputPassword">
		  </div>
		  <button id="submit" type="submit" class="btn btn-primary">Submit</button>
		</form>
	</div>
	<script type="text/javascript">
		const userName = document.querySelector("#userName");
		const password = document.querySelector("#inputPassword");
		const submit = document.querySelector("#submit");
		submit.onsubmit = function (e) {
			if (userName.value.length <= 6 || password.value.length <=6 ){
				e.preventDefault();
			}
		}
		
	</script>
</body>
</html>