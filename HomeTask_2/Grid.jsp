<%
try
{
	String col = request.getParameter("columns");
	String row = request.getParameter("rows");

	int columns = Integer.parseInt(col);
	int rows = Integer.parseInt(row);

	if(columns % 2 == 0 && rows % 2 == 0)
	{
		out.write("ONLY ODD NUMBERS");
	}
	else
	{
		if(columns < 3 || rows < 3)
			out.write("BOTH OF NUMBERS MUST BE 3 OR MORE");
		else
		{

		int midCol = columns / 2;
		int midRows = rows / 2;
		int counter = 0;

%>
<table border = '5' bordercolor = red>
<%
		for(int i = 0; i < rows; i++)
		{
			for(int j = 0; j < columns; j++)
			{
				counter++;

				if(i == midRows && j == midCol)
				{
%>
				<td width = "50" align = "center"> <%=counter%> </td>
<%				
				}
				else
				{
%>
				<td width = "50" align = "center" bgcolor = "green"> <%=counter%> </td>
<%
				}
			}	
%>
	<tr>
<%
		}
%>
	</table>
<%
		}
	}
}
catch(Exception ex)
{
	out.write("ERROR, ONLY ODD NUMBERS");
}
	
%>

<form action="HomeTask_2.jsp">	
	<br> 
	<input type="submit" value="BACK"/>
</form>
