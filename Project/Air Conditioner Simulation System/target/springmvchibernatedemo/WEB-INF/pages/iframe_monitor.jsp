<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Xiongtao
  Date: 2017/5/1
  Time: 15:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <link rel="stylesheet" href="plugins/layui/css/layui.css" media="all" />
  <link rel="stylesheet" href="css/global.css" media="all">
  <link rel="stylesheet" href="plugins/font-awesome/css/font-awesome.min.css">
  <link rel="stylesheet" href="css/table_monitor.css" />
</head>

<body>
<table class="site-table table-hover" style="margin-top: 0px;">
  <thead>
  <tr>
    <th>从机号</th>
    <th>当前温度(°C)</th>
    <th>目标温度(°C)</th>
    <th>风速</th>
    <th>运行状态</th>
    <th>服务状态</th>
    <th>总计费用(元)</th>
    <th>已服务时长</th>
    <th>操作</th>
  </tr>
  </thead>

  <tbody>
  <c:forEach items="${users}" var="e">
    <tr>
      <td>${e.user.user_id}</td>
      <td>${e.user.now_temp}</td>
      <td>${e.user.final_temp}</td>
      <c:choose>
        <c:when test="${e.user.wind == 3}">
          <td>高风速</td>
        </c:when>
        <c:when test="${e.user.wind == 2}">
          <td>中风速</td>
        </c:when>
        <c:when test="${e.user.wind == 1}">
          <td>低风速</td>
        </c:when>
      </c:choose>
      <c:choose>
        <c:when test="${e.mode == false}">
          <td>升温</td>
        </c:when>
        <c:when test="${e.mode == true}">
          <td>降温</td>
        </c:when>
      </c:choose>
      <c:choose>
        <c:when test="${e.user.is_open == false}">
          <td>关机</td>
        </c:when>
        <c:when test="${e.user.is_dd == false}">
          <td>正在挂起</td>
        </c:when>
        <c:when test="${e.user.is_dd == true}">
          <td>正在服务</td>
        </c:when>
      </c:choose>
      <td>${e.fee}</td>
      <td>${e.user.serv_time}</td>
      <!-- 								<td style="text-align:center;"><i class="layui-icon" style="color:green;"></i></td> -->
      <td><a href="view_bill${e.user.user_id}" target="_blank" class="layui-btn layui-btn-normal layui-btn-mini" style="background: gray;">查看账单</a></td>
        <!-- 									<a href="/manage/article_edit_1" class="layui-btn layui-btn-mini">编辑</a>
                                            <a href="javascript:;" data-id="1" data-opt="del" class="layui-btn layui-btn-danger layui-btn-mini">删除</a> -->
      </td>
    </tr>
  </c:forEach>
  </tbody>

</table>

<script language="javascript">

  function myrefresh()
  {
    window.location.reload();
  }
  setTimeout('myrefresh()',5000); //指定2秒刷新一次



  layui.config({
    base: 'plugins/layui/modules/'
  });

  layui.use(['icheck', 'laypage','layer'], function() {
    var $ = layui.jquery,
            laypage = layui.laypage,
            layer = parent.layer === undefined ? layui.layer : parent.layer;
    $('input').iCheck({
      checkboxClass: 'icheckbox_flat-green'
    });
  });
</script>

</body>

</html>
