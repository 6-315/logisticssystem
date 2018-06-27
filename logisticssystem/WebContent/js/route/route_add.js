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
            }
        },
        unitList: [],
        myRole: role
    }
    const viewRouteData = new Vue({
        el: '#routeassd',
        data: routeAddData,
        methods: {
            saveRoute: function () {
                //保存路线
                $.ajax({
                    url: '/logisticssystem/routemanagement/routemanagement_addRoute',
                    type: 'POST',
                    data: {
                        'rout.route_id': routeAddData.routeDTO.routeInfo.route_id,
                        'rout.route_num': routeAddData.routeDTO.routeInfo.route_num,
                        'rout.route_creater': routeAddData.routeDTO.routeInfo.route_creater,
                        'rout.route_departurestation': routeAddData.routeDTO.routeInfo.route_departurestation,
                        'rout.route_terminalstation': routeAddData.routeDTO.routeInfo.route_terminalstation,
                        'rout.route_state': routeAddData.routeDTO.routeInfo.route_state,
                        'rout.route_createtime': routeAddData.routeDTO.routeInfo.route_createtime,
                        'rout.route_modifytime': routeAddData.routeDTO.routeInfo.route_modifytime
                    },
                    success: function (data) {
                        if (data == null) {
                            toastr.error('保存失败')
                        } else {
                            const da = JSON.parse(data)
                            routeAddData.routeDTO.routeInfo.route_id = da.route_id
                            routeAddData.routeDTO.routeInfo.route_num = da.route_num
                            routeAddData.routeDTO.routeInfo.route_creater = da.route_creater
                            routeAddData.routeDTO.routeInfo.route_departurestation = da.route_departurestation
                            routeAddData.routeDTO.routeInfo.route_terminalstation = da.route_terminalstation
                            routeAddData.routeDTO.routeInfo.route_state = da.route_state
                            routeAddData.routeDTO.routeInfo.route_createtime = da.route_createtime
                            routeAddData.routeDTO.routeInfo.route_modifytime = da.route_modifytime
                            toastr.success('保存成功,编号为:' + routeAddData.routeDTO.routeInfo.route_num)
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
                url: '/logisticssystem/expressmanagement/expressmanagement_getRouteInfo',
                type: 'POST',
                data: {
                    'idList': obj
                },
                success: function (data) {
                    if (data != null) {
                        let da = JSON.parse(data)
                        routeAddData.routeDTO.routeInfo.route_id = da.routeInfo.route_id
                        routeAddData.routeDTO.routeInfo.route_num = da.routeInfo.route_num
                        routeAddData.routeDTO.routeInfo.route_creater = da.routeInfo.route_creater
                        routeAddData.routeDTO.routeInfo.route_departurestation = da.routeInfo.route_departurestation
                        routeAddData.routeDTO.routeInfo.route_terminalstation = da.routeInfo.route_terminalstation
                        routeAddData.routeDTO.routeInfo.route_state = da.routeInfo.route_state
                        routeAddData.routeDTO.routeInfo.route_createtime = da.routeInfo.route_createtime
                        routeAddData.routeDTO.routeInfo.route_modifytime = da.routeInfo.route_modifytime
                    }
                }
            })
            $.ajax({})
            // 获取单位信息
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
    })
})()