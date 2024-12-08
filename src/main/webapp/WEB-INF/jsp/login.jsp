<%@page contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<%@taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Аутентификация</title>
		<style>
			.error {
				display: inline-block;
				padding: 5px;
				border: 1px solid red;
				color: red;
			}
		</style>
	</head>
	<body>
		<h1>Аутентификация</h1>
		<c:if test="${not empty param['err-msg']}">
			<p class="error">${param['err-msg']}</p>
		</c:if>
		<c:url var="url__login" value="/login.html"/>
		<form action="${url__login}" method="post">
			<div>
				<label for="id-login">Имя пользователя:</label>
				<input type="text" name="login" id="id-login">
			</div>
			<div>
				<label for="id-password">Дата:</label>
				<input type="password" name="password" id="id-password">
			</div>
			<div>
				<button type="submit">Войти</button>
			</div>
		</form>
	</body>
</html>