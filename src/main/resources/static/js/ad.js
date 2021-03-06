function showAd() {
    var ad_id = sessionStorage.getItem("ad_id");
    $.ajax({
        url: '/ad_servlet',
        method: 'GET',
        data: {
            ad_id: ad_id
        },
        complete: function (response) {
            var ad = JSON.parse(response.responseText);
            var result = ""
                + " <h2>" + ad.car.brand + " " + ad.car.model + "</h2>\n" +
                "  <ul class=\"list-group\">\n" +
                "    <li class=\"list-group-item\">Manufacturer : " + ad.car.brand + "</li>\n" +
                "    <li class=\"list-group-item\">Model : " + ad.car.model + "</li>\n" +
                "    <li class=\"list-group-item\">Body type : " + ad.car.body + "</li>\n" +
                "    <li class=\"list-group-item\">Engine type : " + ad.car.engine.type + "</li>\n" +
                "    <li class=\"list-group-item\">Engine value : " + ad.car.engine.value + "</li>\n" +
                "    <li class=\"list-group-item\">Gearbox type : " + ad.car.gearbox + "</li>\n" +
                "    <li class=\"list-group-item\">Year : " + ad.year + "</li>\n" +
                "    <li class=\"list-group-item\">Mileage : " + ad.mileage + "</li>\n" +
                "    <li class=\"list-group-item\">Status : " + ad.status + "</li>\n" +
                "    <li class=\"list-group-item\">Description : " + ad.description + "</li>\n" +
                "    <li class=\"list-group-item\">Contacts : " + ad.user.email + "</li>\n" +
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
        async: true,
        data: {
            ad_id: sessionStorage.getItem("ad_id")
        },
        complete: function (response) {
            var result = '';
            var photosIdList = JSON.parse(response.responseText);
            for (var i = 0; i < photosIdList.length; i++) {
                result += "<img class=\"img-thumbnail\" width=\"912\" height=\"708\" src=\"/show_photo_servlet?id=" + photosIdList[i] + "\" />";
            }
            $('#carPhotos').html(result);
        }
    });
}