import Dropzone from "react-dropzone";
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
            onDrop={(acceptedFiles) => {
                document.getElementById("selectedFileLabel").innerHTML = acceptedFiles[0]["path"];
                fileHandler(acceptedFiles);
            }}
            maxFiles={1}
            accept={".xlsx"}
        >
            {({ getRootProps, getInputProps }) => (
                <div className="form-group">
                    <div
                        {...getRootProps()}
                        className="file-drop-area"
                    >
                        <div className="mb-3">
                            <span className="choose-file-button">Choose files</span>
                            <span
                                className="file-message"
                                id="selectedFileLabel"
                            >
                                or drag and drop files here
                            </span>
                        </div>
                        <div className="file-message">
                            <em>(Only *.csv, *.txt and *.xlsx files will be accepted)</em>
                        </div>
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
