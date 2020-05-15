//轮播图效果实现
var time = null;//全局定时器
var index = 1;

function auto() {
    timer = setInterval(function () { //设置自动播放的定时器
        next();
    }, 2000);
}

auto();

/* 动态修改ul的宽度 */
var width = $(".pic li").width() * $(".pic li").length;
width = width + "px";

$(".pic").css("width", width);

$('.next').click(function () {
    next();
});

$('.prev').click(function () {
    prev();
});

function next() {
    index++;
    if (index > 5) {
        index = 1;
    }
    $(".pic").stop(false, true);
    $(".pic").animate({left: "-1440"}, function () {
        $(".pic li:eq(0)").appendTo($(".pic"));
        $(".pic").css("left", "-720px")
    });
    // iconHover(index);
}

function prev() {
    index--;
    if (index < 1) {
        index = 5;
    }
    $(".pic").stop(false, true);
    $(".pic").animate({left: "0"}, function () {
        $(".pic li:last-child").prependTo($(".pic"));
        $(".pic").css("left", "-720px")
    });
}

$('.carousel').mouseover(function () { //鼠标移入
    clearInterval(timer);
    $('.btn').css("opacity", 1)
});

$('.carousel').mouseleave(function () { //鼠标移出
    auto();
    $('.btn').css("opacity", 0)
});