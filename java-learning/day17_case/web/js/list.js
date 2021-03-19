function del_checked() {
    var delCheckedBtn = document.getElementById("delChecked");
    var checkboxes = document.getElementsByClassName("checkbox-inline");
    var hrefStr = delCheckedBtn.href;
    for (var i = 1,count=0; i < checkboxes.length; i++) {
        if(checkboxes[i].checked===true){
            if(!(count === 0)){
                hrefStr += "&"
            }
            hrefStr += "id="+checkboxes[i].getAttribute("user_id");
            count++;
        }
    }
    delCheckedBtn.href = hrefStr;
}

function del_alert(id){
    if(confirm("确定删除么")){
        location.herf="${pageContext.request.contextPath}/delUserServlet?id="+id;
    }
}

function check_it(){
    var firstCheckbox = document.getElementById("firstCheckbox");
    var checkboxes = document.getElementsByClassName("checkbox-inline");
    var flag = firstCheckbox.checked;
    for (var checkbox of checkboxes) {
        checkbox.checked = flag;
    }
}

