

function highlightStars(star) {
    console.log('highlightStars called');

    const rating = parseInt(star.getAttribute("data-rating"));
    const stars = star.parentElement.querySelectorAll(".star-icon");

    stars.forEach((starElement, index) => {
        if (index < rating) {
            starElement.classList.remove("fa-star-o");
            starElement.classList.add("fa-star");
        } else {
            starElement.classList.remove("fa-star");
            starElement.classList.add("fa-star-o");
        }
    });
}

function resetStars(ratingContainer) {
    console.log('resetStars called');

    const ratingValueElement = ratingContainer.querySelector(".ratingValue");

    if (ratingValueElement) {
        const averageRating = parseFloat(ratingValueElement.getAttribute("data-average-rating"));
        ratingValueElement.textContent = averageRating.toFixed(1);

        const stars = ratingContainer.querySelectorAll(".star-icon");
        stars.forEach((starElement, index) => {
            if (index < averageRating) {
                starElement.classList.remove("fa-star-o");
                starElement.classList.add("fa-star");
            } else {
                starElement.classList.remove("fa-star");
                starElement.classList.add("fa-star-o");
            }
        });
    }
}


function handleStarClick(star) {
    console.log('handleStarClick called');

    const rating = parseInt(star.getAttribute("data-rating"));
    const recipeId = star.getAttribute("data-recipe-id");

    fetch(`/rating/add?recipeId=${recipeId}&rating=${rating}`, {method: "POST"})
        .then(response => response.json())
        .then(data => {
            if (data.success) {
                // Update stars and average rating based on the response
                const ratingContainer = star.parentElement;
                const ratingValueElement = ratingContainer.querySelector(".ratingValue");
                const newAverageRating = parseFloat(data.newAverageRating);
                ratingValueElement.textContent = newAverageRating.toFixed(1);
                ratingValueElement.setAttribute("data-average-rating", newAverageRating.toFixed(1)); // update the data-average-rating attribute

                const stars = ratingContainer.querySelectorAll(".star-icon");
                stars.forEach((starElement, index) => {
                    if (index < newAverageRating) {
                        starElement.classList.remove("fa-star-o");
                        starElement.classList.add("fa-star");
                    } else {
                        starElement.classList.remove("fa-star");
                        starElement.classList.add("fa-star-o");
                    }
                });
            } else {
                showLoginPopup();
            }
        })
        .catch(error => console.error(error));
}

document.querySelectorAll(".ratingContainer").forEach((ratingContainer) => {
    const stars = ratingContainer.querySelectorAll(".star-icon");
    stars.forEach((star) => {
        star.addEventListener("mouseenter", () => highlightStars(star));
        star.addEventListener("mouseleave", () => resetStars(ratingContainer));
        star.addEventListener("click", () => handleStarClick(star));
    });


    // Set initial state of stars based on average ratings
    const recipeId = ratingContainer.getAttribute("data-recipe-id");
    const averageRatingElement = document.querySelector(`[data-recipe-id="${recipeId}"][data-average-rating]`);
    const ratingValueElement = ratingContainer.querySelector(".ratingValue");
    if (averageRatingElement) {
        const averageRating = parseFloat(averageRatingElement.getAttribute("data-average-rating"));
        ratingValueElement.textContent = averageRating.toFixed(1);

        stars.forEach((starElement, index) => {
            if (index < averageRating) {
                starElement.classList.remove("fa-star-o");
                starElement.classList.add("fa-star");
            } else {
                starElement.classList.remove("fa-star");
                starElement.classList.add("fa-star-o");
            }
        });
    }
});


