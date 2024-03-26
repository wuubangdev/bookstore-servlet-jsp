<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP-Basic</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</head>
<body>
	<div class="m-auto" style="width: 800px">
		<form action="trangDatHang.jsp" method="get">
		  <div class="mb-3">
		    <label for="fullName" class="form-label">Họ và tên: </label>
		    <input type="text" class="form-control" id="fullName" name="fullName">
		  </div>
		  <div class="mb-3">
		    <label for="inputEmail" class="form-label">Email address</label>
		    <input type="email" class="form-control" id="inputEmail" name="inputEmail">
		  </div>
		  <div class="mb-3">
		    <label for="productQuantity" class="form-label">Số lượng đặt hàng:</label>
		    <input type="number" class="form-control" id="productQuantity" name="productQuantity" min="0">
		  </div>
		  <button type="submit" class="btn btn-primary">Đặt hàng</button>
		</form>
	</div>
</body>
</html>