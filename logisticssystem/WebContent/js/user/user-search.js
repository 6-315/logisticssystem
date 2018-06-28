(function () {
    const liuZhuanData = {
        liuZhuanList: [],
        search: ''
    }
    const viewLiuZhuan = new Vue({
        el: '#searchId',
        data: liuZhuanData,
        methods: {
            searchExpress() {
                $.ajax({
                    url: '/logisticssystem/expressmanagement2/expressmanagement2_getExpressCirculation',
                    type: 'POST',
                    data: {
                        'waybillNumber': liuZhuanData.search
                    },
                    success: function (data) {
                        if (JSON.parse(data) !== null) {
                            liuZhuanData.liuZhuanList = JSON.parse(data)
                        } else {
                            toastr.error('请输入正确的快件单号')
                        }

                    }

                })
            }
        }
    })
})()