

/**
 * 
 * @memberof form
  * 
  * @param fileHandler  @type function @description function to read and parse delivered file 
  * 
  * @example <ImportFileButton fileHandler ={(file)=>console.log(file)}/>
 */

function ImportFileButton({fileHandler}) {
    return (
        <>
        <p></p>
        <input
            type="file"
            id="file_selector"
            name="students"
            onChange={(v) => {
                fileHandler(v.target.files)}}
        ></input>
        <p></p>
        </>
    );
}

export default ImportFileButton;