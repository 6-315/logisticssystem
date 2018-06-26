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
                unit_phonenumber: '',
                unit_type: '',
                unit_superiorunit: '',
                unit_creator: '',
                unit_state: '',
                unit_admin: '',
                unit_createtime: '',
                unit_modifytime: ''
            }
        },
        cacleReservationId: '',
        selectState: ''
    }
    const reservationList = new Vue({
        el: '#reverservationList',
        data: reservationData,
        methods: {
            openDetail: function (reser) {
                reservationData.reservationExpressDTO.reservation.reservation_num = reser.reservationInfo.reservation_num
                reservationData.reservationExpressDTO.reservation.reservation_id = reser.reservationInfo.reservation_id
                reservationData.reservationExpressDTO.reservation.reservation_expressinfo = reser.reservationInfo.reservation_expressinfo
                reservationData.reservationExpressDTO.reservation.reservation_user = reser.reservationInfo.reservation_user
                reservationData.reservationExpressDTO.reservation.reservation_state = reser.reservationInfo.reservation_state
                reservationData.reservationExpressDTO.reservation.reservation_createtime = reser.reservationInfo.reservation_createtime
                reservationData.reservationExpressDTO.reservation.reservation_unit = reser.reservationInfo.reservation_unit
                reservationData.reservationExpressDTO.reservation.reservation_distributiontor = reser.reservationInfo.reservation_distributiontor
                reservationData.reservationExpressDTO.reservation.reservation_modifytime = reser.reservationInfo.reservation_modifytime
                reservationData.reservationExpressDTO.expressinfo.expressinfo_productname = reser.expressInfo.expressinfo_productname
                reservationData.reservationExpressDTO.expressinfo.expressinfo_id = reser.expressInfo.expressinfo_id
                reservationData.reservationExpressDTO.expressinfo.expressinfo_protectprice = reser.expressInfo.expressinfo_protectprice
                reservationData.reservationExpressDTO.expressinfo.expressinfo_productweight = reser.expressInfo.expressinfo_productweight
                reservationData.reservationExpressDTO.expressinfo.expressinfo_addresseerealname = reser.expressInfo.expressinfo_addresseerealname
                reservationData.reservationExpressDTO.expressinfo.expressinfo_addresseeaddress = reser.expressInfo.expressinfo_addresseeaddress
                reservationData.reservationExpressDTO.expressinfo.expressinfo_adderdetailaddress = reser.expressInfo.expressinfo_adderdetailaddress
                reservationData.reservationExpressDTO.expressinfo.expressinfo_addresseephonenumber = reser.expressInfo.expressinfo_addresseephonenumber
                reservationData.reservationExpressDTO.expressinfo.expressinfo_addresseeemail = reser.expressInfo.expressinfo_addresseeemail
                reservationData.reservationExpressDTO.expressinfo.expressinfo_senderrealname = reser.expressInfo.expressinfo_senderrealname
                reservationData.reservationExpressDTO.expressinfo.expressinfo_senderaddress = reser.expressInfo.expressinfo_senderaddress
                reservationData.reservationExpressDTO.expressinfo.expressinfo_senderdetailaddress = reser.expressInfo.expressinfo_senderdetailaddress
                reservationData.reservationExpressDTO.expressinfo.expressinfo_senderphonenumber = reser.expressInfo.expressinfo_senderphonenumber
                reservationData.reservationExpressDTO.expressinfo.expressinfo_senderemail = reser.expressInfo.expressinfo_senderemail
                reservationData.reservationExpressDTO.expressinfo.expressinfo_mark = reser.expressInfo.expressinfo_mark
                reservationData.reservationExpressDTO.unit.unit_id = reser.unitInfo.unit_id
                reservationData.reservationExpressDTO.unit.unit_num = reser.unitInfo.unit_num
                reservationData.reservationExpressDTO.unit.unit_name = reser.unitInfo.unit_name
                reservationData.reservationExpressDTO.unit.unit_address = reser.unitInfo.unit_address
                reservationData.reservationExpressDTO.unit.unit_detailaddress = reser.unitInfo.unit_detailaddress
                reservationData.reservationExpressDTO.unit.unit_phonenumber = reser.unitInfo.unit_phonenumber
                reservationData.reservationExpressDTO.unit.unit_type = reser.unitInfo.unit_type
                reservationData.reservationExpressDTO.unit.unit_superiorunit = reser.unitInfo.unit_superiorunit
                reservationData.reservationExpressDTO.unit.unit_creator = reser.unitInfo.unit_creator
                reservationData.reservationExpressDTO.unit.unit_state = reser.unitInfo.unit_state
                reservationData.reservationExpressDTO.unit.unit_admin = reser.unitInfo.unit_admin
                reservationData.reservationExpressDTO.unit.unit_createtime = reser.unitInfo.unit_createtime
                reservationData.reservationExpressDTO.unit.unit_modifytime = reser.unitInfo.unit_modifytime
                $('#reservationDetail').modal()
            },
            cancelDetail: function () {
                $.ajax({
                    url: '/logisticssystem/expressmanagement/expressmanagement_cancelReservation',
                    type: 'POST',
                    data: {
                        'reservationInfo.reservation_id': reservationData.cacleReservationId
                    },
                    success: function (data) {
                        if (data === 'completed') {
                            $('#deleteReservation').modal('hide')
                            toastr.error('预约单已完成，不允许取消')
                        } else if (data === 'success') {
                            $('#deleteReservation').modal('hide')
                            reservationList.getReservationData(reservationData.selectState)
                            toastr.success('取消成功')
                        } else if (data === 'error') {
                            $('#deleteReservation').modal('hide')
                            toastr.error('取消失败,系统错误')
                        }
                    }

                })
            },
            openDelete: function (reservation_id) {
                reservationData.cacleReservationId = reservation_id
                $('#deleteReservation').modal()
            },
            getReservationData: function (state) {
                $.ajax({
                    url: '/logisticssystem/expressmanagement/expressmanagement_queryUserReservation',
                    type: 'POST',
                    data: {
                        state: state
                    },
                    success: function (data) {
                        let pro = JSON.parse(data)
                        reservationData.reservationExpressList = pro
                    }
                })
            },
            getReservationByState: function (reserState) {
                console.log('fdfdfdfdfdfd---')
                reservationData.selectState = reserState
                reservationList.getReservationData(reservationData.selectState)
            }
        },
        mounted() {
            $.ajax({
                url: '/logisticssystem/expressmanagement/expressmanagement_queryUserReservation',
                type: 'POST',
                data: {
                    state: ''
                },
                success: function (data) {
                    let pro = JSON.parse(data)
                    reservationData.reservationExpressList = pro
                }
            })
        }
    })
})()
