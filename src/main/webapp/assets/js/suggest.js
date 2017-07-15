$(document).ready(function () {

    var input = $('#search-word');
    var suggestion = $('#search-suggestion');

    input.blur(function () {
        setTimeout(hideSuggest, 100);
    });
    input.keyup(function (event) {
        var x = event.keyCode || event.which;
        console.log(x);
        if (13 === x) {
            $('#search-button').click();
        }

        if ('block' === suggestion.css('display') && 38 === x || 40 === x) {
            var current = suggestion.find('li.hover');
            if (38 === x) {
                if (current.length > 0) {
                    var prevLi = current.removeClass('hover').prev();
                    if (prevLi.length > 0) {
                        prevLi.addClass('hover');
                        input.val(prevLi.html());
                    }
                } else {
                    var last = suggestion.find('li:last');
                    last.addClass('hover');
                    input.val(last.html());
                }

            } else if (40 === x) {
                if (current.length > 0) {
                    var nextLi = current.removeClass('hover').next();
                    if (nextLi.length > 0) {
                        nextLi.addClass('hover');
                        input.val(nextLi.html());
                    }
                } else {
                    var first = suggestion.find('li:first');
                    first.addClass('hover');
                    input.val(first.html());
                }
            }
        } else {
            $.post('/suggest/' + $('#search-word').val(), {}, function (data) {
                if (data.status) {
                    var json = JSON.parse(data.suggestions);
                    if (json.length <= 0) {
                        hideSuggest();
                    } else {
                        suggestion.empty();
                        for (var i = 0; i < json.length; ++i) {
                            suggestion.append('<li>' + json[i].suggestion + '</li>');
                            console.log(json[i].suggestion);
                        }
                        $('#gov_search_suggest').css('display', 'block');
                        suggestion.find('li').hover(function () {
                            suggestion.find('li').removeClass('hover');
                            $(this).addClass('hover');

                        }, function () {
                            $(this).removeClass('hover');
                        }).bind('click', function () {
                            input.val(this.innerHTML);
                            hideSuggest();
                            input.focus();
                        });

                    }
                } else {
                    hideSuggest();
                    console.log('发送失败');
                }
            }, function () {
                console.log('网络异常');
            })
        }


    });
    function hideSuggest() {
        $('#gov_search_suggest').css('display', 'none');
    }


});