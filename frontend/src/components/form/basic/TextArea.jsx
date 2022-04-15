/**
 * Input component without state.
 * 
 * 
 * @memberOf Form.Basic
 * @param   {string} label  Value of label above input.
 * @param   {string} placeholder   Value of textarea placeholder
 * @param   {string} value   Textarea value, ( should be pass by parent component)
 * @param   {function} onChange   Callback which call on input change. Callback should get new value as parameter.
 * @param   {string} id   Is not required, but its should be passed to connect label with input.
 * 
 * 
 * 
 * @example 
 * 
 *    <TextArea 
        label="Text area test" 
        placeholder="type something" 
        value={state.textArea} 
        onChange={(v) => setState({...state,textArea:v})}
        id="example from textarea"
    />
 */

function TextArea({ label, value, placeholder, onChange, id }) {
    const onChangeWrapper = (e) => {
        if (onChange) onChange(e.target.value);
    };

    return (
        <div className="form-group">
            {label && <label htmlFor={id}>{label}</label>}
            <textarea
                className="form-control"
                onChange={onChangeWrapper}
                placeholder={placeholder}
                value={value}
                id={id}
                rows="3"
            ></textarea>
        </div>
    );
}

export default TextArea;
