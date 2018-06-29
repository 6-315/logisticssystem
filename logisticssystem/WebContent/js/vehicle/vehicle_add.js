(function () {
    const vehicleAddData = {
        vehicleInfo: {
            vehicle_id: '',
            vehicle_num: '',
            vehicle_platenum: '',
            vehicle_standard: '',
            vehicle_state: '',
            vehicle_distribution_state: '',
            vehicle_drivingdirection: '',
            vehicle_current_weight: '',
            vehicle_express_state: '',
            vehicle_unit: '',
            vehicle_acquisitiontime: '',
            vehicle_acquisitionpeople: '',
            vehicle_team: '',
            vehicle_mark: '',
            vehicle_createtime: '',
            vehicle_modifytime: '',
        },
        isAdd: true,
        myRole: role
    }
    const viewVehicleAdd = new Vue({
        el: '#addVehicle',
        data: vehicleAddData,
        methods: {
            saveVehicle() {
                if (vehicleAddData.vehicleInfo.vehicle_standard == null || vehicleAddData.vehicleInfo.vehicle_standard.trim().length <= 0) {
                    toastr.error('请输入车辆规格')
                    return
                }
                if (vehicleAddData.vehicleInfo.vehicle_standard < 1000 || vehicleAddData.vehicleInfo.vehicle_standard > 8000) {
                    toastr.error('请输入车辆规格为1000kg-8000kg')
                    return
                }
                if (vehicleAddData.vehicleInfo.vehicle_platenum == null || vehicleAddData.vehicleInfo.vehicle_platenum.trim().length <= 0) {
                    toastr.error('请输入车辆车牌号')
                    return
                }
                if (vehicleAddData.vehicleInfo.vehicle_state == null || vehicleAddData.vehicleInfo.vehicle_state.trim().length <= 0) {
                    toastr.error('请选择车辆状态')
                    return
                }
                // 验证
                // 提交
                $.ajax({
                    url: '/logisticssystem/vehiclemanagement/vehiclemanagement_addVehicle',
                    type: 'POST',
                    dataType: 'json',
                    data: {
                        'vehicleInfo.vehicle_id': vehicleAddData.vehicleInfo.vehicle_id,
                        'vehicleInfo.vehicle_num': vehicleAddData.vehicleInfo.vehicle_num,
                        'vehicleInfo.vehicle_platenum': vehicleAddData.vehicleInfo.vehicle_platenum,
                        'vehicleInfo.vehicle_standard': vehicleAddData.vehicleInfo.vehicle_standard,
                        'vehicleInfo.vehicle_state': vehicleAddData.vehicleInfo.vehicle_state,
                        'vehicleInfo.vehicle_distribution_state': vehicleAddData.vehicleInfo.vehicle_distribution_state,
                        'vehicleInfo.vehicle_drivingdirection': vehicleAddData.vehicleInfo.vehicle_drivingdirection,
                        'vehicleInfo.vehicle_current_weight': vehicleAddData.vehicleInfo.vehicle_current_weight,
                        'vehicleInfo.vehicle_express_state': vehicleAddData.vehicleInfo.vehicle_express_state,
                        'vehicleInfo.vehicle_unit': vehicleAddData.vehicleInfo.vehicle_unit,
                        'vehicleInfo.vehicle_acquisitiontime': vehicleAddData.vehicleInfo.vehicle_acquisitiontime,
                        'vehicleInfo.vehicle_team': vehicleAddData.vehicleInfo.vehicle_team,
                        'vehicleInfo.vehicle_mark': vehicleAddData.vehicleInfo.vehicle_mark,
                        'vehicleInfo.vehicle_createtime': vehicleAddData.vehicleInfo.vehicle_createtime,
                        'vehicleInfo.vehicle_modifytime': vehicleAddData.vehicleInfo.vehicle_modifytime
                    },
                    success: function (data) {
                        if (data != null) {
                            vehicleAddData.vehicleInfo = data
                            toastr.success('添加成功')
                        } else {
                            toastr.error('添加失败')
                        }
                    }
                })
            }
        },
        mounted() {
            // 根据id获取车辆信息
            let obj = $('#shuju').val()
            if (obj.length === 0) {
                return
            }
            $.ajax({
                url: '/logisticssystem/vehiclemanagement/vehiclemanagement_getVehicleInfoById',
                type: 'POST',
                data: {
                    'idList': obj
                },
                success: function (data) {
                    if (data != null) {
                        let vehicleDa = JSON.parse(data)
                        vehicleAddData.vehicleInfo.vehicle_id = vehicleDa.vehicle_id
                        vehicleAddData.vehicleInfo.vehicle_num = vehicleDa.vehicle_num
                        vehicleAddData.vehicleInfo.vehicle_platenum = vehicleDa.vehicle_platenum
                        vehicleAddData.vehicleInfo.vehicle_standard = vehicleDa.vehicle_standard
                        vehicleAddData.vehicleInfo.vehicle_state = vehicleDa.vehicle_state
                        vehicleAddData.vehicleInfo.vehicle_distribution_state = vehicleDa.vehicle_distribution_state
                        vehicleAddData.vehicleInfo.vehicle_drivingdirection = vehicleDa.vehicle_drivingdirection
                        vehicleAddData.vehicleInfo.vehicle_current_weight = vehicleDa.vehicle_current_weight
                        vehicleAddData.vehicleInfo.vehicle_express_state = vehicleDa.vehicle_express_state
                        vehicleAddData.vehicleInfo.vehicle_unit = vehicleDa.vehicle_unit
                        vehicleAddData.vehicleInfo.vehicle_acquisitiontime = vehicleDa.vehicle_acquisitiontime
                        vehicleAddData.vehicleInfo.vehicle_acquisitionpeople = vehicleDa.vehicle_acquisitionpeople
                        vehicleAddData.vehicleInfo.vehicle_team = vehicleDa.vehicle_team
                        vehicleAddData.vehicleInfo.vehicle_mark = vehicleDa.vehicle_mark
                        vehicleAddData.vehicleInfo.vehicle_createtime = vehicleDa.vehicle_createtime
                        vehicleAddData.vehicleInfo.vehicle_modifytime = vehicleDa.vehicle_modifytime
                        vehicleAddData.isAdd = false
                    }
                }
            })
        }
    })
})()