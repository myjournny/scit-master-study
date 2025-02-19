// JavaScript
window.onload = function () {
    document.getElementById('submitBtn').addEventListener('click', function () {
        let subjectName = document.querySelector('#subjectName');
        let className = document.querySelector('input[name="className"]:checked');
        let studentName = document.querySelector('#studentName');
        let assignmentName = document.querySelector('#assignmentName');
        let assignmentDescription = document.querySelector('#assignmentDescription');
        let file = document.querySelector('#file');

        alert("수업명: " + subjectName.value +
            "\n분반: " + className.value +
            "\n이름: " + studentName.value +
            "\n과제 제목: " + assignmentName.value +
            "\n과제 설명: " + assignmentDescription.value +
            "\n첨부 파일: " + file.value
        );
    });

    subjectName.addEventListener('click', function () {
        subjectName.style.backgroundColor = 'lightblue';
    });
    studentName.addEventListener('click', function () {
        studentName.style.backgroundColor = 'lightgreen';
    });
    assignmentName.addEventListener('click', function () {
        assignmentName.style.backgroundColor = 'lightgreen';
    });
    assignmentDescription.addEventListener('click', function () {
        assignmentDescription.style.backgroundColor = 'lightgreen';
    });
};