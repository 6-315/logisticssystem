(function () {
    const expressData = {
        expressVO: {}
    }
    const express_view = new Vue({
        el: 'expressList',
        data: expressData,
        methods: {},
        mounted() {
            $.ajax({
                url: '/logisticssystem/expressmanagement/expressmanagement_queryExpressInfo',
                type: 'POST',
                data: '',
                success: function (data) {
                    console.log('data:', data)
                }
            })
        }
    })
})()