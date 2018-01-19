<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Xiongtao
  Date: 2017/5/8
  Time: 17:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head>
  <meta charset="UTF-8">
  <title>从机日志</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <meta name="apple-mobile-web-app-status-bar-style" content="black">
  <meta name="apple-mobile-web-app-capable" content="yes">
  <meta name="format-detection" content="telephone=no">
  <link rel="stylesheet" href="css/global.css" media="all">
  <link rel="stylesheet" href="plugins/font-awesome/css/font-awesome.min.css">
  <link rel="stylesheet" href="css/table_log.css" />
  <link rel="stylesheet" href="plugins/layui/css/layui.css" media="all" />

</head>

<body>

<script type="text/javascript" src="plugins/layui/layui.js"></script>

<div class="admin-main">
  <blockquote class="layui-elem-quote">
    <p><b>系统日志查询</b></p>
  </blockquote>

  <!-- <fieldset class="layui-elem-field" style="padding: 10px;">

      <form class="layui-form" action="">
          <div class="layui-form-pane" style="margin-top: 15px;">
            <div class="layui-form-item">
              <label class="layui-form-label">范围选择</label>
              <div class="layui-input-inline">
                <input class="layui-input" placeholder="开始日" id="LAY_demorange_s" name="time_start">
              </div>
              <div class="layui-input-inline">
              <input class="layui-input" placeholder="截止日" id="LAY_demorange_e" name="time_stop">
              </div>
  <button class="layui-btn" id="search"><i class="layui-icon">&#xe615;</i> 查询</button>
            </div>
          </div>
      </form>
  </fieldset> -->

  <div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">

    <ul class="layui-tab-title">
      <li class="layui-this">操作日志</li>
      <li>状态日志</li>
    </ul>
    <div class="layui-tab-content">

      <div class="layui-tab-item layui-show">


        <fieldset class="layui-elem-field">
          <legend>操作日志</legend>
          <div class="layui-field-box">
            <table class="site-table table-hover">
              <thead>
              <tr>
                <th style="text-align: center;">序号</th>
                <th style="text-align: center;">从机编号</th>
                <th style="text-align: center;">操作时间</th>
                <th style="text-align: center;">操作内容</th>
              </tr>
              </thead>
              <tbody>
              <c:forEach items="${opLogList}" var="e">
              <tr>
                <td>${e.keyId}</td>
                <td>${e.slaveId}</td>
                <td>${e.operationTime}</td>
                <td>${e.operationContent}</td>
              </tr>
              </c:forEach>
              </tbody>
            </table>
          </div>
        </fieldset>


      </div>
      <div class="layui-tab-item">

        <fieldset class="layui-elem-field">
          <legend>状态日志</legend>
          <div class="layui-field-box">
            <table class="site-table table-hover">
              <thead>
              <tr>
                <th style="text-align: center;">序号</th>
                <th style="text-align: center;">记录时间</th>
                <th style="text-align: center;">从机编号</th>
                <th style="text-align: center;">当前温度(°C)</th>
                <th style="text-align: center;">目标温度(°C)</th>
                <th style="text-align: center;">当前风速</th>
                <th style="text-align: center;">电源状态</th>
                <th style="text-align: center;">工作模式</th>
                <th style="text-align: center;">服务状态</th>
                <th style="text-align: center;">运行状态</th>

              </tr>
              </thead>
              <tbody>
              <c:forEach items="${stateLogList}" var="e">
              <tr>
                <td>${e.keyId}</td>
                <td>${e.nowTime}</td>
                <td>${e.slaveId}</td>
                <td>${e.slaveNowTemp}</td>
                <td>${e.slaveTargetTemp}</td>
                <c:choose>
                  <c:when test="${e.slaveNowWindLevel == 3}">
                    <td>高风速</td>
                  </c:when>
                  <c:when test="${e.slaveNowWindLevel == 2}">
                    <td>中风速</td>
                  </c:when>
                  <c:when test="${e.slaveNowWindLevel == 1}">
                    <td>低风速</td>
                  </c:when>
                </c:choose>
                <c:choose>
                  <c:when test="${e.isPowerOn == 0}">
                    <td>OFF</td>
                  </c:when>
                  <c:when test="${e.isPowerOn == 1}">
                    <td>ON</td>
                  </c:when>
                </c:choose>
                <c:choose>
                  <c:when test="${e.slaveWorkMode == 1}">
                    <td>制热</td>
                  </c:when>
                  <c:when test="${e.slaveWorkMode == 0}">
                    <td>制冷</td>
                  </c:when>
                </c:choose>
                <c:choose>
                  <c:when test="${e.isServed == 1}">
                    <td>正在服务</td>
                  </c:when>
                  <c:when test="${e.isServed == 0}">
                    <td>结束服务</td>
                  </c:when>
                </c:choose>
                <c:choose>
                  <c:when test="${e.slaveState == 1}">
                    <td>调度中</td>
                  </c:when>
                  <c:when test="${e.slaveState == 0}">
                    <td>待机</td>
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


    <script>
      layui.use('laydate', function(){
        var laydate = layui.laydate;

        var start = {
          min: laydate.now()
          ,max: '2099-06-16 23:59:59'
          ,istoday: false
          ,choose: function(datas){
            end.min = datas; //开始日选好后，重置结束日的最小日期
            end.start = datas //将结束日的初始值设定为开始日
          }
        };

        var end = {
          min: laydate.now()
          ,max: '2099-06-16 23:59:59'
          ,istoday: false
          ,choose: function(datas){
            start.max = datas; //结束日选好后，重置开始日的最大日期
          }
        };

        document.getElementById('LAY_demorange_s').onclick = function(){
          start.elem = this;
          laydate(start);
        }
        document.getElementById('LAY_demorange_e').onclick = function(){
          end.elem = this
          laydate(end);
        }

      });
    </script>


    <script>
      layui.use('element', function() {
        var $ = layui.jquery,
                element = layui.element(); //Tab的切换功能，切换事件监听等，需要依赖element模块

        //触发事件
        var active = {
          tabAdd: function() {
            //新增一个Tab项
            element.tabAdd('demo', {
              title: '新选项' + (Math.random() * 1000 | 0) //用于演示
              ,
              content: '内容' + (Math.random() * 1000 | 0)
            })
          },
          tabDelete: function() {
            //删除指定Tab项
            element.tabDelete('demo', 2); //删除第3项（注意序号是从0开始计算）
          },
          tabChange: function() {
            //切换到指定Tab项
            element.tabChange('demo', 1); //切换到第2项（注意序号是从0开始计算）
          }
        };

        $('.site-demo-active').on('click', function() {
          var type = $(this).data('type');
          active[type] ? active[type].call(this) : '';
        });
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
