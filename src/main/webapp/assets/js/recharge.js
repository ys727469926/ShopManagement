$(document).ready(function () {
    $('#charge-button').click(function () {
        var input = $('#charge-value');
        var value = input.val();
        if (!$.validatePriceFormat(value)) {
            $.alert('警告', '格式不正确');
            return;
        }
        $.post('/user/modify/charge', {value: value}, function (data) {
            if (data.status) {
                $.warn('充值成功！', function () {
                    input.val('');
                    location.reload();
                });
            } else {
                $.alert('警告', data.msg);
            }
        }, function () {
            $.alert('警告', '网络异常');

        });
    });
});
