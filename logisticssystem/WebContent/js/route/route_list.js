(function () {
    const routeListData = {
        routManagerVO: {
            totalRecords: '',
            pageIndex: 1,
            pageSize: 10,
            totalPages: '',
            havePrePage: false,
            haveNextPage: false,
            search: '',
            startTime: '',
            endTime: '',
            state: '',
            startUnit: '',
            endUnit: '',
            listRouteManagerDTO: []
        },
        search: '',
        startTime: '',
        endTime: '',
        state: '',
        startUnit: '',
        endUnit: '',
        page: 1,
        ready: false,
        preDisabled: false,
        nextDisabled: false
    }
    const viewRouteData = new Vue({
        el: '#routeList',
        data: routeListData,
        methods: {
            selectRouteSearch() {
                viewRouteData.getAllData()
                viewRouteData.judge()
            },
            getAllData() {
                $.ajax({
                    url: '/logisticssystem/routemanagement/routemanagement_getRouteManagerVO',
                    type: 'POST',
                    data: {
                        'search': routeListData.search,
                        'state': routeListData.state,
                        'page': routeListData.page,
                        'startUnit': routeListData.startUnit,
                        'endUnit': routeListData.endUnit
                    },
                    success: function (data) {
                        console.log('data:', data)
                        const getRouteList = JSON.parse(data)
                        routeListData.routManagerVO.totalRecords = getRouteList.totalRecords
                        routeListData.routManagerVO.pageIndex = getRouteList.pageIndex
                        routeListData.routManagerVO.pageSize = getRouteList.pageSize
                        routeListData.routManagerVO.totalPages = getRouteList.totalPages
                        routeListData.routManagerVO.havePrePage = getRouteList.havePrePage
                        routeListData.routManagerVO.haveNextPage = getRouteList.haveNextPage
                        routeListData.routManagerVO.search = getRouteList.search
                        routeListData.routManagerVO.startTime = getRouteList.startTime
                        routeListData.routManagerVO.endTime = getRouteList.endTime
                        routeListData.routManagerVO.state = getRouteList.state
                        routeListData.routManagerVO.startUnit = getRouteList.startUnit
                        routeListData.routManagerVO.endUnit = getRouteList.endUnit
                        routeListData.routManagerVO.listRouteManagerDTO = getRouteList.listRouteManagerDTO
                    }
                })
            },
            // 分页-上一页
            prePage: function () {
                if (routeListData.preDisabled) {
                    return
                }
                routeListData.page = routeListData.routManagerVO.pageIndex - 1
                viewRouteData.getAllData()
                viewRouteData.judge()
            },
            judge: function () {
                routeListData.preDisabled = false
                routeListData.nextDisabled = false
                if (routeListData.routManagerVO.pageIndex <= 1) {
                    routeListData.preDisabled = true
                }
                if (routeListData.routManagerVO.pageIndex === routeListData.routManagerVO.totalPages) {
                    routeListData.nextDisabled = true
                }
            },
            // 下一页
            nextPage: function () {
                if (routeListData.nextDisabled) {
                    return
                }
                routeListData.page = routeListData.routManagerVO.pageIndex + 1
                viewRouteData.getAllData()
                viewRouteData.judge()
            },
            // 首页
            shouye: function () {
                routeListData.page = 1
                viewRouteData.getAllData()
                viewRouteData.judge()
            },
            // 尾页
            weiye: function () {
                routeListData.page = routeListData.routManagerVO.totalPages
                viewRouteData.getAllData()
                viewRouteData.judge()
            }
        },
        mounted() {
            // 获取单位列表
            $.ajax({
                url: '/logisticssystem/routemanagement/routemanagement_getRouteManagerVO',
                type: 'POST',
                data: '',
                success: function (data) {
                    console.log('data:', data)
                    const getRouteList = JSON.parse(data)
                    routeListData.routManagerVO.totalRecords = getRouteList.totalRecords
                    routeListData.routManagerVO.pageIndex = getRouteList.pageIndex
                    routeListData.routManagerVO.pageSize = getRouteList.pageSize
                    routeListData.routManagerVO.totalPages = getRouteList.totalPages
                    routeListData.routManagerVO.havePrePage = getRouteList.havePrePage
                    routeListData.routManagerVO.haveNextPage = getRouteList.haveNextPage
                    routeListData.routManagerVO.search = getRouteList.search
                    routeListData.routManagerVO.startTime = getRouteList.startTime
                    routeListData.routManagerVO.endTime = getRouteList.endTime
                    routeListData.routManagerVO.state = getRouteList.state
                    routeListData.routManagerVO.startUnit = getRouteList.startUnit
                    routeListData.routManagerVO.endUnit = getRouteList.endUnit
                    routeListData.routManagerVO.listRouteManagerDTO = getRouteList.listRouteManagerDTO
                    routeListData.ready = true
                    viewRouteData.judge()
                }
            })
        }
    })
})()