<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
  Created by IntelliJ IDEA.
  User: Xiongtao
  Date: 2017/5/1
  Time: 15:22
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<html style="height: 100%">
<head>
  <title>费用统计</title>
  <meta charset="utf-8">
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
<body style="height: 100%; margin: 0">

<fieldset class="layui-elem-field" style="padding-left: 15px;">

  <div class="layui-form-pane" style="margin-top: 15px;">
    <div class="layui-form-item">
      <label class="layui-form-label">所选日期</label>
      <div class="layui-input-inline">
        <input class="layui-input" placeholder="所选日期" id="LAY_demorange_s" value="${beginDate}" disabled="1">
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
        <th>房间号</th>
        <th>统计时间</th>
        <th>开机次数</th>
        <th>关机次数</th>
        <th>低风速运行时长</th>
        <th>中风速运行时长</th>
        <th>高风速运行时长</th>
        <th>调度次数</th>
        <th>能耗(W)</th>
        <th>费用(元)</th>
        <th>到达目标温度次数</th>
      </tr>
      </thead>
      <tbody>
      <c:set var="index" value="1"></c:set>
      <c:forEach items="${list}" var="e">
        <tr>
          <td>${index}</td>
          <td>${e.date}</td>
          <td>${e.onCnt}</td>
          <td>${e.offCnt}</td>
          <td>${e.lowWindTime}</td>
          <td>${e.middleWindTime}</td>
          <td>${e.highWindTime}</td>
          <td>${e.scheduleCnt}</td>
          <td>${e.energyConsume}</td>
          <td>${e.totalFee}</td>
          <td>${e.targetCnt}</td>
          <c:set var="index" value="${index + 1}"></c:set>
        </tr>
      </c:forEach>
      </tbody>
    </table>
  </div>
</fieldset>

<p style="text-align: left;font-family:微软雅黑;font-weight: bold; font-size: 18px;color: gray;">&nbsp;&nbsp;&nbsp;&nbsp;房间费用统计</p><hr>

<div style="width: 95%; margin: 0 auto;">
  <div id="container0" style="height: 400px;width:100%;"></div>
  <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/echarts-all-3.js"></script>
  <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts-stat/ecStat.min.js"></script>
  <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/extension/dataTool.min.js"></script>
  <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/map/js/china.js"></script>
  <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/map/js/world.js"></script>
  <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=ZUONbpqGBsYGXNIYHicvbAbM"></script>
  <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/extension/bmap.min.js"></script>
  <script type="text/javascript">
    var dom = document.getElementById("container0");
    var myChart = echarts.init(dom);
    var app = {};
    option = null;
    app.title = '折柱混合';

    option = {
      tooltip: {
        trigger: 'axis',
        axisPointer: {
          type: 'cross',
          crossStyle: {
            color: '#999'
          }
        }
      },
      toolbox: {
        feature: {
          dataView: {show: true, readOnly: false},
          magicType: {show: true, type: ['line', 'bar']},
          restore: {show: true},
          saveAsImage: {show: true}
        }
      },
      legend: {
        data:['低风速费用','中风速费用','高风速费用', '使用时长']
      },
      xAxis: [
        {
          type: 'category',
          data: ['1号房间','2号房间','3号房间','4号房间'],
          axisPointer: {
            type: 'shadow'
          }
        }
      ],
      yAxis: [
        {
          type: 'value',
          name: '费用',
          min: 0,
          max: 15,
          interval: 3,
          axisLabel: {
            formatter: '{value} 元'
          }
        },
        {
          type: 'value',
          name: '费用',
          min: 0,
          max: 15,
          interval: 3,
          axisLabel: {
            formatter: '{value} 元'
          }
        }
      ],
      series: [
        {
          name:'低风速费用',
          type:'bar',
          data:[${lowFee1}, ${lowFee2}, ${lowFee3}, ${lowFee4}]
        },
        {
          name:'中风速费用',
          type:'bar',
          data:[${middleFee1}, ${middleFee2}, ${middleFee3}, ${middleFee4}]
        },
        {
          name:'高风速费用',
          type:'bar',
          yAxisIndex: 1,
          data:[${highFee1}, ${highFee2}, ${highFee3}, ${highFee4}]
        },
        {
          name:'总费用',
          type:'line',
          yAxisIndex: 1,
          data:[${totalFee1}, ${totalFee2}, ${totalFee3}, ${totalFee4}]
        }
      ]
    };
    ;
    if (option && typeof option === "object") {
      myChart.setOption(option, true);
    }
  </script>

  <!--
  <p style="text-align: left;font-family:微软雅黑;font-weight: bold; font-size: 18px;color: gray;">&nbsp;&nbsp;&nbsp;&nbsp;调用数据统计</p><hr>
  <div style="width: 100%;height: 50%;"> -->

</div>

</div>

<p style="text-align: left;font-family:微软雅黑;font-weight: bold; font-size: 18px;color: gray;">&nbsp;&nbsp;&nbsp;&nbsp;占比数据统计</p><hr>

<div style="height:50%;width: 95%; margin: 0 auto;">

  <div id="container1" style="height: 100%;width: 25%;border-right:1px solid lightgray;float: left;"></div>

  <script type="text/javascript">
    var dom = document.getElementById("container1");
    var myChart = echarts.init(dom);
    var app = {};
    option = null;
    option = {
      title : {
        text: '房间1',
        subtext: '被调度${scheduleCnt1}次' ,
        x:'center'
      },
      tooltip : {
        trigger: 'item',
        formatter: "{a} <br/>{b} : {c} ({d}%)"
      },
      legend: {
        orient: 'vertical',
        left: 'left',
        data: ['低风速','中风速','高风速']
      },
      series : [
        {
          name: '风速类型',
          type: 'pie',
          radius : '55%',
          center: ['50%', '60%'],
          data:[
            {value:${lowTime1}, name:'低风速'},
            {value:${middleTime1}, name:'中风速'},
            {value:${highTime1}, name:'高风速'},
          ],
          itemStyle: {
            emphasis: {
              shadowBlur: 10,
              shadowOffsetX: 0,
              shadowColor: 'rgba(0, 0, 0, 0.5)'
            }
          }
        }
      ]
    };
    ;
    if (option && typeof option === "object") {
      myChart.setOption(option, true);
    }
  </script>



  <div id="container2" style="height:100%;width: 25%;border-right:1px solid lightgray;float: left;"></div>

  <script type="text/javascript">
    var dom = document.getElementById("container2");
    var myChart = echarts.init(dom);
    var app = {};
    option = null;
    option = {
      title : {
        text: '房间2',
        subtext: '被调度${scheduleCnt2}次' ,
        x:'center'
      },
      tooltip : {
        trigger: 'item',
        formatter: "{a} <br/>{b} : {c} ({d}%)"
      },
      legend: {
        orient: 'vertical',
        left: 'left',
        data: ['低风速','中风速','高风速']
      },
      series : [
        {
          name: '风速类型',
          type: 'pie',
          radius : '55%',
          center: ['50%', '60%'],
          data:[
            {value:${lowTime2}, name:'低风速'},
            {value:${middleTime2}, name:'中风速'},
            {value:${highTime2}, name:'高风速'},
          ],
          itemStyle: {
            emphasis: {
              shadowBlur: 10,
              shadowOffsetX: 0,
              shadowColor: 'rgba(0, 0, 0, 0.5)'
            }
          }
        }
      ]
    };
    ;
    if (option && typeof option === "object") {
      myChart.setOption(option, true);
    }
  </script>


  <div id="container3" style="height:100%;width: 25%;border-right:1px solid lightgray;float: left;"></div>

  <script type="text/javascript">
    var dom = document.getElementById("container3");
    var myChart = echarts.init(dom);
    var app = {};
    option = null;
    option = {
      title : {
        text: '房间3',
        subtext: '被调度${scheduleCnt3}次' ,
        x:'center'
      },
      tooltip : {
        trigger: 'item',
        formatter: "{a} <br/>{b} : {c} ({d}%)"
      },
      legend: {
        orient: 'vertical',
        left: 'left',
        data: ['低风速','中风速','高风速']
      },
      series : [
        {
          name: '风速类型',
          type: 'pie',
          radius : '55%',
          center: ['50%', '60%'],
          data:[
            {value:${lowTime3}, name:'低风速'},
            {value:${middleTime3}, name:'中风速'},
            {value:${highTime3}, name:'高风速'},
          ],
          itemStyle: {
            emphasis: {
              shadowBlur: 10,
              shadowOffsetX: 0,
              shadowColor: 'rgba(0, 0, 0, 0.5)'
            }
          }
        }
      ]
    };
    ;
    if (option && typeof option === "object") {
      myChart.setOption(option, true);
    }
  </script>


  <div id="container4" style="height:100%;width: 24%;float: left;"></div>

  <script type="text/javascript">
    var dom = document.getElementById("container4");
    var myChart = echarts.init(dom);
    var app = {};
    option = null;
    option = {
      title : {
        text: '房间4',
        subtext: '被调度${scheduleCnt4}次' ,
        x:'center'
      },
      tooltip : {
        trigger: 'item',
        formatter: "{a} <br/>{b} : {c} ({d}%)"
      },
      legend: {
        orient: 'vertical',
        left: 'left',
        data: ['低风速','中风速','高风速']
      },
      series : [
        {
          name: '风速类型',
          type: 'pie',
          radius : '55%',
          center: ['50%', '60%'],
          data:[
            {value:${lowTime4}, name:'低风速'},
            {value:${middleTime4}, name:'中风速'},
            {value:${highTime4}, name:'高风速'},
          ],
          itemStyle: {
            emphasis: {
              shadowBlur: 10,
              shadowOffsetX: 0,
              shadowColor: 'rgba(0, 0, 0, 0.5)'
            }
          }
        }
      ]
    };
    ;
    if (option && typeof option === "object") {
      myChart.setOption(option, true);
    }
  </script>


</div>

</body>
</html>
