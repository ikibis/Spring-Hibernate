function fillFields() {
    $.ajax({
        url: '/ad_edit_servlet',
        method: 'GET',
        data: {
            ad_id: sessionStorage.getItem("ad_id")
        },
        complete: function (response) {
            let parts = fillLists();
            console.log(fillLists());
            let ad = JSON.parse(response.responseText);

            var result =
                "<div class=\"col-sm-1\">"
                + "</div>"
                + "<div class=\"col-sm-5\">"
                + "<form class=\"form-horizontal\">"
                + "<div class=\"form-group \">"
                + "<label class=\"control-label col-sm-4\" for=\"car_name\">Car name:</label>"
                + "<input type=\"text\" class=\"form-control\" id=\"car_name\" value=\"" + ad.car.name + "\" name=\"car_name\">"
                + "</div>"
                + "<div class=\"form-group\">"
                + "<label class=\"control-label col-sm-4\" for=\"body_type\">Body type:</label>"
                + "<select class=\"form-control col-sm-3\" id=\"body_type\" name=\"body_type\">";
            for (let i = 0; i < parts[0].length; i++) {
                var sel = "";
                if (parts[0][i] == ad.car.body) {
                    sel = "selected";
                }
                result +=
                    "<option " + sel + " value=\"" + parts[0][i] + "\">" + parts[0][i] + "</option>";
            }
            result += "</select>"
                + "</div>"
                + "<div class=\"form-group\">"
                + "<label class=\"control-label col-sm-4\" for=\"engine_type\">Engine type:</label>"
                + "<select class=\"form-control col-sm-3\" id=\"engine_type\">";
            for (let i = 0; i < parts[1].length; i++) {
                var sel = "";
                if (parts[1][i] == ad.car.engine.type) {
                    sel = "selected";
                }
                result +=
                    "<option " + sel + " value=\"" + parts[1][i] + "\">" + parts[1][i] + "</option>";
            }
            result += "</select>"
                + "</div>"
                + "<div class=\"form-group\">"
                + "<label class=\"control-label col-sm-4\" for=\"engine_value\">Engine value:</label>"
                + "<input type=\"number\" class=\"form-control\" id=\"engine_value\" value=\"" + ad.car.engine.value + "\" name=\"engine_value\">"
                + "</div>"
                + "<div class=\"form-group\">"
                + "<label class=\"control-label col-sm-4\" for=\"gearbox_type\">Gearbox type:</label>"
                + "<select class=\"form-control col-sm-3\" id=\"gearbox_type\">";

            for (let i = 0; i < parts[2].length; i++) {
                var sel = "";
                if (parts[2][i] == ad.car.gearbox) {
                    sel = "selected";
                }
                result +=
                    "<option " + sel + " value=\"" + parts[2][i] + "\">" + parts[2][i] + "</option>";
            }
            result += "</select>"
                + "</div>"
                + "<div class=\"form-group\">"
                + "<label class=\"control-label col-sm-4\" for=\"year\">Car year:</label>"
                + "<input type=\"number\" class=\"form-control\" id=\"year\" value=\"" + ad.year + "\" name=\"year\">"
                + "</div>"
                + "<div class=\"form-group\">"
                + "<label class=\"control-label col-sm-4\" for=\"mileage\">Car mileage:</label>"
                + "<input type=\"number\" class=\"form-control\" id=\"mileage\" value=\"" + ad.mileage + "\" name=\"mileage\">"
                + "</div>"
                + "<div class=\"form-group\">"
                + "<label for=\"description\">Description:</label>"
                + "<textarea class=\"form-control\" rows=\"5\" id=\"description\">" + ad.description + "</textarea>"
                + "</div>"
                + "<div class=\"form-group\">"
                + "<div class=\"col-sm-offset-2 col-sm-10\">"
                + "<button type=\"button\" class=\"btn btn-danger\" onclick=\"updateAd()\">Save Changes</button>"
                + "</div>"
                + "</div>"
                + "</form>";

            $('#edited_ad').html(result);

        }
    });
}

function updateAd() {
    if (validateAd()) {
        $.ajax({
            url: '/ad_edit_servlet',
            method: 'POST',
            data: {
                user_id: sessionStorage.getItem("id"),
                ad_id: sessionStorage.getItem("ad_id"),
                car_name: $('#car_name').val(),
                body_type: $('#body_type').val(),
                engine_type: $('#engine_type').val(),
                engine_value: $('#engine_value').val(),
                gearbox_type: $('#gearbox_type').val(),
                year: $('#year').val(),
                mileage: $('#mileage').val(),
                description: $('#description').val(),
            },
            complete: function () {
                location.replace("/");
            }
        });
    }
}

function validateAd() {
    let result = true;
    let car_name = $('#car_name').val();
    let body_type = $('#body_type').val();
    let engine_type = $('#engine_type').val();
    let engine_value = $('#engine_value').val();
    let gearbox_type = $('#gearbox_type').val();
    let year = $('#year').val();
    let mileage = $('#mileage').val();
    let description = $('#description').val();
    if (car_name === '') {
        result = false;
        alert('Please, enter your car_name');
    }
    if (body_type === '') {
        result = false;
        alert('Please, enter your body_type');
    }
    if (engine_type === '') {
        result = false;
        alert('Please, enter your engine_type');
    }
    if (engine_value === '') {
        result = false;
        alert('Please, enter your engine_value');
    }
    if (gearbox_type === '') {
        result = false;
        alert('Please, enter your gearbox_type');
    }
    if (year === '') {
        result = false;
        alert('Please, enter your year');
    }
    if (mileage === '') {
        result = false;
        alert('Please, enter your mileage');
    }
    if (description === '') {
        result = false;
        alert('Please, enter your description');
    }
    return result;
}

function fillLists() {
    $.ajax({
            url: '/ad_create_servlet',
            method: 'GET',
            async: false,
            complete: function (response) {
                lists = JSON.parse(response.responseText);
            }
        }
    );
    return lists;
}