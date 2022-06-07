import { toast } from "react-toastify";
class Student {
    constructor(firstName, lastName, phoneNumber, fieldOfStudy, faculty, indexNumber, emailAdress) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.FieldOfStudy = fieldOfStudy;
        this.Faculty = faculty;
        this.indexNumber = indexNumber;
        this.emailAddress = emailAdress;
    }
}
function createStudent(firstName, lastName, phoneNumber, fieldOfStudy, faculty, indexNumber, emailAdress){
    const nameReg = /^\D+$/
    const phoneReg = /^[0-9]{9}$/
    const indexReg = /^[0-9]{6}$/
    const emailReg = /^.+@[\\.a-z]+$/

    if (!nameReg.test(firstName)){
        toast.error("Invalid data in file","Name should contains only characters",firstName);
    }
    else if (!nameReg.test(lastName)){
        toast.error("Invalid data in file","Name should contains only characters",lastName);
        return null;
    }
    else if(!phoneReg.test(phoneNumber)){
        toast.error("Invalid data in file","Wrong phonde number",phoneNumber);
    }
    else if(!indexReg.test(indexNumber)){
        toast.error("Invalid data in file","Index number should have 6 digits",indexNumber);
    }
    else if(!emailReg.test(emailAdress.toLowerCase())){
        toast.error("Invalid data in file","Wrong email address",emailAdress);
    }
    else return new Student(firstName, lastName, phoneNumber, fieldOfStudy, faculty, indexNumber, emailAdress);
    
    return null;
}   

/**
 * function to convert array to Student object
 *
 * @param row @type array @description array with student informations
 * @returns Student object
 */
function parseStudentRow(row) {
    return createStudent(row[0], row[1], row[2], row[3], row[4], row[5], row[6]);
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
    if (studentsList.includes(null)){
        return null;
    }
    return studentsList;
}
