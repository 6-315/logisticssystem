(function () {
    const addressData = {
        addressList: [],
        address: {
            address_id: '',
            address_address: '',
            address_detailaddress: '',
            address_postalnumber: '',
            address_realname: '',
            address_phonenumber: '',
            address_isdefault: '',
            address_userinfo_id: '',
            address_state: '',
            address_createtime: '',
            address_modifytime: ''
        },
        deleteAddress: {
            address_id: '',
            address_address: '',
            address_detailaddress: '',
            address_postalnumber: '',
            address_realname: '',
            address_phonenumber: '',
            address_isdefault: '',
            address_userinfo_id: '',
            address_state: '',
            address_createtime: '',
            address_modifytime: ''
        },
        selectProvince: true,
        selectCity: false,
        selectCounty: false,
        bl: {
            display: 'block'
        },
        no: {
            display: 'none'
        },
        isOpen: false,
        province: [],
        city: [],
        country: [],
        sheng: '',
        shi: '',
        qu: '',
        setMoRen: false,
        disabled: false,
        deleteOn: false
    }
    const view_address = new Vue({
        el: '#useraddress',
        data: addressData,
        methods: {
            addAddressShow: function () {
                $('#addAddress').modal()
            },
            openBox: function () {
                addressData.isOpen = true
                // 添加省份数据
                $.ajax({
                    url: '/logisticssystem/loginregister/loginregister_getAllProvince',
                    type: 'POST',
                    data: '',
                    success: function (data) {
                        let pro = JSON.parse(data)
                        addressData.province = pro
                    }
                })
            },
            closeBox: function () {
                addressData.isOpen = false
            },
            selectProvinceMethod: function () {
                addressData.selectProvince = true
                addressData.selectCity = false
                addressData.selectCounty = false
            },
            selectCityMethod: function () {
                addressData.selectProvince = false
                addressData.selectCity = true
                addressData.selectCounty = false
                // 获取对应的市数据
            },
            selectCountyMethod: function () {
                addressData.selectProvince = false
                addressData.selectCity = false
                addressData.selectCounty = true
                // 获取对应的县数据
            },
            inputProvince: function (dat, provinceName) {
                addressData.sheng = provinceName
                // 添加市数据
                $.ajax({
                    url: '/logisticssystem/loginregister/loginregister_getAllCityByProvinceID',
                    type: 'POST',
                    data: {
                        cityFatherId: dat
                    },
                    success: function (data) {
                        let cit = JSON.parse(data)
                        addressData.city = cit
                        view_address.selectCityMethod()
                    }
                })
            },
            inputCity: function (cityID, cityName) {
                addressData.shi = cityName
                // 添加县数据
                $.ajax({
                    url: '/logisticssystem/loginregister/loginregister_getAllCountryByCityID',
                    type: 'POST',
                    data: {
                        cityFatherId: cityID
                    },
                    success: function (data) {
                        let cou = JSON.parse(data)
                        addressData.country = cou
                        view_address.selectCountyMethod()
                    }
                })
            },
            inputCountry: function (countryName) {
                addressData.qu = countryName
                addressData.address.address_address = addressData.sheng + addressData.shi + addressData.qu
                view_address.closeBox()
            },
            addAddress: function () {
                addressData.disabled = true
                if (addressData.setMoRen) {
                    addressData.address.address_isdefault = '是'
                } else {
                    addressData.address.address_isdefault = '否'
                }
                $.ajax({
                    url: '/logisticssystem/userinfo/userinfo_addAddress',
                    type: 'POST',
                    data: {
                        'addressNew.address_id': addressData.address.address_id,
                        'addressNew.address_address': addressData.address.address_address,
                        'addressNew.address_detailaddress': addressData.address.address_detailaddress,
                        'addressNew.address_postalnumber': addressData.address.address_postalnumber,
                        'addressNew.address_realname': addressData.address.address_realname,
                        'addressNew.address_phonenumber': addressData.address.address_phonenumber,
                        'addressNew.address_isdefault': addressData.address.address_isdefault,
                        'addressNew.address_userinfo_id': addressData.address.address_userinfo_id,
                        'addressNew.address_state': '1',
                        'addressNew.address_createtime': addressData.address.address_createtime,
                        'addressNew.address_modifytime': addressData.address.address_modifytime
                    },
                    success: function (data) {
                        addressData.disabled = false
                        if (data === 'success') {
                            $('#addAddress').modal('hide')
                            // addressData.addressList.push(addressData.address)
                            view_address.getAddress()
                            toastr.success('添加成功')
                        }
                    }
                })
            },
            openDeleteAddress: function (addre) {
                addressData.deleteAddress = addre
                $('#deleteAddress').modal()
            },
            deleteAddressMethod: function () {
                addressData.deleteOn = true
                $.ajax({
                    url: '/logisticssystem/userinfo/userinfo_addAddress',
                    type: 'POST',
                    data: {
                        'addressNew.address_id': addressData.deleteAddress.address_id,
                        'addressNew.address_address': addressData.deleteAddress.address_address,
                        'addressNew.address_detailaddress': addressData.deleteAddress.address_detailaddress,
                        'addressNew.address_postalnumber': addressData.deleteAddress.address_postalnumber,
                        'addressNew.address_realname': addressData.deleteAddress.address_realname,
                        'addressNew.address_phonenumber': addressData.deleteAddress.address_phonenumber,
                        'addressNew.address_isdefault': addressData.deleteAddress.address_isdefault,
                        'addressNew.address_userinfo_id': addressData.deleteAddress.address_userinfo_id,
                        'addressNew.address_state': '0',
                        'addressNew.address_createtime': addressData.deleteAddress.address_createtime,
                        'addressNew.address_modifytime': addressData.deleteAddress.address_modifytime
                    },
                    success: function (data) {
                        if (data === 'success') {
                            view_address.getAddress()
                            $('#deleteAddress').modal('hide')
                            addressData.deleteOn = false
                            toastr.success('删除成功')
                        } else {
                            addressData.deleteOn = false
                            $('#deleteAddress').modal('hide')
                            toastr.success('删除失败')
                        }
                    }
                })
            },
            openMorenAddress: function (morenAddre) {
                $.ajax({
                    url: '/logisticssystem/userinfo/userinfo_addAddress',
                    type: 'POST',
                    data: {
                        'addressNew.address_id': morenAddre.address_id,
                        'addressNew.address_address': morenAddre.address_address,
                        'addressNew.address_detailaddress': morenAddre.address_detailaddress,
                        'addressNew.address_postalnumber': morenAddre.address_postalnumber,
                        'addressNew.address_realname': morenAddre.address_realname,
                        'addressNew.address_phonenumber': morenAddre.address_phonenumber,
                        'addressNew.address_isdefault': '是',
                        'addressNew.address_userinfo_id': morenAddre.address_userinfo_id,
                        'addressNew.address_state': '1',
                        'addressNew.address_createtime': morenAddre.address_createtime,
                        'addressNew.address_modifytime': morenAddre.address_modifytime
                    },
                    success: function (data) {
                        if (data === 'success') {
                            view_address.getAddress()
                            toastr.success('设置成功')
                        } else {
                            toastr.success('设置失败')
                        }
                    }
                })
            },
            getAddress: function () {
                $.ajax({
                    url: '/logisticssystem/userinfo/userinfo_allAddressByUserInfo',
                    type: 'POST',
                    data: {},
                    success: function (data) {
                        let addressList = JSON.parse(data)
                        addressData.addressList = addressList
                    }
                })
            }

        },
        mounted() {
            $.ajax({
                url: '/logisticssystem/userinfo/userinfo_allAddressByUserInfo',
                type: 'POST',
                data: {},
                success: function (data) {
                    let addressList = JSON.parse(data)
                    addressData.addressList = addressList
                }
            })
        }
    })
})()