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
            comparePassword: function () {
                if (userInfo.userinfo_password !== userInfo.userinfo_confirmPassword) {
                    toastr.error('两次密码不一致，请重新输入')
                }
                return
            },
            registerUser: function () {
                registerView.comparePassword()
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

                        }
                    }
                })
            }
        }
    })
})()