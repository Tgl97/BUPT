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
  <title>分布式温控系统管理后台</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <meta name="apple-mobile-web-app-status-bar-style" content="black">
  <meta name="apple-mobile-web-app-capable" content="yes">
  <meta name="format-detection" content="telephone=no">

  <link rel="stylesheet" href="plugins/layui/css/layui.css" media="all" />
  <link rel="stylesheet" href="css/global.css" media="all">
  <link rel="stylesheet" href="plugins/font-awesome/css/font-awesome.min.css">

</head>

<body>

<div class="layui-layout layui-layout-admin">
  <div class="layui-header header header-demo">

    <div class="layui-main">
      <div class="admin-login-box">
        <a class="logo" style="left: 0;" href="">
          <span style="font-size: 22px;"><b>分布式温控系统</b></span>
        </a>
        <div class="admin-side-toggle">
          <i class="fa fa-bars" aria-hidden="true"></i>
        </div>
      </div>

      <ul class="layui-nav admin-header-item">
        <li class="layui-nav-item">
          <a href="javascript:;" class="admin-header-user">
            <img src="images/people.jpg" />
            <span>Admin</span>
          </a>

          <dl class="layui-nav-child">
            <!-- <dd>
            <a href="javascript:void(0);"><i class="fa fa-user-circle" aria-hidden="true"></i>账号信息</a>
            </dd>

            <dd>
            <a href="javascript:vodi(0);"><i class="fa fa-gear" aria-hidden="true"></i>设置</a>
            </dd>

            <dd id="lock">
                <a href="javascript:;">
            <i class="fa fa-lock" aria-hidden="true" style="padding-right: 3px;padding-left: 1px;"></i>关闭系统
                </a>
            </dd> -->
            <dd>
              <a href="javascript:void(0);"><i class="fa fa-sign-out" aria-hidden="true"></i> 登出</a>
            </dd>
          </dl>
        </li>
      </ul>
    </div>
  </div>

  <div class="layui-side layui-bg-black" id="admin-side">
    <div class="layui-side-scroll" id="admin-navbar-side" lay-filter="side"></div>
  </div>

  <div class="layui-body" style="bottom: 0;border-left: solid 2px #1AA094;" id="admin-body">
    <div class="layui-tab admin-nav-card layui-tab-brief" lay-filter="admin-tab">
      <ul class="layui-tab-title">
        <li class="layui-this">
          <i class="fa fa-dashboard" aria-hidden="true"></i>
          <cite>项目纪要</cite>
        </li>
      </ul>
      <div class="layui-tab-content" style="min-height: 150px; padding: 5px 0 0 0;">
        <div class="layui-tab-item layui-show">
          <iframe src="main.html"></iframe>
        </div>
      </div>
    </div>
  </div>

  <!--锁屏模板 start-->
  <script type="text/template" id="lock-temp">
    <div class="admin-header-lock" id="lock-box">
      <div class="admin-header-lock-img">
        <img src="images/people.jpg"/>
      </div>
      <div class="admin-header-lock-name" id="lockUserName">Admin</div>
      <input type="password" class="admin-header-lock-input" value="" name="lockPwd" id="lockPwd" />
      <button class="layui-btn layui-btn-small" id="unlock">开启系统</button>
    </div>
  </script>
  <!--锁屏模板 end -->

  <script type="text/javascript" src="plugins/layui/layui.js"></script>
  <script type="text/javascript" src="datas/nav.js"></script>
  <script src="js/index.js"></script>
</div>
</body>

</html>
