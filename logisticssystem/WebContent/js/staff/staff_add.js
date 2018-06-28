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
        },
        myRole: role
    }
    const viewStaffAdd = new Vue({
        el: '#staffAdd',
        data: staffAddData,
        methods: {
            saveStaff: function () {
                if (!viewStaffAdd.checkPhone(staffAddData.staffBasicInfo.staff_phonenumber)) {
                    toastr.error('请输入正确的员工联系方式')
                    return
                }
                if (!viewStaffAdd.checkRealName(staffAddData.staffBasicInfo.staff_name)) {
                    toastr.error('请输入正确的员工姓名')
                    return
                }
                /*if (staffAddData.staffBasicInfo.staff_unit == null || staffAddData.staffBasicInfo.staff_unit.trim().length > 0) {
                    toastr.error('请输入员工单位')
                    return
                }*/
                if (staffAddData.staffBasicInfo.staff_position == null || staffAddData.staffBasicInfo.staff_position.trim().length <= 0) {
                    toastr.error('请输入员工职位')
                    return
                }
                $.ajax({
                    url: '/logisticssystem/personnelmanagement/personnelmanagement_addStaff',
                    type: 'POST',
                    data: {
                        'staffBasicinfo.staff_id': staffAddData.staffBasicInfo.staff_id,
                        'staffBasicinfo.staff_num': staffAddData.staffBasicInfo.staff_num,
                        'staffBasicinfo.staff_name': staffAddData.staffBasicInfo.staff_name,
                        'staffBasicinfo.staff_password': staffAddData.staffBasicInfo.staff_password,
                        'staffBasicinfo.staff_phonenumber': staffAddData.staffBasicInfo.staff_phonenumber,
                        'staffBasicinfo.staff_birthday': $('#birthday').val(),
                        'staffBasicinfo.staff_entrytime': $('#entrytime').val(),
                        'staffBasicinfo.staff_sex': staffAddData.staffBasicInfo.staff_sex,
                        'staffBasicinfo.staff_position': staffAddData.staffBasicInfo.staff_position,
                        'staffBasicinfo.staff_recruit': staffAddData.staffBasicInfo.staff_recruit,
                        'staffBasicinfo.staff_unit': staffAddData.staffBasicInfo.staff_unit,
                        'staffBasicinfo.staff_superiorleader': staffAddData.staffBasicInfo.staff_superiorleader,
                        'staffBasicinfo.staff_createtime': staffAddData.staffBasicInfo.staff_createtime,
                        'staffBasicinfo.staff_modifytime': staffAddData.staffBasicInfo.staff_modifytime,
                        'staffBasicinfo.staff_state': staffAddData.staffBasicInfo.staff_state,
                    },
                    success: function (data) {
                        if (data === null) {
                            toastr.error('添加失败，请重新添加')
                        } else {
                            let staffInfo = JSON.parse(data)
                            staffAddData.staffBasicInfo.staff_num = staffInfo.staff_num
                            staffAddData.staffBasicInfo.staff_id = staffInfo.staff_id
                            staffAddData.staffBasicInfo.staff_name = staffInfo.staff_name
                            staffAddData.staffBasicInfo.staff_password = staffInfo.staff_password
                            staffAddData.staffBasicInfo.staff_phonenumber = staffInfo.staff_phonenumber
                            staffAddData.staffBasicInfo.staff_birthday = staffInfo.staff_birthday
                            staffAddData.staffBasicInfo.staff_entrytime = staffInfo.staff_entrytime
                            staffAddData.staffBasicInfo.staff_sex = staffInfo.staff_sex
                            staffAddData.staffBasicInfo.staff_position = staffInfo.staff_position
                            staffAddData.staffBasicInfo.staff_unit = staffInfo.staff_unit
                            staffAddData.staffBasicInfo.staff_recruit = staffInfo.staff_recruit
                            staffAddData.staffBasicInfo.staff_superiorleader = staffInfo.staff_superiorleader
                            staffAddData.staffBasicInfo.staff_createtime = staffInfo.staff_createtime
                            staffAddData.staffBasicInfo.staff_modifytime = staffInfo.staff_modifytime
                            staffAddData.staffBasicInfo.staff_state = staffInfo.staff_state
                            toastr.success('添加成功')
                        }
                    }
                })
            },
            //判断手机号码
            checkPhone(phone) {
                if (!(/^1[34578]\d{9}$/.test(phone))) {
                    return false;
                }
                return true
            },
            //判断真实姓名
            checkRealName(name) {
                if ('' === name || !(/^[\u4e00-\u9fa5]{2,4}$/.test(name))) {
                    return false
                }
                return true
            },
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
            //获取员工信息
            let obj = $('#shuju').val()
            if (obj.length === 0) {
                return
            }
            $.ajax({
                url: '/logisticssystem/personnelmanagement/personnelmanagement_getStaffManagerDTO',
                type: 'POST',
                data: {
                    'ID': obj
                },
                success: function (data) {
                    if (data != null) {
                        let staffInfo = JSON.parse(data)
                        console.log('staffInfo:', staffInfo)
                        staffAddData.staffBasicInfo.staff_num = staffInfo.staffBasicInfo.staff_num
                        staffAddData.staffBasicInfo.staff_id = staffInfo.staffBasicInfo.staff_id
                        staffAddData.staffBasicInfo.staff_name = staffInfo.staffBasicInfo.staff_name
                        staffAddData.staffBasicInfo.staff_password = staffInfo.staffBasicInfo.staff_password
                        staffAddData.staffBasicInfo.staff_phonenumber = staffInfo.staffBasicInfo.staff_phonenumber
                        staffAddData.staffBasicInfo.staff_birthday = staffInfo.staffBasicInfo.staff_birthday
                        staffAddData.staffBasicInfo.staff_entrytime = staffInfo.staffBasicInfo.staff_entrytime
                        staffAddData.staffBasicInfo.staff_sex = staffInfo.staffBasicInfo.staff_sex
                        staffAddData.staffBasicInfo.staff_position = staffInfo.position.position_id
                        staffAddData.staffBasicInfo.staff_recruit = staffInfo.staffBasicInfo.staff_recruit
                        staffAddData.staffBasicInfo.staff_unit = staffBasicinfo.unit.unit_id
                        staffAddData.staffBasicInfo.staff_superiorleader = staffInfo.staffBasicInfo.staff_superiorleader
                        staffAddData.staffBasicInfo.staff_createtime = staffInfo.staffBasicInfo.staff_createtime
                        staffAddData.staffBasicInfo.staff_modifytime = staffInfo.staffBasicInfo.staff_modifytime
                        staffAddData.staffBasicInfo.staff_state = staffInfo.staffBasicInfo.staff_state
                    }
                }
            })
        }
    })
})()