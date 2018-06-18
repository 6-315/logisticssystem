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
        country: []
    }
    const city = new Vue({
        el: '#reservation_express',
        data: cityData,
        methods: {
            openBox: function () {
                cityData.isOpen = true
                //添加省份数据
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
            }
        }
    })
})()