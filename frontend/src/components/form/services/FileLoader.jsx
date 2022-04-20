
class Student{
    constructor(idx,mail){
        this.index=idx;
        this.mail=mail;
    }
}

function FileLoader() {
    // const filesystem = require('fs');
    return (<input type ="file" id = "file_selector" name = "students" onChange={(v)=>{
        console.log(v.target.files[0]);
        const reader = new FileReader();
        reader.addEventListener('load', (event) => {
          var result = event.target.result;
           result = result.toString().split('\r\n');
        var students = result.map((s,key)=>{
            var s_a=s.split(',');
            return new Student(s_a[0],s_a[1]);
        })
        console.log(students);
        console.log(JSON.stringify(students));
        //   var csv = fs.readFileSync(result)
        //   console.log(csv);
        });
        // reader.readAsBinaryString(v.target.files[0]);
         reader.readAsText(v.target.files[0]);
        
    }}></input>);
}

export default FileLoader;