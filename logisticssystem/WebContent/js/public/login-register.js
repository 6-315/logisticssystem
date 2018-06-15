(function () {
    const userinfo = null
    const user = new Vue({
        el: '#sslogin',
        data: {
            user: '',
            password: ''
        },
        methods: {
            login: function (event) {
                axios.post('${pagecontext.request.contextPath}/loginregister/loginregister_login', {
                    username: this.user,
                    password: this.password
                })
                    .then(function (response) {
                        
                    })
                    .catch(function (error) {
                        console.log(error);
                    });
            }
        }
    })
})()