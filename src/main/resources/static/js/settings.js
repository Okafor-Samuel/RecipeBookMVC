const form = document.getElementById('update-settings-form');
const username = document.getElementById('name');
const confirmUsername = document.getElementById('confirm_name');
const password = document.getElementById('password');
const confirmPassword = document.getElementById('confirm_password');

form.addEventListener('submit', function (event) {
    console.log("form is submitted");
    let formIsValid = true;

    if (username.value.trim() !== '' || confirmUsername.value.trim() !== '') {
        username.required = true;
        confirmUsername.required = true;

        // Check if the username and confirmUsername fields match
        if (username.value !== confirmUsername.value) {
            formIsValid = false;
            alert("Usernames do not match.");
        }
    } else {
        username.required = false;
        confirmUsername.required = false;
    }

    if (password.value.trim() !== '' || confirmPassword.value.trim() !== '') {
        password.required = true;
        confirmPassword.required = true;

        // Check if the password and confirmPassword fields match
        if (password.value !== confirmPassword.value) {
            formIsValid = false;
            alert("Passwords do not match.");
        }
    } else {
        password.required = false;
        confirmPassword.required = false;
    }

    // If the form is not valid, prevent submission
    if (!formIsValid) {
        event.preventDefault();
    }
});