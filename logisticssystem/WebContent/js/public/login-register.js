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
                        	
                        }
					}
				})
			}
		}
	})
})()