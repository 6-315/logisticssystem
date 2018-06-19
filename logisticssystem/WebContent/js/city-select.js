(function () {
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
            inputProvince: function (dat) {
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
                        console.log('fdfd:', cityData.city)
                        city.selectCityMethod()
                    }
                })
            },
            inputCity: function (cityID) {
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
                        console.log('fdfd:', cityData.country)
                        city.selectCountyMethod()
                    }
                })
            },
        }
    })
})()