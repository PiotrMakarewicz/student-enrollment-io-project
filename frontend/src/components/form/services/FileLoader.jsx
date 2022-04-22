import readXlsxFile from "read-excel-file";
import DropZone from "./DropZone";
// class Student {
//     constructor(student_details_array) {
//         this.firstName = student_details_array[0];
//         this.lastName = student_details_array[1];
//         this.phoneNumber = student_details_array[2];
//         this.FieldOfStudy = student_details_array[3];
//         this.Faculty = student_details_array[4];
//         this.indexNumber = student_details_array[5];
//         this.emailAdress = student_details_array[6];
//     }
// }

// function parseXlsxFile(fileData) {
//     fileData.shift();
//     var studentsList = fileData.map((el) => new Student(el));
//     return studentsList;
// }

function parseXlsxFile(fileData){
    var studentsList = []
    for (var i = 1;i<fileData.length;i++){
        var student ={}
        for(var j =0;j<fileData[0].length;j++){
            student[fileData[0][j]]=fileData[i][j];
        }
        studentsList.push(student);
    }
    console.log(studentsList);
    return studentsList;
}
function fileHandler(files){
    readXlsxFile(files[0]).then((rows) => {
        parseXlsxFile(rows);
    });
}
function FileLoader() {
    return (
        <>
        <input
            type="file"
            id="file_selector"
            name="students"
            onChange={(v) => {
                fileHandler(v.target.files)}}
        ></input>
        
        <DropZone fileHandler={fileHandler}/>
        </>
    );
}

export default FileLoader;
