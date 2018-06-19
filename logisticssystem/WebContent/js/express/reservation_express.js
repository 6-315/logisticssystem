(function () {
    let reservationExpressInfoDTO = {
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
            reservation_unit: '',
            reservation_num: ''
        },
        distributionList: [],
        disabled: false,
        isOpen: false,
        selectProvince: true,
        selectCity: false,
        selectCounty: false,
        bl: {
            display: 'block'
        },
        no: {
            display: 'none'
        },
        province: [],
        city: [],
        country: [],
        sheng: '',
        shi: '',
        qu: '',
        selectDistribution: false,
        reservation_unit_view: '',
        addressBoxIsOpen: false,
        selectAddProvince: true,
        selectAddCity: false,
        selectAddCounty: false,
        addProvince: [],
        addCity: [],
        addCountry: [],
        isProtect: false
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
                                var dto = JSON.parse(data)
                                reservationExpressInfoDTO = dto
                                console.log('reservationExpressInfoDTO:', reservationExpressInfoDTO)
                                // window.location = '/logisticssystem/loginregister/loginregister_pageReservationSuccess'
                                return
                            } else {
                                toastr.error('预约失败，请重新下单')
                                reservationData.disabled = false
                            }
                        }
                    })
                },
                openBox: function () {
                    reservationData.isOpen = true
                    // 添加省份数据
                    $.ajax({
                        url: '/logisticssystem/loginregister/loginregister_getAllProvince',
                        type: 'POST',
                        data: '',
                        success: function (data) {
                            let pro = JSON.parse(data)
                            reservationData.province = pro
                        }
                    })
                },
                closeBox: function () {
                    reservationData.isOpen = false
                },
                selectProvinceMethod: function () {
                    reservationData.selectProvince = true
                    reservationData.selectCity = false
                    reservationData.selectCounty = false
                },
                selectCityMethod: function () {
                    reservationData.selectProvince = false
                    reservationData.selectCity = true
                    reservationData.selectCounty = false
                    // 获取对应的市数据
                },
                selectCountyMethod: function () {
                    reservationData.selectProvince = false
                    reservationData.selectCity = false
                    reservationData.selectCounty = true
                    // 获取对应的县数据
                },
                inputProvince: function (dat, provinceName) {
                    reservationData.sheng = provinceName
                    // 添加市数据
                    $.ajax({
                        url: '/logisticssystem/loginregister/loginregister_getAllCityByProvinceID',
                        type: 'POST',
                        data: {
                            cityFatherId: dat
                        },
                        success: function (data) {
                            let cit = JSON.parse(data)
                            reservationData.city = cit
                            express.selectCityMethod()
                        }
                    })
                },
                inputCity: function (cityID, cityName) {
                    reservationData.shi = cityName
                    // 添加县数据
                    $.ajax({
                        url: '/logisticssystem/loginregister/loginregister_getAllCountryByCityID',
                        type: 'POST',
                        data: {
                            cityFatherId: cityID
                        },
                        success: function (data) {
                            let cou = JSON.parse(data)
                            reservationData.country = cou
                            express.selectCountyMethod()
                        }
                    })
                },
                inputCountry: function (countryName) {
                    reservationData.qu = countryName
                    reservationData.view_express.expressinfo_senderaddress = reservationData.sheng + reservationData.shi + reservationData.qu
                    express.getDistributionByPosition()
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
                                if (reservationData.distributionList === null || reservationData.distributionList.length === 0) {
                                    console.log('bucunzai')
                                    reservationData.reservation_unit_view = null
                                    toastr.error('该区市下没有配送点，请就近选择')
                                    return
                                }
                                express.closeBox()
                            }
                        }
                    })
                },
                selectD: function () {
                    reservationData.selectDistribution = true
                },
                noSelectD: function () {
                    reservationData.selectDistribution = false
                },
                selectPeisong: function (unitId, unitAddress) {
                    reservationData.reservation_unit_view = unitAddress
                    reservationData.reservationInfo.reservation_unit = unitId
                    express.noSelectD()
                },
                openAddressBox: function () {
                    reservationData.addressBoxIsOpen = true
                    // 添加省份数据
                    $.ajax({
                        url: '/logisticssystem/loginregister/loginregister_getAllProvince',
                        type: 'POST',
                        data: '',
                        success: function (data) {
                            let pro = JSON.parse(data)
                            reservationData.addProvince = pro
                        }
                    })
                },
                closeAddressBox: function () {
                    reservationData.addressBoxIsOpen = false
                },
                selectAddProvinceMethod: function () {
                    reservationData.selectAddProvince = true
                    reservationData.selectAddCity = false
                    reservationData.selectAddCounty = false
                },
                selectAddCityMethod: function () {
                    reservationData.selectAddProvince = false
                    reservationData.selectAddCity = true
                    reservationData.selectAddCounty = false
                },
                selectAddCountyMethod: function () {
                    reservationData.selectAddProvince = false
                    reservationData.selectAddCity = false
                    reservationData.selectAddCounty = true
                },
                inputAddProvince: function (dat, provinceName) {
                    reservationData.sheng = provinceName
                    // 添加市数据
                    $.ajax({
                        url: '/logisticssystem/loginregister/loginregister_getAllCityByProvinceID',
                        type: 'POST',
                        data: {
                            cityFatherId: dat
                        },
                        success: function (data) {
                            let cit = JSON.parse(data)
                            reservationData.addCity = cit
                            express.selectAddCityMethod()
                        }
                    })
                },
                inputAddCity: function (cityID, cityName) {
                    reservationData.shi = cityName
                    // 添加县数据
                    $.ajax({
                        url: '/logisticssystem/loginregister/loginregister_getAllCountryByCityID',
                        type: 'POST',
                        data: {
                            cityFatherId: cityID
                        },
                        success: function (data) {
                            let cou = JSON.parse(data)
                            reservationData.addCountry = cou
                            express.selectAddCountyMethod()
                        }
                    })
                },
                inputAddCountry: function (countryName) {
                    reservationData.qu = countryName
                    reservationData.view_express.expressinfo_addresseeaddress = reservationData.sheng + reservationData.shi + reservationData.qu
                    express.closeAddressBox()
                },
                openProtectBox: function () {
                    reservationData.isProtect = true
                },
                closeProtectBox: function () {
                    reservationData.isProtect = false
                },
                selectProtectService: function () {
                    reservationData.view_express.expressinfo_protectprice = '保价'
                    express.closeProtectBox()
                }
            }
        })
})()