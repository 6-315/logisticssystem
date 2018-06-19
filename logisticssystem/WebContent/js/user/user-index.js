(function () {
    const reservationList = new Vue({
        el: '#reverservationList',
        data: {
            reservationExpressList: []
        },
        methods: {},
        mounted() {
            $.ajax({
                url: '/logisticssystem/expressmanagement/expressmanagement_queryUserReservation',
                type: 'POST',
                data: '',
                success: function (data) {
                    let pro = JSON.parse(data)
                    console.log('fdfd:', pro)
                    // reservationData.province = pro
                }
            })
        }
    })
})()
