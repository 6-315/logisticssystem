(function () {
   /* $('#sendRegion').focus(function () {
        $('#sendRegion').parent().addClass('open')
    });*/
    $('#sendPro').click(function () {
        $('#sendProC').css({
            "display": "block"
        });
        $('#cityC').css({
            "display": "none"
        });
        $('#xianC').css({
            "display": "none"
        });
        $('#sendPro').addClass('hover')
        $('#city').removeClass('hover')
        $('#xian').removeClass('hover')
    });
    $('#city').click(function () {
        $('#cityC').css({
            "display": "block"
        });
        $('#sendProC').css({
            "display": "none"
        });
        $('#xianC').css({
            "display": "none"
        });
        $('#city').addClass('hover')
        $('#sendPro').removeClass('hover')
        $('#xian').removeClass('hover')
    });
    $('#xian').click(function () {
        $('#sendProC').css({
            "display": "none"
        });
        $('#cityC').css({
            "display": "none"
        });
        $('#xianC').css({
            "display": "block"
        });
        $('#sendPro').removeClass('hover');
        $('#city').removeClass('hover')
        $('#xian').addClass('hover')
    });
    $('#close').click(function () {
        $('#sendRegion').parent().removeClass('open')
    })
})()