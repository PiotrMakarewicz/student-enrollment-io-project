class Student {
    constructor(idx, mail) {
        this.index = idx;
        this.mail = mail;
    }
}

function FileLoader() {
    return (
        <input
            type="file"
            id="file_selector"
            name="students"
            onChange={(v) => {
                const reader = new FileReader();
                reader.addEventListener("load", (event) => {
                    var result = event.target.result;
                    result = result.toString().split("\r\n");
                    var students = result.map((s) => {
                        var s_a = s.split(",");
                        return new Student(s_a[0], s_a[1]);
                    });
                    console.log(students);
                    console.log(JSON.stringify(students));
                });
                reader.readAsText(v.target.files[0]);
            }}
        ></input>
    );
}

export default FileLoader;
