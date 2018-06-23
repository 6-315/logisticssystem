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
                    url: '/logisticssystem/transferstation/transferstation_queryTransferStation',
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
                        unitListData.unitManagerVO.listUnitManagerDTO = getUnitList.listUnitManagerDTO
                        unitListData.unitManagerVO.totalRecords = getUnitList.totalRecords
                        unitListData.unitManagerVO.type = getUnitList.type
                        unitListData.unitManagerVO.state = getUnitList.state
                        unitListData.unitManagerVO.search = getUnitList.search
                        unitListData.unitManagerVO.pageIndex = getUnitList.pageIndex
                        unitListData.unitManagerVO.pageSize = getUnitList.pageSize
                        unitListData.unitManagerVO.totalPages = getUnitList.totalPages
                        unitListData.unitManagerVO.havePrePage = getUnitList.havePrePage
                        unitListData.unitManagerVO.haveNextPage = getUnitList.haveNextPage
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
                view_unitList.getAllData()
                view_unitList.judge()
            },
            judge: function () {
                unitListData.preDisabled = false
                unitListData.nextDisabled = false
                if (unitListData.unitManagerVO.pageIndex <= 1) {
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
                view_unitList.getAllData()
                view_unitList.judge()
            },
            // 首页
            shouye: function () {
                unitListData.page = 1
                view_unitList.getAllData()
                view_unitList.judge()
            },
            // 尾页
            weiye: function () {
                unitListData.page = unitListData.unitManagerVO.totalPages
                view_unitList.getAllData()
                view_unitList.judge()
            }
        },
        mounted() {
            // 获取单位列表
            $.ajax({
                url: '/logisticssystem/transferstation/transferstation_queryTransferStation',
                type: 'POST',
                data: '',
                success: function (data) {
                    console.log('data:', data)
                    const getUnitList = JSON.parse(data)
                    unitListData.unitManagerVO.listUnitManagerDTO = getUnitList.listUnitManagerDTO
                    unitListData.unitManagerVO.totalRecords = getUnitList.totalRecords
                    unitListData.unitManagerVO.type = getUnitList.type
                    unitListData.unitManagerVO.state = getUnitList.state
                    unitListData.unitManagerVO.search = getUnitList.search
                    unitListData.unitManagerVO.pageIndex = getUnitList.pageIndex
                    unitListData.unitManagerVO.pageSize = getUnitList.pageSize
                    unitListData.unitManagerVO.totalPages = getUnitList.totalPages
                    unitListData.unitManagerVO.havePrePage = getUnitList.havePrePage
                    unitListData.unitManagerVO.haveNextPage = getUnitList.haveNextPage
                    unitListData.ready = true
                }
            })
        }
    })
})()