<%--
  Created by IntelliJ IDEA.
  User: Xiongtao
  Date: 2017/4/22
  Time: 23:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>

<head>
  <meta charset="utf-8">
  <title>系统设置</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <link rel="stylesheet" href="plugins/layui/css/layui.css" media="all" />

  <meta name="apple-mobile-web-app-status-bar-style" content="black">
  <meta name="apple-mobile-web-app-capable" content="yes">
  <meta name="format-detection" content="telephone=no">

  <link rel="stylesheet" href="plugins/font-awesome/css/font-awesome.min.css">
</head>

<body>

<div style="margin: 15px;">


  <fieldset class="layui-elem-field">
    <legend>系统设置</legend>
    <form class="layui-form" action="/systemSet" method="post">

      <div class="layui-form-item" style="margin-top: 50px;margin-left: 350px;">
        <label class="layui-form-label">工作模式：</label>
        <div class="layui-input-block">
          <input type="radio" name="mode" value="false" title="制热模式" ${heating}>
          <input type="radio" name="mode" value="true" title="制冷模式" ${cooling}>
        </div>
      </div>



      <div class="layui-form-item" style="margin-left: 350px;">
        <div class="layui-inline">
          <label class="layui-form-label">温度范围：</label>
          <div class="layui-input-inline" style="width: 100px;">
            <input type="number" name="temp_min" value="${lowT}" placeholder="°C" class="layui-input">
          </div>
          <div class="layui-form-mid">—</div>
          <div class="layui-input-inline" style="width: 100px;">
            <input type="number" name="temp_max" value="${highT}" placeholder="°C" class="layui-input">
          </div>
        </div>
      </div>

      <div class="layui-form-item" style="margin-left: 350px;">
        <div class="layui-inline">
          <label class="layui-form-label">目标温度：</label>
          <div class="layui-input-inline">
            <input type="number" name="target_temp" value="${targetT}" placeholder="°C" class="layui-input">
          </div>
        </div>
      </div>

      <div class="layui-form-item" style="margin-left: 350px;">
        <label class="layui-form-label">默认风速：</label>
        <div class="layui-input-inline">
          <select name="wind_speed">
            <option value="1" ${lowSelect}>低风速</option>
            <option value="2" ${middleSelect}>中风速</option>
            <option value="3" ${highSelect}>高风速</option>
          </select>
        </div>
      </div>

      <div class="layui-form-item" style="margin-top: 50px; margin-left: 450px;">
        <button class="layui-btn" type="submit">保存设置</button>
      </div>

    </form>

  </fieldset>

</div>

<script type="text/javascript" src="plugins/layui/layui.js"></script>
<script>
  layui.use('element', function() {
    var element = layui.element(); //导航的hover效果、二级菜单等功能，需要依赖element模块

  });

  layui.use(['form', 'layedit', 'laydate'], function() {
    var form = layui.form(),
            layer = layui.layer,
            layedit = layui.layedit,
            laydate = layui.laydate;

    //监听提交
    form.on('submit(demo1)', function(data) {
      layer.alert(JSON.stringify(data.field), {
        title: '最终的提交信息'
      })
      return false;
    });
  });

</script>

</body>

</html>
