(function () {
    const userInfo = {
        userinfo_phonenumber: '',
        userinfo_email: '',
        userinfo_password: '',
        userinfo_confirmPassword: ''
    }


    const registerView = new Vue({
        el: '#register',
        data: userInfo,
        methods: {
            checkPhone(phone) {
                if (!(/^1[34578]\d{9}$/.test(phone))) {
                    return false;
                }
                return true
            },
            checkEmail(email) {
                if (!(/^[a-zA-Z\d]+([-_\.][a-zA-Z\d]+)*@[a-zA-Z\d]+\.[a-zA-Z\d]{2,4}$/.test(email))) {
                    return false
                }
                return true
            },
            checkPassword(password) {
                var patrn = /^(\w){6,20}$/;
                if (!patrn.exec(password)) return false
                return true
            },
            checkLength(string) {
                if (string.length < 8 || string.length > 16) {
                    return false
                }
                return true
            },
            password() {
                console.log('fdlk', registerView.checkPassword(userInfo.userinfo_password))
                if (!(registerView.checkPassword(userInfo.userinfo_password))) {
                    toastr.error('密码格式为6-20位字母、数字、下划线')
                    return false
                }
            },
            email() {
                console.log('fd:')
                if (!registerView.checkEmail(userInfo.userinfo_email)) {
                    toastr.error('请输入正确的邮箱')
                    return false
                }
            },
            phone() {
                if (!registerView.checkPhone(userInfo.userinfo_phonenumber)) {
                    toastr.error('请输入正确的手机号')
                    return false
                }
            },
            comparePassword: function () {
                if (userInfo.userinfo_password !== userInfo.userinfo_confirmPassword) {
                    toastr.error('两次密码不一致，请重新输入')
                    return false
                }
                return
            },
            registerUser: function () {
                if (!registerView.phone()) {
                    return
                }
                if (!registerView.email()) {
                    return
                }
                if (!registerView.password()) {
                    return
                }
                if (!registerView.comparePassword()) {
                    return
                }
                $.ajax({
                    url: '/logisticssystem/loginregister/loginregister_register',
                    type: 'POST',
                    data: {
                        'userInfo.userinfo_phonenumber': userInfo.userinfo_phonenumber,
                        'userInfo.userinfo_password': userInfo.userinfo_password
                    },
                    success: function (data) {
                        if (data === 'success') {
                            $.ajax({
                                url: '/logisticssystem/loginregister/loginregister_login',
                                type: 'POST',
                                data: {
                                    username: userInfo.userinfo_phonenumber,
                                    password: userInfo.userinfo_password
                                },
                                success: function (data) {
                                    userInfo.userinfo_phonenumber = ''
                                    userInfo.userinfo_password = ''
                                    userInfo.userinfo_email = ''
                                    userInfo.userinfo_confirmPassword = ''

                                    window.location = '/logisticssystem/loginregister/loginregister_pageUser'
                                }
                            })
                        } else {
                            toastr.error('注册失败，请填写正确的数据')
                        }
                    }
                })
            }
        }
    })
})()