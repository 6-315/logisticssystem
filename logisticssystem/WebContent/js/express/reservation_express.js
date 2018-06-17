(function () {
    const reservationExpressInfoDTO = {
        reservationInfo: {},
        expressInfo: {}
    }
    const express = new Vue({
        el: '#reservation_express',
        data: {
            view_express: {
                expressinfo_senderrealname: '',
                expressinfo_senderaddress: '',
                expressinfo_senderdetailaddress: '',
                expressinfo_senderphonenumber: '',
                expressinfo_addresseerealname: '',
                expressinfo_addresseeaddress: '',
                expressinfo_adderdetailaddress: '',
                expressinfo_addresseephonenumber: '',
                expressinfo_mark: '',
                expressinfo_protectprice: '',
                expressinfo_productname: '',
                expressinfo_productweight: ''
            },
            disabled: false
        },
        methods: {
            addReservation: function (event) {
                this.disabled = true
                reservationExpressInfoDTO.expressInfo = this.view_express
                $.ajax({
                    url: '/logisticssystem/expressmanagement/expressmanagement_addReservationAndExpressInfo',
                    type: 'POST',
                    data: {
                        reservationExpressInfoDTO: reservationExpressInfoDTO
                    },
                    success: function () {
                        console.log('kaishi')
                    }
                })
            }
        }
    })
})()