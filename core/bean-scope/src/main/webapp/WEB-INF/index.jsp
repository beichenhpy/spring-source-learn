<%--
  Created by IntelliJ IDEA.
  User: hanpengyu
  Date: 2023/8/22
  Time: 16:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>hello world</title>
</head>
<body>
<div >
    <h1>hello world</h1>
    session: ${sessionUser.id}<br>
    request: ${requestUser.id}<br>
    application: ${applicationScope['scopedTarget.applicationUser'].name}

</div>
</body>
</html>
