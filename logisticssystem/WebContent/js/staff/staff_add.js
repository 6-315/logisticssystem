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
            saveStaff: function () {
                $.ajax({
                    url: '',
                    type: 'POST',
                    data: {
                        'staffBasicInfo.staff_id': staffAddData.staffBasicInfo.staff_id,
                        'staffBasicInfo.staff_num': staffAddData.staffBasicInfo.staff_num,
                        'staffBasicInfo.staff_name': staffAddData.staffBasicInfo.staff_name,
                        'staffBasicInfo.staff_password': staffAddData.staffBasicInfo.staff_password,
                        'staffBasicInfo.staff_phonenumber': staffAddData.staffBasicInfo.staff_phonenumber,
                        'staffBasicInfo.staff_birthday': $('#birthday').val(),
                        'staffBasicInfo.staff_entrytime': $('#entrytime').val(),
                        'staffBasicInfo.staff_sex': staffAddData.staffBasicInfo.staff_sex,
                        'staffBasicInfo.staff_position': staffAddData.staffBasicInfo.staff_position,
                        'staffBasicInfo.staff_recruit': staffAddData.staffBasicInfo.staff_recruit,
                        'staffBasicInfo.staff_superiorleader': staffAddData.staffBasicInfo.staff_superiorleader,
                        'staffBasicInfo.staff_createtime': staffAddData.staffBasicInfo.staff_createtime,
                        'staffBasicInfo.staff_modifytime': staffAddData.staffBasicInfo.staff_modifytime,
                        'staffBasicInfo.staff_state': staffAddData.staffBasicInfo.staff_state,
                    },
                    success: function (data) {
                        if (data === null) {
                            toastr.error('添加失败，请重新添加')
                        } else {
                            let staffInfo = JSON.parse(data)
                            console.log(staffInfo)
                        }
                    }
                })
            }
        },
        mounted() {
            // 获取单位列表
            $.ajax({
                url: '/logisticssystem/personnelmanagement/personnelmanagement_lowerUnit',
                type: 'POST',
                data: '',
                success: function (data) {
                    let uList = JSON.parse(data)
                    staffAddData.unitList = uList
                }
            })
            // 获取职位信息
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