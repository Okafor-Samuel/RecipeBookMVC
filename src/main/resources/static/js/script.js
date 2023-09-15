let userIcon = document.getElementById("user-icon");
let loginForm = document.getElementById("loginForm");
let profileForm = document.getElementById("profileForm");
checkLoginStatus();
userIcon.addEventListener("click", toggleLoginForm);


async function checkLoginStatus() {
    try {
        const response = await fetch('/checkSession');
        const data = await response.json();
        if (data.sessionId) {
            window.isLoggedIn = true;
        } else {
            window.isLoggedIn = false;
        }
    } catch (error) {
        console.error(error);
    }
}

function toggleLoginForm() {
    if (window.isLoggedIn) {
        if (profileForm.style.display === "none") {
            profileForm.style.opacity = 0;
            profileForm.style.display = "block";
            setTimeout(() => {
                profileForm.style.opacity = 1;
            }, 10);
        } else {
            profileForm.style.opacity = 0;
            setTimeout(() => {
                profileForm.style.display = "none";
            }, 300);
        }
    } else {
        if (loginForm.style.display === "none") {
            loginForm.style.opacity = 0;
            loginForm.style.display = "block";
            setTimeout(() => {
                loginForm.style.opacity = 1;
            }, 10);
        } else {
            loginForm.style.opacity = 0;
            setTimeout(() => {
                loginForm.style.display = "none";
            }, 300);
        }
    }
}

function toggleDropdown() {
    let dropdownMenu = document.getElementById("dropdown-menu");
    let logoImg = document.querySelector('.dropdown img');

    if (dropdownMenu.style.display === "none") {
        dropdownMenu.style.opacity = 0;
        dropdownMenu.style.display = "flex";
        setTimeout(() => {
            dropdownMenu.style.opacity = 1;
        }, 10);
        logoImg.src = "https://recept.se/assets/images/menu/close.svg";
    } else {
        dropdownMenu.style.opacity = 0;
        setTimeout(() => {
            dropdownMenu.style.display = "none";
        }, 300);
        logoImg.src = "https://recept.se/assets/images/menu/hamburger.svg";
    }
}



// function toggleSearchField() {
//     let searchField = document.querySelector('.search-field');
//     let searchIcon = document.querySelector('.search-icon img');
//
//     if (searchField.style.display === "none") {
//         searchField.style.display = "block";
//         searchIcon.src = "https://recept.se/assets/images/menu/close.svg";
//     } else {
//         searchField.style.display = "none";
//         searchIcon.src = "https://recept.se/assets/images/menu/search.svg";
//     }
// }

function toggleSearchField() {
    let searchField = document.querySelector('.search-field');

    if (searchField.style.display === 'none' || searchField.style.display === '') {
        searchField.style.display = 'block';
        searchField.style.opacity = 0;
        setTimeout(() => {
            searchField.style.opacity = 1;
            searchField.focus();
        }, 10);
    } else {
        searchField.style.opacity = 0;
        setTimeout(() => {
            searchField.style.display = 'none';
            searchField.blur();
        }, 300);
    }
}

// Contact-form
function showSuccessMessage() {
    document.getElementById("success-message").style.display = "block";
}
