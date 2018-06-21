(function () {
    const expressData = {
        unitList: [],
        expressInfoVO: {
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
        },
        search: '',
        page: '',
        state: '',
        unit: '',
        isDistributedDistribution: '',
        isDistributedDistributor: '',
        ready: false,
        preDisabled: false,
        nextDisabled: false,
    }
    const express_view = new Vue({
        el: 'expressList',
        data: expressData,
        methods: {
            getAllData: function () {
                $.ajax({
                    url: '/logisticssystem/expressmanagement/expressmanagement_queryExpressInfo',
                    type: 'POST',
                    data: {
                        'search': expressData.search,
                        'page': expressData.page,
                        'state': expressData.state,
                        'unit': expressData.unit,
                        'isDistributedDistribution': expressData.isDistributedDistribution,
                        'isDistributedDistributor': expressData.isDistributedDistributor
                    },
                    success: function (data) {
                        const expressInfo = JSON.parse(data)
                        expressData.expressInfoVO.ExpressInfoDTO = expressInfo.listExpressInfoDTO
                        expressData.expressInfoVO.pageIndex = expressInfo.pageIndex
                        expressData.expressInfoVO.totalRecords = expressInfo.totalRecords
                        expressData.expressInfoVO.pageSize = expressInfo.pageSize
                        expressData.expressInfoVO.totalPages = expressInfo.totalPages
                        expressData.expressInfoVO.HavePrePage = expressInfo.HavePrePage
                        expressData.expressInfoVO.HaveNextPage = expressInfo.HaveNextPage
                        expressData.expressInfoVO.search = expressInfo.search
                        expressData.expressInfoVO.state = expressInfo.state
                        expressData.expressInfoVO.unit = expressInfo.unit
                        expressData.ready = true
                    }
                })
            },
            //分页-上一页
            prePage: function () {
                if (expressData.preDisabled) {
                    return
                }
                expressData.page = expressData.expressInfoVO.pageIndex - 1
                express_view.getAllData()
                express_view.judge()
            },
            judge: function () {
                expressData.preDisabled = false
                expressData.nextDisabled = false
                if (expressData.expressInfoVO.pageIndex == 1) {
                    expressData.preDisabled = true
                }
                if (expressData.expressInfoVO.pageIndex === expressData.expressInfoVO.totalPages) {
                    expressData.nextDisabled = true
                }
            },
            //下一页
            nextPage: function () {
                if (expressData.nextDisabled) {
                    return
                }
                expressData.page = expressData.expressInfoVO.pageIndex + 1
                express_view.getAllData()
                express_view.judge()
            },
            //首页
            shouye: function () {
                expressData.page = 1
                express_view.getAllData()
                express_view.judge()
            },
            //尾页
            weiye: function () {
                expressData.page = reservationData.expressInfoVO.totalPages
                express_view.getAllData()
                express_view.judge()
            },
        },
        mounted() {
            $.ajax({
                url: '/logisticssystem/expressmanagement/expressmanagement_queryExpressInfo',
                type: 'POST',
                data: '',
                success: function (data) {
                    const expressInfo = JSON.parse(data)
                    expressData.expressInfoVO.ExpressInfoDTO = expressInfo.listExpressInfoDTO
                    expressData.expressInfoVO.pageIndex = expressInfo.pageIndex
                    expressData.expressInfoVO.totalRecords = expressInfo.totalRecords
                    expressData.expressInfoVO.pageSize = expressInfo.pageSize
                    expressData.expressInfoVO.totalPages = expressInfo.totalPages
                    expressData.expressInfoVO.HavePrePage = expressInfo.HavePrePage
                    expressData.expressInfoVO.HaveNextPage = expressInfo.HaveNextPage
                    expressData.expressInfoVO.search = expressInfo.search
                    expressData.expressInfoVO.state = expressInfo.state
                    expressData.expressInfoVO.unit = expressInfo.unit
                    expressData.ready = true


                }
            })
        }
    })
})()