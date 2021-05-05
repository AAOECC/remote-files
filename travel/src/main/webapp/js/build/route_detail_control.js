$(function () {
    let rid = getParameter("rid");

    detail_load(rid);

});

function detail_load(rid){

    $.get("route/findOne", {rid: rid}, function (result) {
        if (!result.flag) {
            let h1_msg = '<div style="text-align:center;red:yellow;font-weight:bold;height:250px;padding-top:100px;font-size:30px;">\n' +
                '    <h4>' + result.errorMsg + '</h4>\n' +
                '</div>';
            $("#wrap").html(h1_msg);
            return;
        }

        let route = result.data;
        // bread_box
        /*
        <a href="/">首页</a>
        <span> &gt;</span>
        <a href="#">国内游</a><span> &gt;</span>
        <a href="#">全国-曼谷6-7天自由行 泰国出境旅游 特价往返机票自由行二次确认</a>
         */
        // console.log(category_name);
        let bread_box = '<a href="/">首页</a>\n' +
            '        <span> &gt;</span>\n' +
            '        <a href="http://localhost/travel/route_list.html?cid='+route.cid+'"></a><span> &gt;</span>\n' +
            '        <a href="javascript:void(0)">'+route.rname+'</a>';
        $("#bread_box").html(bread_box);
        findCategory(route.cid);

        //prosum_left
        /*
        <dt>
            <img alt="" class="big_img"
                 src="http://www.jinmalvyou.com/Public/uploads/goods_img/img_size4/201703/m49788843d72171643297ccc033d9288ee.jpg">
        </dt>
        <dd>
            <a class="up_img up_img_disable"></a>
            <a title="" class="little_img"
               data-bigpic="http://www.jinmalvyou.com/Public/uploads/goods_img/img_size4/201703/m40920d0669855e745d97f9ad1df966ebb.jpg">
                <img src="http://www.jinmalvyou.com/Public/uploads/goods_img/img_size2/201703/m20920d0669855e745d97f9ad1df966ebb.jpg">
            </a>
            <a title="" class="little_img cur_img"
               data-bigpic="http://www.jinmalvyou.com/Public/uploads/goods_img/img_size4/201703/m49788843d72171643297ccc033d9288ee.jpg">
                <img src="http://www.jinmalvyou.com/Public/uploads/goods_img/img_size2/201703/m29788843d72171643297ccc033d9288ee.jpg">
            </a>
            <a title="" class="little_img"
               data-bigpic="http://www.jinmalvyou.com/Public/uploads/goods_img/img_size4/201703/m4531a8dbceefa2c44e6d0e35627cd2689.jpg">
                <img src="http://www.jinmalvyou.com/Public/uploads/goods_img/img_size2/201703/m2531a8dbceefa2c44e6d0e35627cd2689.jpg">
            </a>
            <a title="" class="little_img"
               data-bigpic="http://www.jinmalvyou.com/Public/uploads/goods_img/img_size4/201703/m46d8cb900e9f6c0a762aca19eae40c00c.jpg">
                <img src="http://www.jinmalvyou.com/Public/uploads/goods_img/img_size2/201703/m26d8cb900e9f6c0a762aca19eae40c00c.jpg">
            </a>
            <a title="" class="little_img"
               data-bigpic="http://www.jinmalvyou.com/Public/uploads/goods_img/img_size4/201703/m45ea00f6eba562a767b5095bbf8cffe07.jpg"
               style="display:none;">
                <img src="http://www.jinmalvyou.com/Public/uploads/goods_img/img_size2/201703/m25ea00f6eba562a767b5095bbf8cffe07.jpg">
            </a>
            <a title="" class="little_img"
               data-bigpic="http://www.jinmalvyou.com/Public/uploads/goods_img/img_size4/201703/m4265ec488cd1bc7ce749bc8c9b34b87bc.jpg"
               style="display:none;">
                <img src="http://www.jinmalvyou.com/Public/uploads/goods_img/img_size2/201703/m2265ec488cd1bc7ce749bc8c9b34b87bc.jpg">
            </a>
            <a title="" class="little_img"
               data-bigpic="http://www.jinmalvyou.com/Public/uploads/goods_img/img_size4/201703/m4e7e964909d7dd1a9f6e5494d4dc0c847.jpg"
               style="display:none;">
                <img src="http://www.jinmalvyou.com/Public/uploads/goods_img/img_size2/201703/m2e7e964909d7dd1a9f6e5494d4dc0c847.jpg">
            </a>
            <a title="" class="little_img"
               data-bigpic="http://www.jinmalvyou.com/Public/uploads/goods_img/img_size4/201703/m467db00e1b76718fab0fe8b96e10f4d35.jpg"
               style="display:none;">
                <img src="http://www.jinmalvyou.com/Public/uploads/goods_img/img_size2/201703/m267db00e1b76718fab0fe8b96e10f4d35.jpg">
            </a>
            <a title="" class="little_img"
               data-bigpic="http://www.jinmalvyou.com/Public/uploads/goods_img/img_size4/201703/m487bbbc6e43eba6aa6a36cc1a182f7a20.jpg"
               style="display:none;">
                <img src="http://www.jinmalvyou.com/Public/uploads/goods_img/img_size2/201703/m287bbbc6e43eba6aa6a36cc1a182f7a20.jpg">
            </a>
            <a class="down_img down_img_disable" style="margin-bottom: 0;"></a>
        </dd>
         */

        var route_imgs = result.data.routeImgList;

        var prosum_left = "";
        var dt = '<dt>\n' +
            '            <img alt="" class="big_img"\n' +
            '                 src="'+route_imgs[0].bigPic+'">\n' +
            '        </dt>\n';
        prosum_left += dt;

        var dd = '<dd>\n';
        dd += '<a class="up_img up_img_disable"></a>\n';
        for (let i=0; i<route_imgs.length; i++){
            var dd_a;
            if (i < 4){
                dd_a = '<a title="" class="little_img"\n' +
                    '               data-bigpic="'+route_imgs[i].bigPic+'">\n' +
                    '                <img src="'+route_imgs[i].smallPic+'">\n' +
                    '            </a>\n';
            } else {
                dd_a = '<a title="" class="little_img"\n' +
                    '               data-bigpic="'+route_imgs[i].bigPic+'" style="display:none;">\n' +
                    '                <img src="'+route_imgs[i].smallPic+'">\n' +
                    '            </a>\n';
            }
            dd += dd_a;
        }
        dd += '<a class="down_img down_img_disable" style="margin-bottom: 0;"></a>\n';
        dd += '</dd>\n'
        prosum_left += dd;
        // console.log(prosum_left);
        $("#prosum_left").html(prosum_left);

        //prosum_right
        /*
        <p class="pros_title">【尾单特卖】全国-曼谷6-7天自由行 泰国出境旅游 特价往返机票自由行二次确认</p>
        <p class="hot">1-2月出发，网付立享￥1099/2人起！爆款位置有限，抢完即止！</p>
        <div class="pros_other">
            <p>经营商家 ：黑马国旅</p>
            <p>咨询电话 : 400-618-9090</p>
            <p>地址 ： 传智播客黑马程序员</p>
        </div>
        <div class="pros_price">
            <p class="price"><strong>¥2699.00</strong><span>起</span></p>
            <p class="collect">
                <a class="btn"><i class="glyphicon glyphicon-heart-empty"></i>点击收藏</a>

                <a class="btn already" disabled="disabled"><i class="glyphicon glyphicon-heart-empty"></i>点击收藏</a>
                <span>已收藏100次</span>
            </p>
        </div>
         */

        var prosum_right = '';
        prosum_right += '<p class="pros_title">'+route.rname+'</p>\n';
        prosum_right += '<p class="hot">'+route.routeIntroduce+'</p>\n';
        var seller = route.seller;
        prosum_right += '<div class="pros_other">\n' +
            '            <p>经营商家 ：'+seller.sname+'</p>\n' +
            '            <p>咨询电话 : '+seller.consphone+'</p>\n' +
            '            <p>地址 ： '+seller.address+'</p>\n' +
            '        </div>\n';
        prosum_right += '<div class="pros_price">\n' +
            '            <p class="price"><strong>¥'+route.price+'</strong><span>起</span></p>\n' +
            '            <p class="collect">\n' +
            '                <a class="btn"><i class="glyphicon glyphicon-heart-empty"></i>点击收藏</a>\n' +
            '\n' +
            '                <a class="btn already" disabled="disabled"><i class="glyphicon glyphicon-heart-empty"></i>点击收藏</a>\n' +
            '                <span>已收藏100次</span>\n' +
            '            </p>\n' +
            '        </div>';
        $("#prosum_right").html(prosum_right);

        give();
        auto_play();
    }, "json");

}

function findCategory(cid){

    $.get("category/findOne",{cid:cid},function (category_name) {
        $("#bread_box a:eq(1)").text(category_name);
    },"text");

}
