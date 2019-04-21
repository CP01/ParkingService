<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="java.util.List, target.cp.vehicle.parking.Level,
    target.cp.vehicle.parking.enums.ColumnNames, target.cp.vehicle.parking.enums.RowNames"
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Welcome to Gondor Parking</title>
<style>
table, th, td {
  border: 1px solid black;
}
</style>
</head>
<body>


<%
if(request.getAttribute("exitingOwner") != null) {
	out.println("<h4>Thanks "+request.getAttribute("exitingOwner")+" for using Gondor Parking Service.</h4>");
}
else if(request.getAttribute("failed") != null) {
	out.println("<h4>Failed to remove "+request.getAttribute("failed")+". Please try again.</h4>");
}
else if(request.getAttribute("slot") != null) {
	out.println("<h4>Generated Slot : "+request.getAttribute("slot")+"</h4>");
}
%>

<br>
<br>

<form method="get">
Contact Number: <input type="tel" name="contactNumber" id="contactNumber">
<button type="button" onclick="location.href='/redirect?conNum='+document.getElementById('contactNumber').value">Search/Add Owner OR Exit</button>
</form>
<br>
<br>
<h2>Summary:</h2>
<%
List<Level> levels = (List<Level>) request.getAttribute("levels");
int bikeVacantSpots = 0;
int carVacantSpots = 0;
for(Level level : levels) {
	out.print("<table><tr>Summary of Level "+levels.indexOf(level)+"</tr><tr>");
	out.print("<td>Bikes' vacant spots : "+level.getBikeSlotsAvailability());
	out.print("</td><td>Cars' vacant spots : "+level.getCarSlotsAvailability()+"</td></tr></table><br>");
	bikeVacantSpots += level.getBikeSlotsAvailability();
	carVacantSpots += level.getCarSlotsAvailability();
	out.print("<table style='width:60%'>");
	out.print("<tr>");
	out.print("<th style='width:15%'>ROWS||COLS</th>");
	for(ColumnNames col : ColumnNames.values()) {
		out.print("<th style='width:15%'>"+col+"</th>");
	}
	out.print("</tr>");
	for(RowNames row : RowNames.values()) {
		out.print("<tr><th>");
		out.print(row+"</th>");
		for(ColumnNames col : ColumnNames.values()) {
			if((row.equals(RowNames.FORMAT) || row.equals(RowNames.NARRAT)) && 
					(col.equals(ColumnNames._ABLE) || col.equals(ColumnNames._IVE) || col.equals(ColumnNames._ING))) {
				out.print("<td style='width:15%'>"+level.getBikeSummary().get(row.name()+col.name())+"</td>");
			}
			else {
				out.print("<td style='width:15%'>"+level.getCarSummary().get(row.name()+col.name())+"</td>");
			}
		}
		out.print("</tr>");
	}
	out.print("<table><br>");
}

%>

<br>

<table>
<tr>
<td>Total Bikes' vacant spots : </td><td><b><%=bikeVacantSpots%></b></td>
</tr><tr>
<td>Total Cars' vacant spots : </td><td><b><%=carVacantSpots%></b></td>
</tr>
</table>

</body>
</html>