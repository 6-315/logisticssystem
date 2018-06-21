(function () {
    const expressData = {
        expressVO: {
            ExpressInfoDTO: [],
            search: '',
            state: '',
            unit: '',
            isDistributedDistribution: '',
            isDistributedDistributor: '',
            pageIndex: '',
            totalRecords: '',
            pageSize: '',
            totalPages: '',
            HavePrePage: '',
            HaveNextPage: '',
        }
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