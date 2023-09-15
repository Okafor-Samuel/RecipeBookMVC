function filterRecipes() {
    // Get the user's input from the search field
    let input = document.getElementById("searchInput").value.toLowerCase();
    // Get all of the recipe cards on the page
    let recipeCards = document.getElementsByClassName("recipeCard");
    // Loop through each recipe card
    for (let i = 0; i < recipeCards.length; i++) {
        const recipeTitle = recipeCards[i].getElementsByClassName("recipeTitle")[0].textContent.toLowerCase();
        // If the recipe title contains the user's input, show the recipe card, otherwise hide it
        if (recipeTitle.includes(input)) {
            recipeCards[i].style.display = "";
        } else {
            recipeCards[i].style.display = "none";
        }
    }
}

// Call filterRecipes() when the page loads
filterRecipes();
// Call filterRecipes() whenever the user types in the search field
document.getElementById("searchInput").addEventListener("keyup", function() {
    filterRecipes();
});