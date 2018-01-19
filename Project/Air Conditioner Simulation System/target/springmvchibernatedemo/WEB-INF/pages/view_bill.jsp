<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Xiong
  Date: 2017/4/16
  Time: 0:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head>
  <meta charset="UTF-8">
  <title>查看账单</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">

  <link rel="stylesheet" href="plugins/layui/css/layui.css" media="all" />
  <link rel="stylesheet" href="css/begtable.css" />
  <link rel="stylesheet" href="css/global.css" media="all">
  <link rel="stylesheet" href="plugins/font-awesome/css/font-awesome.min.css">

</head>

<body>
<div style="margin: 15px;">
  <blockquote class="layui-elem-quote">注：服务总时长=低风速耗时+中风速耗时+高风速耗时+空闲时长。</blockquote>

  <fieldset class="layui-elem-field" style="margin-top: 20px;">
    <legend>账单信息</legend>

    <div class="layui-field-box">
      <table class="layui-table">
        <thead>
        <tr>
          <th style="text-align: center;">房间号</th>
          <th style="text-align: center;">服务开始时间</th>
          <th style="text-align: center;">服务结束时间</th>
          <th style="text-align: center;">服务总时长</th>
          <th style="text-align: center;">低风速耗时</th>
          <th style="text-align: center;">中风速耗时</th>
          <th style="text-align: center;">高风速耗时</th>
          <th style="text-align: center;">空闲时长</th>
          <th style="text-align: center;">总能量消耗(W)</th>
          <th style="text-align: center;">总费用(元)</th>
        </tr>
        </thead>
        <tbody style="text-align: center;">
        <tr>
          <td>${userId}</td>
          <td>${beginTime}</td>
          <td>${endTime}</td>
          <td>${serverTime}</td>
          <td>${lowTime}</td>
          <td>${middleTime}</td>
          <td>${highTime}</td>
          <td>${freeTime}</td>
          <td>${energyConsume}</td>
          <td>${totalFee}</td>
        </tr>
        </tbody>
      </table>
      <button class="layui-btn layui-btn-small" name="print" value="print">打印按钮</button>
    </div>
  </fieldset>

  <fieldset class="layui-elem-field" style="margin-top: 50px;">
    <legend>空调服务详细信息</legend>

    <div class="layui-field-box">
      <table class="layui-table">
        <thead>
        <tr>
          <th style="text-align: center;">调度开始时间</th>
          <th style="text-align: center;">调度结束时间</th>
          <th style="text-align: center;">调度时长</th>
          <th style="text-align: center;">调度风速</th>
        </tr>
        </thead>
        <tbody style="text-align: center;">
        <c:forEach items="${rqList}" var="e">
        <tr>
          <td>${e.requestTime}</td>
          <td>${e.stopTime}</td>
          <td>${e.requestTime}</td>
          <c:choose>
            <c:when test="${e.requestLevelWind == 3}">
              <td>高风速</td>
            </c:when>
            <c:when test="${e.requestLevelWind == 2}">
              <td>中风速</td>
            </c:when>
            <c:when test="${e.requestLevelWind == 1}">
              <td>低风速</td>
            </c:when>
          </c:choose>
        </tr>
        </c:forEach>
        </tbody>
      </table>
    </div>

  </fieldset>

</div>

</div>


</body>

</html>
