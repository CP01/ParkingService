<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Welcome to Gondor Parking</title>
</head>
<body>
<form method="post" action="/addOwner">
<table style='width:60%'> <tr>
<td style='width:50%'>First Name: </td><td style='width:50%'><input type="text" name="firstName"></td>
</tr><tr>
<td style='width:50%'>Last Name: </td><td style='width:50%'><input type="text" name="lastName"></td>
</tr><tr>
<td style='width:50%'>Contact Number: </td><td style='width:50%'><input type="tel" name="contactNumber" id="contactNumber" value="${contactNumber}"></td>
</tr></table><table style='width:60%'>
<tr>
<td style='width:30%'>Owner Type </td><td style='width:23%'><input type="radio" name="ownerType" value="NORMAL" checked>Normal
</td><td style='width:23%'><input type="radio" name="ownerType" value="ELDER">Senior Citizen</td>
<td style='width:23%'><input type="radio" name="ownerType" value="ROYAL">Royal Family Member</td>
</tr><tr>
<td><input type="submit" value="Save and Add Vechicle"></td>
</tr>
</table>
</form>


</body>
</html>