(function () {
    const userinfo = {
        user: '',
        password: '',
        disabled: false
    }
    const user = new Vue({
        el: '#sslogin',
        data: userinfo,
        methods: {
            login: function (event) {
                //
                userinfo.disabled = true;
                console.log('kaishi')
                //验证数据的正确性
                if ('' === userinfo.user || userinfo.user === null) {
                    userinfo.disabled = false
                    toastr.error('用户名不为空')
                    return
                }
                if ('' === userinfo.password || userinfo.password === null) {
                    userinfo.disabled = false
                    toastr.error('密码不为空')
                    return
                }
                $.ajax({
                    url: '/logisticssystem/loginregister/loginregister_login',
                    type: 'POST',
                    data: {
                        username: userinfo.user,
                        password: userinfo.password
                    },
                    success: function (data) {
                        if ("error" === data) {
                            userinfo.disabled = false
                            toastr.error("用户名或密码错误!");
                        } else {
                            switch (data) {
                                case '用户登录成功':
                                    window.location = '/logisticssystem/loginregister/loginregister_pageUser'
                                    break;
                                case '总公司管理员登录成功':
                                    console.log('denglu')
                                    window.location = '/logisticssystem/loginregister/loginregister_pageStaff'
                                    break;
                                case '中转站管理员登录成功':
                                    window.location = '/logisticssystem/loginregister/loginregister_pageStaff'
                                    break;
                                case '配送点管理员登录成功':
                                    window.location = '/logisticssystem/loginregister/loginregister_pageStaff'
                                    break;
                                case '配送员登录成功':
                                    window.location = '/logisticssystem/loginregister/loginregister_pageStaff'
                                    break;
                                case '车队队长登录成功':
                                    window.location = '/logisticssystem/loginregister/loginregister_pageStaff'
                                    break;
                                case '驾驶员登录成功':
                                    window.location = '/logisticssystem/loginregister/loginregister_pageStaff'
                                    break;
                            }
                            userinfo.disabled = false
                            return
                        }
                    }
                })
            }
        }
    })
})()