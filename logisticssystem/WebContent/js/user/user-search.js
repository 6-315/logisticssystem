(function () {
    const liuZhuanData = {
        liuZhuanList: [],
        search: ''
    }
    const viewLiuZhuan = new Vue({
        el: searchId,
        data: liuZhuanList,
        methods: {
            searchExpress() {
                $.ajax({
                    url: '/logisticssystem/expressmanagement2/expressmanagement2_getExpressCirculation',
                    type: 'POST',
                    data: {
                        'waybillNumber': search
                    },
                    success: function (data) {
                        console.log('data:', data)
                    }

                })
            }
        }
    })
})()