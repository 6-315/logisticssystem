(function () {
    const reservationData = {
        unitList: [],
        reservationVO: {
            listReservationInfoDTO: [],
            pageIndex: '',
            totalRecords: '',
            pageSize: '',
            totalPages: '',
            HavePrePage: '',
            HaveNextPage: '',
            search: '',
            state: '',
            unit: '',
            isDistributed: ''
        },
        search: '',
        page: '',
        state: '',
        unit: '',
        isDistributed: '',
        ready: false,
        preDisabled: false,
        nextDisabled: false,
        updateState: '',
        listDistributiontorAndStaffBasicinfoDTO: [],
        idReady: ''
    }
    const view_reservation = new Vue({
        el: '#reservation_manager',
        data: reservationData,
        methods: {
            searchReservationNum: function () {
                view_reservation.getAllData()
                view_reservation.judge()
            },
            getAllData: function () {
                $.ajax({
                    url: '/logisticssystem/expressmanagement/expressmanagement_queryReservationInfo',
                    type: 'POST',
                    data: {
                        'search': reservationData.search,
                        'page': reservationData.page,
                        'state': reservationData.state,
                        'unit': reservationData.unit,
                        'isDistributed': reservationData.isDistributed
                    },
                    success: function (data) {
                        let reservationListVO = JSON.parse(data)
                        reservationData.reservationVO.listReservationInfoDTO = reservationListVO.listReservationInfoDTO
                        reservationData.reservationVO.pageIndex = reservationListVO.pageIndex
                        reservationData.reservationVO.totalRecords = reservationListVO.totalRecords
                        reservationData.reservationVO.pageSize = reservationListVO.pageSize
                        reservationData.reservationVO.totalPages = reservationListVO.totalPages
                        reservationData.reservationVO.HavePrePage = reservationListVO.HavePrePage
                        reservationData.reservationVO.HaveNextPage = reservationListVO.HaveNextPage
                        reservationData.reservationVO.search = reservationListVO.search
                        reservationData.reservationVO.state = reservationListVO.state
                        reservationData.reservationVO.unit = reservationListVO.unit
                        view_reservation.judge()
                    }
                })
            },
            // 分页-上一页
            prePage: function () {
                if (reservationData.preDisabled) {
                    return
                }
                reservationData.page = reservationData.reservationVO.pageIndex - 1
                view_reservation.getAllData()
                view_reservation.judge()
            },
            judge: function () {
                reservationData.preDisabled = false
                reservationData.nextDisabled = false
                if (reservationData.reservationVO.pageIndex == 1) {
                    reservationData.preDisabled = true
                }
                if (reservationData.reservationVO.pageIndex === reservationData.reservationVO.totalPages) {
                    reservationData.nextDisabled = true
                }
            },
            // 下一页
            nextPage: function () {
                if (reservationData.nextDisabled) {
                    return
                }
                reservationData.page = reservationData.reservationVO.pageIndex + 1
                view_reservation.getAllData()
                view_reservation.judge()
            },
            // 首页
            shouye: function () {
                reservationData.page = 1
                view_reservation.getAllData()
                view_reservation.judge()
            },
            // 尾页
            weiye: function () {
                reservationData.page = reservationData.reservationVO.totalPages
                view_reservation.getAllData()
                view_reservation.judge()
            },
            // 单位筛选
            selectUnit: function (selectUnitId) {
                reservationData.unit = selectUnitId
                view_reservation.getAllData()
                view_reservation.judge()
            },
            distributionStaff: function (con) {
                reservationData.isDistributed = con
                view_reservation.getAllData()
                view_reservation.judge()
            },
            selectState: function (s) {
                reservationData.state = s
                view_reservation.getAllData()
                view_reservation.judge()
            },
            replaceState: function (state) {
                let replaceState = ''
                switch (state) {
                    case '待受理':
                        replaceState = `<span class="label label-info">
                                    ${state}
                                 </span>
                                `
                        break;
                    case '已受理':
                        replaceState = `<span class="label label-success">
                                    ${state}
                                 </span>
                                `
                        break;
                    case '已拒绝':
                        replaceState = `<span class="label label-danger">
                                    ${state}
                                 </span>
                                `
                        break;
                    case '待取件':
                        replaceState = `<span class="label label-primary">
                                    ${state}
                                 </span>
                                `
                        break;
                    case '已取件':
                        replaceState = `<span class="label label-warning">
                                    ${state}
                                 </span>
                                `
                        break;
                    case '已完成':
                        replaceState = `<span class="label label-default">
                                    ${state}
                                 </span>
                                `
                        break;
                }
                return replaceState
            },
            acceptanceReservation: function (upState, oldState, idList) {
                if (oldState === '待受理') {
                    view_reservation.updateReservation(upState, idList)
                } else {
                    toastr.error('改状态不可受理')
                }
            },
            cancleReservation: function (upState, oldState, idList) {
                if (oldState === '待受理') {
                    view_reservation.updateReservation(upState, idList)
                } else {
                    toastr.error('改状态不可拒绝')
                    return
                }
            },
            takePart: function (upState, oldState, idList) {
                if (oldState === '待取件') {
                    view_reservation.updateReservation(upState, idList)
                } else {
                    toastr.error('改状态不可拒绝')
                    return
                }
            },
            completeReserv: function (upState, oldState, idList) {
                if (oldState === '已取件') {
                    view_reservation.updateReservation(upState, idList)
                } else {
                    toastr.error('改状态不可拒绝')
                    return
                }
            },
            updateReservation: function (upState, idList) {
                $.ajax({
                    url: '/logisticssystem/expressmanagement/expressmanagement_updateReservation',
                    type: 'POST',
                    data: {
                        'state': upState,
                        'idList': idList
                    },
                    success: function (data) {
                        if (data === 'success') {
                            view_reservation.getAllData()
                            view_reservation.judge()
                            toastr.success('受理成功')
                        }
                    }

                })
            },
            opendistributionReservationStaff: function (oldState, idList) {
                if (oldState === '已受理') {
                    reservationData.idReady = idList
                    view_reservation.getListDistributiontor()
                    $('#distributionReservationStaff').modal()
                } else {
                    toastr.error('此时不可进行分配')
                    return
                }

            },
            getListDistributiontor: function () {
                $.ajax({
                    url: '/logisticssystem/expressmanagement2/expressmanagement2_getDispatcher',
                    type: 'POST',
                    data: '',
                    success: function (data) {
                        if (data !== null) {
                            let listDistri = JSON.parse(data)
                            reservationData.listDistributiontorAndStaffBasicinfoDTO = listDistri
                        }
                    }

                })
            },
            // 分配配送员
            distributePerson: function (staffId) {
                $.ajax({
                    url: '/logisticssystem/expressmanagement/expressmanagement_updateReservationWithDistributor',
                    type: 'POST',
                    data: {
                        'idList': reservationData.idReady,
                        'distributor.distributiontor_id': staffId
                    },
                    success: function (data) {
                        console.log('data:', data)
                        if (data === 'success') {
                            reservationData.idReady = ''
                            view_reservation.getAllData()
                            view_reservation.judge()
                            $('#distributionReservationStaff').modal('hide')
                            toastr.success('分配成功')
                        } else {
                            toastr.error('分配失败,请重新分配')
                        }
                    }
                })
            },
            skipExpressPage: function (reserId) {
                window.location = '/logisticssystem/expressmanagement/expressmanagement_skipPage?idList=' + reserId
            }
        },
        mounted() {
            // 获取单位信息
            $.ajax({
                url: '/logisticssystem/personnelmanagement/personnelmanagement_lowerUnit',
                type: 'POST',
                data: '',
                success: function (data) {
                    let uList = JSON.parse(data)
                    reservationData.unitList = uList
                }
            })
            $.ajax({
                url: '/logisticssystem/expressmanagement/expressmanagement_queryReservationInfo',
                type: 'POST',
                data: '',
                success: function (data) {
                    let reservationListVO = JSON.parse(data)
                    reservationData.reservationVO.listReservationInfoDTO = reservationListVO.listReservationInfoDTO
                    reservationData.reservationVO.pageIndex = reservationListVO.pageIndex
                    reservationData.reservationVO.totalRecords = reservationListVO.totalRecords
                    reservationData.reservationVO.pageSize = reservationListVO.pageSize
                    reservationData.reservationVO.totalPages = reservationListVO.totalPages
                    reservationData.reservationVO.HavePrePage = reservationListVO.HavePrePage
                    reservationData.reservationVO.HaveNextPage = reservationListVO.HaveNextPage
                    reservationData.reservationVO.search = reservationListVO.search
                    reservationData.reservationVO.state = reservationListVO.state
                    reservationData.reservationVO.unit = reservationListVO.unit
                    reservationData.page = reservationListVO.pageIndex
                    reservationData.ready = true
                    view_reservation.judge()
                }
            })
        }
    })
})
()
