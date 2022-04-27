class Student {
    constructor(firsName, lastName, phoneNumber, FieldOfStudy, Faculty, indexNumber, emailAdress) {
        this.firstName = firsName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.FieldOfStudy = FieldOfStudy;
        this.Faculty = Faculty;
        this.indexNumber = indexNumber;
        this.emailAdress = emailAdress;
    }
}

/**
 * function to convert array to Student object
 *
 * @param row @type array @description array with student informations
 * @returns Student object
 */
function parseStudentRow(row) {
    return new Student(row[0], row[1], row[2], row[3], row[4], row[5], row[6]);
}

/**
 * function to convert fileData to array of Students
 *
 * @param fileData @type array @description array which contains rows frow file
 * @returns array of Student objects
 */
export function parseXlsxFile(fileData) {
    fileData.shift();
    var studentsList = fileData.map((el) => parseStudentRow(el));
    return studentsList;
}
