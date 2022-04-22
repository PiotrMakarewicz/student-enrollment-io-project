import Dropzone from "react-dropzone";


function DropZone({ fileHandler }) {
    return (
        <Dropzone
            onDrop={(acceptedFiles) => {
                let container = new DataTransfer(); 
                container.items.add(acceptedFiles[0]);
                document.getElementById("file_selector").files=container.files;
                fileHandler(acceptedFiles);
            }}
        >
            {({ getRootProps, getInputProps }) => (
                <div
                    {...getRootProps()}
                    className="dragDropField"
                >
                    <input {...getInputProps()} />
                    <p>Drag and drop some files here</p>
                </div>
            )}
        </Dropzone>
    );
}

export default DropZone;
