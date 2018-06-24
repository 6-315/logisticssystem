(function () {
    const expressData = {
        unitList: [],
        expressInfoVO: {
            ExpressInfoDTO: [],
            search: '',
            state: '',
            unit: '',
            isDistributedDistribution: '',
            isDistributedDistributor: '',
            pageIndex: '',
            totalRecords: '',
            pageSize: '',
            totalPages: '',
            HavePrePage: '',
            HaveNextPage: '',
        },
        search: '',
        page: 1,
        state: '',
        unit: '',
        isDistributedDistribution: '',
        isDistributedDistributor: '',
        ready: false,
        preDisabled: false,
        nextDisabled: false,
        checkData: false,
        expressRoute: '',
        expressListR: [],
        tmpExpressId: '',
        tmpVehicleExpressId: '',
        tmpReserExpressId: '',
        ExpressRouteDTO: {
            listRouteDTO: [],
            currentUnit: ''
        },
        lastAddress: '',
        routeDirectionArr: [],
        vehicleList: [],
        reserList: [],
        paiSongYuanList: [],
        psiSongExpressId: ''
    }


    let trCom = Vue.extend({
        template: `<table class="table table-hover">
                   <thead>
                   <tr>
                   <th>路线</th>
                   <th>始发站</th>
                   <th>中转站</th>
                   </tr>
                   </thead><tbody>
                   <tr v-for="(expressL,index) in expresslistr" :key="index">
                   <td>{{index+1}}</td> 
                   <td>{{expressL.currentUnit.unit_name}}</td>
                   <td>
                   <select :disabled="index+1!=expresslistr.length" @change="nextNode" class="form-control">
                      <option value="-1">请选择</option>
                      <option v-for="listRoute in expressL.listRouteDTO" v-if="listRoute.direction == '正向'" :address="listRoute.endUnit.unit_address" :value1="listRoute.direction" :value2="listRoute.routeInfo.route_id" :value="listRoute.endUnit.unit_id">{{listRoute.endUnit.unit_name}}</option>
                      <option v-for="listRoute in expressL.listRouteDTO" v-if="listRoute.direction == '反向'" :address="listRoute.beginUnit.unit_address" :value1="listRoute.direction" :value2="listRoute.routeInfo.route_id"  :value="listRoute.beginUnit.unit_id">{{listRoute.beginUnit.unit_name}}</option>
                   </select>
                   </td>
                   </tr></tbody></table>`,
        data() {
            return {}
        },
        props: ['expresslistr', 'lastaddress'],
        methods: {
            nextNode($event) {
                var op = $($event.target.selectedOptions[0]);
                let a_a = {
                    route_id: op.attr("value2"),
                    direction: op.attr("value1")
                }
                this.$emit('pushroute', a_a);
                if (this.lastaddress == $($event.target.selectedOptions[0]).attr("address")) {
                    $($event.target).attr("disabled", true);
                } else {
                    this.$emit('getroute', $event.target.value);
                }
            },
        },
    })


    const express_view = new Vue({
        el: '#expressList',
        data: expressData,
        methods: {
            getAllData: function () {
                $.ajax({
                    url: '/logisticssystem/expressmanagement/expressmanagement_queryExpressInfo',
                    type: 'POST',
                    data: {
                        'search': expressData.search,
                        'page': expressData.page,
                        'state': expressData.state,
                        'unit': expressData.unit,
                        'isDistributedDistribution': expressData.isDistributedDistribution,
                        'isDistributedDistributor': expressData.isDistributedDistributor
                    },
                    success: function (data) {
                        let expressInfo = JSON.parse(data)
                        expressData.expressInfoVO.ExpressInfoDTO = expressInfo.listExpressInfoDTO
                        expressData.expressInfoVO.pageIndex = expressInfo.pageIndex
                        expressData.expressInfoVO.totalRecords = expressInfo.totalRecords
                        expressData.expressInfoVO.pageSize = expressInfo.pageSize
                        expressData.expressInfoVO.totalPages = expressInfo.totalPages
                        expressData.expressInfoVO.HavePrePage = expressInfo.HavePrePage
                        expressData.expressInfoVO.HaveNextPage = expressInfo.HaveNextPage
                        expressData.expressInfoVO.search = expressInfo.search
                        expressData.expressInfoVO.state = expressInfo.state
                        expressData.expressInfoVO.unit = expressInfo.unit
                        expressData.ready = true
                    }
                })
            },
            // 分页-上一页
            prePage: function () {
                if (expressData.preDisabled) {
                    return
                }
                expressData.page = expressData.expressInfoVO.pageIndex - 1
                express_view.getAllData()
                express_view.judge()
            },
            judge: function () {
                expressData.preDisabled = false
                expressData.nextDisabled = false
                if (expressData.expressInfoVO.pageIndex === 1) {
                    expressData.preDisabled = true
                }
                if (expressData.expressInfoVO.pageIndex === expressData.expressInfoVO.totalPages) {
                    expressData.nextDisabled = true
                }
            },
            // 下一页
            nextPage: function () {
                if (expressData.nextDisabled) {
                    return
                }
                expressData.page = expressData.expressInfoVO.pageIndex + 1
                express_view.getAllData()
                express_view.judge()
            },
            // 首页
            shouye: function () {
                expressData.page = 1
                express_view.getAllData()
                express_view.judge()
            },
            // 尾页
            weiye: function () {
                expressData.page = reservationData.expressInfoVO.totalPages
                express_view.getAllData()
                express_view.judge()
            },
            //
            selectUnit: function (selectUnitId) {
                expressData.unit = selectUnitId
                express_view.getAllData()
                express_view.judge()
            },
            isFenPeiSongDian: function (isDistributedDistribution) {
                expressData.isDistributedDistribution = isDistributedDistribution
                express_view.getAllData()
                express_view.judge()
            },
            isFenPeiSongYuan: function (isDistributedDistributor) {
                expressData.isDistributedDistributor = isDistributedDistributor
                express_view.getAllData()
                express_view.judge()
            },
            selectState: function (selectState) {
                expressData.state = selectState
                express_view.getAllData()
                express_view.judge()
            },
            selectSearch: function () {
                express_view.getAllData()
                express_view.judge()
            },
            checkAll: function () {
                if (expressData.checkData) {
                    $("input[name='flag']:checkbox").each(function () {
                        $(this).attr("checked", false)
                    })
                } else {
                    $("input[name='flag']:checkbox").each(function () {
                        $(this).attr("checked", true)
                    })
                }
            },
            expressAddJ: function () {
                $('#expressAdd').modal()
            },
            daozhan: function () {
                let dataDa = ''
                $("input[name='flag']:checkbox").each(function () {
                    if ($(this).is(':checked')) {
                        dataDa = dataDa + $(this).attr('id') + ','
                    }
                })
                console.log('dataDa:', dataDa)
                $.ajax({
                    url: '/logisticssystem/expressmanagement2/expressmanagement2_updateNotScan',
                    type: 'POST',
                    data: {
                        'listExpressId': dataDa
                    },
                    success: function (data) {
                        console.log('data:', data)
                        if (data === 'succcess') {
                            $('#expressAdd').modal('hide')
                            express_view.getAllData()
                            express_view.judge()
                            toastr.success('到站成功')
                        } else {
                            toastr.error('系统错误，到站失败')
                        }
                    }
                })
            },
            jinCangSaoMiao: function (address, expressId, unitId) {
                expressData.lastAddress = address;
                expressData.tmpExpressId = expressId
                $.ajax({
                    url: '/logisticssystem/expressmanagement/expressmanagement_judgeExpressType',
                    type: 'POST',
                    data: {
                        'expressInfo.express_id': expressId
                    },
                    success: function (data) {
                        if (data === 'error') {
                            toastr.error('扫描失败')
                            return
                        } else if (data === 'begin') {
                            express_view.getRoute(unitId)
                            $('#expressRoute').modal()
                            // 弹出模态框选择地址
                            // toastr.error('begin')
                        } else if (data === 'trans') {
                            express_view.compliteSaoMiao()
                        } else if (data === 'end') {
                            express_view.compliteSaoMiao()
                        }
                    }
                })
            },
            getRoute: function (unitId) {
                $.ajax({
                    url: '/logisticssystem/expressmanagement/expressmanagement_queryAllRouteWithUnit',
                    type: 'POST',
                    data: {
                        'unitInfo.unit_id': unitId
                    },
                    success: function (data) {
                        if (data === null) {
                            toastr.error('系统错误，没有路线')
                        } else {
                            let expressRouteDTO = JSON.parse(data);
                            expressData.expressListR.push(expressRouteDTO);
                        }
                    }
                })
            },
            pushRoute(routeDirection) {
                expressData.routeDirectionArr.push(routeDirection);
            },
            saveExpressRoute: function () {
                // 分割
                let id_directionList = ''
                for (let i = 0; i < expressData.routeDirectionArr.length; i++) {
                    id_directionList = id_directionList + (expressData.routeDirectionArr[i].route_id + '_' + expressData.routeDirectionArr[i].direction) + ','
                }
                // 保存
                $.ajax({
                    url: '/logisticssystem/expressmanagement/expressmanagement_saveExpressRoute',
                    type: 'POST',
                    data: {
                        'id_directionList': id_directionList,
                        'expressInfo.express_id': expressData.tmpExpressId
                    },
                    success: function (data) {
                        if (data === 'success') {
                            $('#expressRoute').modal('hide')
                            express_view.compliteSaoMiao()
                            // expressData.tmpExpressId = ''
                        } else {
                            $('#expressRoute').modal('hide')
                            toastr.error('扫描失败')
                        }
                    }
                })
            },
            compliteSaoMiao() {
                $.ajax({
                    url: '/logisticssystem/expressmanagement/expressmanagement_updateVehicleAndExpressCirculationAndExpressInfo',
                    type: 'POST',
                    data: {
                        'expressAndCirculationDTO.expressInfo.express_id': expressData.tmpExpressId
                    },
                    success: function (data) {
                        if (data === 'success') {
                            express_view.getAllData()
                            express_view.judge()
                            toastr.success('扫描成功')
                        } else {
                            toastr.error('扫描失败')
                        }
                    }
                })
            },
            scanVehicle(expressId) {
                expressData.tmpVehicleExpressId = expressId
                // 获取车辆信息
                $.ajax({
                    url: '/logisticssystem/expressmanagement2/expressmanagement2_getVehicleByID',
                    type: 'POST',
                    data: {
                        'expressNew.express_id': expressData.tmpVehicleExpressId
                    },
                    success: function (data) {
                        if (data === null) {
                            toastr.error('系统错误，获取车辆失败')
                        } else {
                            let listVehicle = JSON.parse(data)
                            console.log('list:', listVehicle)
                            expressData.vehicleList = listVehicle
                            $('#expressVehicle').modal()
                        }
                    }
                })

            },
            loadCar(vehicleId) {
                $.ajax({
                    url: '/logisticssystem/expressmanagement2/expressmanagement2_getVehicleIsOverWeight',
                    type: 'POST',
                    data: {
                        'getWeightDTO.expressNew.express_id': expressData.tmpVehicleExpressId,
                        'getWeightDTO.vehicleNew.vehicle_id': vehicleId
                    },
                    success: function (data) {
                        if (data === 'error') {
                            toastr.error('分配失败')
                        } else if (data === 'success') {
                            $('#expressVehicle').modal('hide')
                            express_view.getAllData()
                            express_view.judge()
                            toastr.success('分配成功')
                        } else if (data === 'overweight') {
                            toastr.error('车辆超重')
                        }
                    }
                })
            },
            distributionExpressToReser(expressId) {
                expressData.tmpReserExpressId = expressId
                // 获取配送点信息
                $.ajax({
                    url: '/logisticssystem/expressmanagement2/expressmanagement2_getDistributionBySession',
                    type: 'POST',
                    data: '',
                    success: function (data) {
                        if (data === null) {
                            toastr.error('系统错误，获取配送点失败')
                        } else {
                            let listReser = JSON.parse(data)
                            console.log('list:', listReser)
                            expressData.reserList = listReser
                            $('#expressReser').modal()
                        }
                    }
                })
            },
            selectDistribution(unitId) {
                // 分配给配送点
                $.ajax({
                    url: '/logisticssystem/expressmanagement2/expressmanagement2_chooseDistribution',
                    type: 'POST',
                    data: {
                        'expressNew.express_id': expressData.tmpReserExpressId,
                        'unitNew.unit_id': unitId
                    },
                    success: function (data) {
                        if (data === 'error') {
                            toastr.error('分配失败')
                        } else if (data === 'success') {
                            express_view.getAllData()
                            express_view.judge()
                            $('#expressReser').modal('hide')
                            toastr.success('分配成功')
                        }
                    }
                })
            },
            distribuStaff(staffExpressId) {
                // 获取配送员列表
                expressData.psiSongExpressId = staffExpressId
                $.ajax({
                    url: '/logisticssystem/expressmanagement2/expressmanagement2_getDispatcher',
                    type: 'POST',
                    data: '',
                    success: function (data) {
                        if (data === null) {
                            toastr.error('系统错误，配送点为空失败')
                        } else {
                            let listDis = JSON.parse(data)
                            console.log('list:', listDis)
                            expressData.paiSongYuanList = listDis
                            $('#peiSongYuan').modal()
                        }
                    }
                })
            },
            paiSongStaff(staffPeiSongId) {
                //分配快件给配送员
                $.ajax({
                    url: '/logisticssystem/expressmanagement2/expressmanagement2_updateExpressState',
                    type: 'POST',
                    data: {
                        'getExpressAndDispatcherDTO.expressNew.express_id': expressData.psiSongExpressId,
                        'getExpressAndDispatcherDTO.staffBasicInfo.staff_id': staffPeiSongId
                    },
                    success: function (data) {
                        if (data === 'error') {
                            toastr.error('分配失败')
                        } else if (data === 'success') {
                            express_view.getAllData()
                            express_view.judge()
                            $('#peiSongYuan').modal('hide')
                            toastr.success('分配成功')
                        }
                    }
                })
            },
            qianShouExpress(expressId) {
                //签收
                $.ajax({
                    url: '/logisticssystem/expressmanagement2/expressmanagement2_updateExpressStateByExpressId',
                    type: 'POST',
                    data: {
                        'expressNew.express_id': expressId,
                        'expressState': '已签收'
                    },
                    success: function (data) {
                        if (data === 'error') {
                            toastr.error('系统错误，签收失败')
                        } else if (data === 'success') {
                            express_view.getAllData()
                            express_view.judge()
                            toastr.success('签收成功')
                        }
                    }
                })
            },
            completeExpress(expressId) {
                //完成
                $.ajax({
                    url: '/logisticssystem/expressmanagement2/expressmanagement2_updateExpressStateByExpressId',
                    type: 'POST',
                    data: {
                        'expressNew.express_id': expressId,
                        'expressState': '已完成'
                    },
                    success: function (data) {
                        if (data === 'error') {
                            toastr.error('系统错误，完成失败')
                        } else if (data === 'success') {
                            express_view.getAllData()
                            express_view.judge()
                            toastr.success('完成成功')
                        }
                    }
                })
            },
            expressFaVehicle: function () {
                $.ajax({
                    url: '/logisticssystem/expressmanagement2/expressmanagement2_updateStateByDriver',
                    type: 'POST',
                    data: '',
                    success: function (data) {
                        if (data === 'success') {
                            toastr.success('发车成功')
                        } else {
                            toastr.error('发车失败')
                        }
                    }
                })
            }
        },
        mounted() {
            // 获取单位信息
            $.ajax({
                url: '/logisticssystem/personnelmanagement/personnelmanagement_lowerUnit',
                type: 'POST',
                data: '',
                success: function (data) {
                    let uList = JSON.parse(data)
                    expressData.unitList = uList
                }
            })
            $.ajax({
                url: '/logisticssystem/expressmanagement/expressmanagement_queryExpressInfo',
                type: 'POST',
                data: '',
                success: function (data) {
                    const expressInfo = JSON.parse(data)
                    expressData.expressInfoVO.ExpressInfoDTO = expressInfo.listExpressInfoDTO
                    expressData.expressInfoVO.pageIndex = expressInfo.pageIndex
                    expressData.expressInfoVO.totalRecords = expressInfo.totalRecords
                    expressData.expressInfoVO.pageSize = expressInfo.pageSize
                    expressData.expressInfoVO.totalPages = expressInfo.totalPages
                    expressData.expressInfoVO.HavePrePage = expressInfo.HavePrePage
                    expressData.expressInfoVO.HaveNextPage = expressInfo.HaveNextPage
                    expressData.expressInfoVO.search = expressInfo.search
                    expressData.expressInfoVO.state = expressInfo.state
                    expressData.expressInfoVO.unit = expressInfo.unit
                    expressData.ready = true
                    express_view.judge()
                }
            })
            $('#expressRoute').on('hidden.bs.modal', function (e) {
                expressData.expressListR = [];
                expressData.routeDirectionArr = [];
            })
        },
        components: {
            "tr-com": trCom
        }
    })
})()