let userInfoSession = null
let superAdminStaff = null
let transAdminStaff = null
let deliveryAdminStaff = null
let deliveryStaff = null
let teamStaff = null
let driverStaff = null
let userJson = null;
(function () {
    $.ajax({
        url: '/logisticssystem/loginregister/loginregister_getSessionData',
        type: 'POST',
        data: {},
        success: function (data) {
            try {
                userJson = JSON.parse(data)
            } catch (e) {
                toastr.error("登录状态失效，即将退出登录")
                setTimeout(function () {
                    window.location = '/logisticssystem/index.jsp'
                }, 3000)
                return
            }
            if (userJson.userInfoSession != null) {
                userInfoSession = userJson
            } else if (userJson.superAdminStaff != null) {
                superAdminStaff = userJson
            } else if (userJson.transAdminStaff != null) {
                transAdminStaff = userJson
            } else if (userJson.deliveryAdminStaff != null) {
                deliveryAdminStaff = userJson
            } else if (userJson.deliveryStaff != null) {
                deliveryStaff = userJson
            } else if (userJson.teamStaff != null) {
                teamStaff = userJson
            } else if (userJson.driverStaff != null) {
                driverStaff = userJson
            } else {
                setTimeout(function () {
                    window.location = '/logisticssystem/index.jsp'
                }, 3000)
                return
            }
        },
        error: function (e) {
            console.log('fdfdaaaaa:', e)
        }
    })
})()