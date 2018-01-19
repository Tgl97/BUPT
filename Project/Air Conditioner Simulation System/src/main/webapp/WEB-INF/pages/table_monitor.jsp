<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Xiongtao
  Date: 2017/4/16
  Time: 16:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head>
  <meta charset="UTF-8">
  <title>从机监控</title>
  <link rel="stylesheet" href="plugins/layui/css/layui.css" media="all" />
  <link rel="stylesheet" href="css/global.css" media="all">
  <link rel="stylesheet" href="plugins/font-awesome/css/font-awesome.min.css">
  <link rel="stylesheet" href="css/table_monitor.css" />
</head>

<body>
<div class="admin-main">

  <blockquote class="layui-elem-quote">
    <p><b>从机运行状况实时监控</b></p>
  </blockquote>

  <fieldset class="layui-elem-field">
    <legend>监控数据</legend>

    <div class="layui-field-box">
      <iframe src="iframe_monitor" width="100%" height="350px" frameborder="0" name="mainframe" id="mainframe" scrolling="no"></iframe>
    </div>
  </fieldset>
  </div>

  <script language="javascript">

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
