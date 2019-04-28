function addAd() {
    if (validateAd()) {
        $.ajax({
            url: '/ad_create_servlet',
            method: 'POST',
            data: {
                user_id: sessionStorage.getItem("id"),
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
            complete: function (response) {
                var ad = JSON.parse(response.responseText);
                sendPhoto(ad.id);
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

function fillLists() {
    $.ajax({
            url: '/ad_create_servlet',
            method: 'GET',
            complete: function (response) {
                var lists = JSON.parse(response.responseText);
                var result = "";
                for (var i = 0; i < lists[0].length; i++) {
                    result +=
                        "<option value=\"" + lists[0][i] + "\">" + lists[0][i] + "</option>";
                }
                $('#body_type').html('<option value="0">-Body Type-</option>' + result);

                result = "";
                for (var i = 0; i < lists[1].length; i++) {
                    result +=
                        "<option value=\"" + lists[1][i] + "\">" + lists[1][i] + "</option>";
                }
                $('#engine_type').html('<option value="0">-Engine Type-</option>' + result);

                result = "";
                for (var i = 0; i < lists[2].length; i++) {
                    result +=
                        "<option value=\"" + lists[2][i] + "\">" + lists[2][i] + "</option>";
                }
                $('#gearbox_type').html('<option value="0">-Gearbox Type-</option>' + result);

                result = "";
                for (var i = 0; i < lists[3].length; i++) {
                    result +=
                        "<option value=\"" + lists[3][i] + "\">" + lists[3][i] + "</option>";
                }
                $('#manufacturer').html('<option value="0">-Manufacturer-</option>' + result);
            }
        }
    );
}

function sendPhoto(id) {
    var ajaxData = new FormData();
    $.each($("input[type=file]"), function (i, obj) {
        $.each(obj.files, function (j, file) {
            ajaxData.append(id, file);
        })
    });
    alert(ajaxData);
    $.ajax({
        url: '/photo_servlet',
        data: {
            files: ajaxData,
            ad_id: id
        },
        processData: false,
        contentType: false,
        type: 'POST',
        complete: function () {
            location.replace("/");
        }
    });
}

function fillModels(manufacturer) {
    $.ajax({
            url: '/brand_servlet',
            method: 'POST',
            data: {
                manufacturer: manufacturer,
            },
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
}