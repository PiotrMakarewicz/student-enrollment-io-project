import Dropzone from "react-dropzone";
import { toast } from "react-toastify";
/**
 * @memberof form
 *
 * @param fileHandler  @type function @description function to read and parse delivered file
 *
 * @example <DropZone fileHandler ={(file)=>console.log(file)}/>
 */

function DropZone({ fileHandler }) {
    return (
        <Dropzone
            onDrop={async (acceptedFiles) => {
                let isFilesCorrect = (await fileHandler(acceptedFiles));
                if(isFilesCorrect){
                    document.getElementById("selectedFileLabel").innerHTML = acceptedFiles[0]["path"];
                    toast.success("Successfuly added file",acceptedFiles[0]["path"]);
                }
                
            }}
        >
            {({ getRootProps, getInputProps }) => (
                <div className="form-group">
                    <div
                        {...getRootProps()}
                        className="file-drop-area"
                    >
                        <span className="choose-file-button">Choose files</span>
                        <span
                            className="file-message"
                            id="selectedFileLabel"
                        >
                            {" "}
                            or drag and drop files here
                        </span>
                        <input
                            {...getInputProps()}
                            className="file-input"
                            type="file"
                        />
                    </div>
                </div>
            )}
        </Dropzone>
    );
}

export default DropZone;
