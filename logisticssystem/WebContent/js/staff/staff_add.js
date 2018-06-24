(function () {
    const staffAddData = {
        unitList: [],
        positionList: [],
        staffBasicInfo: {
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
    }
    const viewStaffAdd = new Vue({
        el: '#staffAdd',
        data: staffAddData,
        methods: {

        },
        mounted() {
            //获取单位列表
            $.ajax({
                url: '/logisticssystem/personnelmanagement/personnelmanagement_lowerUnit',
                type: 'POST',
                data: '',
                success: function (data) {
                    let uList = JSON.parse(data)
                    staffAddData.unitList = uList
                }
            })
            //获取职位信息
            $.ajax({
                url: '/logisticssystem/personnelmanagement/personnelmanagement_lowerPosition',
                type: 'POST',
                data: '',
                success: function (data) {
                    let pList = JSON.parse(data)
                    staffAddData.positionList = pList
                }
            })
        }
    })
})()