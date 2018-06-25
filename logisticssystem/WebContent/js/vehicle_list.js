(function () {
    const vehicleListData = {
        unitList: []
    }
    const viewVehicleList = new Vue({
        el: '#vehicleList',
        data: vehicleListData,
        methods: {},
        mounted() {
            // 获取单位信息
            $.ajax({
                url: '/logisticssystem/personnelmanagement/personnelmanagement_lowerUnit',
                type: 'POST',
                data: '',
                success: function (data) {
                    let uList = JSON.parse(data)
                    vehicleListData.unitList = uList
                }
            })
            //获取车队信息
            

        }
    })
})()