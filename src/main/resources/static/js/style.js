/**
* Created by wangchengyu on 2017/3/14.
*/

//载入动画
$(function () {
    function loadings() {
        $("body").css({
            "overflow-y":"hidden"
        });
        setTimeout(function(){
            var loader = document.getElementsByClassName("loader")[0];
            loader.className="loader fadeout" ;//使用渐隐的方法淡出loading page
            $("body").css({
                "overflow-y":"auto"
            });
            setTimeout(function(){
                loader.style.display="none";
            },1000)
        },1000);//强制显示loading page 1s
    }
    window.onload=loadings();
});


$(function () {
    function pageready() {
        //禁止x轴滚动条
        $("body").css({
            "overflow-x":"hidden"
        });

        //滚动条滚动时获取左侧导航栏以及背景的top
        $(window).scroll(function () {
            var top=$(window).scrollTop();
            $(".left").css({
                "top":top
            });
            $(".background").css({
                "top":top
            });
        });

        //动态获取整个页面高度，设置给导航栏和背景
        var height=$(window).height();
        $(window).resize(function () {
            var height=$(window).height();
            $(".left").css({
                "height":height
            });
            $(".background").css({
                "height":height
            });
        });
        $(".left").css({
            "height":height
        });
        $(".background").css({
            "height":height
        });
    }
    pageready()
});


//回到顶部
$(function () {
    function top() {
        $("#top-button").click(function () {
            $('html,body').animate({
                scrollTop:$($.attr(this,'href')).offset().top
            },500);
            return false;
        });
    }
    top()
});


//导航栏弹出绑定事件
$(function () {
    function nanup() {
        $(".nav-btn").click(function () {
            $(".left").animate({
                "left":0
            },500);
            $(".background").css({
                "display":"block"
            });
            $(this).rotate({animateTo: 180});
            $(".search-btn").rotate({animateTo: 45});
            $(this).animate({
                "left":"330px"
            },500);
            $(".search-btn").animate({
                "left":"330px"
            },500);
            $("body").css({
                "overflow-y":"hidden"
            });
            $(".search-area").animate({
                "width":"0px"
            },500);
            setTimeout(function () {
                $(".form-control").css({
                    "display":"none"
                });
            },500);
            return false;
        });
    }
    nanup()
});


//搜索栏监听事件
$(function () {
    function inputup() {
        $(".search-btn").click(function () {
            $(this).animate({
                "left":"200px"
            },500);
            $(this).rotate({animateTo: 45});
            $(".search-area").animate({
                "width":"150px"
            },500);
            $(".form-control").css({
                "display":"block"
            });
            $(".form-control").focus();
        });
    }
    inputup();
});


//input失去焦点时触发，收起搜索栏
$(function () {
    function inputdown() {
        $(".form-control").blur(function () {
            $(".search-area").animate({
                "width":"0px"
            },500);
            setTimeout(function () {
                $(".form-control").css({
                    "display":"none"
                });
            },500);
            $(".search-btn").animate({
                "left":"30px"
            },500);
            $(".search-btn").rotate({animateTo: 0});
        });
    }
    inputdown()
});


//导航栏收起绑定事件
$(function () {
    function navdown() {
        $(".background").click(function () {
            $(".left").animate({
                "left":"-300px"
            },500);
            $(".background").css({
                "display":"none"
            });
            $(".nav-btn").animate({
                "left":"30px"
            },500);
            $(".search-btn").animate({
                "left":"30px"
            },500);
            $(".nav-btn").rotate({animateTo: 0});
            $(".search-btn").rotate({animateTo: 0});
            $("body").css({
                "overflow-y":"auto"
            });
            $(".left-list-out ul li").slideUp();
            $(".left-list-down").rotate({animateTo: 0});
        });
    }
    navdown();
});



//导航栏子标签弹出收起事件
$(function () {
    function listslide() {
        $(".left-list").click(function () {
            //让导航栏显示y轴滚动条，禁止x轴滚动条
            $(".left").css({
                "overflow-y":"auto",
                "overflow-x":"hidden"
            });
            //判断是收起还是弹出
            var realheight=$(this).find(".left-list-out").height();
            if (realheight===0){
                $(this).find(".left-list-out ul li").slideDown();
                $(".left-list-down").rotate({animateTo: 180});
            }else{
                $(this).find(".left-list-out ul li").slideUp();
                $(".left-list-down").rotate({animateTo: 0});
            }
        });
    }
    listslide();
});

//foot固定在底部
$(function(){
    function footerPosition(){
        $(".foot").removeClass("fixed-bottom");
        var contentHeight = document.body.scrollHeight,//网页正文全文高度
            winHeight = window.innerHeight;//可视窗口高度，不包括浏览器顶部工具栏
        if(!(contentHeight > winHeight)){
            //当网页正文高度小于可视窗口高度时，为footer添加类fixed-bottom
            $(".foot").addClass("fixed-bottom");
        } else {
            $(".foot").removeClass("fixed-bottom");
        }
    }
    footerPosition();
    $(window).resize(footerPosition);
});
