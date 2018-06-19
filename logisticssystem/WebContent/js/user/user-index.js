(function () {
    let reservationData = {
        reservationExpressList: []
    }
    const reservationList = new Vue({
        el: '#reverservationList',
        data: reservationData,
        methods: {},
        mounted() {
            $.ajax({
                url: '/logisticssystem/expressmanagement/expressmanagement_queryUserReservation',
                type: 'POST',
                data: '',
                success: function (data) {
                    let pro = JSON.parse(data)
                    reservationData.reservationExpressList = pro
                }
            })
        }
    })
})()
