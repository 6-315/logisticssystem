(function () {
    const reservationExpressInfoDTO = {
        reservationInfo: {},
        expressInfo: {}
    }
    const reservationData = {
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
        reservationInfo: {
            reservation_unit: ''
        },
        disabled: false,
        distributionList: []
    }
    const express = new Vue(
        {
            el: '#reservation_express',
            data: reservationData,
            methods: {
                addReservation: function (event) {
                    this.disabled = true
                    reservationExpressInfoDTO.expressInfo = reservationData.view_express

                    /**
					 * 预约
					 */
                    $.ajax({
                        url: '/logisticssystem/expressmanagement/expressmanagement_addReservationAndExpressInfo',
                        type: 'POST',
                        data: {
                            'reservationExpressInfoDTO.expressInfo.expressinfo_senderrealname': reservationData.view_express.expressinfo_senderrealname,
                            'reservationExpressInfoDTO.expressInfo.expressinfo_senderaddress': reservationData.view_express.expressinfo_senderaddress,
                            'reservationExpressInfoDTO.expressInfo.expressinfo_senderdetailaddress': reservationData.view_express.expressinfo_senderdetailaddress,
                            'reservationExpressInfoDTO.expressInfo.expressinfo_senderphonenumber': reservationData.view_express.expressinfo_senderphonenumber,
                            'reservationExpressInfoDTO.expressInfo.expressinfo_addresseerealname': reservationData.view_express.expressinfo_addresseerealname,
                            'reservationExpressInfoDTO.expressInfo.expressinfo_addresseeaddress': reservationData.view_express.expressinfo_addresseeaddress,
                            'reservationExpressInfoDTO.expressInfo.expressinfo_adderdetailaddress': reservationData.view_express.expressinfo_adderdetailaddress,
                            'reservationExpressInfoDTO.expressInfo.expressinfo_addresseephonenumber': reservationData.view_express.expressinfo_addresseephonenumber,
                            'reservationExpressInfoDTO.expressInfo.expressinfo_mark': reservationData.view_express.expressinfo_mark,
                            'reservationExpressInfoDTO.expressInfo.expressinfo_protectprice': reservationData.view_express.expressinfo_protectprice,
                            'reservationExpressInfoDTO.expressInfo.expressinfo_productname': reservationData.view_express.expressinfo_productname,
                            'reservationExpressInfoDTO.expressInfo.expressinfo_productweight': reservationData.view_express.expressinfo_productweight,
                            'reservationExpressInfoDTO.reservationInfo.reservation_unit': reservationData.reservationInfo.reservation_unit
                        },
                        success: function (data) {
                            if (data !== null) {
                                window.location = '/logisticssystem/loginregister/loginregister_pageReservationSuccess'
                                return
                            } else {
                                toastr.error('预约失败，请重新下单')
                                reservationData.disabled = false
                            }
                        }
                    })
                },
                getDistributionByPosition: function (event) {
                    console.log('view_express.expressinfo_senderaddress:', reservationData.view_express.expressinfo_senderaddress)
                    $.ajax({
                        url: '/logisticssystem/expressmanagement2/expressmanagement2_getAddressByUnit',
                        type: 'POST',
                        data: {
                            'address': reservationData.view_express.expressinfo_senderaddress
                        },
                        success: function (data) {
                            if (data != null) {
                                const distributionAddress = JSON.parse(data)
                                reservationData.distributionList = distributionAddress
                            }
                        }
                    })
                }
            }
        })
})()