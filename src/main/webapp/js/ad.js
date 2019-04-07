function showAd() {
    let ad_id = sessionStorage.getItem("ad_id");
    $.ajax({
        url: '/ad_servlet',
        method: 'GET',
        data: {
            ad_id: ad_id
        },
        complete: function (response) {
            let ad = JSON.parse(response.responseText);
            let result = ""
                + " <h2>" + ad.car.name + "</h2>\n" +
                "  <ul class=\"list-group\">\n" +
                "    <li class=\"list-group-item\">Model : " + ad.car.name + "</li>\n" +
                "    <li class=\"list-group-item\">Body type : " + ad.car.body + "</li>\n" +
                "    <li class=\"list-group-item\">Engine type : " + ad.car.engine.type + "</li>\n" +
                "    <li class=\"list-group-item\">Engine value : " + ad.car.engine.value + "</li>\n" +
                "    <li class=\"list-group-item\">Gearbox type : " + ad.car.gearbox + "</li>\n" +
                "    <li class=\"list-group-item\">Year : " + ad.year + "</li>\n" +
                "    <li class=\"list-group-item\">Mileage : " + ad.mileage + "</li>\n" +
                "    <li class=\"list-group-item\">Status : " + ad.status + "</li>\n" +
                "    <li class=\"list-group-item\">Description : " + ad.description + "</li>\n" +
                "    <li class=\"list-group-item\">Contacts : " + ad.user.contacts + "</li>\n" +
                "  </ul>";
            $('#car_ad').html(result);
            getPhoto(ad.id);
        }
    });
}

function getPhoto() {
    $.ajax({
        url: '/photo_servlet',
        method: 'GET',
        async: false,
        data: {
            ad_id: sessionStorage.getItem("ad_id")
        },
        complete: function (response) {
            var result = '';
            var photosIdList = JSON.parse(response.responseText);
            for (let i = 0; i < photosIdList.length; i++) {
                if (i == 0) {
                    result += '<div class="item active">';
                } else {
                    result += '<div class="item">';
                }
                result +=  "<img src=\"/show_photo_servlet?id=" + photosIdList[i] + "\"  style=\"width:100%;\"/></div>";
            }
            $('#carPhotos').html(result);
        }
    });
}