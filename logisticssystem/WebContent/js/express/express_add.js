(function () {
    const expressData = {
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
            expressinfo_mark: '',
            expressinfo_createtime: '',
            expressinfo_modifytime: ''
        },
        express: {
            express_id: '',
            express_expressinfoid: '',
            express_number: '',
            express_belongunit: '',
            express_belong: '',
            express_state: '',
            express_originating: '',
            express_end: '',
            express_createtime: '',
            express_modifytime: ''
        },
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
        addressBoxIsOpen: false,
        selectAddProvince: true,
        selectAddCity: false,
        selectAddCounty: false,
        addProvince: [],
        addCity: [],
        addCountry: []
    }
    const view_express = new Vue({
        el: '#express_add',
        data: expressData,
        methods: {
            openBox: function () {
                expressData.isOpen = true
                // 添加省份数据
                $.ajax({
                    url: '/logisticssystem/loginregister/loginregister_getAllProvince',
                    type: 'POST',
                    data: '',
                    success: function (data) {
                        let pro = JSON.parse(data)
                        expressData.province = pro
                    }
                })
            },
            closeBox: function () {
                expressData.isOpen = false
            },
            selectProvinceMethod: function () {
                expressData.selectProvince = true
                expressData.selectCity = false
                expressData.selectCounty = false
            },
            selectCityMethod: function () {
                expressData.selectProvince = false
                expressData.selectCity = true
                expressData.selectCounty = false
                // 获取对应的市数据
            },
            selectCountyMethod: function () {
                expressData.selectProvince = false
                expressData.selectCity = false
                expressData.selectCounty = true
                // 获取对应的县数据
            },
            inputProvince: function (dat, provinceName) {
                expressData.sheng = provinceName
                // 添加市数据
                $.ajax({
                    url: '/logisticssystem/loginregister/loginregister_getAllCityByProvinceID',
                    type: 'POST',
                    data: {
                        cityFatherId: dat
                    },
                    success: function (data) {
                        let cit = JSON.parse(data)
                        expressData.city = cit
                        view_express.selectCityMethod()
                    }
                })
            },
            inputCity: function (cityID, cityName) {
                expressData.shi = cityName
                // 添加县数据
                $.ajax({
                    url: '/logisticssystem/loginregister/loginregister_getAllCountryByCityID',
                    type: 'POST',
                    data: {
                        cityFatherId: cityID
                    },
                    success: function (data) {
                        let cou = JSON.parse(data)
                        expressData.country = cou
                        view_express.selectCountyMethod()
                    }
                })
            },
            inputCountry: function (countryName) {
                expressData.qu = countryName
                expressData.expressinfo.expressinfo_senderaddress = expressData.sheng + expressData.shi + expressData.qu
                view_express.closeBox()
            },
            openAddressBox: function () {
                expressData.addressBoxIsOpen = true
                // 添加省份数据
                $.ajax({
                    url: '/logisticssystem/loginregister/loginregister_getAllProvince',
                    type: 'POST',
                    data: '',
                    success: function (data) {
                        let pro = JSON.parse(data)
                        expressData.addProvince = pro
                    }
                })
            },
            closeAddressBox: function () {
                expressData.addressBoxIsOpen = false
            },
            selectAddProvinceMethod: function () {
                expressData.selectAddProvince = true
                expressData.selectAddCity = false
                expressData.selectAddCounty = false
            },
            selectAddCityMethod: function () {
                expressData.selectAddProvince = false
                expressData.selectAddCity = true
                expressData.selectAddCounty = false
            },
            selectAddCountyMethod: function () {
                expressData.selectAddProvince = false
                expressData.selectAddCity = false
                expressData.selectAddCounty = true
            },
            inputAddProvince: function (dat, provinceName) {
                expressData.sheng = provinceName
                // 添加市数据
                $.ajax({
                    url: '/logisticssystem/loginregister/loginregister_getAllCityByProvinceID',
                    type: 'POST',
                    data: {
                        cityFatherId: dat
                    },
                    success: function (data) {
                        let cit = JSON.parse(data)
                        expressData.addCity = cit
                        view_express.selectAddCityMethod()
                    }
                })
            },
            inputAddCity: function (cityID, cityName) {
                expressData.shi = cityName
                // 添加县数据
                $.ajax({
                    url: '/logisticssystem/loginregister/loginregister_getAllCountryByCityID',
                    type: 'POST',
                    data: {
                        cityFatherId: cityID
                    },
                    success: function (data) {
                        let cou = JSON.parse(data)
                        expressData.addCountry = cou
                        view_express.selectAddCountyMethod()
                    }
                })
            },
            inputAddCountry: function (countryName) {
                expressData.qu = countryName
                expressData.expressinfo.expressinfo_addresseeaddress = expressData.sheng + expressData.shi + expressData.qu
                view_express.closeAddressBox()
            },
            addExpress: function () {
                
            }
        },
        mounted() {
            //获取后台预约单数据
            let obj = $('#shuju').val()
            if (obj.length === 0) {
                return
            }
            console.log('kaishi')
            $.ajax({
                url: '/logisticssystem/expressmanagement/expressmanagement_queryCurrentReservationInfo',
                type: 'POST',
                data: {
                    'idList': obj
                },
                success(data) {
                    let shuju = JSON.parse(data)
                    expressData.expressinfo.expressinfo_id = shuju.expressInfo.expressinfo_id
                    expressData.expressinfo.expressinfo_protectprice = shuju.expressInfo.expressinfo_protectprice
                    expressData.expressinfo.expressinfo_productname = shuju.expressInfo.expressinfo_productname
                    expressData.expressinfo.expressinfo_productweight = shuju.expressInfo.expressinfo_productweight
                    expressData.expressinfo.expressinfo_addresseerealname = shuju.expressInfo.expressinfo_addresseerealname
                    expressData.expressinfo.expressinfo_addresseeaddress = shuju.expressInfo.expressinfo_addresseeaddress
                    expressData.expressinfo.expressinfo_adderdetailaddress = shuju.expressInfo.expressinfo_adderdetailaddress
                    expressData.expressinfo.expressinfo_addresseephonenumber = shuju.expressInfo.expressinfo_addresseephonenumber
                    expressData.expressinfo.expressinfo_addresseeemail = shuju.expressInfo.expressinfo_addresseeemail
                    expressData.expressinfo.expressinfo_senderrealname = shuju.expressInfo.expressinfo_senderrealname
                    expressData.expressinfo.expressinfo_senderaddress = shuju.expressInfo.expressinfo_senderaddress
                    expressData.expressinfo.expressinfo_senderdetailaddress = shuju.expressInfo.expressinfo_senderdetailaddress
                    expressData.expressinfo.expressinfo_senderphonenumber = shuju.expressInfo.expressinfo_senderphonenumber
                    expressData.expressinfo.expressinfo_senderemail = shuju.expressInfo.expressinfo_senderemail
                    expressData.expressinfo.expressinfo_mark = shuju.expressInfo.expressinfo_mark
                    expressData.expressinfo.expressinfo_createtime = shuju.expressInfo.expressinfo_createtime
                    expressData.expressinfo.expressinfo_modifytime = shuju.expressInfo.expressinfo_modifytime
                    expressData.reservation.reservation_id = shuju.reservationInfo.reservation_id
                    expressData.reservation.reservation_num = shuju.reservationInfo.reservation_num
                    expressData.reservation.reservation_expressinfo = shuju.reservationInfo.reservation_expressinfo
                    expressData.reservation.reservation_user = shuju.reservationInfo.reservation_user
                    expressData.reservation.reservation_state = shuju.reservationInfo.reservation_state
                    expressData.reservation.reservation_unit = shuju.reservationInfo.reservation_unit
                    expressData.reservation.reservation_distributiontor = shuju.reservationInfo.reservation_distributiontor
                    expressData.reservation.reservation_createtime = shuju.reservationInfo.reservation_createtime
                    expressData.reservation.reservation_modifytime = shuju.reservationInfo.reservation_modifytime
                }
            })
        }
    })
})()