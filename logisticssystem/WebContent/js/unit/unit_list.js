(function () {
    const unitListData = {
        unitManagerVO: {
            listUnitManagerDTO: [],
            totalRecords: '',
            type: '',
            state: '',
            search: '',
            pageIndex: '',
            pageSize: '',
            totalPages: '',
            havePrePage: false,
            haveNextPage: false
        },
        type: '',
        state: '',
        search: '',
        ready: false,
        page: 1,
        preDisabled: false,
        nextDisabled: false
    }
    const view_unitList = new Vue({
        el: '#unit_list',
        data: unitListData,
        methods: {
            getAllData() {
                // 获取单位列表
                $.ajax({
                    url: '/logisticssystem/transferstation / transferstation_queryTransferStation',
                    type: 'POST',
                    data: {
                        'page': unitListData.page,
                        'type': unitListData.type,
                        'search': unitListData.search,
                        'state': unitListData.state
                    },
                    success: function (data) {
                        console.log('data:', data)
                        const getUnitList = JSON.parse(data)
                        unitListData.unitManagerVO.listUnitManagerDTO = data.listUnitManagerDTO
                        unitListData.unitManagerVO.totalRecords = data.totalRecords
                        unitListData.unitManagerVO.type = data.type
                        unitListData.unitManagerVO.state = data.state
                        unitListData.unitManagerVO.search = data.search
                        unitListData.unitManagerVO.pageIndex = data.pageIndex
                        unitListData.unitManagerVO.pageSize = data.pageSize
                        unitListData.unitManagerVO.totalPages = data.totalPages
                        unitListData.unitManagerVO.havePrePage = data.havePrePage
                        unitListData.unitManagerVO.haveNextPage = data.haveNextPage
                        unitListData.ready = true
                    }
                })
            },
            // 分页-上一页
            prePage: function () {
                if (unitListData.preDisabled) {
                    return
                }
                unitListData.page = unitListData.unitManagerVO.pageIndex - 1
                view_reservation.getAllData()
                view_reservation.judge()
            },
            judge: function () {
                unitListData.preDisabled = false
                unitListData.nextDisabled = false
                if (unitListData.unitManagerVO.pageIndex == 1) {
                    unitListData.preDisabled = true
                }
                if (unitListData.unitManagerVO.pageIndex === unitListData.unitManagerVO.totalPages) {
                    unitListData.nextDisabled = true
                }
            },
            // 下一页
            nextPage: function () {
                if (unitListData.nextDisabled) {
                    return
                }
                unitListData.page = unitListData.unitManagerVO.pageIndex + 1
                view_reservation.getAllData()
                view_reservation.judge()
            },
            // 首页
            shouye: function () {
                unitListData.page = 1
                view_reservation.getAllData()
                view_reservation.judge()
            },
            // 尾页
            weiye: function () {
                unitListData.page = unitListData.unitManagerVO.totalPages
                view_reservation.getAllData()
                view_reservation.judge()
            }
        },
        mounted() {
            // 获取单位列表
            $.ajax({
                url: '/logisticssystem/transferstation / transferstation_queryTransferStation',
                type: 'POST',
                data: '',
                success: function (data) {
                    console.log('data:', data)
                    const getUnitList = JSON.parse(data)
                    unitListData.unitManagerVO.listUnitManagerDTO = data.listUnitManagerDTO
                    unitListData.unitManagerVO.totalRecords = data.totalRecords
                    unitListData.unitManagerVO.type = data.type
                    unitListData.unitManagerVO.state = data.state
                    unitListData.unitManagerVO.search = data.search
                    unitListData.unitManagerVO.pageIndex = data.pageIndex
                    unitListData.unitManagerVO.pageSize = data.pageSize
                    unitListData.unitManagerVO.totalPages = data.totalPages
                    unitListData.unitManagerVO.havePrePage = data.havePrePage
                    unitListData.unitManagerVO.haveNextPage = data.haveNextPage
                    unitListData.ready = true
                }
            })
        }
    })
})()