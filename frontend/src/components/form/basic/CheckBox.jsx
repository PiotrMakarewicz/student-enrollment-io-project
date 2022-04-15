/**
 *
 * Check box without own state.
 * 
 * 
 * @memberOf Form.Basic
 * @param   {string} label  Value of label above input.
 * @param   {string} checked   State of checkbox, ( should be pass by parent component)
 * @param   {function} onChange   Callback which call on input change. Callback should get new value as parameter.
 * @param   {string} id   Is not required, but its should be passed to connect label with checkbox.
 * 
 * 
 * @example
 * 
 *  <CheckBox 
        label="Agree" 
        placeholder="whats your name" 
        value={state.checkBox} 
        onChange={(v) => setState({...state,checkBox:v})}
        id="example from checkbox"
    />
 * 
 * 
 */

function CheckBox({ label, value, onChange, id }) {
    const onChangeWrapper = (e) => {
        if (onChange) onChange(e.target.checked);
    };

    return (
        <div className="form-check">
            <input
                type="checkbox"
                className="form-check-input"
                id={id}
                checked={value}
                onChange={onChangeWrapper}
            />
            <label
                className="form-check-label"
                htmlFor={id}
            >
                {label}
            </label>
        </div>
    );
}

export default CheckBox;
