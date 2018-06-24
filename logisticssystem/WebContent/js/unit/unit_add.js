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
                    $.ajax({
                        url: '/logisticssystem/transferstation/transferstation_addTransferStation',
                        type: 'POST',
                        data: {
                            'transferStation.unit_id': unitAddData.transferStation.unit_id,
                            'transferStation.unit_num': unitAddData.transferStation.unit_num,
                            'transferStation.unit_name': unitAddData.transferStation.unit_name,
                            'transferStation.unit_address': unitAddData.transferStation.unit_address,
                            'transferStation.unit_detailaddress': unitAddData.transferStation.unit_detailaddress,
                            'transferStation.unit_type': unitAddData.transferStation.unit_type,
                            'transferStation.unit_superiorunit': unitAddData.transferStation.unit_superiorunit,
                            'transferStation.unit_creator': unitAddData.transferStation.unit_creator,
                            'transferStation.unit_state': unitAddData.transferStation.unit_state,
                            'transferStation.unit_admin': unitAddData.transferStation.unit_admin,
                            'transferStation.unit_phonenumber': unitAddData.transferStation.unit_phonenumber,
                            'transferStation.unit_createtime': unitAddData.transferStation.unit_createtime,
                            'transferStation.unit_modifytime': unitAddData.transferStation.unit_modifytime
                        },
                        success: function (data) {
                            if (data === null) {
                                toastr.error('添加失败')
                            } else {
                                let transferStation = JSON.parse(data)
                                unitAddData.transferStation.unit_id = transferStation.unit_id
                                unitAddData.transferStation.unit_num = transferStation.unit_num
                                unitAddData.transferStation.unit_name = transferStation.unit_name
                                unitAddData.transferStation.unit_address = transferStation.unit_address
                                unitAddData.transferStation.unit_detailaddress = transferStation.unit_detailaddress
                                unitAddData.transferStation.unit_type = transferStation.unit_type
                                unitAddData.transferStation.unit_superiorunit = transferStation.unit_superiorunit
                                unitAddData.transferStation.unit_creator = transferStation.unit_creator
                                unitAddData.transferStation.unit_state = transferStation.unit_state
                                unitAddData.transferStation.unit_admin = transferStation.unit_admin
                                unitAddData.transferStation.unit_phonenumber = transferStation.unit_phonenumber
                                unitAddData.transferStation.unit_createtime = transferStation.unit_createtime
                                unitAddData.transferStation.unit_modifytime = transferStation.unit_modifytime
                                toastr.success('添加成功')
                            }
                        }
                    })
                }
            },
            mounted() {
                //获取后台数据
                let obj = $('#shuju').val()
                if (obj.length === 0) {
                    return
                }
                $.ajax({
                    url: '/logisticssystem/transferstation/transferstation_getUnitAdmin',
                    type: 'POST',
                    data: {
                        'transferStation.unit_id': obj
                    },
                    success: function (data) {
                        if (data != null) {
                            let transferStation = JSON.parse(data)
                            console.log('fdfd', data)
                            unitAddData.transferStation.unit_id = transferStation.unit.unit_id
                            unitAddData.transferStation.unit_num = transferStation.unit.unit_num
                            unitAddData.transferStation.unit_name = transferStation.unit.unit_name
                            unitAddData.transferStation.unit_address = transferStation.unit.unit_address
                            unitAddData.transferStation.unit_detailaddress = transferStation.unit.unit_detailaddress
                            unitAddData.transferStation.unit_type = transferStation.unit.unit_type
                            unitAddData.transferStation.unit_superiorunit = transferStation.unit.unit_superiorunit
                            unitAddData.transferStation.unit_creator = transferStation.unit.unit_creator
                            unitAddData.transferStation.unit_state = transferStation.unit.unit_state
                            unitAddData.transferStation.unit_admin = transferStation.unit.unit_admin
                            unitAddData.transferStation.unit_phonenumber = transferStation.unit.unit_phonenumber
                            unitAddData.transferStation.unit_createtime = transferStation.unit.unit_createtime
                            unitAddData.transferStation.unit_modifytime = transferStation.unit.unit_modifytime
                        }
                    }
                })
            }
        })
})()