(function () {
    const unitAddData = {
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
        transferStation: {
            unit_id: '',
            unit_num: '',
            unit_name: '',
            unit_address: '',
            unit_detailaddress: '',
            unit_type: '',
            unit_superiorunit: '',
            unit_creator: '',
            unit_state: '',
            unit_admin: '',
            unit_phonenumber: '',
            unit_createtime: '',
            unit_modifytime: ''
        },
        adminList: []
    }
    const view_unitAdd = new Vue(
        {
            el: '#unitAdd',
            data: unitAddData,
            methods: {
                openBox: function () {
                    unitAddData.isOpen = true
                    // 添加省份数据
                    $
                        .ajax({
                            url: '/logisticssystem/loginregister/loginregister_getAllProvince',
                            type: 'POST',
                            data: '',
                            success: function (data) {
                                let pro = JSON.parse(data)
                                unitAddData.province = pro
                            }
                        })
                },
                closeBox: function () {
                    unitAddData.isOpen = false
                },
                selectProvinceMethod: function () {
                    unitAddData.selectProvince = true
                    unitAddData.selectCity = false
                    unitAddData.selectCounty = false
                },
                selectCityMethod: function () {
                    unitAddData.selectProvince = false
                    unitAddData.selectCity = true
                    unitAddData.selectCounty = false
                    // 获取对应的市数据
                },
                selectCountyMethod: function () {
                    unitAddData.selectProvince = false
                    unitAddData.selectCity = false
                    unitAddData.selectCounty = true
                    // 获取对应的县数据
                },
                inputProvince: function (dat, provinceName) {
                    unitAddData.sheng = provinceName
                    // 添加市数据
                    $
                        .ajax({
                            url: '/logisticssystem/loginregister/loginregister_getAllCityByProvinceID',
                            type: 'POST',
                            data: {
                                cityFatherId: dat
                            },
                            success: function (data) {
                                let cit = JSON.parse(data)
                                unitAddData.city = cit
                                view_unitAdd.selectCityMethod()
                            }
                        })
                },
                inputCity: function (cityID, cityName) {
                    unitAddData.shi = cityName
                    // 添加县数据
                    $
                        .ajax({
                            url: '/logisticssystem/loginregister/loginregister_getAllCountryByCityID',
                            type: 'POST',
                            data: {
                                cityFatherId: cityID
                            },
                            success: function (data) {
                                let cou = JSON.parse(data)
                                unitAddData.country = cou
                                view_unitAdd.selectCountyMethod()
                            }
                        })
                },
                inputCountry: function (countryName) {
                    unitAddData.qu = countryName
                    unitAddData.transferStation.unit_address = unitAddData.sheng
                        + unitAddData.shi + unitAddData.qu
                    view_unitAdd.closeBox()
                },
                getAdmin(event) {
                    //获取对应的所有管理员
                    $.ajax({
                        url: '/logisticssystem/vehiclemanagement/vehiclemanagement_getAllManager',
                        type: 'POST',
                        data: {
                            'position': event.target.value
                        },
                        success: function (data) {
                            if (data != null) {
                                const da = JSON.parse(data)
                                unitAddData.adminList = da
                            }
                        }
                    })
                },
                addUnit() {
                    // 添加单位
                }
            }
        })
})()