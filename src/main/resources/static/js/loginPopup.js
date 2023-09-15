const loginPopup = document.getElementById("loginPopup");
const overlay = document.getElementById("overlay");

function showLoginPopup() {
    loginPopup.style.display = "block";
    overlay.style.display = "block";
}

function closeLogin() {
    loginPopup.style.display = "none";
    overlay.style.display = "none";
}

document.addEventListener("DOMContentLoaded", function () {
    const closeLoginButton = document.getElementById("close-btn");
    closeLoginButton.addEventListener("click", closeLogin);
});