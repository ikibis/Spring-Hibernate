function getUserAds() {
    $.ajax({
        url: '/my_ads_servlet',
        method: 'GET',
        data: {
            user_id: sessionStorage.getItem("id")
        },
        complete: function (response) {
            let list = JSON.parse(response.responseText);
            $("#user_ads td").parent().remove();
            for (let i = 0; i < list.length; i++) {
                $('#user_ads tr:last').after(
                    '<tr>' +
                    '<td>' + list[i].car.brand + '</td>' +
                    '<td>' + list[i].car.model + '</td>' +
                    '<td>' + list[i].year + '</td>' +
                    '<td>' + list[i].mileage + '</td>' +
                    '<td>' + list[i].status + '</td>' +
                    '<td>'
                    + "<div class=\"btn-group\">"
                    + "<button type=\"button\" class=\"btn btn-danger\" onclick=\"GoToEditAd(" + list[i].id + ")\">Edit</button>");
                if(list[i].status == 'ACTIVE') {
                    $('#user_ads button:last').after(
                        "<button type=\"button\" class=\"btn btn-danger\" onclick=\"closeAd(" + list[i].id + ")\">Close Ad</button>");
                } else {
                    $('#user_ads button:last').after(
                        "<button type=\"button\" class=\"btn btn-danger\" onclick=\"activateAd(" + list[i].id + ")\">Activate Ad</button>");
                }
                $('#user_ads button:last').after(
                    "</div>"
                    + '</td>'
                    + '</tr>');
            }
        }
    });
}

function GoToEditAd(id) {
    sessionStorage.removeItem("ad_id");
    sessionStorage.setItem("ad_id", id);
    location.replace("/edit-ad.html");
}

function closeAd(id) {
    $.ajax({
        url: '/my_ads_servlet',
        method: 'POST',
        data: {
            ad_id: id,
            action : "updateStatus",
            ad_status : "CLOSED"
        },
        complete: function () {
            getUserAds();
        }
    });
}

function activateAd(id) {
    $.ajax({
        url: '/my_ads_servlet',
        method: 'POST',
        data: {
            ad_id: id,
            action : "updateStatus",
            ad_status : "ACTIVE"
        },
        complete: function () {
            getUserAds();
        }
    });
}