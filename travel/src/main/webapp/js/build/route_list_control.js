$(function () {
    //获取页面cid 值
    let cid = getParameter("cid");
    let rname = getParameter("rname");
    let currentPage = 1;
    currentPage = getParameter("currentPage")

    if(rname){
        rname = window.decodeURIComponent(rname);
    }
    if(currentPage){

    }
    //请求数据
    load(cid, currentPage, rname);

});

function load(cid, currentPage, rname, pageSize) {
    $.get("route/pageQuery", {cid: cid, currentPage: currentPage, rname: rname, pageSize: pageSize}, function (data) {
        //0. 总页数和总条数
        var page_num_inf = '<i></i> 共\n' +
            '                        <span>' + data.totalPage + '</span>页<span>' + data.totalCount + '</span>条';
        $("#page_num_inf").html(page_num_inf);

        //1. 展示底部页码导航条
        var pageNum_lis = '';//总字符串
        /*
            <li><a href="">首页</a></li>
            <li class="threeword"><a href="#">上一页</a></li>
            <li><a href="#">1</a></li>
            <li><a href="#">2</a></li>
            <li><a href="#">3</a></li>
            <li><a href="#">4</a></li>
            <li><a href="#">5</a></li>
            <li><a href="#">6</a></li>
            <li><a href="#">7</a></li>
            <li><a href="#">8</a></li>
            <li><a href="#">9</a></li>
            <li><a href="#">10</a></li>
            <li class="threeword"><a href="javascript:;">下一页</a></li>
            <li class="threeword"><a href="javascript:;">末页</a></li>
         */
        //1.1 首页
        pageNum_lis += '<li onclick="load(' + cid + ',1,\''+rname+'\')"><a href="javascript:void(0);">首页</a></li>';
        //1.2 上一页
        if (data.currentPage === 1) {
            pageNum_lis += '<li onclick="load(' + cid + ',1,\''+rname+'\')" class="threeword"><a href="javascript:void(0);">上一页</a></li>';
        } else {
            pageNum_lis += '<li onclick="load(' + cid + ',' + (data.currentPage - 1) + ',\''+rname+'\')" class="threeword"><a href="javascript:void(0);">上一页</a></li>';
        }
        //1.3 页码
        var begin, end;
        if (data.currentPage <= 5) {
            begin = 1;
            end = data.totalPage > 10 ? 10 : data.totalPage;
        } else if (data.currentPage + 4 > data.totalPage) {
            begin = data.totalPage - 9;
            end = data.totalPage;
        } else {
            begin = data.currentPage - 5;
            end = data.currentPage + 4;
        }
        for (let i = begin; i <= end; i++) {
            let li;
            if (i == data.currentPage) {
                li = '<li class="curPage" onclick="load(' + cid + ',' + i + ',\''+rname+'\')"><a href="javascript:void(0)">' + i + '</a></li>';
            } else {
                li = '<li onclick="load(' + cid + ',' + i + ',\''+rname+'\')"><a href="javascript:void(0)">' + i + '</a></li>';
            }
            pageNum_lis += li;
        }
        //1.4 下一页
        if (data.currentPage === data.totalPage) {
            pageNum_lis += '<li onclick="load(' + cid + ',' + data.totalPage + ',\''+rname+'\')" class="threeword"><a href="javascript:void(0);">下一页</a></li>';
        } else {
            pageNum_lis += '<li onclick="load(' + cid + ',' + (data.currentPage + 1) + ',\''+rname+'\')" class="threeword"><a href="javascript:void(0);">下一页</a></li>';
        }
        //1.5 末页
        pageNum_lis += '<li onclick="load(' + cid + ',' + data.totalPage + ',\''+rname+'\')"><a href="javascript:void(0);">末页</a></li>';
        $("#pageNum_list").html(pageNum_lis);

        //2. 展示每页列表数据
        /*
            <li>
                <div class="img"><img src="images/04-search_03.jpg" alt=""></div>
                <div class="text1">
                    <p>【减100元 含除夕/春节出发】广州增城三英温泉度假酒店/自由行套票</p>
                    <br/>
                    <p>1-2月出发，网付立享￥1099/2人起！爆款位置有限，抢完即止！</p>
                </div>
                <div class="price">
                    <p class="price_num">
                        <span>&yen;</span>
                        <span>299</span>
                        <span>起</span>
                    </p>
                    <p><a href="route_detail.html">查看详情</a></p>
                </div>
            </li>
         */
        var route_lis = '';
        for (let i = 0; i < data.list.length; i++) {
            let li = '<li>\n' +
                '                <div class="img"><img src="' + data.list[i].rimage + '" alt=""></div>\n' +
                '                <div class="text1">\n' +
                '                    <p>' + data.list[i].rname + '</p>\n' +
                '                    <br/>\n' +
                '                    <p>' + data.list[i].routeIntroduce + '</p>\n' +
                '                </div>\n' +
                '                <div class="price">\n' +
                '                    <p class="price_num">\n' +
                '                        <span>&yen;</span>\n' +
                '                        <span>' + data.list[i].price + '</span>\n' +
                '                        <span>起</span>\n' +
                '                    </p>\n' +
                '                    <p><a href="route_detail.html?rid='+data.list[i].rid+'">查看详情</a></p>\n' +
                '                </div>\n' +
                '            </li>';
            route_lis += li;
        }
        $("#route_list").html(route_lis);
    }, "json");
}