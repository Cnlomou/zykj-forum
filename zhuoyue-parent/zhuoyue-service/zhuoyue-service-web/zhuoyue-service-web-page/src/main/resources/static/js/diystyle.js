//验证码
var count = 50; //时间

function sendMessage() {
    counttemp = count;
    $('#verify').attr("disabled", "true").text(counttemp + "秒后获取");
    timer = window.setInterval(function () {
        if (counttemp == 0) {
            window.clearInterval(timer);
            $('#verify').removeAttr("disabled").text("重新获取");
        } else {
            counttemp--;
            $('#verify').text(counttemp + "秒后获取");
        }
    }, 1000)
}

$('#model').click(function () {
        $('.ui.modal')
            .modal('setting', 'closable', false)
            .modal('show')
    }
);

$('.menu .item').tab();

$('.menu.toggle').click(function () {
    $('.m-item').toggleClass('m-mobile-hide');

});

$('.ui.dropdown')
    .dropdown();

$('#toTop-button').click(function () {
    $(window).scrollTo(0, 1000);
});

$('.ui.login').form({
    fields: {
        username: {
            identifier: 'username',
            rules: [{
                type: 'empty',
                prompt: '请输入用户名'
            }]
        },
        password: {
            identifier: 'password',
            rules: [{
                type: 'empty',
                prompt: '请输入密码'
            }]
        }
    }
});

$('.ui.register').form({
    fields: {
        email: {
            identifier: 'email',
            rules: [{
                type: 'empty',
                prompt: '请输入邮箱'
            }]
        },
        username: {
            identifier: 'username',
            rules: [{
                type: 'empty',
                prompt: '请输入用户名'
            }]
        },
        password: {
            identifier: 'password',
            rules: [{
                type: 'empty',
                prompt: '请输入密码'
            }]
        },
        code: {
            identifier: 'code',
            rules: [{
                type: 'empty',
                prompt: '请输入验证码'
            }]
        }
    }
});


if (document.getElementById('waypoint') != undefined) {
    var waypoint = new Waypoint({
        element: document.getElementById('waypoint'),
        handler: function (direction) {
            if (direction == 'down') {
                $('#toolbar').show(500);
            } else {
                $('#toolbar').hide(500);
            }
        }
    });
}




