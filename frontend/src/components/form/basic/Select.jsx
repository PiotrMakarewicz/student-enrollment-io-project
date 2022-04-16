/**
 * Input component without state.
 * 
 * 
 * @memberOf Form.Basic
 * @param   {string} label  Value of label above input.
 * @param   {array} options  Array of avaible options in format [{value:...,label:...,}, {...}, ].
 * @param   {string} value   Select actual value. On start schould be equal null;
 * @param   {string} placeholder   Defoult opition label.
 * @param   {function} onChange   Callback which call on input change. Callback should get new value as parameter.
 * @param   {string} id   Is not required, but its should be passed to connect label with input.
 * 
 * 
 * 
 * @example 
 * 
 *  <Select 
        label="Select your name" 
        value={state.select} 
        placeholder="whats your name" 
        options={[
            {value:1, label:"Alice"},
            {value:2, label:"Bob"}
        ]} 
        onChange={(v) => setState({...state,select:v})}
        id="example from select"
    />
 * 
 */

function Select({ label, options, value, onChange, placeholder, id }) {
    const onChangeWrapper = (e) => {
        if (onChange) onChange(e.target.value);
    };

    const mappedOptions = options.map((o) => {
        return (
            <option
                key={o.value}
                value={o.value}
            >
                {o.label}
            </option>
        );
    });

    if (!value) value = "DEFOULT";

    return (
        <div className="form-group">
            <label htmlFor={id}>{label}</label>
            <select
                className="form-control"
                id={id}
                onChange={onChangeWrapper}
                value={value}
            >
                {value === "DEFOULT" && (
                    <option
                        disabled
                        value="DEFOULT"
                    >
                        {placeholder}
                    </option>
                )}
                {mappedOptions}
            </select>
        </div>
    );
}

export default Select;
