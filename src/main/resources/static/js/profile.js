function findUser() {
    var id = sessionStorage.getItem("id");
    var result;
    $.ajax({
        url: '/user_update_servlet',
        method: 'GET',
        data: {
            id: id
        },
        complete: function (response) {
            user = JSON.parse(response.responseText);
            result = ""
                + "<div class=\"col-sm-1\">"
                + "</div>"
                + "<div class=\"col-sm-5\">"
                + "<form class=\"form-horizontal\">"
                + "<div class=\"form-group\">"
                + "<div class=\"col-sm-10\">"
                + "<input type=\"text\" class=\"form-control\" id=\"login\" value=\"" + user.login + "\" name=\"login\">"
                + "</div>"
                + "</div>"
                + "<div class=\"form-group\">"
                + "<label class=\"control-label col-sm-2\" for=\"password\">Password:</label>"
                + "<div class=\"col-sm-10\">"
                + "<input type=\"password\" class=\"form-control\" id=\"password\" value=\"" + user.password + "\" name=\"password\">"
                + "</div>"
                + "</div>"
                + "<div class=\"form-group\">"
                + "<label class=\"control-label col-sm-2\" for=\"name\">Name:</label>"
                + "<div class=\"col-sm-10\">"
                + "<input type=\"text\" class=\"form-control\" id=\"name\" value=\"" + user.name + "\" name=\"name\">"
                + "</div>"
                + "</div>"
                + "<div class=\"form-group\">"
                + "<label class=\"control-label col-sm-2\" for=\"email\">Email:</label>"
                + "<div class=\"col-sm-10\">"
                + "<input type=\"email\" class=\"form-control\" id=\"email\" value=\"" + user.email + "\" name=\"email\">"
                + "</div>"
                + "</div>"
                + "<div class=\"form-group\">"
                + "<label class=\"control-label col-sm-2\" for=\"city\">City:</label>"
                + "<div class=\"col-sm-10\">"
                + "<input type=\"text\" class=\"form-control\" id=\"city\" value=\"" + user.city + "\" name=\"city\">"
                + "</div>"
                + "</div>"
                + "<div class=\"form-group\">"
                + "<div class=\"col-sm-offset-2 col-sm-10\">"
                + "<button type=\"button\" class=\"btn btn-danger\" onclick=\"updateUser()\">Update Profile</button>"
                + "</div>"
                + "</div>"
                + "</form>"
                + "</div>";
            $('#user_update').html(result);
        }
    });
}

function updateUser() {
    if (validateUser()) {
        $.ajax({
            url: '/user_update_servlet',
            method: 'POST',
            data : {
                id: sessionStorage.getItem("id"),
                login: $('#login').val(),
                password: $('#password').val(),
                name: $('#name').val(),
                email : $('#email').val(),
                city: $('#city').val()
            },
            complete: function (responce) {
                var updated_user = JSON.parse(responce.responseText);
                sessionStorage.setItem("login", updated_user.login);
                location.replace("/index.html");
            }
        });
    }
}

function validateUser() {
    var result = true;
    var login = $('#login').val();
    var password = $('#password').val();
    var name = $('#name').val();
    var email = $('#email').val();
    var city = $('#city').val();
    if (login == '') {
        result = false;
        alert('Please, enter your login');
    }
    if (password == '') {
        result = false;
        alert('Please, enter your password');
    }
    if (name == '') {
        result = false;
        alert('Please, enter your name');
    }
    if (email == '') {
        result = false;
        alert('Please, enter your email');
    }
    if (city == '') {
        result = false;
        alert('Please, enter your city');
    }
    return result;
}