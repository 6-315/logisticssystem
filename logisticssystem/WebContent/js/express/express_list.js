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
        ExpressRouteDTO: {
            listRouteDTO: [],
            currentUnit: ''
        }
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
                   <select @change="nextNode" class="form-control">
                      <option v-for="listRoute in expressL.listRouteDTO" v-if="listRoute.direction == '正向'" :value="listRoute.endUnit.unit_id">{{listRoute.endUnit.unit_name}}</option>
                      <option v-for="listRoute in expressL.listRouteDTO" v-if="listRoute.direction == '反向'" :value="listRoute.endUnit.unit_id">{{listRoute.beginUnit.unit_name}}</option>
                   </select>
                   </td>
                   </tr></tbody></table>`,
        data(){
            return{

            }
        },
        props: ['expresslistr'],
        methods: {
            nextNode($event){
                this.$emit('getroute',$event.target.value);
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
            //分页-上一页
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
            //下一页
            nextPage: function () {
                if (expressData.nextDisabled) {
                    return
                }
                expressData.page = expressData.expressInfoVO.pageIndex + 1
                express_view.getAllData()
                express_view.judge()
            },
            //首页
            shouye: function () {
                expressData.page = 1
                express_view.getAllData()
                express_view.judge()
            },
            //尾页
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
                        if (data === 'success') {
                            express_view.getAllData()
                            express_view.judge()
                            toastr.success('到站成功')
                        }
                    }
                })
            },
            jinCangSaoMiao: function (expressId, unitId) {
                expressData.tmpExpressId = expressId
                $.ajax({
                    url: '/logisticssystem/expressmanagement/expressmanagement_judgeExpressType',
                    type: 'POST',
                    data: {
                        'expressInfo.express_id': expressId
                    },
                    success: function (data) {
                        data = 'begin'
                        if (data === 'error') {
                            toastr.error('扫描失败')
                            return
                        } else if (data === 'begin') {
                            express_view.getRoute(unitId)
                            $('#expressRoute').modal()
                            //弹出模态框选择地址
                            // toastr.error('begin')
                        } else if (data === 'trans') {
                            //中转站
                            toastr.error('trans')
                        } else if (data === 'end') {
                            //终点站
                            toastr.error('end')
                        }
                    }
                })
            },
            /*productExpressRoute: function () {
                const routeRow = `
                                 <tr>
                                    <td>${expressData.expressListR.length + 1}</td> 
                                    <td>${ expressData.ExpressRouteDTO.currentUnit.unit_name }</td>
                                    <td>
                                        <select name="route" class="form-control">
                                            <option v-if="listRoute.direction == '正向'" value="" v-for="listRoute in expressData.ExpressRouteDTO.listRouteDTO" :key="listRoute.routeInfo.route_id">{{listRoute.endUnit.unit_name}}}</option>    
                                            <option v-if="listRoute.direction == '反向'" value="" v-for="listRoute in expressData.ExpressRouteDTO.listRouteDTO" :key="listRoute.routeInfo.route_id">{{listRoute.beginUnit.unit_name}}}</option>    
                                        </select>
                                    </td>
                                 </tr>
                                 `
                console.log('hjeihei:', routeRow)
                expressListR.push(routeRow)
            },*/
            //单位id
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
                            expressData.ExpressRouteDTO.listRouteDTO = expressRouteDTO.listRouteDTO
                            expressData.ExpressRouteDTO.currentUnit = expressRouteDTO.currentUnit
                            expressData.expressListR.push(expressData.ExpressRouteDTO);
                            /*express_view.productExpressRoute()*/
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
        },
        components: {
            "tr-com": trCom
        }
    })
})()