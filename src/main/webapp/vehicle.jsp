<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Welcome to Gondor Parking</title>
</head>
<body>
<h2>Welcome ${firstName}</h2> <br>

<form method="post" action="/vehicleIn">
<table style='width:60%'> <tr>
<td style='width:50%'>Vehicle Number: </td><td style='width:50%'><input type="text" name="vehicleNumber"></td>
</tr><tr>
<td style='width:50%'>Vehicle Type </td><td style='width:50%'><input type="radio" name="vehicleType" value="BIKE" checked>Bike
<input type="radio" name="vehicleType" value="CAR">Car</td>
</tr><tr>
<td style='width:50%'>Contact Number: </td><td style='width:50%'><input type="tel" name="contactNumber" value="${contactNumber}"></td>
</tr><tr>
<td style='width:50%'>Shared Ride: </td><td><input type="checkbox" name="isRideShared"></td>
</tr><tr>
<td style='width:50%'><input type="submit" value="Generate Slot"></td>
</tr>
</table>
</form>

</body>
</html>