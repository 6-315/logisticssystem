(function () {
    let reservationData = {
        reservationExpressList: [],
        reservationExpressDTO: {
            reservation: {
                reservation_id: '',
                reservation_num: '',
                reservation_expressinfo: '',
                reservation_user: '',
                reservation_state: '',
                reservation_unit: '',
                reservation_distributiontor: '',
                reservation_createtime: '',
                reservation_modifytime: ''
            },
            expressinfo: {
                expressinfo_id: '',
                expressinfo_protectprice: '',
                expressinfo_productname: '',
                expressinfo_productweight: '',
                expressinfo_addresseerealname: '',
                expressinfo_addresseeaddress: '',
                expressinfo_adderdetailaddress: '',
                expressinfo_addresseephonenumber: '',
                expressinfo_addresseeemail: '',
                expressinfo_senderrealname: '',
                expressinfo_senderaddress: '',
                expressinfo_senderdetailaddress: '',
                expressinfo_senderphonenumber: '',
                expressinfo_senderemail: '',
                expressinfo_mark: ''
            },
            unit: {
                unit_id: '',
                unit_num: '',
                unit_name: '',
                unit_address: '',
                unit_detailaddress: '',
                unit_phonenumber: ''
            }
        }
    }
    const reservationList = new Vue({
        el: '#reverservationList',
        data: reservationData,
        methods: {
            openDetail: function (reser) {
                console.log(reser)
                console.log('fdfd:', reser.reservationInfo.reservation_num)
                reservationData.reservationExpressDTO.reservation.reservation_num = reser.reservationInfo.reservation_num
                console.log('lplp:', reservationData.reservationExpressDTO.reservation.reservation_num)
                $('#reservationDetail').modal()
            }
        },
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
