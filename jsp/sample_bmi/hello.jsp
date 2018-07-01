<%@ page language="java" contentType="text/html; charset=UTF-8" 
	pageEncoding="UTF-8"%>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.text.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>This is the first JSP and HTML Page</title>
</head>

<body>
<h1> This is Java Server Page!!</h1>

<!-- カレンダークラス -->
<!-- %=java.util.Calendar.getInstance().getTime() % -->
<!-- importしてあるので、以下のような書き方が可能になる -->
<!-- %=Calendar.getInstance().getTime()% -->

<%
Calendar gcal = Calendar.getInstance();
SimpleDateFormat format1 = new SimpleDateFormat("GYYYY年 MM月 dd月 (E)");
SimpleDateFormat format2 = new SimpleDateFormat("ahh時 mm分");

String string1 = format1.format(gcal.getTime());
String string2 = format2.format(gcal.getTime());

out.println("本日の日付： " + string1 + "<br>現在の時刻: " + string2);
%>
<!-- JSPなのでコンパイルは不要 -->
</body>
</html>