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
            console.log('1')
            $.ajax({
                url: '/logisticssystem/personnelmanagement/personnelmanagement_lowerUnit',
                type: 'POST',
                data: '',
                success: function (data) {
                    console.log('1success:', data)
                    let uList = JSON.parse(data)
                    console.log(uList)
                }
            })
            console.log('2')
            $.ajax({
                url: '/logisticssystem/expressmanagement/expressmanagement_queryReservationInfo',
                type: 'POST',
                data: '',
                success: function (data) {
                    console.log('2success:', data)
                    let reservationListVO = JSON.parse(data)
                    reservationData.reservationVO.listReservationInfoDTO = reservationListVO.listReservationInfoDTO
                    console.log('2fdf:', reservationData.reservationVO.listReservationInfoDTO)
                }
            })
        }
    })
})()
