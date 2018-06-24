(function () {
    const staffListData = {
        staffManagerVO: {
            totalRecords: 0,
            pageIndex: 1,
            pageSize: 8,
            totalPages: 1,
            havePrePage: false,
            haveNextPage: false,
            belongUnit: '',
            position: '',
            listStaDTO: [],
            search: '',
            state: ''
        },
        search: '',
        belongUnit: '',
        position: '',
        postionList: [],
        unitList: [],
        ready: false,
        preDisabled: false,
        nextDisabled: false,
        pageIndex: 1,
        state: '',
        yixiaPosition: [],
        positionName: '',
        positionDiaoStaffId: '',
        unitStaffId: '',
        unitNum: '',
        unitName: '',
        yixiaUnit: []
    }
    const viewStaffData = new Vue({
        el: '#staffList',
        data: staffListData,
        methods: {
            searchStaffNum(search) {
                // 搜索
                viewStaffData.getAllData()
                viewStaffData.judge()
            },
            getAllData: function () {
                $.ajax({
                    url: '/logisticssystem/personnelmanagement/personnelmanagement_staffManager',
                    type: 'POST',
                    data: {
                        'search': staffListData.search,
                        'belongUnit': staffListData.belongUnit,
                        'position': staffListData.position,
                        'state': staffListData.state,
                        'pageIndex': staffListData.pageIndex
                    },
                    success: function (data) {
                        let staffVO = JSON.parse(data)
                        staffListData.staffManagerVO.listStaDTO = staffVO.listStaDTO
                        staffListData.staffManagerVO.totalRecords = staffVO.totalRecords
                        staffListData.staffManagerVO.pageIndex = staffVO.pageIndex
                        staffListData.staffManagerVO.pageSize = staffVO.pageSize
                        staffListData.staffManagerVO.totalPages = staffVO.totalPages
                        staffListData.staffManagerVO.havePrePage = staffVO.havePrePage
                        staffListData.staffManagerVO.haveNextPage = staffVO.haveNextPage
                        staffListData.staffManagerVO.belongUnit = staffVO.belongUnit
                        staffListData.staffManagerVO.position = staffVO.position
                        staffListData.staffManagerVO.search = staffVO.search
                        staffListData.pageIndex = staffVO.pageIndex
                        staffListData.ready = true
                        viewStaffData.judge()
                    }
                })
            },
            // 分页-上一页
            prePage: function () {
                if (staffListData.preDisabled) {
                    return
                }
                staffListData.pageIndex = staffListData.staffManagerVO.pageIndex - 1
                staffListData.getAllData()
                viewStaffData.judge()
            },
            judge: function () {
                staffListData.preDisabled = false
                staffListData.nextDisabled = false
                if (staffListData.staffManagerVO.pageIndex <= 1) {
                    staffListData.preDisabled = true
                }
                if (staffListData.staffManagerVO.pageIndex === staffListData.staffManagerVO.totalPages) {
                    staffListData.nextDisabled = true
                }
            },
            // 下一页
            nextPage: function () {
                if (staffListData.nextDisabled) {
                    return
                }
                staffListData.pageIndex = staffListData.staffManagerVO.pageIndex + 1
                viewStaffData.getAllData()
                viewStaffData.judge()
            },
            // 首页
            shouye: function () {
                staffListData.pageIndex = 1
                viewStaffData.getAllData()
                viewStaffData.judge()
            },
            // 尾页
            weiye: function () {
                staffListData.pageIndex = staffListData.staffManagerVO.totalPages
                viewStaffData.getAllData()
                viewStaffData.judge()
            },
            selectPosition(postionId) {
                // 职位筛选
                staffListData.position = postionId
                viewStaffData.getAllData()
                viewStaffData.judge()
            },
            selectUnit(unitId) {
                // 单位筛选
                staffListData.belongUnit = unitId
                viewStaffData.getAllData()
                viewStaffData.judge()
            },
            selectState(state) {
                //状态筛选
                staffListData.state = state
                viewStaffData.getAllData()
                viewStaffData.judge()
            },
            positionDiaoDu(staffId, positionName) {
                //职位调度
                staffListData.positionName = positionName
                staffListData.positionDiaoStaffId = staffId
                $.ajax({
                    url: '',
                    type: 'POST',
                    data: {
                        '': staffId   //员工id
                    },
                    success: function (data) {
                        if (data != null) {
                            const positionLi = JSON.parse(data)
                            staffListData.yixiaPosition = positionLi
                            //获取列表
                            $('#positionDiaodu').modal()
                        }
                    }

                })
            },
            posDiaoDu(postionId) {
                //确定调度
                $.ajax({
                    url: '',
                    type: 'POST',
                    data: {
                        '': staffListData.positionDiaoStaffId,      //员工id
                        '': postionId           //职位id
                    },
                    success: function (data) {
                        if (data === success) {
                            toastr.success('调度成功')
                            viewStaffData.getAllData()
                            viewStaffData.judge()
                            $('#positionDiaodu').modal('hide')
                        } else {
                            toastr.success('调度失败')
                        }
                    }
                })
            },
            unitDiaoDu(unitStaffId, unitNum, unitName) {
                staffListData.unitStaffId = unitStaffId
                staffListData.unitNum = unitNum
                staffListData.unitName = unitName
                //单位调度
                $.ajax({
                    url: '/logisticssystem/personnelmanagement/personnelmanagement_lowerUnit',
                    type: 'POST',
                    data: '',
                    success: function (data) {
                        let uList = JSON.parse(data)
                        staffListData.yixiaUnit = uList
                        $('#unitDiaodu').modal()
                    }
                })
            },
            confirmUnitDiaoDu(unitId) {
                //确定调度
                $.ajax({
                    url: '',
                    type: 'POST',
                    data: {
                        '': staffListData.unitStaffId,      //员工id
                        '': unitId           //单位id
                    },
                    success: function (data) {
                        if (data === success) {
                            toastr.success('调度成功')
                            viewStaffData.getAllData()
                            viewStaffData.judge()
                            $('#unitDiaodu').modal('hide')
                        } else {
                            toastr.success('调度失败')
                        }
                    }
                })
            }
        },
        mounted(selectState) {
            // 进入页面时获取数据
            //单位
            // 获取单位信息
            $.ajax({
                url: '/logisticssystem/personnelmanagement/personnelmanagement_lowerUnit',
                type: 'POST',
                data: '',
                success: function (data) {
                    let uList = JSON.parse(data)
                    staffListData.unitList = uList
                }
            })
            //获取职位信息
            $.ajax({
                url: '/logisticssystem/personnelmanagement/personnelmanagement_lowerPosition',
                type: 'POST',
                data: '',
                success: function (data) {
                    let pList = JSON.parse(data)
                    staffListData.postionList = pList
                }
            })
            //页面
            $.ajax({
                url: '/logisticssystem/personnelmanagement/personnelmanagement_staffManager',
                type: 'POST',
                data: '',
                success: function (data) {
                    let staffVO = JSON.parse(data)
                    staffListData.staffManagerVO.listStaDTO = staffVO.listStaDTO
                    staffListData.staffManagerVO.totalRecords = staffVO.totalRecords
                    staffListData.staffManagerVO.pageIndex = staffVO.pageIndex
                    staffListData.staffManagerVO.pageSize = staffVO.pageSize
                    staffListData.staffManagerVO.totalPages = staffVO.totalPages
                    staffListData.staffManagerVO.havePrePage = staffVO.havePrePage
                    staffListData.staffManagerVO.haveNextPage = staffVO.haveNextPage
                    staffListData.staffManagerVO.belongUnit = staffVO.belongUnit
                    staffListData.staffManagerVO.position = staffVO.position
                    staffListData.staffManagerVO.search = staffVO.search
                    staffListData.staffManagerVO.state = staffVO.state
                    staffListData.pageIndex = staffVO.pageIndex
                    staffListData.ready = true
                    viewStaffData.judge()
                }
            })
        }
    })
})()