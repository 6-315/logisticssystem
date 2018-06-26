(function () {
    const routeAddData = {
        routeDTO: {
            routeInfo: {
                route_id: '',
                route_num: '',
                route_creater: '',
                route_departurestation: '',
                route_terminalstation: '',
                route_state: '',
                route_createtime: '',
                route_modifytime: ''
            },
            endUnit: {
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
            beginUnit: {
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
            creator: {
                staff_id: '',
                staff_num: '',
                staff_name: '',
                staff_password: '',
                staff_phonenumber: '',
                staff_birthday: '',
                staff_entrytime: '',
                staff_state: '',
                staff_sex: '',
                staff_recruit: '',
                staff_position: '',
                staff_superiorleader: '',
                staff_unit: '',
                staff_createtime: '',
                staff_modifytime: ''
            }
        },
        unitList: []
    }
    const viewRouteData = new Vue({
        el: '#routeAdd',
        data: routeAddData,
        methods: {},
        mounted() {
            //获取后台数据
            let obj = $('#shuju').val()
            if (obj.length === 0) {
                return
            }
            $.ajax({
                url: '/logisticssystem/expressmanagement/expressmanagement_getRoute',
                type: 'POST',
                data: {
                    '': obj
                },
                success: function (data) {
                    if (data != null) {
                        let routeInfo = JSON.parse(data)
                        routeAddData.routeDTO.routeInfo.route_id = routeInfo.route_id
                        routeAddData.routeDTO.routeInfo.route_num = routeInfo.route_num
                        routeAddData.routeDTO.routeInfo.route_creater = routeInfo.route_creater
                        routeAddData.routeDTO.routeInfo.route_departurestation = routeInfo.route_departurestation
                        routeAddData.routeDTO.routeInfo.route_terminalstation = routeInfo.route_terminalstation
                        routeAddData.routeDTO.routeInfo.route_state = routeInfo.route_state
                        routeAddData.routeDTO.routeInfo.route_createtime = routeInfo.route_createtime
                        routeAddData.routeDTO.routeInfo.route_modifytime = routeInfo.route_modifytime
                    }
                }
            })
            // 获取单位信息
            if (vehicleListData.myRole == 6) {
                $.ajax({
                    url: '/logisticssystem/personnelmanagement/personnelmanagement_lowerUnit',
                    type: 'POST',
                    data: '',
                    success: function (data) {
                        let uList = JSON.parse(data)
                        routeAddData.unitList = uList.filter(u => u.unit_type == '中转站')
                    }
                })
            }

        }
    })
})()