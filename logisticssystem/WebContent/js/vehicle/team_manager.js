(function () {
    const teamManagerData = {
        teamInfoVO: {
            listTeamDTO: [],
            pageIndex: 1,
            totalRecords: 0,
            pageSize: 10,
            totalPages: 1,
            HavePrePage: false,
            HaveNextPage: false,
            search: '',
            unit: '',
            state: ''
        },
        unitList: [],
        search: '',
        state: '',
        unit: '',
        ready: false,
        preDisabled: false,
        nextDisabled: false,
        page: 1,
        myRole: role,
        member: [],
        teamLeader: [],
        routeList: [],
        teamOb: {
            team_id: '',
            tean_num: '',
            team_leader: '',
            team_unit: '',
            team_route: '',
            team_state: '',
            team_createtime: '',
            team_modifytime: ''
        }
    }
    const viewTeamManager = new Vue({
        el: '#teamManager',
        data: teamManagerData,
        methods: {
            getAllData: function () {
                // 数据
                $.ajax({
                    url: '/logisticssystem/vehiclemanagement/vehiclemanagement_queryTeam',
                    type: 'POST',
                    data: {
                        'search': teamManagerData.search,
                        'state': teamManagerData.state,
                        'unit': teamManagerData.unit,
                        'page': teamManagerData.page
                    },
                    success: function (data) {
                        if (data !== null) {
                            const teamInfo = JSON.parse(data)
                            teamManagerData.teamInfoVO.listTeamDTO = teamInfo.listTeamDTO
                            teamManagerData.teamInfoVO.pageIndex = teamInfo.pageIndex
                            teamManagerData.teamInfoVO.totalRecords = teamInfo.totalRecords
                            teamManagerData.teamInfoVO.pageSize = teamInfo.pageSize
                            teamManagerData.teamInfoVO.totalPages = teamInfo.totalPages
                            teamManagerData.teamInfoVO.HavePrePage = teamInfo.HavePrePage
                            teamManagerData.teamInfoVO.HaveNextPage = teamInfo.HaveNextPage
                            teamManagerData.teamInfoVO.search = teamInfo.search
                            teamManagerData.teamInfoVO.unit = teamInfo.unit
                            teamManagerData.teamInfoVO.state = teamInfo.state
                            teamManagerData.ready = true
                            viewTeamManager.judge()
                        }
                    }
                })
            },
            // 分页-上一页
            prePage: function () {
                if (teamManagerData.preDisabled) {
                    return
                }
                teamManagerData.pageIndex = teamManagerData.teamInfoVO.pageIndex - 1
                viewTeamManager.getAllData()
                viewTeamManager.judge()
            },
            judge: function () {
                teamManagerData.preDisabled = false
                teamManagerData.nextDisabled = false
                if (teamManagerData.teamInfoVO.pageIndex <= 1) {
                    teamManagerData.preDisabled = true
                }
                if (teamManagerData.teamInfoVO.pageIndex === teamManagerData.teamInfoVO.totalPages) {
                    teamManagerData.nextDisabled = true
                }
            },
            // 下一页
            nextPage: function () {
                if (teamManagerData.nextDisabled) {
                    return
                }
                teamManagerData.pageIndex = teamManagerData.teamInfoVO.pageIndex + 1
                viewTeamManager.getAllData()
                viewTeamManager.judge()
            },
            // 首页
            shouye: function () {
                teamManagerData.pageIndex = 1
                viewTeamManager.getAllData()
                viewTeamManager.judge()
            },
            // 尾页
            weiye: function () {
                teamManagerData.pageIndex = teamManagerData.teamInfoVO.totalPages
                viewTeamManager.getAllData()
                viewTeamManager.judge()
            },
            //选择单位
            selectUnit(unitId) {
                teamManagerData.unit = unitId
                viewTeamManager.getAllData()
                viewTeamManager.judge()
            },
            selectSearch() {
                viewTeamManager.getAllData()
                viewTeamManager.judge()
            },
            myTeamMember(teamMember) {
                if (teamMember == null) {
                    toastr.info('该车队没有员工')
                }
                teamManagerData.member = teamMember
                $('#myTeamMember').modal()
            },
            addTeamModal() {
                $('#addTeam').modal()
            }
        },
        mounted() {
            //
            /*if (teamManagerData.myRole == 5) {
                $.ajax({
                    url: '/logisticssystem/personnelmanagement/personnelmanagement_getCarTeamCaptain',
                    type: 'POST',
                    data: '',
                    dataType: 'json',
                    success: function (data) {
                        if (data != null) {
                            const teamLeader = JSON.parse(data)
                            teamManagerData.teamLeader = teamLeader
                        }
                    }

                })
            }
            if (teamManagerData.myRole == 5) {
                $.ajax({
                    url: '/logisticssystem/expressmanagement/expressmanagement_queryAllRouteWithUnitSession',
                    type: 'post',
                    data: '',
                    dataType: 'json',
                    success: function (data) {
                        if (data != null && data != '') {
                            const routeList = data
                            teamManagerData.routeList = routeList
                        }
                    }
                })
            }*/
            // 获取单位信息
            if (teamManagerData.myRole == 6) {
                $.ajax({
                    url: '/logisticssystem/personnelmanagement/personnelmanagement_lowerUnit',
                    type: 'POST',
                    data: '',
                    success: function (data) {
                        let uList = JSON.parse(data)
                        teamManagerData.unitList = uList.filter(u => u.unit_type == '中转站')
                    }
                })
            }
            // 数据
            $.ajax({
                url: '/logisticssystem/vehiclemanagement/vehiclemanagement_queryTeam',
                type: 'POST',
                data: '',
                success: function (data) {
                    if (data !== null) {
                        const teamInfo = JSON.parse(data)
                        teamManagerData.teamInfoVO.listTeamDTO = teamInfo.listTeamDTO
                        teamManagerData.teamInfoVO.pageIndex = teamInfo.pageIndex
                        teamManagerData.teamInfoVO.totalRecords = teamInfo.totalRecords
                        teamManagerData.teamInfoVO.pageSize = teamInfo.pageSize
                        teamManagerData.teamInfoVO.totalPages = teamInfo.totalPages
                        teamManagerData.teamInfoVO.HavePrePage = teamInfo.HavePrePage
                        teamManagerData.teamInfoVO.HaveNextPage = teamInfo.HaveNextPage
                        teamManagerData.teamInfoVO.search = teamInfo.search
                        teamManagerData.teamInfoVO.unit = teamInfo.unit
                        teamManagerData.teamInfoVO.state = teamInfo.state
                        teamManagerData.ready = true
                        viewTeamManager.judge()
                    }
                }
            })
        }
    })
})()