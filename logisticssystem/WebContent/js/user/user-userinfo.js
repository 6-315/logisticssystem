(function () {
    let userinfo = {
        userinfo_id: '',
        userinfo_username: '',
        userinfo_nickname: '',
        userinfo_password: '',
        userinfo_sex: '',
        userinfo_phonenumber: '',
        userinfo_email: '',
        userinfo_mark: '',
        userinfo_lastlytime: '',
        userinfo_createtime: '',
        userinfo_modifytime: ''
    }
    const vue_userinfo = new Vue({
        el: '#userinfo',
        data: userinfo,
        methods: {
            confirmUserInfo: function () {
                $('#updateUserInfo').modal()
            },
            updateUserInfo: function () {
                console.log('kaishifdfd')
                //更新用户数据
                $.ajax({
                    url: '/logisticssystem/userinfo/userinfo_updateUserInfo',
                    type: 'POST',
                    data: {
                        'userInfo.userinfo_id': userinfo.userinfo_id,
                        'userInfo.userinfo_username': userinfo.userinfo_username,
                        'userInfo.userinfo_nickname': userinfo.userinfo_nickname,
                        'userInfo.userinfo_password': userinfo.userinfo_password,
                        'userInfo.userinfo_sex': userinfo.userinfo_sex,
                        'userInfo.userinfo_phonenumber': userinfo.userinfo_phonenumber,
                        'userInfo.userinfo_email': userinfo.userinfo_email,
                        'userInfo.userinfo_mark': userinfo.userinfo_mark,
                        'userInfo.userinfo_lastlytime': userinfo.userinfo_lastlytime,
                        'userInfo.userinfo_createtime': userinfo.userinfo_createtime,
                        'userInfo.userinfo_modifytime': userinfo.userinfo_modifytime,
                    },
                    success: function (data) {
                        if (data === 'success') {
                            $('#updateUserInfo').modal('hide')
                            toastr.success('修改成功')
                        } else {
                            $('#updateUserInfo').modal('hide')
                            toastr.error('修改失败')
                        }
                    }
                })
            }
        },
        mounted() {
            //获取用户信息
            $.ajax({
                url: '/logisticssystem/loginregister/loginregister_getSessionData',
                type: 'POST',
                data: '',
                success: function (data) {
                    let pro = JSON.parse(data)
                    userinfo.userinfo_id = pro.userInfoSession.userinfo_id
                    userinfo.userinfo_username = pro.userInfoSession.userinfo_username
                    userinfo.userinfo_nickname = pro.userInfoSession.userinfo_nickname
                    userinfo.userinfo_password = pro.userInfoSession.userinfo_password
                    userinfo.userinfo_sex = pro.userInfoSession.userinfo_sex
                    userinfo.userinfo_phonenumber = pro.userInfoSession.userinfo_phonenumber
                    userinfo.userinfo_email = pro.userInfoSession.userinfo_email
                    userinfo.userinfo_mark = pro.userInfoSession.userinfo_mark
                    userinfo.userinfo_lastlytime = pro.userInfoSession.userinfo_lastlytime
                    userinfo.userinfo_createtime = pro.userInfoSession.userinfo_createtime
                    userinfo.userinfo_modifytime = pro.userInfoSession.userinfo_modifytime
                }
            })
        }

    })
})()