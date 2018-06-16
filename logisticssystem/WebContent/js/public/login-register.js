(function() {
	const userinfo = null
	const user = new Vue({
		el : '#sslogin',
		data : {
			user : '',
			password : ''
		},
		methods : {
			login : function(event) {
				$.ajax({
					url : '/logisticssystem/loginregister/loginregister_login',
					type : 'POST',
					data : {
						username : this.user,
						password : this.password
					},
					success : function(data) {
						if("error" === data){
                        	toastr.error("用户名或密码错误!");
                        }else{
							switch (data){
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
                        	return
                        }
					}
				})
			}
		}
	})
})()