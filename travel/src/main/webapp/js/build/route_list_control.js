$(function () {
    //获取页面cid 值
    let search = location.search;
    var cid = search.split("=")[1];

    //请求数据
    load(cid);

});

function load(cid, currentPage, pageSize){
    $.get("route/pageQuery",{cid:cid,currentPage:currentPage,pageSize:pageSize},function (data) {

    },"json");
}