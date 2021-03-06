$(document).ready(function () {

    var motoNum;

    function updateCount(input) {
        var id = input[0].dataset.id;
        var count = input.val();
        $.post('/user/shopcar/modify', {id: id, count: count}, function (data) {
            if (!data.status) {
                $.warn('修改数量失败', function () {
                    location.reload();
                });
            }
        }, function () {
            $.warn('网络异常', function () {
                location.reload();
            });
        });
        //修改单个物品价格
        var parent = input.parent().parent().parent();
        var price = parseFloat(parent.find('div[data-type="price"]').find('strong').text());
        parent.find('div[data-type="total"]').find('strong').text($.formatFloat(count * price, 2));
        //修改所有物品总价
        var total = 0;
        $('.one-of-goods').each(function () {
            var that = $(this);
            total += parseInt(that.find('div[data-type="total"]').find('strong').text());
        });
        $('.total-font').text('总计： ' + $.formatFloat(total, 2) + ' 元')
    }

    $('.del-button').click(function () {
        var that = $(this);
        var id = that[0].dataset.id;
        $.post('/user/shopcar/del', {id: id}, function (data) {
            if (data.status) {
                location.reload();
            } else {
                $.msg('删除失败');
            }
        }, function () {
            $.msg('网络异常');
        });
    });

    $('.icon-plus').click(function () {
        var that = $(this);
        var countInput = that.parent().parent().find('input');
        var count = countInput.val();
        var number = that.parent().parent().parent().find('strong[data-type="good-number"]').text();
        if (number < ++count) {
            return;
        }
        countInput.val(count);
        updateCount(countInput);
    });

    $('.icon-minus').click(function () {
        var that = $(this);
        var countInput = that.parent().parent().find('input');
        var count = countInput.val();
        if (count <= 1) {
            return;
        }
        countInput.val(--count);
        updateCount(countInput);
    });

    $('.good-number').focus(function () {
        motoNum = $(this).val();
    }).change(function () {
        var that = $(this);
        var count = that.val();
        var number = that.parent().parent().parent().find('strong[data-type="good-number"]').text();
        if (count <= 0 || isNaN(count) || number < count) {
            that.val(motoNum);
        } else {
            updateCount(that);
        }
    });

    $('#pay').click(function () {
        var addressId = $('input[name="address"]:checked').val();
        $.post('/order/create', {address: addressId}, function (data) {
            if (data.status) {
                location.href = 'user/order';
            } else {
                $.alert('错误', '创建订单失败，失败原因：' + data.msg);
            }
        }, function () {
            $.alert('错误', '网络异常');
        });
    });

});
