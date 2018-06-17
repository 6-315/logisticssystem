(function () {
    const reservationExpressInfoDTO = {
        reservationInfo: {},
        expressInfo: {}
    }
    const express = new Vue(
        {
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
                    $
                        .ajax({
                            url: '/logisticssystem/expressmanagement/expressmanagement_addReservationAndExpressInfo',
                            type: 'POST',
                            data: {
                                'reservationExpressInfoDTO.expressInfo.expressinfo_senderrealname': this.view_express.expressinfo_senderrealname,
                                'reservationExpressInfoDTO.expressInfo.expressinfo_senderaddress': this.view_express.expressinfo_senderaddress,
                                'reservationExpressInfoDTO.expressInfo.expressinfo_senderdetailaddress': this.view_express.expressinfo_senderdetailaddress,
                                'reservationExpressInfoDTO.expressInfo.expressinfo_senderphonenumber': this.view_express.expressinfo_senderphonenumber,
                                'reservationExpressInfoDTO.expressInfo.expressinfo_addresseerealname': this.view_express.expressinfo_addresseerealname,
                                'reservationExpressInfoDTO.expressInfo.expressinfo_addresseeaddress': this.view_express.expressinfo_addresseeaddress,
                                'reservationExpressInfoDTO.expressInfo.expressinfo_adderdetailaddress': this.view_express.expressinfo_adderdetailaddress,
                                'reservationExpressInfoDTO.expressInfo.expressinfo_addresseephonenumber': this.view_express.expressinfo_addresseephonenumber,
                                'reservationExpressInfoDTO.expressInfo.expressinfo_mark': this.view_express.expressinfo_mark,
                                'reservationExpressInfoDTO.expressInfo.expressinfo_protectprice': this.view_express.expressinfo_protectprice,
                                'reservationExpressInfoDTO.expressInfo.expressinfo_productname': this.view_express.expressinfo_productname,
                                'reservationExpressInfoDTO.expressInfo.expressinfo_productweight': this.view_express.expressinfo_productweight
                            },
                            success: function (data) {
                                if (data !== null) {
                                    window.location = '/logisticssystem/userinfo/userinfo_pageReservationSuccess'
                                    return
                                } else {
                                    toastr.error('预约失败，请重新下单')
                                    this.disabled = false
                                }
                            }
                        })
                }
            }
        })
})()