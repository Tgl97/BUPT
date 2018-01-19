<%--
  Created by IntelliJ IDEA.
  User: Genglintng
  Date: 2017/5/16
  Time: 19:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <!-- <meta http-equiv="refresh" content="10;url=login.html"> -->
  <title>系统登录</title>
  <link rel="stylesheet" type="text/css" href="css/login.css"/>
</head>

<body>
<div id="login">
  <h1>温控系统管理后台</h1>

  <form id="formid" method="post" action="/login">
    <input type="text" required="required" placeholder="请输入账号" name="userid" id="userid"/>
    <input type="password" required="required" placeholder="请输入密码" name="userpassid" id="userpassid">
    <button type="submit" class="but" onclick="checkUser();">登录</button>
  </form>
</div>


<script type="text/javascript">
  <%--检测用户名密码是否为空--%>
  function checkUser(){
    var result = document.getElementById("userid").value;
    var password = document.getElementById("userpassid").value;

    if(result == ""  ){
      alert("用户名不能为空！");
      return false;
    }
    if(password == ""  ){
      alert("密码不能为空！");
      return false;
    }
    else{
      return true;
    }

    //document.getElementById("formid").submit()
  }

  //setTimeout("location.href='login.html'",20000)

</script>


</body>

</html>