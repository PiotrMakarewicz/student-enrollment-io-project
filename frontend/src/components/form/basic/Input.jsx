/**
 * Input component without state.
 * 
 * @memberOf Form.Basic
 * @param   {string} label  Value of label above input.
 * @param   {string} placeholder   Value of input placeholder
 * @param   {string} value   Input value, ( should be pass by parent component)
 * @param   {function} onChange   Callback which call on input change. Callback should get new value as parameter.
 * @param   {string} type   Type of input.
 * @param   {string} id   Is not required, but its should be passed to connect label with input.
 * 
 *  @example
 * 
 *  <Input 
        label="Enter your name" 
        value={state.input} 
        onChange={(v) => setState({...state,input:v})}
        id="example from input"
    />
 */

function Input({ label, placeholder, value, onChange, type, id, error}) {
    const onChangeWrapper = (e) => {
        if (onChange) onChange(e.target.value);
    };

    return (
        <div className="form-group">
            {label && <label htmlFor={id}>{label}</label>}
            <input
                type={type}
                value={value}
                className="form-control"
                id={id}
                placeholder={placeholder}
                onChange={onChangeWrapper}
            />
            <span className="error input-error">{error}</span>
        </div>
    );
}

export default Input;
