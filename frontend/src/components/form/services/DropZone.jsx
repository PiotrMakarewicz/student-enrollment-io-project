import Dropzone from "react-dropzone";
/**
 * @memberof form
 *
 * @param fileHandler  @type function @description function to read and parse delivered file
 *
 * @example <DropZone fileHandler ={(file)=>console.log(file)}/>
 */
//                 ".csv, text/csv, application/vnd.ms-excel, application/csv, text/x-csv, application/x-csv, text/comma-separated-values, text/x-comma-separated-values"

function DropZone({ fileHandler }) {
    return (
        <Dropzone
            onDrop={(acceptedFiles) => {
                console.log(acceptedFiles);
                document.getElementById("selectedFileLabel").innerHTML = acceptedFiles[0]["path"];
                fileHandler(acceptedFiles);
            }}
            // accept={[".xslx, .csv"]}
            maxFiles={1}
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
                                {" "}
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
