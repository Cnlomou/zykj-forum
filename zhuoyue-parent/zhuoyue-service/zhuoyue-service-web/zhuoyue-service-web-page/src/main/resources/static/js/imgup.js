//图像上传
function selectImg(file) {
    if (!file.files || !file.files[0]) {
        return;
    }
    var reader = new FileReader();
    reader.onload = function (evt) {
        var replaceSrc = evt.target.result;
        //更换cropper的图片
        $('#tailoringImg').cropper('replace', replaceSrc, false);//默认false，适应高度，不失真
    };
    reader.readAsDataURL(file.files[0]);
}

//cropper图片裁剪
$('#tailoringImg').cropper({
    aspectRatio: 1 / 1,//默认比例
    preview: '.previewImg',//预览视图
    guides: false,  //裁剪框的虚线(九宫格)
    autoCropArea: 0.3,  //0-1之间的数值，定义自动剪裁区域的大小，默认0.8
    movable: false, //是否允许移动图片
    dragCrop: false,  //是否允许移除当前的剪裁框，并通过拖动来新建一个剪裁框区域
    resizable: false,  //是否允许改变裁剪框的大小
    zoomable: false,  //是否允许缩放图片大小
    mouseWheelZoom: false,  //是否允许通过鼠标滚轮来缩放图片
    touchDragZoom: false,  //是否允许通过触摸移动来缩放图片
    rotatable: false,  //是否允许旋转图片
    cropBoxResizable: false,
    crop: function (e) {
        // 输出结果数据裁剪图像。
    }
});

//裁剪后的处理
$("#sureCut").on("click", function () {
    if ($("#tailoringImg").attr("src") == null) {
        alert("你没有上传图片哟！")
        return false;
    } else {
        var cas = $('#tailoringImg').cropper('getCroppedCanvas');//获取被裁剪后的canvas
        var base64url = cas.toDataURL('image/png'); //转换为base64地址形式
        $("#finalImg").prop("src", base64url);//显示为图片的形式

    }
});