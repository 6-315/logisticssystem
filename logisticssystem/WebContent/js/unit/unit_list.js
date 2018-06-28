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
        nextDisabled: false,
        myRole: role
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
                        view_unitList.judge()
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
            },
            selectUnitType: function (type) {
                unitListData.page = 1
                //单位筛选
                unitListData.type = type
                view_unitList.getAllData()
                view_unitList.judge()
            },
            selectUnitState: function (state) {
                unitListData.page = 1
                //状态筛选
                unitListData.state = state
                view_unitList.getAllData()
                view_unitList.judge()
            },
            selectUnitSearch: function () {
                unitListData.page = 1
                //搜索筛选
                view_unitList.getAllData()
                view_unitList.judge()
            },
            skipPageAddUnit: function (unitId) {
                //跳转后台
                window.location = '/logisticssystem/expressmanagement/expressmanagement_addUnitPage?idList=' + unitId
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
                    view_unitList.judge()
                    unitListData.ready = true
                }
            })
        }
    })
})()