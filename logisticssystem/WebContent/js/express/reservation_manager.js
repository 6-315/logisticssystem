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
            unit: ''
        }
    }
    const view_reservation = new Vue({
        el: '#reservation_manager',
        data: reservationData,
        methods: {},
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
                }
            })
        }
    })
})()
