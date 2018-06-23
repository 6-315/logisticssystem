(function () {
    const reservationExpressInfoDTO = {
        reservationInfo: {},
        expressInfo: {}
    }
    const cityData = {
        loginBeforeInfo: {
            expressinfo_productweight: '',
            expressinfo_productname: '',
            expressinfo_protectprice: '',
            expressinfo_mark: '',
            expressinfo_addresseephonenumber: '',
            expressinfo_adderdetailaddress: '',
            expressinfo_addresseeaddress: '',
            expressinfo_addresseerealname: '',
            expressinfo_senderphonenumber: '',
            expressinfo_senderdetailaddress: '',
            expressinfo_senderaddress: '',
            expressinfo_senderrealname: ''
        },
        reservationInfo: {
            reservation_unit: ''
        },
        distributionList: [],
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
    const city = new Vue({
        el: '#reservation_express',
        data: cityData,
        methods: {
            openBox: function () {
                cityData.isOpen = true
                //添加省份数据
                $.ajax({
                    url: '/logisticssystem/loginregister/loginregister_getAllProvince',
                    type: 'POST',
                    data: '',
                    success: function (data) {
                        let pro = JSON.parse(data)
                        cityData.province = pro
                    }
                })
            },
            closeBox: function () {
                cityData.isOpen = false
            },
            selectProvinceMethod: function () {
                cityData.selectProvince = true
                cityData.selectCity = false
                cityData.selectCounty = false
            },
            selectCityMethod: function () {
                cityData.selectProvince = false
                cityData.selectCity = true
                cityData.selectCounty = false
                //获取对应的市数据
            },
            selectCountyMethod: function () {
                cityData.selectProvince = false
                cityData.selectCity = false
                cityData.selectCounty = true
                //获取对应的县数据
            },
            inputProvince: function (dat, provinceName) {
                cityData.sheng = provinceName
                //添加市数据
                $.ajax({
                    url: '/logisticssystem/loginregister/loginregister_getAllCityByProvinceID',
                    type: 'POST',
                    data: {
                        cityFatherId: dat
                    },
                    success: function (data) {
                        let cit = JSON.parse(data)
                        cityData.city = cit
                        city.selectCityMethod()
                    }
                })
            },
            inputCity: function (cityID, cityName) {
                cityData.shi = cityName
                //添加县数据
                $.ajax({
                    url: '/logisticssystem/loginregister/loginregister_getAllCountryByCityID',
                    type: 'POST',
                    data: {
                        cityFatherId: cityID
                    },
                    success: function (data) {
                        let cou = JSON.parse(data)
                        cityData.country = cou
                        city.selectCountyMethod()
                    }
                })
            },
            inputCountry: function (countryName) {
                cityData.qu = countryName
                cityData.loginBeforeInfo.expressinfo_senderaddress = cityData.sheng + cityData.shi + cityData.qu
                city.getDistributionByPosition()
            },
            getDistributionByPosition: function (event) {
                console.log('loginBeforeInfo.expressinfo_senderaddress:', cityData.loginBeforeInfo.expressinfo_senderaddress)
                $.ajax({
                    url: '/logisticssystem/expressmanagement2/expressmanagement2_getAddressByUnit',
                    type: 'POST',
                    data: {
                        'address': cityData.loginBeforeInfo.expressinfo_senderaddress
                    },
                    success: function (data) {
                        if (data != null) {
                            const distributionAddress = JSON.parse(data)
                            cityData.distributionList = distributionAddress
                            if (cityData.distributionList === null || cityData.distributionList.length === 0) {
                                console.log('bucunzai')
                                cityData.reservation_unit_view = null
                                toastr.error('该区市下没有配送点，请就近选择')
                                return
                            }
                            city.closeBox()
                        }
                    }
                })
            },
            selectD: function () {
                cityData.selectDistribution = true
            },
            noSelectD: function () {
                cityData.selectDistribution = false
            },
            selectPeisong: function (unitId, unitAddress) {
                cityData.reservation_unit_view = unitAddress
                cityData.reservationInfo.reservation_unit = unitId
                city.noSelectD()
            },
            openAddressBox: function () {
                cityData.addressBoxIsOpen = true
                //添加省份数据
                $.ajax({
                    url: '/logisticssystem/loginregister/loginregister_getAllProvince',
                    type: 'POST',
                    data: '',
                    success: function (data) {
                        let pro = JSON.parse(data)
                        cityData.addProvince = pro
                    }
                })
            },
            closeAddressBox: function () {
                cityData.addressBoxIsOpen = false
            },
            selectAddProvinceMethod: function () {
                cityData.selectAddProvince = true
                cityData.selectAddCity = false
                cityData.selectAddCounty = false
            },
            selectAddCityMethod: function () {
                cityData.selectAddProvince = false
                cityData.selectAddCity = true
                cityData.selectAddCounty = false
            },
            selectAddCountyMethod: function () {
                cityData.selectAddProvince = false
                cityData.selectAddCity = false
                cityData.selectAddCounty = true
            },
            inputAddProvince: function (dat, provinceName) {
                cityData.sheng = provinceName
                //添加市数据
                $.ajax({
                    url: '/logisticssystem/loginregister/loginregister_getAllCityByProvinceID',
                    type: 'POST',
                    data: {
                        cityFatherId: dat
                    },
                    success: function (data) {
                        let cit = JSON.parse(data)
                        cityData.addCity = cit
                        city.selectAddCityMethod()
                    }
                })
            },
            inputAddCity: function (cityID, cityName) {
                cityData.shi = cityName
                //添加县数据
                $.ajax({
                    url: '/logisticssystem/loginregister/loginregister_getAllCountryByCityID',
                    type: 'POST',
                    data: {
                        cityFatherId: cityID
                    },
                    success: function (data) {
                        let cou = JSON.parse(data)
                        cityData.addCountry = cou
                        city.selectAddCountyMethod()
                    }
                })
            },
            inputAddCountry: function (countryName) {
                cityData.qu = countryName
                cityData.loginBeforeInfo.expressinfo_addresseeaddress = cityData.sheng + cityData.shi + cityData.qu
                city.closeAddressBox()
            },
            openProtectBox: function () {
                cityData.isProtect = true
            },
            closeProtectBox: function () {
                cityData.isProtect = false
            },
            selectProtectService: function () {
                cityData.loginBeforeInfo.expressinfo_protectprice = '保价'
                city.closeProtectBox()
            }
        }
    })
})()