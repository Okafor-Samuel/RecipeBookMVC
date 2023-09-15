document.addEventListener("DOMContentLoaded", function () {
    const commentForm = document.getElementById("commentForm");

    commentForm.addEventListener("submit", function (event) {
        event.preventDefault();

        const formData = new FormData(commentForm);
        const recipeId = formData.get("recipeId");
        const content = formData.get("comment"); // Change this line

        fetch("/addComment", {
            method: "POST",
            body: new URLSearchParams(formData),
            headers: {
                "Content-Type": "application/x-www-form-urlencoded"
            }
        }).then(response => {
            if (response.ok) {
                window.location.href = "/recipe/" + recipeId;
            } else {
                // Handle errors, display a message or alert, etc.
            }
        }).catch(error => {
            console.error("Error posting comment:", error);
        });
    });
});