function addAd() {
    if (validateAd()) {
        $.ajax({
            url: '/ad_create_servlet',
            method: 'POST',
            data: {
                user_id: sessionStorage.getItem("id"),
                car_name: $('#car_name').val(),
                body_type: $('#body_type').val(),
                engine_type: $('#engine_type').val(),
                engine_value: $('#engine_value').val(),
                gearbox_type: $('#gearbox_type').val(),
                year: $('#year').val(),
                mileage: $('#mileage').val(),
                description: $('#description').val(),
            },
            complete: function (response) {
                let ad = JSON.parse(response.responseText);
                sendPhoto(ad.id);
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
            complete: function (response) {
                let lists = JSON.parse(response.responseText);
                let result = "";
                for (let i = 0; i < lists[0].length; i++) {
                    result +=
                        "<option value=\"" + lists[0][i] + "\">" + lists[0][i] + "</option>";
                }
                $('#body_type').html('<option value="0">-Body Type-</option>' + result);

                result = "";
                for (let i = 0; i < lists[1].length; i++) {
                    result +=
                        "<option value=\"" + lists[1][i] + "\">" + lists[1][i] + "</option>";
                }
                $('#engine_type').html('<option value="0">-Engine Type-</option>' + result);

                result = "";
                for (let i = 0; i < lists[2].length; i++) {
                    result +=
                        "<option value=\"" + lists[2][i] + "\">" + lists[2][i] + "</option>";
                }
                $('#gearbox_type').html('<option value="0">-Gearbox Type-</option>' + result);
            }
        }
    );
}

function sendPhoto (id) {
    var ajaxData = new FormData();
    $.each($("input[type=file]"), function(i, obj) {
        $.each(obj.files,function(j,file){
            ajaxData.append(id, file);
        })
    });
    $.ajax({
        url: '/photo_servlet',
        data: ajaxData,
        processData: false,
        contentType: false,
        type: 'POST'
    });
}
