function goToRegistration() {
    location.replace("/registration.html");
}

function validateAuth() {
    var result = true;
    var login = $('#login').val();
    var password = $('#password').val();
    if (login == '') {
        result = false;
        alert('Please, enter your login');
    }
    if (password == '') {
        result = false;
        alert('Please, enter your password');
    }
    return result;
}

function validateNewUser() {
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

function registerUser() {
    if (validateNewUser()) {
        $.ajax({
            url: '/user_create_servlet',
            method: 'POST',
            data: {
                login: $('#login').val(),
                password: $('#password').val(),
                name: $('#name').val(),
                email: $('#email').val(),
                city: $('#city').val()
            }
        });
    }
}

function logIn() {
    if (validateAuth()) {
        $.ajax({
            url: '/login',
            method: 'POST',
            data: {
                login: $('#login').val(),
                password: $('#password').val()
            },
            complete: function (response) {
                var result = JSON.parse(response.responseText);
                sessionStorage.setItem("login", result.login);
                sessionStorage.setItem("id", result.id);
                checkUser();
            }
        });
    }
}

function logOut() {
    if (validateAuth()) {
        $.ajax({
            url: '/exit',
            method: 'POST',
            complete: function () {
                sessionStorage.removeItem("login");
                sessionStorage.removeItem("id");
                checkUser();
            }
        });
    }
}

function checkUser() {
    var login = sessionStorage.getItem("login");
    var result = '';
    if (login == 'error') {
        result = ""
            + "<div class=\"col-sm-1\">"
            + "</div>"
            + "<div class=\"col-sm-5\">"
            + "<form class=\"form-inline\">"
            + "<div class=\"form-group\">"
            + "<label for=\"login\">Login: </label>"
            + "<input type=\"login\" class=\"form-control\" id=\"login\">"
            + "</div>"
            + "<div class=\"form-group\">"
            + "<label for=\"password\">Password: </label>"
            + "<input type=\"password\" class=\"form-control\" id=\"password\">"
            + "</div>"
            + "<div class=\"col-sm-1\">"
            + "</div>"
            + "<button type=\"button\" class=\"btn btn-danger\" onclick=\"logIn()\">Log in</button>"
            + "</form>"
            + "</div>"
            + "<div class=\"col-sm-2\">"
            + "<input class=\"form-control\" type=\"text\" id=\"inputError\" placeholder=\"Autorization Error, try again\" disabled>"
            + "<span class=\"glyphicon glyphicon-remove form-control-feedback\"></span>"
            + "</div>"
            + "<div class=\"col-sm-1\">"
            + "<div class=\"container\">"
            + "<div class=\"btn-group\">"
            + "<button type=\"button\" class=\"btn btn-danger\" onclick=\"goToIndexPage()\">Show All Ads</button>"
            + "<button type=\"button\" class=\"btn btn-danger\" onclick=\"goToRegistration()\">Create Account</button>"
            + "</div>"
            + "</div>"
            + "</div>";
    } else if (login == null) {
        result = ""
            + "<div class=\"col-sm-1\">"
            + "</div>"
            + "<div class=\"col-sm-5\">"
            + "<form class=\"form-inline\">"
            + "<div class=\"form-group\">"
            + "<label for=\"login\">Login: </label>"
            + "<input type=\"login\" class=\"form-control\" id=\"login\">"
            + "</div>"
            + "<div class=\"form-group\">"
            + "<label for=\"password\">Password: </label>"
            + "<input type=\"password\" class=\"form-control\" id=\"password\">"
            + "</div>"
            + "<div class=\"col-sm-1\">"
            + "</div>"
            + "<button type=\"button\" class=\"btn btn-danger\" onclick=\"logIn()\">Log in</button>"
            + "</form>"
            + "</div>"
            + "<div class=\"col-sm-2\">"
            + "</div>"
            + "<div class=\"col-sm-1\">"
            + "<div class=\"container\">"
            + "<div class=\"btn-group\">"
            + "<button type=\"button\" class=\"btn btn-danger\" onclick=\"goToIndexPage()\">Show All Ads</button>"
            + "<button type=\"button\" class=\"btn btn-danger\" onclick=\"goToRegistration()\">Create Account</button>"
            + "</div>"
            + "</div>"
            + "</div>";
    } else {
        result = ""
            + "<div class=\"col-sm-1\">"
            + "</div>"
            + "<div class=\"col-sm-6\">"
            + "<h3> You are logged in as \"" + login + "\"</h3>"
            + "</div>"
            + "<div class=\"col-sm-4\">"
            + "<div class=\"container\">"
            + "<div class=\"btn-group\">"
            + "<button type=\"button\" class=\"btn btn-danger\" onclick=\"goToIndexPage()\">Show All Ads</button>"
            + "<button type=\"button\" class=\"btn btn-danger\" onclick=\"createAd()\">Create Ad</button>"
            + "<button type=\"button\" class=\"btn btn-danger\" onclick=\"goToProfile()\">My Profile</button>"
            + "<button type=\"button\" class=\"btn btn-danger\" onclick=\"goToMyAds()\">My Ads</button>"
            + "<button type=\"button\" class=\"btn btn-danger\" onclick=\"logOut()\">Exit</button>"
            + "</div>"
            + "</div>"
            + "</div>"
    }
    $('#login_details').html(result);
}

function goToProfile() {
    location.replace("/profile.html");
}

function goToIndexPage() {
    location.replace("/");
}

function createAd() {
    location.replace("/create-ad.html");
}

function showAll() {
    $.ajax({
        url: '/ad_servlet',
        method: 'POST',
        complete: function (response) {
            let list = JSON.parse(response.responseText);
            $("#ads td").parent().remove();
            for (let i = 0; i < list.length; i++) {
                $('#ads tr:last').after(
                    '<tr>' +
                    '<td>' + list[i].name + '</td>' +
                    '<td>' + list[i].year + '</td>' +
                    '<td>' + list[i].mileage + '</td>' +
                    '<td>'
                    + "<div class=\"col-sm-offset-4 col-sm-4\">"
                    + "<button type=\"button\" class=\"btn btn-danger\" onclick=\"goToAd(" + list[i].id + ")\">Details</button>"
                    + "</div>"
                    + '</td>'
                    + '</tr>'
                );
            }
        }
    });
}

function goToAd(id) {
    sessionStorage.removeItem("ad_id");
    sessionStorage.setItem("ad_id", id);
    location.replace("/ad.html");
}

function goToMyAds() {
    location.replace("/my-ads.html");
}