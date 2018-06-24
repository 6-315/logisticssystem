(function () {
    const userMyOrderData = {
        expressinfoAndExpressVO: {
            totalRecords: 0,
            pageIndex: 1,
            pageSize: 8,
            totalPages: 1,
            havePrePage: false,
            haveNextPage: false,
            search: '',
            state: '',
            listExpressinfoAndExpressDTO: []
        },
        search: '',
        state: '',
        page: 1,
        ready: false,
        preDisabled: false,
        nextDisabled: false,
        active1: true,
        active2: false,
        active3: false,
        active4: false,
        active5: false,
        active6: false,
        active7: false,
    }
    const viewUserMyOrder = new Vue({
        el: '#userMyOrder',
        data: userMyOrderData,
        methods: {
            getAllData: function () {
                $.ajax({
                    url: '/logisticssystem/userinfo/userinfo_selectExpressInfo',
                    type: 'POST',
                    data: {
                        'search': userMyOrderData.search,
                        'state': userMyOrderData.state,
                        'page': userMyOrderData.page
                    },
                    success: function (data) {
                        console.log('data:', data)
                        const expressInfo = JSON.parse(data)
                        userMyOrderData.expressinfoAndExpressVO.totalRecords = expressInfo.totalRecords
                        userMyOrderData.expressinfoAndExpressVO.pageIndex = expressInfo.pageIndex
                        userMyOrderData.expressinfoAndExpressVO.pageSize = expressInfo.pageSize
                        userMyOrderData.expressinfoAndExpressVO.totalPages = expressInfo.totalPages
                        userMyOrderData.expressinfoAndExpressVO.havePrePage = expressInfo.havePrePage
                        userMyOrderData.expressinfoAndExpressVO.haveNextPage = expressInfo.haveNextPage
                        userMyOrderData.expressinfoAndExpressVO.search = expressInfo.search
                        userMyOrderData.expressinfoAndExpressVO.state = expressInfo.state
                        userMyOrderData.expressinfoAndExpressVO.listExpressinfoAndExpressDTO = expressInfo.listExpressinfoAndExpressDTO
                        userMyOrderData.ready = true
                        viewUserMyOrder.judge()
                    }
                })
            },
            // 分页-上一页
            prePage: function () {
                if (userMyOrderData.preDisabled) {
                    return
                }
                userMyOrderData.page = userMyOrderData.expressinfoAndExpressVO.pageIndex - 1
                viewUserMyOrder.getAllData()
                viewUserMyOrder.judge()
            },
            judge: function () {
                userMyOrderData.preDisabled = false
                userMyOrderData.nextDisabled = false
                if (userMyOrderData.expressinfoAndExpressVO.pageIndex === 1) {
                    userMyOrderData.preDisabled = true
                }
                if (userMyOrderData.expressinfoAndExpressVO.pageIndex === userMyOrderData.expressinfoAndExpressVO.totalPages) {
                    userMyOrderData.nextDisabled = true
                }
            },
            // 下一页
            nextPage: function () {
                if (userMyOrderData.nextDisabled) {
                    return
                }
                userMyOrderData.page = userMyOrderData.expressinfoAndExpressVO.pageIndex + 1
                viewUserMyOrder.getAllData()
                viewUserMyOrder.judge()
            },
            // 首页
            shouye: function () {
                userMyOrderData.page = 1
                viewUserMyOrder.getAllData()
                viewUserMyOrder.judge()
            },
            // 尾页
            weiye: function () {
                userMyOrderData.page = userMyOrderData.expressinfoAndExpressVO.totalPages
                viewUserMyOrder.getAllData()
                viewUserMyOrder.judge()
            },
            //搜索
            selectKuaiDi() {
                viewUserMyOrder.getAllData()
                viewUserMyOrder.judge()
            },
            //筛选
            selectStateExpressInfo(expressState) {
                switch (expressState) {
                    case '':
                        active1 = true
                        active2 = false
                        active3 = false
                        active4 = false
                        active5 = false
                        active6 = false
                        active7 = false
                        break;
                    case '待揽件':
                        active1 = false
                        active2 = true
                        active3 = false
                        active4 = false
                        active5 = false
                        active6 = false
                        active7 = false
                        break;
                    case '已揽件':
                        active1 = false
                        active2 = false
                        active3 = true
                        active4 = false
                        active5 = false
                        active6 = false
                        active7 = false
                        break;
                    case '在途中':
                        active1 = false
                        active2 = false
                        active3 = false
                        active4 = true
                        active5 = false
                        active6 = false
                        active7 = false
                        break;
                    case '待派送':
                        active1 = false
                        active2 = false
                        active3 = false
                        active4 = false
                        active5 = true
                        active6 = false
                        active7 = false
                        break;
                    case '派送中':
                        active1 = false
                        active2 = false
                        active3 = false
                        active4 = false
                        active5 = false
                        active6 = true
                        active7 = false
                        break;
                    case '已签收':
                        active1 = false
                        active2 = false
                        active3 = false
                        active4 = false
                        active5 = false
                        active6 = false
                        active7 = true
                        break;
                }
                userMyOrderData.state = expressState
            }

        },
        mounted() {
            $.ajax({
                url: '/logisticssystem/userinfo/userinfo_selectExpressInfo',
                type: 'POST',
                data: '',
                success: function (data) {
                    console.log('data:', data)
                    const expressInfo = JSON.parse(data)
                    userMyOrderData.expressinfoAndExpressVO.totalRecords = expressInfo.totalRecords
                    userMyOrderData.expressinfoAndExpressVO.pageIndex = expressInfo.pageIndex
                    userMyOrderData.expressinfoAndExpressVO.pageSize = expressInfo.pageSize
                    userMyOrderData.expressinfoAndExpressVO.totalPages = expressInfo.totalPages
                    userMyOrderData.expressinfoAndExpressVO.havePrePage = expressInfo.havePrePage
                    userMyOrderData.expressinfoAndExpressVO.haveNextPage = expressInfo.haveNextPage
                    userMyOrderData.expressinfoAndExpressVO.search = expressInfo.search
                    userMyOrderData.expressinfoAndExpressVO.state = expressInfo.state
                    userMyOrderData.expressinfoAndExpressVO.listExpressinfoAndExpressDTO = expressInfo.listExpressinfoAndExpressDTO
                    userMyOrderData.ready = true
                    viewUserMyOrder.judge()
                }
            })
        }
    })
})()