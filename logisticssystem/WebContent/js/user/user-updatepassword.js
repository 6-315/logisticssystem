(function () {
    let passwordInfo = {
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
        userinfo_modifytime: '',
        view_oldpass: '',
        bl: {
            display: 'block'
        },
        no: {
            display: 'none'
        },
        oldEnity: false,
        errorPass: false,
        newPass: '',
        newConfirmPass: '',
        newEnity: false,
        newError: false,
        disabled: false
    }
    const view_password = new Vue({
        el: '#updatePassword',
        data: passwordInfo,
        methods: {
            confirmOldPassword() {
                if (passwordInfo.view_oldpass !== passwordInfo.userinfo_password) {
                    toastr.error('旧密码错误')
                    return false
                }
                return true
            },
            confirmNewPass() {
                if (passwordInfo.newPass !== passwordInfo.newConfirmPass) {
                    toastr.error('两次密码不一致')
                    return false
                }
                return true
            },
            updatePassword: function () {
                passwordInfo.disabled = true
                if (!view_password.confirmOldPassword()) {
                    toastr.error('旧密码错误')
                    return
                }
                if (!view_password.confirmNewPass()) {
                    toastr.error('两次密码不一致')
                    return
                }
                view_password.confirmNewPass()
                $.ajax({
                    url: '/logisticssystem/userinfo/userinfo_updateUserInfo',
                    type: 'POST',
                    data: {
                        'userInfo.userinfo_id': passwordInfo.userinfo_id,
                        'userInfo.userinfo_username': passwordInfo.userinfo_username,
                        'userInfo.userinfo_nickname': passwordInfo.userinfo_nickname,
                        'userInfo.userinfo_password': passwordInfo.newConfirmPass,
                        'userInfo.userinfo_sex': passwordInfo.userinfo_sex,
                        'userInfo.userinfo_phonenumber': passwordInfo.userinfo_phonenumber,
                        'userInfo.userinfo_email': passwordInfo.userinfo_email,
                        'userInfo.userinfo_mark': passwordInfo.userinfo_mark,
                        'userInfo.userinfo_lastlytime': passwordInfo.userinfo_lastlytime,
                        'userInfo.userinfo_createtime': passwordInfo.userinfo_createtime,
                        'userInfo.userinfo_modifytime': passwordInfo.userinfo_modifytime,
                    },
                    success: function (data) {
                        if (data === 'success') {
                            view_password.getSession()
                            passwordInfo.disabled = false
                            passwordInfo.view_oldpass = ''
                            passwordInfo.newPass = ''
                            passwordInfo.newConfirmPass = ''
                            toastr.success('修改成功')
                        } else if (data === 'Error') {
                            passwordInfo.disabled = false
                            toastr.errot('修改失败')
                        }
                    },
                    error: function (error) {
                        passwordInfo.disabled = false
                        toastr.errot('修改失败')
                    }
                })
            },
            getSession: function () {
                //获取数据库中用户的信息
                $.ajax({
                    url: '/logisticssystem/loginregister/loginregister_getSessionData',
                    type: 'POST',
                    data: '',
                    success: function (data) {
                        let pro = JSON.parse(data)
                        passwordInfo.userinfo_id = pro.userInfoSession.userinfo_id
                        passwordInfo.userinfo_username = pro.userInfoSession.userinfo_username
                        passwordInfo.userinfo_nickname = pro.userInfoSession.userinfo_nickname
                        passwordInfo.userinfo_password = pro.userInfoSession.userinfo_password
                        passwordInfo.userinfo_sex = pro.userInfoSession.userinfo_sex
                        passwordInfo.userinfo_phonenumber = pro.userInfoSession.userinfo_phonenumber
                        passwordInfo.userinfo_email = pro.userInfoSession.userinfo_email
                        passwordInfo.userinfo_mark = pro.userInfoSession.userinfo_mark
                        passwordInfo.userinfo_lastlytime = pro.userInfoSession.userinfo_lastlytime
                        passwordInfo.userinfo_createtime = pro.userInfoSession.userinfo_createtime
                        passwordInfo.userinfo_modifytime = pro.userInfoSession.userinfo_modifytime
                    }
                })
            }
        },
        mounted() {
            //获取数据库中用户的信息
            $.ajax({
                url: '/logisticssystem/loginregister/loginregister_getSessionData',
                type: 'POST',
                data: '',
                success: function (data) {
                    let pro = JSON.parse(data)
                    passwordInfo.userinfo_id = pro.userInfoSession.userinfo_id
                    passwordInfo.userinfo_username = pro.userInfoSession.userinfo_username
                    passwordInfo.userinfo_nickname = pro.userInfoSession.userinfo_nickname
                    passwordInfo.userinfo_password = pro.userInfoSession.userinfo_password
                    passwordInfo.userinfo_sex = pro.userInfoSession.userinfo_sex
                    passwordInfo.userinfo_phonenumber = pro.userInfoSession.userinfo_phonenumber
                    passwordInfo.userinfo_email = pro.userInfoSession.userinfo_email
                    passwordInfo.userinfo_mark = pro.userInfoSession.userinfo_mark
                    passwordInfo.userinfo_lastlytime = pro.userInfoSession.userinfo_lastlytime
                    passwordInfo.userinfo_createtime = pro.userInfoSession.userinfo_createtime
                    passwordInfo.userinfo_modifytime = pro.userInfoSession.userinfo_modifytime
                }
            })
        }
    })
})()