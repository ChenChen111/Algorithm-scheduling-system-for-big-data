<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
    <title>无标题文档</title>
</head>

<body>
<table width="337" border="1">
    <tr>
        <th width="104" scope="col">job_id</th>
               <th width="85" scope="col">job_name</th>
        <th width="64" scope="col">job_type</th>
    </tr>
    <tbody>
        <c:forEach items="${allJobList}" var="j">
            <tr>
                <td>${j.job_id}</td>
                <td>${j.job_name}</td>
                <td>${j.job_type}</td>
            </tr>
        </c:forEach>
    </tbody>

</table>

<script>
    <!-- 按id查询Job -->

</script>
</body>
</html>