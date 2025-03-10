<%@page import="model.Customer"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Đăng ký</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js">
</script>
<style type="text/css">
.rq {
	color: red;
}
</style>
</head>
<body>
	<%
		String err = request.getAttribute("errMsg")+"";
		err = (err.equals("null"))?"":err;
		Object obj = request.getAttribute("customer");
		Customer customer = new Customer("null", "null", "null", "null", "null", 
				"null", "null", "null", null, 
				"null", "null", 0);
		if(obj!=null) {
			customer = (Customer)obj;
		}
		String customerName = (customer.getCustomerName().equals("null"))?"":customer.getCustomerName();
		String userName = (customer.getUsername().equals("null"))?"":customer.getUsername();
		String gender = (customer.getGender().equals("null"))?"":customer.getGender();
		String address = (customer.getAddress().equals("null"))?"":customer.getAddress();
		String receiAddress = (customer.getreceiAddress().equals("null"))?"":customer.getreceiAddress();
		String buyAddress = (customer.getbuyAddress().equals("null"))?"":customer.getbuyAddress();
		String dob = ((customer.getDob()+"").equals("null"))?"":(customer.getDob()+"");
		String phoneNumber = (customer.getPhoneNumber().equals("null"))?"":customer.getPhoneNumber();
		String email = (customer.getEmail().equals("null"))?"":customer.getEmail();
		String isRecei_email =  ((customer.isRecei_email()==0))?"":"checked";
	%>
	<div class="container">
		<div class="text-center mt-4">
			<h2>ĐĂNG KÝ TÀI KHOẢN</h2>
			<span class="rq"><%= err %></span>
		</div>
		<%
		String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
				+ request.getContextPath();
		%>
		<form class="regisForm" action="<%=url %>/customer-controller" method="post">
			<input type="hidden" name="activity" value="register">
			<div class="row">
				<div class="col-md-6">
					<h3>Thông tin tài khoản</h3>
					<div class="mb-3">
						<label for="userName" class="form-label">Tên đăng nhập:<span
							class="rq">*</span></label> <input type="text" class="form-control"
							id="userName" name="userName" value="<%=userName%>" required="required">
					</div>
					<div class="mb-3">
						<label for="password" class="form-label">Mật khẩu:<span
							class="rq">*</span></label> <input type="password" class="form-control"
							id="password" name="password" required="required">
					</div>
					<div class="mb-3">
						<label for="rePassword" class="form-label">Nhập lại mật
							khẩu:<span class="rq">* </span> <span id="rePassErrorMsg" class="rq"></span>
						</label> <input type="password" class="form-control" id="rePassword"
							name="rePassword" required="required" oninput="checkRePass()">
					</div>
				</div>
				<div class="col-md-6">
					<h3>Thông tin khách hàng</h3>
					<div class="mb-3">
						<label for="fullName" class="form-label">Họ và tên:</label> <input
							type="text" class="form-control" id="fullName" name="fullName" value="<%=customerName%>">
					</div>
					<div class="mb-3">
						<label for="gender" class="form-label">Giới tính:</label> <select
							class="" id="gender" name="gender">
							<option></option>
							<option value="male" <%=(gender.equals("male"))?"selected":""%>>Nam</option>
							<option value="female" <%=(gender.equals("female"))?"selected":""%>>Nữ</option>
							<option value="other" <%=(gender.equals("other"))?"selected":""%>>Khác</option>
						</select> 
					</div>
					<div class="mb-3">
						<label for="dob" class="form-label">Ngày sinh:</label> <input
							type="date" class="form-control" id="dob" name="dob" value="<%=dob%>">
					</div>
				</div>
			</div>
			<hr />
			<h3>Địa chỉ khách hàng</h3>
			<div class="mb-3">
				<label for="address" class="form-label">Địa chỉ khách hàng</label> <input
					type="text" class="form-control" id="address" name="address" value="<%=address%>">
			</div>
			<div class="mb-3">
				<label for="receiAddress" class="form-label">Địa chỉ nhận
					hàng</label> <input type="text" class="form-control" id="receiAddress"
					name="receiAddress" value="<%=receiAddress%>">
			</div>
			<div class="mb-3">
				<label for="buyAddress" class="form-label">Địa chỉ mua hàng</label>
				<input type="text" class="form-control" id="buyAddress"
					name="buyAddress" value="<%=buyAddress%>">
			</div>
			<div class="mb-3">
				<label for="phoneNumber" class="form-label">Số điện thoại:</label> <input
					type="tel" class="form-control" id="phoneNumber" name="phoneNumber" value="<%=phoneNumber%>">
			</div>
			<div class="mb-3">
				<label for="email" class="form-label">Email:</label> <input
					type="email" class="form-control" id="email" name="email" value="<%=email%>">
			</div>
			<div class="mb-3 text-center">
				<input type="checkbox" id="isAcept" class="form-check-input" oninput="checkSubmit()"
					name="isAcept" value="isAcept" > <label for="isAcept" class="form-label">Bạn
					đồng ý với <a
					href="https://getbootstrap.com/docs/5.3/forms/overview/#overview">điều
						khoản của công ty.</a> <span class="rq">*</span>
				</label>
			</div>
			<div class="mb-3 text-center">
				<input type="checkbox" id="isRecei_email" class="form-check-input"
					name="isRecei_email" <%= isRecei_email %>> <label for="isRecei_email"
					class="form-label">Bạn đồng ý nhận thông tin khuyến mãi qua
					địa chỉ email.</label>
			</div>
			<button type="submit" class="btn btn-primary form-control mb-4" id="submit" name="submit" disabled="disabled">Đăng ký</button>
		</form>
	</div>
<script type="text/javascript">
	function checkRePass() {
		let password = document.getElementById("password").value;
		let rePassword = document.getElementById("rePassword").value;
		let errorMsg = document.getElementById("rePassErrorMsg");
		if (rePassword!=password) {
			errorMsg.innerHTML = "Mật khẩu nhập lại chưa trùng khớp!";
			return true;
		} else {
			errorMsg.innerHTML ="";
			return false;
		}
	}
	function checkSubmit() {
		let isAcept = document.getElementById("isAcept");
		let submit = document.getElementById("submit");
		if (isAcept.checked == 1) {
			submit.removeAttribute('disabled');
		} else {
			submit.setAttribute('disabled', 'disabled')
		}
	}
	
</script>
</body>
</html>