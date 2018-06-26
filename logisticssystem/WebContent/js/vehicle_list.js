(function () {
    const vehicleListData = {
        unitList: [],
        teamList: [],
        vehicleInfoVO: {
            listVehicleDTO: [],
            pageIndex: 1,
            totalRecords: 0,
            pageSize: 10,
            totalPages: 1,
            HavePrePage: false,
            HaveNextPage: false,
            search: '',
            state: '',
            unit: '',
            team: '',
            idList: '',
            //分配状态
            distributionState: '',
            //载货状态
            expressState: ''
        },
        page: 1,
        search: '',
        state: '',
        unit: '',
        team: '',
        idList: '',
        distributionState: '',
        expressState: '',
        ready: false,
        preDisabled: false,
        nextDisabled: false,
        checkData: false,
        myRole: role,
        distVehicleTranIndex: '',
        teamList: [],
        teamInfoList: [],
        driverManagerDTO: []
    }
    const viewVehicleList = new Vue({
        el: '#vehicleList',
        data: vehicleListData,
        methods: {
            getAllData: function () {
                $.ajax({
                    url: '/logisticssystem/vehiclemanagement/vehiclemanagement_queryVehicle',
                    type: 'POST',
                    data: {
                        'search': vehicleListData.search,
                        'state': vehicleListData.state,
                        'unit': vehicleListData.unit,
                        'team': vehicleListData.team,
                        'distributionState': vehicleListData.distributionState,
                        'expressState': vehicleListData.expressState,
                        'page': vehicleListData.page
                    },
                    success: function (data) {
                        const vehicleInfo = JSON.parse(data)
                        vehicleListData.vehicleInfoVO.listVehicleDTO = vehicleInfo.listVehicleDTO
                        vehicleListData.vehicleInfoVO.pageIndex = vehicleInfo.pageIndex
                        vehicleListData.vehicleInfoVO.totalRecords = vehicleInfo.totalRecords
                        vehicleListData.vehicleInfoVO.pageSize = vehicleInfo.pageSize
                        vehicleListData.vehicleInfoVO.totalPages = vehicleInfo.totalPages
                        vehicleListData.vehicleInfoVO.HavePrePage = vehicleInfo.HavePrePage
                        vehicleListData.vehicleInfoVO.HaveNextPage = vehicleInfo.HaveNextPage
                        vehicleListData.vehicleInfoVO.search = vehicleInfo.search
                        vehicleListData.vehicleInfoVO.state = vehicleInfo.state
                        vehicleListData.vehicleInfoVO.unit = vehicleInfo.unit
                        vehicleListData.vehicleInfoVO.team = vehicleInfo.team
                        vehicleListData.vehicleInfoVO.distributionState = vehicleInfo.distributionState
                        vehicleListData.vehicleInfoVO.expressState = vehicleInfo.expressState
                        vehicleListData.ready = true
                        viewVehicleList.judge()
                    }
                })
            },
            selectSearch: function () {
                viewVehicleList.getAllData()
                viewVehicleList.judge()
            },
            // 分页-上一页
            prePage: function () {
                if (vehicleListData.preDisabled) {
                    return
                }
                vehicleListData.page = vehicleListData.vehicleInfoVO.pageIndex - 1
                viewVehicleList.getAllData()
                viewVehicleList.judge()
            },
            judge: function () {
                vehicleListData.preDisabled = false
                vehicleListData.nextDisabled = false
                if (vehicleListData.vehicleInfoVO.pageIndex === 1) {
                    vehicleListData.preDisabled = true
                }
                if (vehicleListData.vehicleInfoVO.pageIndex === vehicleListData.vehicleInfoVO.totalPages) {
                    vehicleListData.nextDisabled = true
                }
            },
            // 下一页
            nextPage: function () {
                if (vehicleListData.nextDisabled) {
                    return
                }
                vehicleListData.page = vehicleListData.vehicleInfoVO.pageIndex + 1
                viewVehicleList.getAllData()
                viewVehicleList.judge()
            },
            // 首页
            shouye: function () {
                vehicleListData.page = 1
                viewVehicleList.getAllData()
                viewVehicleList.judge()
            },
            // 尾页
            weiye: function () {
                vehicleListData.page = vehicleListData.vehicleInfoVO.totalPages
                viewVehicleList.getAllData()
                viewVehicleList.judge()
            },
            //
            selectUnit(unitId) {
                vehicleListData.unit = unitId
                viewVehicleList.getAllData()
                viewVehicleList.judge()
            },
            //
            selectTeam(teamId) {
                vehicleListData.team = teamId
                viewVehicleList.getAllData()
                viewVehicleList.judge()
            },
            //
            isFenPei(fenPeiState) {
                vehicleListData.distributionState = fenPeiState
                viewVehicleList.getAllData()
                viewVehicleList.judge()
            },
            //
            isZaiHuo(zaiHuoState) {
                vehicleListData.expressState = zaiHuoState
                viewVehicleList.getAllData()
                viewVehicleList.judge()
            },
            //
            selectState(state) {
                vehicleListData.state = state
                viewVehicleList.getAllData()
                viewVehicleList.judge()
            },
            distVehicleTran() {
                //弹出模态框
                $('#unitDistList').modal()
            },
            //选择单位
            selectVehicleTran(unitId) {
                let dataDa = ''
                $("input[name='flag']:checkbox").each(function () {
                    if ($(this).is(':checked')) {
                        dataDa = dataDa + $(this).attr('id') + '&' + $(this).attr('uni') + ','
                    }
                })
                $.ajax({
                    url: '/vehiclemanagement/vehiclemanagement_exchangeVehicle',
                    type: 'POST',
                    data: {
                        'idList': dataDa,
                        'unit': unitId
                    },
                    success: function (data) {
                        if (data === 'success') {
                            viewVehicleList.getAllData()
                            viewVehicleList.judge()
                            $('#unitDistList').modal('hide')
                            toastr.success('调度成功')
                        } else {
                            toastr.error('调度失败')
                        }
                    }
                })
            },
            //
            distVehicleTeam() {
                $.ajax({
                    url: '/logisticssystem/vehiclemanagement/vehiclemanagement_queryTeam',
                    type: 'POST',
                    data: '',
                    success: function (data) {
                        const teamInfoVO = JSON.parse(data)
                        vehicleListData.teamInfoList = teamInfoVO.listTeamDTO
                        $('#teamDistList').modal()
                    }
                })
            },
            //选择车队
            selectVehicleTeam: function (teamId) {
                let dataDa = ''
                $("input[name='flag']:checkbox").each(function () {
                    if ($(this).is(':checked')) {
                        dataDa = dataDa + $(this).attr('id') + ','
                    }
                })
                $.ajax({
                    url: '/logisticssystem/transferstation/transferstation_vehicleDistribution',
                    type: 'POST',
                    data: {
                        'vehicleList': dataDa,
                        'teamNum': teamId
                    },
                    success: function (data) {
                        if (data === 'success') {
                            viewVehicleList.getAllData()
                            viewVehicleList.judge()
                            $('#teamDistList').modal('hide')
                            toastr.success('调度成功')
                        } else {
                            toastr.error('调度失败')
                        }
                    }
                })
            },
            checkAll() {
                if (vehicleListData.checkData) {
                    $("input[name='flag']:checkbox").each(function () {
                        $(this).attr("checked", false)
                    })
                } else {
                    $("input[name='flag']:checkbox").each(function () {
                        $(this).attr("checked", true)
                    })
                }
            },
            //分配司机
            distVehicleDriver(vehicleId) {
                vehicleListData.disVehicleId = vehicleId
                $.ajax({
                    url: '/logisticssystem/vehiclemanagement/vehiclemanagement_getDiverUnDistributed',
                    type: 'POST',
                    data: '',
                    success: function (data) {
                        const driverManagerDTO = JSON.parse(data)
                        vehicleListData.driverManagerDTO = vehicleListData.driverManagerDTO
                        $('#teamDistList').modal()
                    }
                })
            },
            //选择司机
            selectVehicleDriver(driverId) {
                $.ajax({
                    url: '/logisticssystem/transferstation/transferstation_distributeDiver',
                    type: 'POST',
                    data: {
                        'vehicle': vehicleListData.disVehicleId,
                        'driver': driverId
                    },
                    success: function (data) {
                        if (data === 'success') {
                            viewVehicleList.getAllData()
                            viewVehicleList.judge()
                            $('#teamDistList').modal('hide')
                            toastr.success('调度成功')
                        } else {
                            toastr.error('调度失败')
                        }
                    }
                })
            }
        },
        mounted() {
            /*$('#unitDistList').on('hidden.bs.modal', function (e) {
                vehicleListData.distVehicleTranIndex = ''
                vehicleListData.distVehicleId = ''
            })*/
            // 获取单位信息
            if (vehicleListData.myRole == 6) {
                $.ajax({
                    url: '/logisticssystem/personnelmanagement/personnelmanagement_lowerUnit',
                    type: 'POST',
                    data: '',
                    success: function (data) {
                        let uList = JSON.parse(data)
                        vehicleListData.unitList = uList.filter(u => u.unit_type == '中转站')
                    }
                })
            }
            //获取车队信息
            $.ajax({
                url: '/logisticssystem/vehiclemanagement/vehiclemanagement_getAllTeam',
                type: 'POST',
                data: '',
                success: function (data) {
                    let uList = JSON.parse(data)
                    vehicleListData.teamList = uList
                }
            })
            //获取内容
            $.ajax({
                url: '/logisticssystem/vehiclemanagement/vehiclemanagement_queryVehicle',
                type: 'POST',
                data: '',
                success: function (data) {
                    const vehicleInfo = JSON.parse(data)
                    vehicleListData.vehicleInfoVO.listVehicleDTO = vehicleInfo.listVehicleDTO
                    vehicleListData.vehicleInfoVO.pageIndex = vehicleInfo.pageIndex
                    vehicleListData.vehicleInfoVO.totalRecords = vehicleInfo.totalRecords
                    vehicleListData.vehicleInfoVO.pageSize = vehicleInfo.pageSize
                    vehicleListData.vehicleInfoVO.totalPages = vehicleInfo.totalPages
                    vehicleListData.vehicleInfoVO.HavePrePage = vehicleInfo.HavePrePage
                    vehicleListData.vehicleInfoVO.HaveNextPage = vehicleInfo.HaveNextPage
                    vehicleListData.vehicleInfoVO.search = vehicleInfo.search
                    vehicleListData.vehicleInfoVO.state = vehicleInfo.state
                    vehicleListData.vehicleInfoVO.unit = vehicleInfo.unit
                    vehicleListData.vehicleInfoVO.team = vehicleInfo.team
                    vehicleListData.vehicleInfoVO.distributionState = vehicleInfo.distributionState
                    vehicleListData.vehicleInfoVO.expressState = vehicleInfo.expressState
                    vehicleListData.ready = true
                    viewVehicleList.judge()
                }
            })
        }
    })
})()