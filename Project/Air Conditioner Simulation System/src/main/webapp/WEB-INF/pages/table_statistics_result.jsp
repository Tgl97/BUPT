<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Xiongtao
  Date: 2017/4/16
  Time: 10:41
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
    <p><b>温控系统运行数据统计</b></p>
  </blockquote>

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
    <legend>统计报单</legend>
    <div class="layui-field-box">
      <table class="site-table table-hover">
        <thead>
        <tr>
          <th>统计时间</th>
          <th>开机次数</th>
          <th>关机次数</th>
          <th>低风速运行时长</th>
          <th>中风速运行时长</th>
          <th>高风速运行时长</th>
          <th>调度次数</th>
          <th>能耗(W)</th>
          <th>费用(元)</th>
          <th>调度起止时间</th>
        </tr>
        </thead>
        <tbody>
        <c:set var="index" value="-1"></c:set>
        <c:forEach items="${list}" var="e">
        <tr>
          <td>${e.date}</td>
          <td>${e.onCnt}</td>
          <td>${e.offCnt}</td>
          <td>${e.lowWindTime}</td>
          <td>${e.middleWindTime}</td>
          <td>${e.highWindTime}</td>
          <td>${e.scheduleCnt}</td>
          <td>${e.energyConsume}</td>
          <td>${e.totalFee}</td>
          <c:set var="index" value="${index + 1}"></c:set>
          <td>
            <a href="/view_schedule_detail${index}" target="_blank" class="layui-btn layui-btn-normal layui-btn-mini">查看明细</a>
            <!-- 									<a href="/manage/article_edit_1" class="layui-btn layui-btn-mini">编辑</a>
                                                <a href="javascript:;" data-id="1" data-opt="del" class="layui-btn layui-btn-danger layui-btn-mini">删除</a> -->
          </td>
        </tr>
        </c:forEach>
        </tbody>
      </table>
    </div>
  </fieldset>

  <!-- 			<div class="admin-table-page">
                  <div id="page" class="page">
                  </div>
              </div>
          </div> -->

  <script>
    layui.use(['form', 'layedit', 'laydate'], function() {
      var form = layui.form(),
              layer = layui.layer,
              layedit = layui.layedit,
              laydate = layui.laydate;

      //创建一个编辑器
      var editIndex = layedit.build('LAY_demo_editor');
      //自定义验证规则
      form.verify({
        title: function(value) {
          if(value.length < 5) {
            return '标题至少得5个字符啊';
          }
        },
        pass: [/(.+){6,12}$/, '密码必须6到12位'],
        content: function(value) {
          layedit.sync(editIndex);
        }
      });

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

      //page
      laypage({
        cont: 'page',
        pages: 25 //总页数
        ,
        groups: 5 //连续显示分页数
        ,
        jump: function(obj, first) {
          //得到了当前页，用于向服务端请求对应数据
          var curr = obj.curr;
          if(!first) {
            //layer.msg('第 '+ obj.curr +' 页');
          }
        }
      });

      // $('#search').on('click', function() {
      // 	parent.layer.alert('你点击了搜索按钮')
      // });

      $('#add').on('click', function() {
        $.get('temp/edit-form.html', null, function(form) {
          layer.open({
            type: 1,
            title: '添加表单',
            content: form,
            btn: ['保存', '取消'],
            area: ['600px', '400px'],
            maxmin: true,
            yes: function(index) {
              console.log(index);
            },
            full: function(elem) {
              var win = window.top === window.self ? window : parent.window;
              $(win).on('resize', function() {
                var $this = $(this);
                elem.width($this.width()).height($this.height()).css({
                  top: 0,
                  left: 0
                });
                elem.children('div.layui-layer-content').height($this.height() - 95);
              });
            }
          });
        });
      });

      $('#import').on('click', function() {
        var that = this;
        var index = layer.tips('只想提示地精准些', that,{tips: [1, 'white']});
        $('#layui-layer'+index).children('div.layui-layer-content').css('color','#000000');
      });

      $('.site-table tbody tr').on('click', function(event) {
        var $this = $(this);
        var $input = $this.children('td').eq(0).find('input');
        $input.on('ifChecked', function(e) {
          $this.css('background-color', '#EEEEEE');
        });
        $input.on('ifUnchecked', function(e) {
          $this.removeAttr('style');
        });
        $input.iCheck('toggle');
      }).find('input').each(function() {
        var $this = $(this);
        $this.on('ifChecked', function(e) {
          $this.parents('tr').css('background-color', '#EEEEEE');
        });
        $this.on('ifUnchecked', function(e) {
          $this.parents('tr').removeAttr('style');
        });
      });
      $('#selected-all').on('ifChanged', function(event) {
        var $input = $('.site-table tbody tr td').find('input');
        $input.iCheck(event.currentTarget.checked ? 'check' : 'uncheck');
      });
    });

  </script>

</body>

</html>
