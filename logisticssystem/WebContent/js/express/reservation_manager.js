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
        nextDisabled: false
    }
    const view_reservation = new Vue({
        el: '#reservation_manager',
        data: reservationData,
        methods: {
            searchReservationNum: function () {
                view_reservation.getAllData()
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
            //分页-上一页
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
            //下一页
            nextPage: function () {
                if (reservationData.nextDisabled) {
                    return
                }
                reservationData.page = reservationData.reservationVO.pageIndex + 1
                view_reservation.getAllData()
                view_reservation.judge()
            },
            //首页
            shouye: function () {
                reservationData.page = 1
                view_reservation.getAllData()
                view_reservation.judge()
            },
            //尾页
            weiye: function () {
                reservationData.page = reservationData.reservationVO.totalPages
                view_reservation.getAllData()
                view_reservation.judge()
            },
            //单位筛选
            selectUnit: function (selectUnitId) {
                reservationData.unit = selectUnitId
                view_reservation.getAllData()
                view_reservation.judge()
            }
        },
        mounted() {
            //获取单位信息
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
