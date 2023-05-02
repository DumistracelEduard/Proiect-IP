// Enroll students manually
const manualEnrollForm = document.querySelector('#manualEnroll');

manualEnrollForm.addEventListener('Salvare', function (event) {
    event.preventDefault(); // prevent the default form submission

    const manualEndpoint = "http://localhost:8082/addStudent";
    const manualFormData = new FormData(manualEnrollForm);

    // make a POST request to the Spring Boot endpoint
    fetch(manualEndpoint, {
        method: 'POST',
        body: manualFormData
    }).catch(console.error)
        // .then(response => {
        //     if (!response.ok) {
        //         throw new Error('Network response was not ok');
        //     }
        //     return response.json();
        // })
        // .then(data => {
        //     console.log('Response from server:', data);
        // })
        // .catch(error => {
        //     console.error('Error:', error);
        // });
});

// Enroll students by excel file
const fileEnrollForm = document.getElementById("fileEnroll");
const inputFile = document.getElementById("file");

fileEnrollForm.addEventListener("submit", e => {
    e.preventDefault();

    const fileEndpoint = "http://localhost:8082/user/upload-customers-data";
    const fileFormData = new FormData();

    fileFormData.append("file", inputFile.files[0]);

    fetch(fileEndpoint, {
        method: "post",
        body: fileFormData,
        // mode: 'no-cors'
    }).catch(console.error);
});
