<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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

    <form class="layui-form" action="/fee_statistics" method="get">
      <div class="layui-inline">
        <label class="layui-form-label">选择日期</label>
        <div class="layui-input-block">
          <input type="text" name="beginDate" id="beginDate" lay-verify="date" placeholder="年-月-日" autocomplete="off" class="layui-input" onclick="layui.laydate({elem: this})">
        </div>
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