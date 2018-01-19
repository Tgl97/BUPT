<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Xiongtao
  Date: 2017/4/16
  Time: 20:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head>
  <meta charset="UTF-8">
  <title>Layui</title>
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
  <blockquote class="layui-elem-quote">您可以在此查看中央控制机调度从机空调的详细情况。</blockquote>

  <fieldset class="layui-elem-field" style="padding-left: 15px;">

    <div class="layui-form-pane" style="margin-top: 15px;">
      <div class="layui-form-item">
        <label class="layui-form-label">时间范围</label>
        <div class="layui-input-inline">
          <input class="layui-input" placeholder="统计起始时间" id="LAY_demorange_s" value="${beginDate}" disabled="1">
        </div>
        <div class="layui-input-inline">
          <input class="layui-input" placeholder="统计截止时间" id="LAY_demorange_e" value="${endDate}" disabled="1">
        </div>
      </div>


      <div class="layui-form-item">
        <label class="layui-form-label">统计粒度</label>
        <div class="layui-input-inline">
          <input type="text" name="statics" placeholder="统计粒度" class="layui-input" value="${size}" disabled="1">
        </div>
      </div>

      <div class="layui-form-item">
        <label class="layui-form-label">所选时段</label>
        <div class="layui-input-inline">
          <input type="text" name="time_part" placeholder="所选时段" disabled="1" class="layui-input" value="${timeInterval}">
        </div>
      </div>
    </div>

  </fieldset>

  <fieldset class="layui-elem-field">
    <legend>温控调度详情</legend>

    <div class="layui-field-box">
      <table class="layui-table">
        <thead>
        <tr>
          <th style="text-align: center;">调度计数</th>
          <th style="text-align: center;">调度起始时间</th>
          <th style="text-align: center;">调度终止时间</th>
          <th style="text-align: center;">调度时长</th>
          <th style="text-align: center;">调度风速</th>
        </tr>
        </thead>
        <tbody style="text-align: center;">
        <c:set var="index" value="0"></c:set>
        <c:forEach items="${list}" var="e">
          <c:set var="index" value="${index + 1}"></c:set>
        <tr>
          <td>${index}</td>
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
  <h1>${message}</h1>
</div>

</div>


</body>

</html>
