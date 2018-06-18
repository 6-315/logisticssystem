(function() {
	const userinfo = null
	const user = new Vue({
		el : '#sslogin',
		data : {
			user : '',
			password : '',
            disabled: false
		},
		methods : {
			login : function(event) {
				//
				this.disabled = true;
				//验证数据的正确性
				if('' === this.user || this.user === null){
					toastr.error('用户名不为空')
					this.disabled = false
					return
				}
				if('' === this.password || this.password === null){
					toastr.error('密码不为空')
					this.disabled = false
					return
				}
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
							this.disabled = false
                        	return
                        }
					}
				})
			}
		}
	})
})()