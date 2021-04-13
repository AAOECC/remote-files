$(function () {
    $("#username").blur(checkUsername);
    $("#password").blur(checkPassword);
    $("#email").blur(checkEmail);
    $("#name").blur(checkName);
    $("#telephone").blur(checkTelephone);
    $("#birthday").blur(checkDate);
    $("#check").blur(checkCheck);

    $("#registerForm").submit(function () {
        //Ajax提交表单
        if (checkUsername() && checkPassword() && checkEmail()
            && checkName() && checkTelephone() && checkDate() && checkCheck()) {
            //校验成功
            $.post("registerUser",$(this).serialize(),function(data){
                if (data.flag) {
                    //注册成功，进行邮箱激活
                    //进行注册页面跳转
                    location.href = "/travel/register_ok.html"
                } else {
                    //注册失败，显示错误信息
                    $("#msg").text(data.errorMsg);
                }
            },"json");
        }

        return false;
    });
});

/*
表单校验函数
 */
function checkUsername() {
    var username = $("#username").val();
    var reg_username = /^\w{8,20}$/;
    var flag = reg_username.test(username);
    //alert(flag);
    if (flag) {
        //格式正确
        $("#username").css("border", "");
    } else {
        //格式错误
        $("#username").css("border", "1px solid red");
    }
    return flag;
}

function checkPassword() {
    var password = $("#password").val();
    var reg_username = /^\w{8,20}$/;
    var flag = reg_username.test(password);
    //alert(flag);
    if (flag) {
        //格式正确
        $("#password").css("border", "");
    } else {
        //格式错误
        $("#password").css("border", "1px solid red");
    }
    return flag;
}

function checkEmail() {
    var email = $("#email").val();
    var reg_username = /^\w+@\w+\.\w+$/;
    var flag = reg_username.test(email);
    //alert(flag);
    if (flag) {
        //格式正确
        $("#email").css("border", "");
    } else {
        //格式错误
        $("#email").css("border", "1px solid red");
    }
    return flag;
}

function checkName() {
    var name = $("#name").val();
    var flag = (name !== "");
    if (flag) {
        //格式正确
        $("#name").css("border", "");
    } else {
        //格式错误
        $("#name").css("border", "1px solid red");
    }
    return flag;
}

function checkTelephone() {
    var telephone = $("#telephone").val();
    var reg_tele = /^[1]([3-9])[0-9]{9}$/;
    var flag = reg_tele.test(telephone);
    if (flag) {
        //格式正确
        $("#telephone").css("border", "");
    } else {
        //格式错误
        $("#telephone").css("border", "1px solid red");
    }
    return flag;
}

function checkDate() {
    var birthday = $("#birthday").val();
    var flag = (birthday !== "");
    if (flag) {
        //格式正确
        $("#birthday").css("border", "");
    } else {
        //格式错误
        $("#birthday").css("border", "1px solid red");
    }
    return flag;
}

function checkCheck() {
    var checkCode = $("#check").val();
    if (checkCode !== "") {
        //格式正确
        $("#check").css("border", "");
    } else {
        //格式错误
        $("#check").css("border", "1px solid red");
    }
    return checkCode !== "";
}