function validate() {
    var result = true;
    var name = $('#username').val();
    var phone = $('#phone').val();
    var re = /^(\s*)?(\+)?([- _():=+]?\d[- _():=+]?){10,14}(\s*)?$/;
    var phoneValidate = (re.test(phone)).valueOf();
    if (name == '') {
        result = false;
        alert('Пожалуйста, введите Ваше имя');
    }
    if (phone == '') {
        result = false;
        alert('Пожалуйста, введите Ваш номер телефона');
    }

    if (phoneValidate != true) {
        result = false;
        alert('Номер телефона введен неправильно!');
    }
    return result;
}

function fillHall() {
    $.ajax({
        url: '/hall',
        method: 'POST',
        complete: function (response) {
            var hall = JSON.parse(response.responseText);
            var current_row = 1;
            var result = "<thead><tr><th style=\"width: 120px;\">"
                + "Ряд / Место</th><th>1</th><th>2</th><th>3</th></tr></thead><tbody><tr><th>1</th>";
            for (var i = 0; i < hall.length; i++) {
                var id = hall[i].id;
                var row = hall[i].row;
                var place = hall[i].place;
                var availability = hall[i].availability;
                var st = "";
                if (row != current_row) {
                    result += "</tr><tr><th>" + row + "</th>";
                    current_row = row;
                }
                if (availability == "Busy") {
                    st = "disabled";
                }
                console.log(st);
                result += "<td><input type = \"radio\" name = \"place\" value =\"" + id + "\"" + st + ">"
                    + "Ряд " + row + ", Место " + place + " " + availability + "</td>";
            }
            result += "</tr></tbody>";
            $('#place').html(result);
        }
    });
}

function getPlace(id) {
    $.ajax({
        url: '/payment',
        method: 'GET',
        data: "id=" + id,
        complete: function (response) {
            var place = JSON.parse(response.responseText);
            var result = "Вы выбрали ряд " + place.row + " место " + place.place + ", Сумма : 500 рублей.";
            $('#place_details').html(result);
        }
    });
}

function booking(id, user_name, user_phone) {
    if (validate() == true) {
        $.ajax({
            url: '/payment',
            method: 'POST',
            data: {
                id: id,
                name: user_name,
                phone: user_phone
            },
            complete: function (response) {
                var resp = JSON.parse(response.responseText);
                if (resp.result) {
                    alert('Спасибо, ваше место забронировано!');
                } else {
                    alert('Извините, но это место уже успел кто-то занять!');
                }
                location.replace("/index.html");
            }
        });
    } else {
        false;
    }
}
