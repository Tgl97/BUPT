<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Xiongtao
  Date: 2017/4/15
  Time: 21:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>1号从机数据统计</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <meta name="apple-mobile-web-app-status-bar-style" content="black">
  <meta name="apple-mobile-web-app-capable" content="yes">
  <meta name="format-detection" content="telephone=no">
  <link rel="stylesheet" href="css/global.css" media="all">
  <link rel="stylesheet" href="plugins/font-awesome/css/font-awesome.min.css">
  <link rel="stylesheet" href="css/table_statistics.css" />
  <link rel="stylesheet" href="plugins/layui/css/layui.css" media="all" />

</head>

<body>

<script type="text/javascript" src="plugins/layui/layui.js"></script>

<div class="admin-main">
  <blockquote class="layui-elem-quote">
  </blockquote>

  <fieldset class="layui-elem-field" style="padding: 10px;">

    <form class="layui-form" action="/showStatistics${userId}" method="get">
      <div class="layui-inline">
        <label class="layui-form-label">开始日期</label>
        <div class="layui-input-block">
          <input type="text" name="beginDate" id="beginDate" lay-verify="date" placeholder="年-月-日" autocomplete="off" class="layui-input" onclick="layui.laydate({elem: this})">
        </div>
      </div>

      <div class="layui-inline">
        <label class="layui-form-label">至</label>
        <div class="layui-input-block">
          <input type="text" name="endDate" id="endDate" lay-verify="date" placeholder="年-月-日" autocomplete="off" class="layui-input" onclick="layui.laydate({elem: this})">
        </div>
      </div>

      <div class="layui-form-item" style="margin-top: 15px;">

        <label class="layui-form-label">统计粒度</label>

        <div class="layui-input-inline">
          <select  name="size" id="size">
            <option value="day">按照天统计</option>
            <option value="week">按照周统计</option>
            <option value="month">按照月统计</option>
          </select>
        </div>

      </div>

      <label class="layui-form-label">选择时段</label>

      <div class="layui-input-inline">
        <select  name="timeInterval" id="timeInterval">
          <option value="00:00-23:59">（全天）00:00-23:59</option>
          <option value="00:00-05:59">（凌晨）00:00-05:59</option>
          <option value="06:00-11:59">（上午）06:00-11:59</option>
          <option value="12:00-17:59">（下午）12:00-17:59</option>
          <option value="18:00-23:59">（晚上）18:00-23:59</option>
        </select>
      </div>

      <input type="submit" lay-filter="demo1" class="layui-btn" value="统计">

</form>

</fieldset>
</div>

<script>
  layui.use(['form','laydate'], function()
  {
    var form = layui.form(), laydate = layui.laydate;
  });

</script>

</body>

</html>
