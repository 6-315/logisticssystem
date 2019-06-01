let userInfoSession = null
let superAdminStaff = null
let transAdminStaff = null
let deliveryAdminStaff = null
let deliveryStaff = null
let teamStaff = null
let driverStaff = null
let userJson = null;
var role = 0;
(function () {
    $.ajax({
        url: '/logisticssystem/loginregister/loginregister_getSessionData',
        type: 'POST',
        data: {},
        async: false,
        success: function (data) {
            try {
                userJson = JSON.parse(data)
            } catch (e) {
                toastr.error("登录状态失效，即将退出登录")
                setTimeout(function () {
                    window.location = '/logisticssystem/index.jsp'
                }, 2000)
                return
            }
            if (userJson.userInfoSession != null) {
                userInfoSession = userJson
                shuzi = 0
            } else if (userJson.superAdminStaff != null) {
                superAdminStaff = userJson
                shuzi = 6
            } else if (userJson.transAdminStaff != null) {
                transAdminStaff = userJson
                shuzi = 5
            } else if (userJson.deliveryAdminStaff != null) {
                deliveryAdminStaff = userJson
                shuzi = 2
            } else if (userJson.deliveryStaff != null) {
                deliveryStaff = userJson
                shuzi = 1
            } else if (userJson.teamStaff != null) {
                teamStaff = userJson
                shuzi = 4
            } else if (userJson.driverStaff != null) {
                driverStaff = userJson
                shuzi = 3
            } else {
                toastr.error("登录状态失效，即将退出登录")
                setTimeout(function () {
                    window.location = '/logisticssystem/loginregister/loginregister_logoff'
                }, 2000)
                return
            }
            role = shuzi
        }
    })
})()