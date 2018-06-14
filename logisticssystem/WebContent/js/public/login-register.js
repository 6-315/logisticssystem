(function () {
    const userinfo = null
    const userinfo_Vue = new Vue({
        el: '#login',
        data: {
            name: '',
            password: ''
        },
        methods: {
            login: function () {
                axios.post('/logisticssystem/loginregister/loginregister_login',{
                    usrname:this.name,
                    password:this.password
                })
                    .then(function(response){
                        console.log(response);
                    })
                    .catch(function(error){
                        console.log(error);
                    });
            }
        }
    })
})()