import readXlsxFile from "read-excel-file";
import DropZone from "./DropZone";
// import ImportFileButton from "./ImportFileButton"
import {parseXlsxFile} from "./Parser.js"



function fileHandler(files){
    readXlsxFile(files[0]).then((rows) => {
        let result =parseXlsxFile(rows);
        console.log(result)
        return result
    
    })
}
function FileLoader() {
    return (
        <>
        {/* <ImportFileButton fileHandler={fileHandler}/> */}

        <DropZone fileHandler={fileHandler}/>
        </>
    );
}

export default FileLoader;
