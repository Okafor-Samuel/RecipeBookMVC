const fileInput = document.getElementById('profile-pic');
const imagePreview = document.getElementById('imagePreview');

const croppedImageCanvas = document.getElementById('croppedImageCanvas');
const cropButton = document.getElementById('crop-btn');

let cropper;
fileInput.addEventListener('change', function() {
    let file = this.files[0];
    let reader = new FileReader();

    if (cropper) {
        cropper.destroy();
    }

    reader.addEventListener('load', function() {
        imagePreview.setAttribute('src', reader.result);

        // Show the imagePreview and hide the croppedImageCanvas
        imagePreview.style.display = 'block';
        croppedImageCanvas.style.display = 'none';

        // Initialize the Cropper instance
        if (!cropper) {
            cropper = new Cropper(imagePreview, {
                aspectRatio: 1,
                viewMode: 1,
                autoCropArea: 1,
                cropBoxMaxWidth: 170,
                cropBoxMaxHeight: 170,
                crop(event) {
                    // Crop event details
                },
            });
        }
    });
    reader.readAsDataURL(file);
});


cropButton.addEventListener('click', function(event) {
    event.preventDefault();
    if (cropper) {
        // Get the cropped image data
        const croppedImageData = cropper.getCroppedCanvas();

        croppedImageCanvas.width = croppedImageData.width;
        croppedImageCanvas.height = croppedImageData.height;

        // Draw the cropped image on the croppedImageCanvas
        const ctx = croppedImageCanvas.getContext('2d');
        ctx.clearRect(0, 0, croppedImageCanvas.width, croppedImageCanvas.height);
        ctx.drawImage(croppedImageData, 0, 0);

        // Show the croppedImageCanvas and hide the imagePreview
        croppedImageCanvas.style.display = 'block';
        imagePreview.style.display = 'none';

        // Destroy the CropperJS instance
        cropper.destroy();
        cropper = null;
    }
});