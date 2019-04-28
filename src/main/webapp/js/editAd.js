function fillLists() {
    $.ajax({
            url: '/ad_edit_servlet',
            method: 'GET',
            data: {
                ad_id: sessionStorage.getItem("ad_id")
            },
            complete: function (response) {

                var ad = JSON.parse(response.responseText);
                var parts = fillParts();
                var models = getModels(ad.car.brand);

                var result = "";
                for (var i = 0; i < parts[3].length; i++) {
                    var sel = "";
                    if (parts[3][i] == ad.car.brand) {
                        sel = "selected";
                    }
                    result +=
                        "<option " + sel + " value=\"" + parts[3][i] + "\">" + parts[3][i] + "</option>";
                }
                $('#manufacturer').html(result);

                result = "";
                for (var i = 0; i < models.length; i++) {
                    var sel = "";
                    if (models[i] == ad.car.model) {
                        sel = "selected";
                    }
                    result +=
                        "<option " + sel + " value=\"" + models[i] + "\">" + models[i] + "</option>";
                }
                $('#model').html('<option value="0">-Model-</option>' + result);

                result = "";
                for (var i = 0; i < parts[0].length; i++) {
                    var sel = "";
                    if (parts[0][i] == ad.car.body) {
                        sel = "selected";
                    }
                    result +=
                        "<option " + sel + " value=\"" + parts[0][i] + "\">" + parts[0][i] + "</option>";
                }
                $('#body_type').html('<option value="0">-Body Type-</option>' + result);

                result = "";
                for (var i = 0; i < parts[1].length; i++) {
                    var sel = "";
                    if (parts[1][i] == ad.car.engine.type) {
                        sel = "selected";
                    }
                    result +=
                        "<option " + sel + " value=\"" + parts[1][i] + "\">" + parts[1][i] + "</option>";
                }
                $('#engine_type').html('<option value="0">-Engine Type-</option>' + result);

                result = "";
                for (var i = 0; i < parts[2].length; i++) {
                    var sel = "";
                    if (parts[2][i] == ad.car.gearbox) {
                        sel = "selected";
                    }
                    result +=
                        "<option " + sel + " value=\"" + parts[2][i] + "\">" + parts[2][i] + "</option>";
                }
                $('#gearbox_type').html('<option value="0">-Gearbox Type-</option>' + result);

                $('#engine_value').val(ad.car.engine.value);
                $('#year').val(ad.year);
                $('#mileage').val(ad.mileage);
                $('#description').val(ad.description);
            }
        }
    );
}

function fillParts() {
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

function getModels(manufacturer) {
    $.ajax({
            url: '/brand_servlet',
            method: 'POST',
            data: {
                manufacturer: manufacturer,
            },
            async: false,
            complete: function (response) {
                list = JSON.parse(response.responseText);
            }
        }
    );
    return list;
}

function fillModels(manufacturer) {
    $.ajax({
            url: '/brand_servlet',
            method: 'POST',
            data: {
                manufacturer: manufacturer,
            },
            async: false,
        complete: function (response) {
            var lists = JSON.parse(response.responseText);
            var result = "";
            for (var i = 0; i < lists.length; i++) {
                result +=
                    "<option value=\"" + lists[i] + "\">" + lists[i] + "</option>";
            }
            $('#model').html('<option value="0">-Car Model-</option>' + result);
        }
        }
    );
    return list;
}

function updateAd() {
    if (validateAd()) {
        $.ajax({
            url: '/ad_edit_servlet',
            method: 'POST',
            data: {
                user_id: sessionStorage.getItem("id"),
                ad_id: sessionStorage.getItem("ad_id"),
                manufacturer: $('#manufacturer').val(),
                model: $('#model').val(),
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
    var result = true;
    var manufacturer = $('#manufacturer').val();
    var model = $('#model').val();
    var body_type = $('#body_type').val();
    var engine_type = $('#engine_type').val();
    var engine_value = $('#engine_value').val();
    var gearbox_type = $('#gearbox_type').val();
    var year = $('#year').val();
    var mileage = $('#mileage').val();
    var description = $('#description').val();
    if (manufacturer == 0 || manufacturer == null) {
        result = false;
        alert('Please, enter your manufacturer');
    }
    if (model == 0 || model == null) {
        result = false;
        alert('Please, enter your model');
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