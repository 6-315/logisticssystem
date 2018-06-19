(function () {
    console.log('fdfd:', userInfoSession)
    let reservationData = {
        userinfo_id: userInfoSession.userinfo_id,
        userinfo_username: userInfoSession.userinfo_username,
        userinfo_nickname: userInfoSession.userinfo_nickname,
        userinfo_phonenumber: userInfoSession.userinfo_phonenumber,
        userinfo_email: userInfoSession.userinfo_email,
        userinfo_mark: userInfoSession.userinfo_mark
    }
    const reservationList = new Vue({
        el: '#userinfo',
        data: reservationData,
        methods: {

        }
    })
})()
